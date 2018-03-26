package interfaces;


public interface I2 {

    default void methodA() {
        System.out.println("Default interface method A is running");
    }

    default void methodB() {
        System.out.println("Default interface method B is running");
    }

    static void staticMethod() {
        System.out.println("staticMethod() interface one-common is running");
    }

}
