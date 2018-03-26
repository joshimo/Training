package access_modifiers;


import access_modifiers.package_1.ParentClass;

public class ChildClassOuterPackage extends ParentClass {

    public ChildClassOuterPackage() {
        System.out.println("Child class constructor is running");
    }

    public static void main(String... args) {
        ChildClassOuterPackage childClassOtherPackage = new ChildClassOuterPackage();
    }

}
