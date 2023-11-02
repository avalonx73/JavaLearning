package orderinitialization;

public class SuperClass {
    static {
        System.out.println("Статический блок №1.");
    }

    public static String staticField = setStaticField();

    public SuperClass() {
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

    protected String setNonStaticField() {
        System.out.println("Не статическое поле.");
        return "nonStaticField";
    }

    protected static String setStaticField() {
        System.out.println("Статическое поле.");
        return "staticField";
    }

}
