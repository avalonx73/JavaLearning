package inheritance;

public abstract class AbstractClass<T> {

    public AbstractClass() {
        System.out.println("AbstractClass.constructor: ");
    }

    public <T> AbstractClass(T param) {
        System.out.println("AbstractClass.constructor: " + String.valueOf(param));
    }

    public static void staticMethod1() {
        System.out.println("AbstractClass.staticMethod1");
    }

    public void method() {
        System.out.println("AbstractClass.staticMethod1()");
    }

    public abstract void abstractMethod();
}
