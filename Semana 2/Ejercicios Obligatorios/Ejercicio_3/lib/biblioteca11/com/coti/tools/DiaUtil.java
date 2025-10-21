package com.coti.tools;

/*
 * Grado en Ingeniería Informática - Universidad de Salamanca
 * Asignatura : Programación III
 * Copyright (C) J.R. García-Bermejo Giner
 *
 * Now under Git control in Github/bruegel/com (May 2017)
 *
 * Test rig is ln-based
 *
 * Para asegurarse de que el compilador utiliza UTF-8, es preciso
 * indicarlo en la línea de órdenes:
  * javac -J-Dfile.encoding=UTF-8 esdia/Esdia.java
  ** Revised September 2021
 */
import java.io.IOException;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class <b>Diautil</b> offers methods that make it easy to calculate<br>
 * execution time by means of a timer;<br>
 * further it offers a method that shows the name of the user<br>
 * and can be called right before program termination in order<br>
 * to indicate the user's name and the program's running time.
 *
 *
 * @author bruegel
 *
 * Last updated Dec 16th 2020
 *
 */
public class DiaUtil {

    static long startingTimeMilliseconds;
    static long stoppingTimeMilliseconds;
    static long startingTimeNanoSeconds;
    static long stoppingTimeNanoSeconds;

    /**
     * This method stores its time of invocation in variable starting time (ms)
     */
    public static void startTimerMS() {
        startingTimeMilliseconds = System.currentTimeMillis();
    }

    /**
     * This method stores its time of invocation in variable starting time ns)
     */
    public static void startTimerNS() {
        startingTimeNanoSeconds = System.nanoTime();
    }

    /**
     * This method stores its time of invocation in variable stopping time (ms)
     * and then prints the elapsed time since the last call to startTimerMS()
     */
    public static void stopTimerAndPrintElapsedTimeMillis() {
        stoppingTimeMilliseconds = System.currentTimeMillis();
        String theCoverForThisPrint = "+---------------------------------+";
        out.printf("%s%n", theCoverForThisPrint);
        out.printf("| Elapsed time (ms): %-12s |%n", (stoppingTimeMilliseconds - startingTimeMilliseconds) + "");
        out.printf("%s%n", theCoverForThisPrint);

    }

    /**
     * This method stores its time of invocation in variable stopping time (ns)
     * and then prints the elapsed time since the last call to startTimerNS()
     */
    public static void stopTimerAndPrintElapsedTimeNanos() {
        stoppingTimeNanoSeconds = System.nanoTime();
        String theCoverForThisPrint = "+---------------------------------+";
        out.printf("%s%n", theCoverForThisPrint);
        out.printf("| Elapsed time (ns): %-12s |%n", (stoppingTimeNanoSeconds - startingTimeNanoSeconds) + "");
        out.printf("%s%n", theCoverForThisPrint);

    }

    /**
     * This method shows the time of invocation and the user name; it is
     * normally called right before the program will finish.
     */
    public static void showFinalTime() {
        String nameOfUserAsKnownToJava = System.getProperty("user.name");
        String nameCutToSize = nameOfUserAsKnownToJava.length() <= 10 ? nameOfUserAsKnownToJava : nameOfUserAsKnownToJava.substring(0, 10);
        String time_at_exit = new SimpleDateFormat("HH.mm.ss").format(new Date());
        String theCoverForThisPrint = "+---------------------------------+";
        out.printf("%n%s%n", theCoverForThisPrint);
        out.printf("| User             : %-12s |%n", nameCutToSize);
        out.printf("%s%n", theCoverForThisPrint);
        out.printf("| Task finished at : %12s |%n", time_at_exit);
        out.printf("%s%n", theCoverForThisPrint);
    }

    /**
     * This method tries to clear the screen (erase the terminal) both in
     * Windows and in Unix platforms.
     */
    public static void clear() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                out.print(ANSI_CLS + ANSI_HOME);
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
}
