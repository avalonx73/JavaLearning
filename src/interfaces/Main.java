package interfaces;

public class Main {
    public static void main(String[] args) {
        A interfaceA = new ABC();
        interfaceA.foo();

        A.staticMethod();
        A.NestedA nestedA = new A.NestedA();
        nestedA.someMethod1();

        B interfaceB = new ABC();
        interfaceB.foo();
    }
}
