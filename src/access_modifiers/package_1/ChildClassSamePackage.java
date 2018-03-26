package access_modifiers.package_1;


class ChildClassSamePackage extends ParentClass {

    ChildClassSamePackage() {
        System.out.println("Child class constructor is running");
    }

    public static void main(String... args) {
        ChildClassSamePackage childClass = new ChildClassSamePackage();
    }
}
