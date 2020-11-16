package com.oraz.service.locale;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LocaleService {

    private static final Map<String, Locale> LOCALES = new HashMap<>();
    private static final String TRANSLATIONS_BUNDLE = "translations";

    static {
        LOCALES.put("eng", new Locale("en", "US"));
        LOCALES.put("rus", new Locale("ru", "RU"));
        LOCALES.put("bel", new Locale("be", "BY"));
    }

    public static String getMessage(String language, String message) {
        Locale locale = LOCALES.getOrDefault(language, Locale.getDefault());
        ResourceBundle bundle = ResourceBundle.getBundle(TRANSLATIONS_BUNDLE, locale);
        return bundle.getString(message);
    }
}
