package syntax;

/**
 * Created by Golota on 28.12.2017.
 */
public class Cycles {

    static int a;
    static int b;
    static int c;
    static int d;
    static int e;
    static int f;
    static int count;

    public static void main(String args[]) {

        for (int i = 1; i <=100; i ++) {
            a ++;
            if (i > 30) continue;
            b ++;
        }

        while (true) {
            c ++;
            d ++;
            if (c == 100) break;
        }

        count = 0;
        do {
            count ++;
            e ++;
            if (f == 30) continue;
            f ++;
        } while (count < 0);

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("c = " + c + ", d = " + d);
        System.out.println("e = " + e + ", f = " + f);
    }


}
