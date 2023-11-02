package nestedclass;

import nestedclass.OuterClass.StaticNestedClass;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html">The Java™ Tutorials - Nested Classes</a>
 */
public class Main {
    public static void main(String[] args) {
        OuterClass build = OuterClass.build();
        OuterClass outerObject = new OuterClass();
        OuterClass.InnerClass innerClass = outerObject.new InnerClass();

        StaticNestedClass staticNestedClass = new StaticNestedClass();
    }
}
