package inheritance;

// If we don’t define a constructor in a class, then the compiler creates a default constructor(with no arguments) for the class
// And if we write a constructor with arguments or no arguments then the compiler does not create a default constructor.
// Default constructor provides the default values to the object like 0, null, etc. depending on the type.
public class SuperClass extends AbstractClass{

    public SuperClass() {
        System.out.println("SuperClass.constructor");
        return;
    }

    public SuperClass(String param) {
        super(param);
        System.out.println("SuperClass.constructor");
    }

    public static void staticMethod1() {
        System.out.println("SuperClass.staticMethod1");
    }

    @Override
    public void abstractMethod() {
        System.out.println("SuperClass.abstractMethod");
    }

    public static void staticMethod2() {
        System.out.println("SuperClass.staticMethod2");
    }
}
