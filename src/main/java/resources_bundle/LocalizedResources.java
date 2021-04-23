package resources_bundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LocalizedResources {
    public static void main(String[] args) {
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        var bundle = ResourceBundle.getBundle("messages", locale);
        System.out.println(bundle.getString("hello"));
        System.out.println(MessageFormat.format(bundle.getString("test"), "Ko"));
    }

}
