package access_modifiers;


public class ClassWithPrivateConstructor {

    private ClassWithPrivateConstructor() {
        System.out.println("Class with private constructor instantiated");
    }

    public static void main(String args[]) {
        new ClassWithPrivateConstructor();
    }
}
