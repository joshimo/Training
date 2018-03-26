package strings;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import static joshimo.io.utils.WindowPrinter.*;

public class ResourceBundleTest {

    private Locale localeEn = new Locale("en", "EN");
    private Locale localeUa = new Locale("ua", "UA");
    private ResourceBundle rb;

    ResourceBundleTest() throws UnsupportedEncodingException {
        rb = ResourceBundle.getBundle("text");
        print(filterEncoding(rb.getString("key_1"), rb.getLocale()));
        rb = ResourceBundle.getBundle("text", localeUa);
        print(filterEncoding(rb.getString("key_1"), rb.getLocale()));
        rb = ResourceBundle.getBundle("text", localeEn);
        print(filterEncoding(rb.getString("key_1"), rb.getLocale()));
    }

    String filterEncoding(String string, Locale locale) throws UnsupportedEncodingException {
        if (locale.getDisplayLanguage() == Locale.ENGLISH.getLanguage())
            return string;
        return new String(string.getBytes("iso-8859-1"), "windows-1251");
    }

    public static void main(String... args) throws UnsupportedEncodingException {
        new ResourceBundleTest();
    }
}