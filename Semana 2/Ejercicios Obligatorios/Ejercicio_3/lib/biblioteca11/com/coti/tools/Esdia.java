package com.coti.tools;

/*
 * Grado en Ingeniería Informática - Universidad de Salamanca
 * Asignatura : Programación III
 * Copyright (C) J.R. García-Bermejo Giner
 *
 *
 Para asegurarse de que el compilador utiliza UTF-8, es preciso
 indicarlo en la línea de órdenes:

 javac -J-Dfile.encoding=UTF-8 esdia/Esdia.java

 */
import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.in;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Class <b>Esdia</b> offers methods that make it easy to read numeric and
 * alphanumeric<br> values from the keyboard while showing a prompt; errors are
 * signalled<br> and indeed it is possible to read only non-empty strings.
 *
 * @author coti
 *
 * Last updated Dec 16th 2020. Do consider that NumberFormatter ignores the dot
 * when a "," locale is in use. Reviewed again Sept 8 2021
 * 
 * Last updated Sep 26 2021
 *
 */
public class Esdia {
// The following constants can be printed on the terminal in order to
    // change the color of the text's foreground and background.

    /**
     * This constant, when printed, resets the terminal.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * This constant, when printed, sets the black printing color
     */
    public static final String ANSI_BLACK = "\u001B[30m";

    /**
     * This constant, when printed, sets the red printing color
     */
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * This constant, when printed, sets the green printing color
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * This constant, when printed, sets the yellos printing color
     */
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * This constant, when printed, sets the blue printing color
     */
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * This constant, when printed, sets the purple printing color
     */
    public static final String ANSI_PURPLE = "\u001B[35m";

    /**
     * This constant, when printed, sets the cyan printing color
     */
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     * This constant, when printed, sets the white printing color
     */
    public static final String ANSI_WHITE = "\u001B[37m";

// Para modificar el color del fondo
    /**
     * Set black bacground
     */
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    /**
     * Set red background
     */
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    /**
     * Set green background
     */
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    /**
     * Set yellow background
     */
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    /**
     * Set blue background
     */
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    /**
     * Set purple background
     */
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";

    /**
     *
     */
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    /**
     * Set white background
     */
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /**
     * This class offers a number of static methods that simplify the
     * construction of text-based programs.
     */
    final public static Scanner SC = new Scanner(in,
            System.getProperty("os.name").contains("Windows") ? "iso-8859-1" : "UTF-8");

    /**
     * This method gives back a float after prompting
     *
     * @param prompt - this String is shown when asking for into
     * @return - the floating point number required by the user
     */
    public static float readFloat(String prompt) {
        float tmpNumber = 0.0f;
        String tmp;
        boolean numberOkay;
        do {
            tmp = readString_ne(prompt);
            try {
                tmpNumber = Float.parseFloat(tmp);
                numberOkay = true;
            } catch (Exception e) {
                out.printf("%nSorry, that is not a valid number. Please try again.%n");
                out.println(e.toString());
                numberOkay = false;
            }
        } while (!numberOkay);
        return tmpNumber;
    }

    public static String underline(String prompt) {
        var tmpArray = new char[prompt.length()];
        Arrays.fill(tmpArray, '=');
        var underThePrompt = new String(tmpArray);
        return String.format("%s%n%s%n",prompt, underThePrompt);
    }
    public static void underline2(String prompt) {
        var tmpArray = new char[prompt.length()];
        Arrays.fill(tmpArray, '=');
        var underThePrompt = new String(tmpArray);
        System.out.printf(String.format("%n%s%n%s%n",prompt, underThePrompt));
    }
    /**
     * This method gives back a float between min and max after prompting with
     * prompt
     *
     * @param prompt - This string is shown when asking for info
     * @param min - The minimum value admitted for this number
     * @param max - The maximum value admitted for this number
     * @return - a float number between min and max
     */
    public static float readFloat(String prompt, float min, float max) {
        float tmpNumber = 0.0f;
        String tmp;
        boolean numberOkay;
        do {
            tmp = readString_ne(prompt + String.format(" (%f <= numero <= %f)", min, max));

            try {
                tmpNumber = Float.parseFloat(tmp);
                numberOkay = true;
            } catch (Exception e) {
                out.printf("%nSorry, that is not a valid number. Please try again.%n");
                numberOkay = false;
            }
            if (tmpNumber < min || tmpNumber > max) {
                out.printf("%n%nPlease write a number between  %f and %f%n%n", min, max);
                numberOkay = false;
            }
        } while (!numberOkay);
        return tmpNumber;
    }

    /**
     * This method gives back a double after prompting with prompt
     *
     * @param prompt - This string is shown when asking for info
     * @return - a double number
     */
    public static double readDouble(String prompt) {
        double tmpNumber = 0.0;
        String tmp;
        boolean numberOkay;
        do {
            tmp = readString_ne(prompt);
            try {
                tmpNumber = Double.parseDouble(tmp);
                numberOkay = true;
            } catch (Exception e) {
                out.printf("%nSorry, that is not a valid number. Please try again.%n");
                numberOkay = false;
            }
        } while (!numberOkay);
        return tmpNumber;
    }

    /**
     * This method gives back a double between min and max after prompting with
     * prompt
     *
     * @param prompt - This string is shown when asking for info
     * @param min - The minimum value admitted for this number
     * @param max - The maximum value admitted for this number
     * @return the double value entered by the user, after validation
     */
    public static double readDouble(String prompt, double min, double max) {
        double tmpNumber = 0;
        String tmp;
        boolean numberOkay;
        do {
            tmp = readString_ne(prompt + String.format(" (%f <= numero <= %f)", min, max));
            try {
                tmpNumber = Double.parseDouble(tmp);
                numberOkay = true;
            } catch (Exception e) {
                out.printf("%nSorry, that is not a valid number. Please try again.%n");
                numberOkay = false;
            }
            if (tmpNumber < min || tmpNumber > max) {
                out.printf("%n%nPlease write a number between  %f and %f%n%n", min, max);
                numberOkay = false;
            }
        } while (!numberOkay);
        return tmpNumber;
    }

