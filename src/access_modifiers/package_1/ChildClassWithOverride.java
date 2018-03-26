package access_modifiers.package_1;


public class ChildClassWithOverride extends ParentClass {

    @Override
    void defaultParentMethod() {
        super.defaultParentMethod();
    }

    @Override
    protected void protectedParentMethod() {
        super.protectedParentMethod();
    }

    ChildClassWithOverride() {
        System.out.println("Child class constructor is running");
    }

    public static void main(String... args) {
        ChildClassWithOverride childClass = new ChildClassWithOverride();
    }
}
