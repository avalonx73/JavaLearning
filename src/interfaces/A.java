package interfaces;

public abstract interface A {
    public static final String CONST_VALUE1 = "MORENA";

    public static class NestedA {
        private double x;
        private double y;
        public void someMethod1() {
            System.out.println("someMethod1");
        }
    }

    public abstract void method();

    public static void staticMethod() {
        NestedA nestedA = new NestedA();
        nestedA.someMethod1();
        System.out.println("staticMethod");
    }

   public default void foo() {
        method();
        privateMethod(CONST_VALUE1);
        System.out.println("InterfaceA");
    }

    private void privateMethod(String value) {
        NestedA nestedA = new NestedA();
        nestedA.someMethod1();
        System.out.println("privateMethod: " + value);
    }
}
