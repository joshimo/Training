package interfaces;


public interface I1 {

    default void methodA() {
        System.out.println("Default interface method A is running");
    }

    default void methodC() {
        System.out.println("Default interface method C is running");
    }

    static void staticMethod() {
        System.out.println("staticMethod() of interface I1 is running");
    }

}
