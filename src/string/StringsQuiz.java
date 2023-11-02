package string;

import java.io.File;
import java.io.FileFilter;

public class StringsQuiz {
    public static void main(String[] args) {

        // Безобидный комментарий \u000a System.out.println("Bugaga");

        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);

        String format = String.format("rnk={}", "123456");

        String s1 = null;

        CharSequence cs = "AAAAA";

        System.out.println(cs.getClass());

        System.out.print("0".indent(1));

        String str = "     ";
        // true
        System.out.println(str.isBlank());
        // false - value.length == 0
        System.out.println(str.isEmpty());

        // nullnull1
        String ss1 = "null" + null + 1;
        System.out.println(s1);

        // ONE234FIVE
        System.out.println("ONE"+2+3+4+"FIVE");

        // 656667495051979899
        "ABC123abc".chars().forEach(System.out::print);
        System.out.println();

        // 0
        System.out.println("A".compareTo("A"));
        // -1
        System.out.println("A".compareTo("B"));
        // 1
        System.out.println("B".compareTo("A"));

        // Java100020003000
        System.out.println("Java" + 1000 + 2000 + 3000);

        StringBuffer sb = new StringBuffer("11111");
        //111false11
        System.out.println(sb.insert(3,false));
        // 111fa3.3lse11
        System.out.println(sb.insert(5,3.3));
        // 111fa3.One3lse11
        System.out.println(sb.insert(7,"One"));

        // Operator '+' cannot be applied to 'int', 'null'
        // String s2 = 1 + null + "null";

        // 3null56
        System.out.println(1 + 2 + "null" + 5 + 6);

        // 11.0java3.37.7
        System.out.println(7.7 + 3.3 + "java" + 3.3 + 7.7);

        String s3 = "JavaJ2EE";
        // vaJ
        System.out.println(s3.substring(2,5));
        // aJ
        System.out.println(s3.substring(2,5).substring(1));
        // J
        System.out.println(s3.substring(2,5).substring(1).charAt(1));

        // ConceptOfTheDay
        System.out.println("JavaConceptOfTheDay".substring(4));

        // 11111
        System.out.println("1".repeat(5));


    }
}
