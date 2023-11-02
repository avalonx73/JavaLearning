package threadcardservice;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class MainThreadCardService {
    public static void main(String[] args) throws InterruptedException {
        int size = 10;
        String batchId = "9563_I_";

        try {
            ThreadCardService threadCardService = new ThreadCardService();

            final AtomicReferenceArray<String> responseXmls = new AtomicReferenceArray<>(new String[size]);

            System.out.println("------ BEGIN ------");

            threadCardService.begin(
                    (personXml, batchId1, position) -> responseXmls.set(position, handleCard(personXml, batchId1))
            );

            Thread.sleep(1000);

            System.out.println("------ PUBLISH ------");
            for (int i = 0; i < size; i++) {
                PersonXml person = new PersonXml();
                person.setRnk(String.valueOf(i));
                Set<String> keys = new HashSet<>();
                threadCardService.publish(/*batchId + */String.valueOf(i), person, i, keys);
            }

          //  System.out.println("------ AWAIT ------");

            threadCardService.await();

            System.out.println("------ END ------");
        } finally {
            System.out.println("finally");
        }
    }

    private static String handleCard(PersonXml personXml, String batchId) throws InterruptedException {
        //    System.out.println("start OK_" + batchId);
        for (int i = 0; i < 40; i++) {
            System.out.print(batchId);
            Thread.sleep(Integer.valueOf(batchId) * 3000);
        }


        // System.out.println("end   OK_" + batchId);
        return "OK" + batchId;
    }
}
