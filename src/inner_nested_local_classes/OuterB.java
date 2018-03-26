package inner_nested_local_classes;

public class OuterB extends OuterA {

    String outerBstring = "Outer B string";
    static String outerBstaticString = "Outer B static string";


    static class NestedB1 {
        static String staticNB1 = "Nested B1 static string";
        String strNB1 = "Nested B1 string";
        void printOwnString() {
            System.out.println(strNB1);
        }
        static void printOwnStaticString() {
            System.out.println(staticNB1);
        }
        static void printOtherStaticString() {
            System.out.println(NestedB2.staticNB2);
        }
    }

    static class NestedB2 {
        static String staticNB2 = "Nested B1 static string";
        String strNB2 = "Nested B1 string";
        void printOwnString() {
            System.out.println(strNB2);
        }
        static void printOwnStaticString() {
            System.out.println(staticNB2);
        }
    }

    class InnerB1 {
        String strIB1 = "Inner B1 string";
        void printOwnString() {
            System.out.println(strIB1);
        }
        void printOtherString() {
            System.out.println(NestedB1.staticNB1);
        }
    }

    class InnerB2 {
        String strIB2 = "Inner B2 string";
        void printOwnString() {
            System.out.println(strIB2);
        }
        void printOtherString() {
            System.out.println();
            /* Вызов метода внешнего класса из внутреннего класса */
            OuterB.this.crossing();
        }
    }

    public void crossing() {
        NestedB1.printOwnStaticString();
        NestedB2.printOwnStaticString();
    }

}
