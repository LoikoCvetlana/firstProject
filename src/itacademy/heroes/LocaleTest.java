package itacademy.heroes;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleTest {

    @Test
    public void testLocale() {
        Locale locale = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("translations", locale);
        System.out.println(bundle.getString("some.text"));
    }

    @Test
    public void testLocaleDefault() {
        Locale.setDefault(new Locale("ru", "RU"));
        ResourceBundle bundle = ResourceBundle.getBundle("translations");
        System.out.println(Locale.getDefault());
        System.out.println(bundle.getString("some.text"));
    }
}
