package nestedclass;

import interfaces.A;

public class OuterClass {

    public static OuterClass build() {
        return new OuterClass();
    }

    private static void staticMethod1(String param) {
        StaticNestedClass staticNestedClass = new StaticNestedClass();
    }

    private static String STATIC_STRING;

    private String status;

    private int x1;

    private void method1() {
        InnerClass innerClass = new InnerClass();
        StaticNestedClass staticNestedClass = new StaticNestedClass();
    }


    /**
     * Статический вложенный класс
     * <p>
     * Note: A static nested class interacts with the instance members of its outer class (and other classes) just like any other top-level class.
     * <p>
     * In effect, a static nested class is behaviorally a top-level class that has been nested in another top-level class for packaging convenience.
     */

    static class StaticNestedClass {
        // Статический вложенный класс не может обращаться к переменным экземпляров и методам экземпляров внешнего класса
        // InnerClass innerClass = new InnerClass();

        // Статические вложенные классы могут обращаться к static членам класса, в который они вложены, с любым модификатором доступа.
        {
            staticMethod1(STATIC_STRING);
        }
    }

    // Static inner class is used in the builder pattern. Static inner class can instantiate it's outer class which has only private constructor.
    static class Builder {
        public static void build() {
            OuterClass outer = new OuterClass();
        }
    }

    /**
     * Внутренний класс
     * <p>
     * Use a non-static nested class (or inner class) if you require access to an enclosing instance's non-public fields and methods.
     * <p>
     * Use a static nested class if you don't require this access.
     */
    class InnerClass {
        // Можно обявлять константы
        public static final int MY_CONSTANT = 34;
        private int x1;
        // Никаких других статических членов объявлять во внутренних классах нельзя! Будет ошибка компиляции.

        class SecondInnerClass {
            private int x1;

            void method1(int x1) {
                // Параметр метода method1
                System.out.println("x1=" + x1);
                // Член класса SecondInnerClass
                System.out.println("this.x1=" + this.x1);
                // Член класса InnerClass
                System.out.println("InnerClass.this.x1=" + InnerClass.this.x1);
                // Член класса OuterClass
                System.out.println("OuterClass.this.x1=" + OuterClass.this.x1);
            }
        }

        // Внутренние классы имеют доступ ко всем членам внешнего класса независимо от модификатора доступа.
        String state = status;
        StaticNestedClass nestedClass = new StaticNestedClass();

        {
            OuterClass.this.method1();
            staticMethod1(STATIC_STRING);
        }

    }
}
