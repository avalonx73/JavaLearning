package inheritance;

public class Main {
    public static void main(String[] args) {
        SubClass subClass = new SubClass("AAA");

        ClassWithoutConstructor object = new ClassWithoutConstructor();

        AbstractClass.staticMethod1();
        SubClass.staticMethod1();
        SubClass.staticMethod2();
    }
}
