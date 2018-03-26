package inheritance;


public class ParentClass {

    private String privateParentString = "private";
    String defaultParentString = "default";
    protected String protectedParentString = "protected";

    static {
        System.out.println("Parent class static block");
    }

    {
        System.out.println("Parent class dynamic block");
    }


    private void privateParentMethod() {
        System.out.println("Private parent method is running");
    }

    void defaultParentMethod() {
        System.out.println("Default parent method is running");
    }

    protected void protectedParentMethod() {
        System.out.println("Protected parent method is running");
    }

    public static void parentStaticMethod() {
        System.out.println("Parent static method is running");
    }

    public ParentClass() {
        System.out.println("Parent class constructor is running");
    }
}
