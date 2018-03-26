package inner_nested_local_classes;

public class OuterA {

    private String privOuterStr = "privOuterStr";
    protected String protectOuterStr = "protectOuterStr";
    public String pubOuterStr = "pubOuterStr";
    static String staticOuterString = "staticOuterString";

    public OuterA() {
        System.out.println("OuterA constructor");
    }

    public class InnerA {

        public InnerA() {
            System.out.println("InnerA constructor");
        }

        void innerAmet1() {
            System.out.println("InnerAmet1");
        }
    }

    public static class NestedA {

        public NestedA() {
            System.out.println("NestedA constructor");
        }

        void nestedAmet1() {
            System.out.println("NestedAmet1");
        }
    }

    public void methodA() {
        String str1 = "Local string og methodA";
        /* Локальные классы доступны только в пределах методов, в которых они объявлены */
        class LocalA {

            public LocalA() {
                System.out.println("LocalA constructor");
            }

            void localAmet1() {
                /* Локальные классы имеют доступ к локальным переменным своего метода, но только для чтения*/
                System.out.println("LocalAmet1 " + str1);
                /* Локальные классы имеют доступ к переменным внешнего класса и могут изменять их */
                privOuterStr = "new privOuterStr";
                System.out.println("LocalAmet1 " + str1 + privOuterStr + protectOuterStr + pubOuterStr + staticOuterString);
            }
        }

        /* Локальный aнонимный класс, наследующийся от LocalA */
        LocalA anonymeLocalA= new LocalA() {
            @Override
            void localAmet1() {
                System.out.println("Анонимный локальный класс");
            }
        };

        LocalA localA = new LocalA();
    }
}
