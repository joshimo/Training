package interfaces;


public class CommonImplementation implements I1, I2 {

    @Override
    public void methodA() {
        System.out.println("Overridden method A is running");
    }

    private void method() {
        this.methodA();
        /* Обращение к дефолтному методу интерфейса I1 */
        I1.super.methodA();
        /* Обращение к дефолтному методу интерфейса I2 */
        I2.super.methodA();
        /* Обращение к статическому методу интерфейса I2 */
        I2.staticMethod();
        /* Обращение к статическому методу интерфейса I1 */
        I1.staticMethod();
        /* Обращение к своему собственному статическому методу */
        staticMethod();
    }

    /* Не является переопределение статического метода интерфейсов I2 и I1,
       т.к. статические методы переопределять нельзя */
    public static void staticMethod() {

    }

    public static void main(String... args) {
        CommonImplementation ci = new CommonImplementation();
        ci.methodA();
        ci.methodB();
        ci.methodC();
    }

}
