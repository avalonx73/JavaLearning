package orderinitialization;

public class SubClass extends SuperClass {
    static {
        System.out.println("Статический блок №1.");
    }

    public static String staticField = setStaticField();

    public SubClass() {
        System.out.println("Конструктор.");
    }

    static {
        System.out.println("Статический блок №2.");
    }

    {
        System.out.println("Блок инициализации №1.");
    }

    public String nonStaticField = setNonStaticField();

    {
        System.out.println("Блок инициализации №2.");
    }

}
