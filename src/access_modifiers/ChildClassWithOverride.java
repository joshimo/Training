package access_modifiers;


import access_modifiers.package_1.ParentClass;

public class ChildClassWithOverride extends ParentClass {

    @Override
    protected void protectedParentMethod() {
        System.out.println("Overridden protected Parent Method is running");
    }

    public ChildClassWithOverride() {
        System.out.println("Child class constructor is running");
    }

    public static void main(String... args) {
        ChildClassWithOverride childClassWithOverride = new ChildClassWithOverride();
    }

}
