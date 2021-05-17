package readability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyllablesCounter {
    public static int countWithRegex(String word) {
        // checks for any vowels except 'e' (if the following one is also vowel) or
        // vowel 'e' (if the following one is also vowel), but exclude e when it's
        // either the last char or the char following e is both d and last char of the word
        String i = "(?i)[aiouy][aeiouy]*|e[aeiouy]*(?!d?\\b)";
        Matcher matcher = Pattern.compile(i).matcher(word);
        int counter = 0;

        while (matcher.find()) {
            counter += 1;
        }

        //return at least 1
        return Math.max(counter, 1);

    }
}
