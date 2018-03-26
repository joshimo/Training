package inheritance;


class ChildClass extends ParentClass {

    static {
        System.out.println("Child class static block");
    }

    {
        System.out.println("Child class dynamic block");
    }

    public void childMethod() {
        System.out.println("Child method is running");
    }

    @Override
    protected void protectedParentMethod() {
        System.out.println("Protected overridden parent method is running");
    }


    ChildClass() {
        System.out.println("Child class constructor is running");
        defaultParentString = "";
        defaultParentMethod();
    }

    public static void main(String... args) {
        ParentClass someClass = new ChildClass();
        someClass.protectedParentMethod();
    }
}
