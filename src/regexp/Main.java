package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

public class Main {
    public static final Pattern repeatingDigits = compile("^(\\d)\\1*$");
    public static final String MUST_NOT_CONTAIN_REPEATING_SYMBOLS = "[Значенния должны быть из символов которые не повторяются]";

    public static final Pattern patternEmail = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", CASE_INSENSITIVE);
    public static final Pattern PATTERN_NON_DIGITS = Pattern.compile("\\P{Digit}+");

    public static void main(String[] args) {
        if (repeatingDigits.matcher("222222").matches()) {
            System.out.println(MUST_NOT_CONTAIN_REPEATING_SYMBOLS);
        }

        if (!patternEmail.matcher("ra.interface@@gmail.com").matches()) {
            System.out.println("email.incorrect");
        }

        Matcher matcher = PATTERN_NON_DIGITS.matcher("C1_23A45678B");
        String s = matcher.replaceAll("");
        System.out.println(s);
    }
}

