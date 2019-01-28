package fr.univtln.malos_samil.i311.projet.jpa.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class URLValidator {
    private static final String URL_REGEX = "^https://cdn\\.myanimelist\\.net/images/.*\\.jpg$";

    private static Pattern pattern;

    private Matcher matcher;

    public URLValidator() {
        pattern = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    public boolean validateURL(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
