package syntax;

/**
 * Created by Golota on 22.12.2017.
 */
public class TypeCast {

    byte b = 1;
    int i = 1;
    long l = 1L;
    float f = 1.0f;
    double d = 1.0;

    void cast() {
        byte b1 = (byte) d;
        int i1 = b;
        long l1 = (long) (int) (byte) f;
        float f1 = (float) d;
        double d1 = l + i;

    }



}
