package threadpool;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;


public class CardDuplicateService {

    private Random rand = SecureRandom.getInstanceStrong();
    public static final int SIZE = 2048;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public CardDuplicateService() throws NoSuchAlgorithmException {
    }

    public List<Card> findDeep(Card card) throws Exception {
        final AtomicInteger counterDepth = new AtomicInteger(0);
        final AtomicInteger counterMatching = new AtomicInteger(0);

        final Set<String> useSet = Collections.newSetFromMap(new ConcurrentHashMap<>(SIZE + 1));
        final ConcurrentMap<String, Card> duplicateMap = new ConcurrentHashMap<>(SIZE + 1);
        final ConcurrentMap<String, List<String>> matchTypesMap = new ConcurrentHashMap<>((SIZE * SIZE)/2);

        useSet.add(card.getId());

        boolean isOk = find(card, useSet, duplicateMap, matchTypesMap, counterDepth, counterMatching, true, card.getRnk());

        if (duplicateMap.isEmpty()) {
            return Collections.emptyList();
        }

        duplicateMap.remove(card);

        if (duplicateMap.isEmpty()) {
            return Collections.emptyList();
        }

        if (isOk) {
            System.out.println(counterMatching + " matches per " + counterDepth + " searches, Number of duplicates = " + duplicateMap.size());
        } else {
            System.out.println("Big card: " + card + " " + counterMatching + " depth for find duplicates, size = " + duplicateMap.size());
        }

        executorService.shutdown();

        return new ArrayList<>(duplicateMap.values());
    }

    private boolean find(final Card card,
                         final Set<String> useSet,
                         final ConcurrentMap<String, Card> duplicateMap,
                         final ConcurrentMap<String, List<String>> matchTypesMap,
                         final AtomicInteger counterDepth,
                         final AtomicInteger counterMatching,
                         final boolean isMultithreading,
                         String path) {

        counterDepth.getAndIncrement();

        final List<Card> duplicateList = findDuplicatesInSearchRepository(card, matchTypesMap, counterMatching);

        if (duplicateList.isEmpty()) {
            return true;
        }


        useSet.add(card.getId());

        for (Iterator<Card> iterator = duplicateList.iterator(); iterator.hasNext(); ) {
            Card duplicate = iterator.next();

            if (!useSet.add(duplicate.getId())) {
                iterator.remove();
            }

            if (!duplicateMap.containsKey(duplicate)) {
                duplicateMap.put(duplicate.getId(), duplicate);
                if (duplicateMap.size() > SIZE) {
                    return false;
                }
            }
        }

        final ConcurrentHashMap<Integer, CompletableFuture<Void>> completableFutures;
        final Set<Boolean> result;
        final AtomicBoolean isError;
        int idx = 0;

        final boolean isUseMultithreading = isMultithreading && duplicateList.size() > 1;

        if (isUseMultithreading) {
            completableFutures = new ConcurrentHashMap<>(duplicateList.size());
            result = Collections.newSetFromMap(new ConcurrentHashMap<>(2));
            isError = new AtomicBoolean(false);
        } else {
            completableFutures = null;
            result = null;
            isError = null;
        }

        for (Card duplicate : duplicateList) {
            if (isUseMultithreading) {
                final int finalIdx = idx++;
                completableFutures.put(finalIdx, CompletableFuture
                        .supplyAsync(
                                () -> {
                                    try {
                                        return ImmutablePair.of(finalIdx, find(duplicate, useSet, duplicateMap, matchTypesMap, counterDepth, counterMatching, true, path + "->" + duplicate.getRnk()));
                                    } catch (Exception e) {
                                        throw new RuntimeException(" Error for " + duplicate + " card: " + e);
                                    }
                                },
                                executorService)
                        .thenAccept(pair -> {
                            if (pair.getRight() != null) {
                                if (!pair.getRight()) {
                                    for (Map.Entry<Integer, CompletableFuture<Void>> entry : completableFutures.entrySet()) {
                                        if (entry.getKey().equals(pair.getLeft())) {
                                            continue;
                                        }

                                        if (!entry.getValue().isDone() && !entry.getValue().isCancelled() && !entry.getValue().isCompletedExceptionally()) {
                                            entry.getValue().cancel(true);

                                            System.out.println("Cancel task: " + entry.getValue());
                                        }
                                    }
                                }
                                result.add(pair.getRight());
                            }
                        })
                        .exceptionally(throwable -> {
                            System.out.println("{} FindError: " + throwable.toString() + ": " + duplicate);
                            isError.set(true);
                            return null;
                        }));
            } else {
                boolean searchResult = find(duplicate, useSet, duplicateMap, matchTypesMap, counterDepth, counterMatching, false, path + "->" + duplicate.getRnk());
                if (!searchResult) {
                    return false;
                }
            }
        }

        if (isUseMultithreading) {
            for (CompletableFuture<Void> completableFuture : completableFutures.values()) {
                try {
                    completableFuture.join();
                } catch (CancellationException ignored) {
                    System.out.println("Canceled task: " + completableFuture);
                }
            }

            if (result.isEmpty()) {
                throw new RuntimeException("General duplication error");
            }

            if (isError.get()) {
                throw new RuntimeException("Duplication error");
            }

            System.out.println("Path2 = " + path);
            return !result.contains(FALSE);
        }

        System.out.println("Path = " + path);
        return true;

    }

