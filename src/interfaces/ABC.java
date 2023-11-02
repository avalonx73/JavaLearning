package interfaces;

public class ABC implements C, B, A {

    @Override
    public void method() {

    }

    private void foo3() {
        System.out.println("AB");
        class Local {  // A local class
            void foo() {
                System.out.println("Local.foo");
            }
        }

        Local local = new Local();
        local.foo();

        Local local2 = new Local() {
            @Override
            void foo() {
                System.out.println("Anoniomus Local.foo");
            }
        };

        local2.foo();
    }

    @Override
    public void foo() {

    }
}
