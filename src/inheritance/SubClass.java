package inheritance;

import java.lang.reflect.Constructor;

public class SubClass extends SuperClass {

    public SubClass() {
        System.out.println("SubClass.constructor");
    }

    public SubClass(String param) {
        // Constructor Chaining
        this();
        System.out.println("SubClass.constructor: " + param);
    }

    public SubClass(String param1, String param2) {
        // Constructor Chaining
        this(add(param1, param2));
        System.out.println("SubClass.constructor: " + param1);
    }


    private static String add(String str1, String str2) {
        return str1 + str2;
    }

    // Copy Constructor
    public SubClass(SubClass object) {
        System.out.println("SubClass.constructor: ");
    }

    public static void staticMethod1() {
        System.out.println("SubClass.staticMethod");
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int add(Integer a, Integer b) {
        return a + b;
    }

}
