package interfaces;

public interface B extends A {

    default void foo() {
        foo();
        System.out.println("InterfaceA");
    }
}