    /**
     * This method gives back an int
     *
     * @param prompt - This string is shown when asking for info
     * @return - An int number
     */
    public static int readInt(String prompt) {
        int tmpNumber = 0;
        String tmp;
        boolean numberOkay;
        do {
            tmp = readString_ne(prompt);
            try {
                tmpNumber = Integer.parseInt(tmp);
                numberOkay = true;
            } catch (NumberFormatException e) {
                out.printf("%nSorry, that is not a valid number. Please try again.%n");
                numberOkay = false;
            }
        } while (!numberOkay);
        return tmpNumber;
    } // End of readInt()

    /**
     * This method give back an int between min and max after prompting with
     * prompt
     *
     * @param prompt - This string is shown when asking for into
     * @param min - The minimum value admitted for this number
     * @param max - The maximum value admitted for this number
     * @return - An int number between min and max
     */
    public static int readInt(String prompt, int min, int max) {
        int tmpNumber = 0;
        String tmp;
        boolean numberOkay;
        do {
            tmp = readString_ne(prompt + String.format(" (%d <= numero <= %d)", min, max));
            try {
                tmpNumber = Integer.parseInt(tmp);
                numberOkay = true;
            } catch (NumberFormatException e) {
                out.printf("%nSorry, that is not a valid number. Please try again.%n");
                numberOkay = false;
            }
            if (tmpNumber < min || tmpNumber > max) {
                out.printf("%n%nPlease write a number between  %d and %d%n%n", min, max);
                numberOkay = false;
            }
        } while (!numberOkay);
        return tmpNumber;
    }

    /**
     * This methos gives back a string after prompting with prompt
     *
     * @param prompt - This string is shown when asking for into
     * @return - A String (which could be empty)
     */
    public static String readString(String prompt) {
        String tmp;
        out.printf(prompt);
        tmp = SC.nextLine();
        return tmp;
    }

    /**
     * This method gives back a non-empty string after prompting with prompt
     *
     * @param prompt - This string is shown when asking for info
     * @return - A String, which cannot be empty
     */
    public static String readString_ne(String prompt) {
        String tmp = null;
        do {
            tmp = readString(prompt);
            if (tmp.isEmpty()) {
                out.printf("%nSorry, no empty strings allowed.%n");
            }
        } while (tmp.isEmpty());
        return tmp;

    }

    /**
     * This method give back either op1 or op2 after prompting with prompt
     *
     * @param prompt - This string is shown when asking for into
     * @param op1 - One of the two admitted value as an answer
     * @param op2 - The other value admitted as an answer
     * @return - Either op1 or op2
     */
    public static String readString(String prompt, String op1, String op2) {
        String tmp = null;
        boolean isOK;
        do {
            tmp = readString_ne(prompt + String.format(" (%s,%s) ? ", op1, op2));

            isOK = tmp.equals(op1) || tmp.equals(op2);
            if (isOK) {
                return tmp;
            } else {
                out.printf("%nThat option is not valid. Please type either %s or %s%n%n", op1, op2);
            }
        } while (!isOK);
        return tmp;
    }

    /**
     * This method gives back on of the options passed in the second argument
     * after prompting with the first
     *
     * @param prompt - This string is shown when asking for into
     * @param options - A String[] that contains the list of possible answers
     * @return - The String entered as answer (which is one of the options)
     */
    public static String readString(String prompt, String[] options) {
        String tmp = null;
        String listOfOptions = "( " + options[0];
        for (int i = 1; i < options.length; i++) {
            listOfOptions += ", " + options[i];
        }
        listOfOptions += " )";
        boolean badOption;
        do {
            badOption = false;
            tmp = readString_ne(prompt + listOfOptions + " ?");
            if (!List.of(options).contains(tmp)) {
                badOption = true;
                out.printf("%n%n%s is not a valid options. Please type one of the valid options.%n%n", tmp);
            } else {
            }
        } while (badOption);
        return tmp;
    }

    /**
     * This method prompts for a boolean, allowing only "y" or "n" as input
     *
     * @param prompt The prompt to be shown; admits only y
     * @return - true if y, false if n
     */
    public static boolean yesOrNo(String prompt) {
        var b = readString(prompt,
                "y",
                "n");
        return b.equals("y");

    }

    /**
     * This method prompts for a boolean, allowing only "y" or "n" as input
     *
     * @param prompt The prompt to be shown; admits only y
     * @return - true if y, false if n
     */
    public static boolean siOno(String prompt) {
        var b = readString(prompt+"[SsNn] ");
        return b.equalsIgnoreCase("s");

    }

    /**
     * This method finds out whether a path is valid
     *
     * @param possiblePath String checked for validity as a Path
     * @return - true if path is valid
     */
    public static boolean isValidPath(String possiblePath) {

        try {
            Paths.get(possiblePath);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

    /**
     * This method prompts for a path and gives it back only if valid
     *
     * @param prompt shown before waiting for a path
     * @return the Path to the given object, after entering a valid String
     */
    public static Path readPathFromKeyboard(String prompt) {
        Path p;
        String possiblePath;
        do {
            possiblePath = readString_ne(prompt);

            p = Paths.get(possiblePath);

        } while (!isValidPath(possiblePath));
        return p;
    }

}
