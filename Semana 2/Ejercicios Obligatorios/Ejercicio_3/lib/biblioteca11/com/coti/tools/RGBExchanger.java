/*
 * Universidad de Salamanca - Departamento de Informática y Automática
 * Asignatura: Programación III (Grado en Ingeniería Informática)
 * Copyright (C) J. R. García-Bermejo Giner (coti@usal.es)
 */
package com.coti.tools;

/**
 *
 * @author Coti (coti at usal.es)
 * @param <T> A valid reference type
 */
public interface RGBExchanger<T> {
    //
    // Export methods
    //

    /**
     *
     * @return A string that holds all values of all attributes as strings
     */
    public abstract String[] stateAsListOfStrings();

    /**
     *
     * @param cols The number of columns taken up by each attribute
     * @return A string that holds each attribute in the given number of columns
     */
    public abstract String stateAsStringWithColumns(int[] cols);

    /**
     *
     * @param delimiter The String to be used as separator between attributes
     * @return A string with all attributes as strings, separated by delimiter (á la CSV)
     */
    public abstract String stateAsDelimitedString(String delimiter);

    //
    // Factory methods
    //

    /**
     *
     * @param <T> A valid reference type
     * @param csvString A string that holds all attributes of an instance of T as CSV
     * @param del The separator used in the previous string
     * @return An instance of T whose attributes have the given values, or null if information is not correct
     */
    public static <T> T instanceFrom(String csvString, String del) {
        throw new UnsupportedOperationException("instanceFrom(String cvsString,"
                + " String del) not done yet!!");

    }

    /**
     *
     * @param <T> A valid reference type
     * @param columnString A string with a given number of columns for each attribute in an instance of T
     * @param cols The number of columns used for each attribute within the given string
     * @return An instance of T whose attributes have the given values, or null if information is not correct
     */
    public static <T> T instanceFrom(String columnString, int[] cols) {
        throw new UnsupportedOperationException("instanceFrom(String columnString,"
                + " String[] cols) not done yet!!");

    }

    /**
     *
     * @param <T> A valid reference type
     * @param items A String[] that holds the string value of each attribute in an instance of T
     * @return An instance of T whose attributes have the given values, or null if information is not correct
     */
    public static <T> T instanceFrom(String[] items) {
        throw new UnsupportedOperationException("instanceFrom(String[] items "
                + "not done yet!!");

    }

}