    private List<Card> findDuplicatesInSearchRepository(Card card, final ConcurrentMap<String, List<String>> matchTypesMap, final AtomicInteger counter) {

        List<Card> duplicates = search(card);

        if (duplicates == null || duplicates.isEmpty()) {
            return Collections.emptyList();
        }

        if (duplicates.size() == 1 && duplicates.get(0).equals(card)) {
            return Collections.emptyList();
        }

        final HashSet<Card> set = new HashSet<>(duplicates.size());
        final ArrayList<Card> filteredDuplicates = new ArrayList<>(duplicates.size());

        for (Iterator<Card> iterator = duplicates.iterator(); iterator.hasNext(); ) {
            Card duplicate = iterator.next();

            try {
                if (duplicate == null) {
                    System.out.println("duplicate is null");
                    continue;
                }

                if (duplicate.equals(card)) {
                   // System.out.println("Skip owner card = " + card.getId());
                    iterator.remove();
                    continue;
                }

                if (!set.add(duplicate)) {
                    System.out.println("Not unique duplicate = " + duplicate);
                    iterator.remove();
                    continue;
                }

                List<String> matchTypes = matchTypesMap.computeIfAbsent(keyOfCoupleOfStrings(card.getId(), duplicate.getId()), k -> matchingCards(card, duplicate, counter));

                String duplicateCardId = duplicate.getId();

                if (matchTypes.isEmpty()) {
                    System.out.println("Not found match type for search = " + duplicate);
                    iterator.remove();
                    continue;
                } else {
                  //  System.out.println("Found match types " +  matchTypes.stream().collect(Collectors.joining(",")) + " for card id " + card.getId() + " comparing with card duplicate id " + duplicateCardId);
                }

                filteredDuplicates.add(duplicate);
            } catch (Exception e) {
                System.out.println("IteratingDuplicates: " + e.toString() + ": " + duplicate);
            }
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicates to return");
            return Collections.emptyList();
        }
        return duplicates;
    }

/*    private List<Card> search(Card card) {
        return IntStream.range(1, 1801).boxed()
                .map(Object::toString)
                .filter(s -> !s.equals(card.getId()))
                .map(t -> new Card(t, t))
                .collect(Collectors.toList());
    }*/

    private List<Card> search(Card card) {
        int size = 1000;

        List<Card> cards = rand.ints(1, 2048)
                .distinct()
                .limit(size)
                .boxed()
                .map(Object::toString)
                .filter(s -> !s.equals(card.getId()))
                .map(t -> new Card(t, t))
                .collect(Collectors.toList());

        cards.add(card);
        return cards;
    }

    private List<String> matchingCards(Card card, Card duplicate, final AtomicInteger counterMatching) {
        counterMatching.getAndIncrement();
        List<String> returnMatchTypes = new ArrayList<>();
        returnMatchTypes.add("TYPE_1");
        return returnMatchTypes;
    }

    private String keyOfCoupleOfStrings(String s1, String s2) {
        return ((s1.compareTo(s2) < 0) ? s1 : s2) + "_" + ((s1.compareTo(s2) > 0) ? s1 : s2);
    }

    private int randomInt(int min, int max) {
        return rand.nextInt(max - min - 1) + min;
    }
}
