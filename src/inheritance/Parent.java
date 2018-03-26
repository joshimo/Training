package inheritance;

public class Parent {

    static {
        System.out.println("Parent static");
    }

    {
        System.out.println("Parent init");
    }

    public Parent() {
        System.out.println("Parent Constructor");
    }

    {
        System.out.println("Parent init 2");
    }
}

class Child extends Parent {
    static {
        System.out.println("Child static");
    }

    {
        System.out.println("Child init");
    }

    public Child() {
        System.out.println("Child Constructor");
    }

    public static void main(String... args) {
        new Child();
    }
}