package access_modifiers.package_2;


import access_modifiers.package_1.ParentClass;

public class ChildClassOtherPackage extends ParentClass {

    public ChildClassOtherPackage() {
        System.out.println("Child class constructor is running");
    }

    public static void main(String... args) {
        ChildClassOtherPackage childClassOtherPackage = new ChildClassOtherPackage();
    }

}
