package regexp;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class Patterns {
    public static final Pattern underscore = compile("_");
    public static final Pattern dash = compile("-");
    public static final Pattern colon = compile(":");
    public static final Pattern slash = compile("/");
    public static final Pattern ampersand = compile("&");
    public static final Pattern newLine = compile("\\n");
    public static final Pattern yyyyMMdd = compile("\\d{4}-\\d{2}-\\d{2}");
    public static final Pattern patternEmail = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", CASE_INSENSITIVE);
    /**
     * Соответствует запрещенным к использованию в XML документе символам
     * "[\\u0000\\u0007\\u0008\\u000B\\u000C\\u001A\\u001B\\u007F]"
     */
    public static final Pattern xmlForbidenSymbols = compile("[\\u0000\\u0007\\u0008\\u000B\\u000C\\u001A\\u001B\\u007F]");
    public static final Pattern digits = compile("[0-9]+");
    public static final Pattern notDigits = compile("[^\\d]");
    public static final Pattern whitespaces = compile("\\s+");
    public static final Pattern specialOkpo = compile("[0]{9,10}|[9]{10}");
    public static final Pattern eddrId = compile("\\d{8}-\\d{5}");
    public static final Pattern nineZeros = compile("[0]{9}");
    public static final Pattern tenZeros = compile("[0]{10}");
    public static final Pattern tenDigits = compile("[\\d]{10}");
    public static final Pattern isLatin = compile("\\p{IsLatin}");
    public static final Pattern isLatinPlus = compile("\\p{IsLatin}+");
    public static final Pattern isLatinPlusAdd = compile("[\\p{IsLatin}°Ў1]+");
    public static final Pattern isCyrillic = compile("\\p{IsCyrillic}");
    public static final Pattern isCyrillicPlus = compile("\\p{IsCyrillic}+");
    public static final Pattern containCyrillicSymbols = compile(".*\\p{InCyrillic}.*");
    public static final Pattern patternContainCyrillicSymbols = Patterns.containCyrillicSymbols;
    public static final Pattern patternStartingSymbols = compile("^[-’\\s]+");
    public static final Pattern patternClosingSymbols = compile("[-’\\s]+$");
    public static final Pattern patternRepeatingWhitespaces = compile("[\\s]{2,}+");
    public static final Pattern patternRepeatingQuotation1 = compile("[“]{2,}+");
    public static final Pattern patternRepeatingQuotation2 = compile("[`]{2,}+");
    public static final Pattern patternRepeatingQuotation3 = compile("[']{2,}+");
    public static final Pattern patternRepeatingQuotation4 = compile("[’]{2,}+");
    public static final Pattern patternRepeatingQuotation5 = compile("[‘]{2,}+");
    public static final Pattern patternRepeatingQuotation6 = compile("[”]{2,}+");
    public static final Pattern patternRepeatingQuotation7 = compile("[\"]{2,}+");
    public static final String QUOTES_1 = "“";
    public static final String QUOTES_2 = "`";
    public static final String QUOTES_3 = "'";
    public static final String QUOTES_4 = "’";
    public static final String QUOTES_5 = "‘";
    public static final String QUOTES_6 = "”";
    public static final String QUOTES_7 = "\"";
    public static final String[] QUOTES = {QUOTES_1, QUOTES_2, QUOTES_3, QUOTES_4, QUOTES_5, QUOTES_6, QUOTES_7};
    public static final Pattern patternRepeatingNumberSign = compile("[№]{2,}+");
    public static final Pattern patternRepeatingDots = compile("[.]{2,}+");
    public static final Pattern patternRepeatingComma = compile("[,]{2,}+");
    public static final Pattern patternRepeatingDash = compile("[-]{2,}+");
    public static final Pattern patternIllegalSymbols = compile("[~!@#$^_={}\\[\\]|\\\\:;<>?*]+");
    public static final Pattern patternDoubleValue = compile("[-+]*[0-9]+,*[0-9]*");
    public static final Pattern patternNonLetters = compile("\\P{L}+");
    public static final Pattern patternIllegalSymbolsRp = compile("[~!@#$^_={}\\[\\]|\\\\:;<>?*]+");
    public static final Pattern patternSplit = compile("[ \"“`'’‘”\\\\№,.-]+");
    public static final Pattern patternCyrillic = compile("((?=\\p{IsCyrillic})(?=[^Ў]))+");
    public static final Pattern patternLatin = Patterns.isLatinPlusAdd;
    public static final Pattern patternFiveEqualSymbols = compile("(.)\\1{4,}");
    public static final Pattern PATTERN_NON_DIGITS = compile("\\P{Digit}+");
    public static final Pattern PATTERN_EMAIL =
            compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", CASE_INSENSITIVE);
    public static final Pattern PATTERN_PHONE = compile("\\+380[0-9]{9}");
    public static final Pattern pattern3EqualSymbols = compile("(.)\\1{2,}");
    public static final Pattern patternCyrillicPunctuation = compile("[\\p{IsCyrillic}\\s.,'-]+");
    public static final Pattern patternFourDigits = compile("\\d{4}");
    public static final Pattern patternQuotes = compile("[“`'’‘”\"]+"); // Обычные кавычки добавлены
    public static final Pattern patternIllegalSymbolsWithNumbers
            = compile("[~!@#$^()_={}\\[\\]|\\\\:;<>?+,./*№0123456789]+");
    public static final Pattern patternIllegalSymbolsWithSpaces
            = compile("[~!@#$^()_={}\\[\\]|\\\\:;<>?+,./*№0123456789\\s]+");
    public static final String TEN_ZEROES_OKPO = "0000000000";

    public static final Pattern ROMAN_LITERAL_DASH_SERIES_REGEX_PATTERN = compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}-" +
                    "(АП|АМ|ЕГ|КИ|НО|ТП|ФМ|ЖС|НМ|ОК|ОЛ|ЕД|СГ|ФП|ЖД|КЕ|ГЮ|БП|ИД|ВЛ|КГ|БВ|СР|МИ|ЕЛ|БК|АС))$",
            CASE_INSENSITIVE | UNICODE_CASE);
    public static final Pattern ROMAN_LITERAL_REGEX_PATTERN = compile("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})",
            CASE_INSENSITIVE | UNICODE_CASE);
    public static final Pattern CYRILLIC_SERIES_REGEX_PATTERN = compile("^.*(АП|АМ|ЕГ|КИ|НО|ТП|ФМ|ЖС|НМ|ОК|ОЛ|ЕД|СГ|ФП|ЖД|КЕ|ГЮ|БП|ИД|ВЛ|КГ|БВ|СР|МИ|ЕЛ|БК|АС).*$",
            CASE_INSENSITIVE | UNICODE_CASE);
    public static final Pattern repeatingDigits = compile("^(\\d)\\1*$");
    public static final Pattern nineRepeatingDigits = compile("^\\d{9}$");

    public static final Pattern patternIllegalSymbolsWithoutDot
            = Pattern.compile("[~!@#$%^()_={}\\[\\]|\\\\:;<>?+,/*№0123456789]+");

    public static final Pattern patternIllegalSymbolsWithSpacesWithoutOne
            = Pattern.compile("[~!@#$%^()_={}\\[\\]|\\\\:;<>?+,./*№0123456789\\s]+");
}
