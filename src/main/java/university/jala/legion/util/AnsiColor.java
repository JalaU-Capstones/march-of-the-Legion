package university.jala.legion.util;

/**
 * A utility class for applying ANSI color codes to console output.
 */
public class AnsiColor {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Wraps the given text with ANSI red color codes.
     * @param text The text to colorize.
     * @return The red-colored text.
     */
    public static String red(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }
}
