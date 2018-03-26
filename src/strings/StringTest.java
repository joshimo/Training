package strings;

import java.io.IOException;

import static joshimo.io.utils.WindowReader.*;
import static joshimo.io.utils.WindowPrinter.*;

public class StringTest {

    private Integer i;
    private Float f;
    private Double d;
    private String s;

    public StringTest() throws IOException {
        i = read(Integer.class);
        f = read(Float.class);
        d = read(Double.class);
        s = read();
        print("Integer i = " + i);
        print("Float f = " + i);
        print("Double d = " + d);
        print("String s = \"" + s + "\"");
    }

    public static void main(String... args) throws IOException {
        new StringTest();
    }

}
