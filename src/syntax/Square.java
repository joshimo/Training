package syntax;

public class Square {

    private int a = 10;
    private int b = 20;

    int perimetre(int a, int b) {
        return 2 * (a + b);
    }

    int square(int a, int b) {
        return a * b;
    }

    int perimetre() {
        return 2 * (a + b);
    }

    int square() {
        return a * b;
    }

    public static void main (String... args) {
        Square square1 = new Square();
        System.out.println("Площадь = " + square1.square(5,6 ));
        System.out.println("Периметр  = " + square1.perimetre(5, 6));
    }

}
