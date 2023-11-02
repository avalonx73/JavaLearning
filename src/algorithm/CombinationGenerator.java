package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static algorithm.MatchType.TYPE_1;
import static algorithm.MatchType.TYPE_2;
import static algorithm.MatchType.TYPE_4;
import static algorithm.MatchType.TYPE_5;

public class CombinationGenerator {
    public static void main(String[] args) {

        MatchType[] values1 = MatchType.values();
        Map<Integer, List<MatchType>> combinations = new HashMap<>();

        Integer code = 0;

        int numCombinations = (int) Math.pow(2, values1.length);

        for(int i = 1; i<numCombinations; i++) {
            int mask = 1;
            List<MatchType> result = new ArrayList<>();
            for (int shift = 0; shift < values1.length - 1;  shift++) {
                int r = i & mask;
                mask <<= 1;
                if (r > 0) {
                    result.add(values1[shift]);
                }
            }
            combinations.put(i, result);
        }



        for (MatchType type : values1) {

        }



        int ordinal1 = TYPE_1.ordinal();
        code |= (1 << ordinal1 - 1);
        System.out.println(Integer.toBinaryString(code));
        int ordinal2 = TYPE_2.ordinal();
        code |= (1 << ordinal2 - 1);
        System.out.println(Integer.toBinaryString(code));
/*        int ordinal3 = TYPE_3.ordinal();
        code |= (1 << ordinal3-1);
        System.out.println(Integer.toBinaryString(code));*/
        int ordinal4 = TYPE_4.ordinal();
        code |= (1 << ordinal4 - 1);
        System.out.println(Integer.toBinaryString(code));
        int ordinal5 = TYPE_5.ordinal();
        code |= (1 << ordinal5 - 1);
        System.out.println(Integer.toBinaryString(code));

        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

        List<Integer> values = List.of(1, 2, 3, 4, 5);
        List<List<Integer>> lists = generateCombinations(values);
        System.out.println();
    }

    public static void generateCombinations(int[] values, int[] combination, int index) {
        Integer size = 1;
        size <<= (values.length - 1);
        String s = Integer.toBinaryString(size);
        System.out.println();
    }

    private static List<List<Integer>> generateCombinations(List<Integer> values) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            List<Integer> combination = new ArrayList<>();
            combination.add(values.get(i));
            result.add(combination);
            List<Integer> subValues = new ArrayList<>(values.subList(i + 1, values.size()));
            List<List<Integer>> subCombinations = generateCombinations(subValues);
            for (List<Integer> subCombination : subCombinations) {
                List<Integer> fullCombination = new ArrayList<>();
                fullCombination.add(values.get(i));
                fullCombination.addAll(subCombination);
                result.add(fullCombination);
            }
        }
        return result;
    }
}
