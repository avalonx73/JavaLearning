package arrays;

import java.util.*;

public class Main {
    public static final Comparator<? super Integer> ORDER_COMPARATOR = Comparator.comparing((Integer order) -> !order.equals(4))
            .thenComparing(Comparator.naturalOrder());

    public static void main(String[] args) {

        List<ProcessStep> processSteps = new ArrayList<>();
        processSteps.add(new ProcessStep(2));
        processSteps.add(new ProcessStep(1));
        processSteps.add(new ProcessStep(0));
        processSteps.add(new ProcessStep(3));
        processSteps.add(new ProcessStep(4));
        processSteps.add(new ProcessStep(5));
        processSteps.add(new ProcessStep(6));

        Optional<ProcessStep> first = processSteps.stream()
                .sorted(Comparator.comparing(ProcessStep::getOrder, ORDER_COMPARATOR))
                .filter(z -> Objects.nonNull(z.getOrder()))
                .findFirst();

        if (first.isPresent()) {
            System.out.println(first.get().getOrder());
        }
    }
}
