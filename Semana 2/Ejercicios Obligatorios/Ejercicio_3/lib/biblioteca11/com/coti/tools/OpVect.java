package com.coti.tools;

import static com.coti.tools.Esdia.*;
import static java.lang.Math.*;
import java.util.Random;

/*
+ Updated in Dec 2020, Javadoc markers are enhanced.
+ Updated again Sept 2021, randomVectorX() added.
 */
/**
 * Class <b>OpVect</b> offers a list of static methods that deal with numeric
 * vectors;<br>it allows the user to read vectors from the keyboard, and to
 * calculate the<br> dot, cross and mixed product, as well as the modulus of a
 * vector and the distance<br> between two points.
 *
 * @author coti
 */
public class OpVect {

    /**
     *
     * @param numberOfElements is the number of double numbers that will be
     * stored in the returned double[].
     * @return a vector that holds numberOfElements double items
     * @throws Exception in case that the number of items passed as argument is
     * actually not positive.
     */
    public static double[] readVectorD(int numberOfElements) throws Exception {
        if (numberOfElements <= 0) {
            throw new Exception("readVectorD: numberOfElements <= 0.");
        }
        double[] result = new double[numberOfElements];
        for (int i = 0; i < result.length; i++) {
            String prompt = String.format("Please write d[%d]: ",
                    i);
            result[i] = readDouble(prompt);

        }
        return result;
    } // End of readVectorD

    /**
     *
     * @param numberOfElements is the number of float numbers that will be
     * stored in the returned float[].
     * @return a vector that holds numberOfElements double items
     * @throws Exception in case that the number of items passed as argument is
     * actually not positive.
     */
    public static float[] readVectorF(int numberOfElements) throws Exception {
        if (numberOfElements <= 0) {
            throw new Exception("readVectorF: numberOfElements <= 0.");
        }
        float[] result = new float[numberOfElements];
        for (int i = 0; i < result.length; i++) {
            String prompt = String.format("Please write d[%d]: ", i);
            result[i] = readFloat(prompt);

        }
        return result;
    } // End of readVectorF

    /**
     *
     * @param numberOfElements is the number of int numbers that will be stored
     * in the returned int[].
     * @return a vector that holds numberOfElements int items
     * @throws Exception in case that the number of items passed as argument is
     * actually not positive.
     */
    public static int[] readVectorI(int numberOfElements) throws Exception {
        if (numberOfElements <= 0) {
            throw new Exception("readVectorI: numberOfElements <= 0.");
        }
        int[] result = new int[numberOfElements];
        for (int i = 0; i < result.length; i++) {
            String prompt = String.format("Please write d[%d]: ", i);
            result[i] = readInt(prompt);

        }
        return result;
    } // End of readVectorI

    /**
     * This method returns the dot product of vectors a and b
     *
     * @param a the first vector
     * @param b the second vector
     * @return the dot product of vectors a[] and b[]
     * @throws Exception if a[] and b[] have different lengths
     */
    public static double dotProduct(double[] a, double[] b) throws Exception {
        double accum = 0;
        if (a.length != b.length) {
            throw new Exception("dotProduct: no coinciden las dimensiones.");
        }

        for (int i = 0; i < a.length; i++) {
            accum += a[i] * b[i];

        }
        return accum;
    } // End of dotProduct

    /**
     * This method returns the cross product of vectos a and b
     *
     * @param a the first vector
     * @param b the second vector
     * @return the cross product of vectors a[] and b[]
     * @throws Exception if a[] and b[] have different lengths, or if the
     * dimension of both vectors is not 3.
     */
    public static double[] crossProduct(double[] a,
            double[] b) throws Exception {
        double[] result = new double[3];
        if (a.length != b.length) {
            throw new Exception(
                    "crossProduct: vectors do not have the same dimensions");
        }
        if (a.length != 3) {
            throw new Exception(
                    "crossProduct: vector do not have dimension 3");
        }
        result[0] = a[1] * b[2] - a[2] * b[1];
        result[1] = a[2] * b[0] - a[0] * b[2];
        result[2] = a[0] * b[1] - a[1] * b[0];
        return result;
    } // End of crossProduct

    /**
     * This method returns the mixed product (a· b x c) of vectors a, b and c.
     *
     * @param a the first vector
     * @param b the second vector
     * @param c the third vector
     * @return
     * @throws Exception
     */
    public static double mixedProduct(double[] a,
            double[] b, double[] c) throws Exception {

        return dotProduct(a, crossProduct(b, c));
    } //  End of mixedProduct

    /**
     * This method returns the module of vector a
     *
     * @param a The vector whose module is wanted
     * @return
     */
    public static double modulus(double[] a) {
        double accum = 0;
        for (int i = 0; i < a.length; i++) {
            accum += pow(a[i], 2);
        }
        return (sqrt(accum));
    } // End of modulus

    /**
     * This method returns the square of the module of vector a
     *
     * @param a is the vector whose squared module is wanted
     * @return
     */
    public static double modulus2(double[] a) {
        double accum = 0;
        for (double n : a) {
            accum += pow(n, 2);
        }
        return accum;
    } // End of modulus

    /**
     * This method returns the Euclidean distance between two points a and b with double coordinates
     *
     * @param a the first point
     * @param b the second point
     * @return the Euclidean distance between points a and b
     */
    public static double distance(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += pow(b[i] - a[i], 2);
        }
        return sqrt(result);
    } // End of distance
    
    /**
     * This method returns the Euclidean distance between two points a and b with float coordinates
     *
     * @param a the first point
     * @param b the second point
     * @return the Euclidean distance between points a and b
     */
    public static float distance(float[] a, float[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += pow(b[i] - a[i], 2);
        }
        return (float)sqrt(result);
    } // End of distance

        /**
     * This method returns the Euclidean distance between two points a and b with double coordinates
     *
     * @param a the first point
     * @param b the second point
     * @return the Euclidean distance between points a and b
     */
    public static int distance(int[] a, int[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += pow(b[i] - a[i], 2);
        }
        return (int)result;
    } // End of distance

    /**
     * This method returns the coordinates of a vector in the usual algebraic
     * disposition ({a, b, c, d}
     *
     * @param v The vector to be returned as a String[] holding its coordinates
     * @return the string that holds the vector in algebraic format
     */
    public static String doubleVectorAsString(double[] v) {
        String result;
        result = "{";
        for (int i = 0; i < v.length; i++) {
            if (i < v.length - 1) {
                result += String.format(" %.2f,", v[i]);
            } else {
                result += String.format(" %.2f}", v[i]);
            }

        }
        return result;
    } // End of doubleVectorAsString

    /**
     * This method is doubleVectorAsString with a shorter name
     *
     * @param v The vector to be returned as a String[] holding its coordinates
     * @return the string that holds the vector in algebraic format
     */
    public static String dToS(double[] v) {
        return doubleVectorAsString(v);
    } // End of vToS
    
   /**
     * This method returns the coordinates of a float vector in the usual algebraic
     * disposition ({a, b, c, d}
     *
     * @param v The vector to be returned as a String[] holding its coordinates
     * @return the string that holds the vector in algebraic format
     */
    public static String floatVectorAsString(float[] v) {
        String result;
        result = "{";
        for (int i = 0; i < v.length; i++) {
            if (i < v.length - 1) {
                result += String.format(" %.2f,", v[i]);
            } else {
                result += String.format(" %.2f}", v[i]);
            }

        }
        return result;
    } // End of floatVectorAsString

    /**
     * This method is doubleVectorAsString with a shorter name
     *
     * @param v The vector to be returned as a String[] holding its coordinates
     * @return the string that holds the vector in algebraic format
     */
    public static String fToS(float[] v) {
        return floatVectorAsString(v);
    } // End of fToS

       /**
     * This method returns the coordinates of an int vector in the usual algebraic
     * disposition ({a, b, c, d}
     *
     * @param v The vector to be returned as a String[] holding its coordinates
     * @return the string that holds the vector in algebraic format
     */
    public static String intVectorAsString(int[] v) {
        String result;
        result = "{";
        for (int i = 0; i < v.length; i++) {
            if (i < v.length - 1) {
                result += String.format("%d,", v[i]);
            } else {
                result += String.format(" %d}", v[i]);
            }

        }
        return result;
    } // End of floatVectorAsString

    /**
     * This method is intVectorAsString with a shorter name
     *
     * @param v The vector to be returned as a String[] holding its coordinates
     * @return the string that holds the vector in algebraic format
     */
    public static String iToS(int[] v) {
        return intVectorAsString(v);
    } // End of fToS

    /**
     * This method produces a random vector of doubles
     *
     * @return a random vector with 3 double coordinates within the unit cube
     */
    public static double[] randomVectorD() {
        var result = new double[3];
        Random r = new Random();
        for(int i=0;i<result.length;i++) {
            result[i] = r.nextDouble();
        }
        return result;
    } // End of randomVector()

        /**
     * This method produces a random vector of doubles
     *
     * @param n the number of coordinates
     * @return a random vector with n coordinates of type double
     */
    public static double[] randomVectorD(int n) {
        var result = new double[n];
        Random r = new Random();
        for(int i=0;i<result.length;i++) {
            result[i] = r.nextDouble();
        }
        return result;
    } // End of randomVectorD()
/**
     * This method a random vector with 3 float coordinates within the unit cube
     *
     * @return
     */
    public static float[] randomVectorF() {
        var result = new float[3];
        Random r = new Random();
        for(int i=0;i<result.length;i++) {
            result[i] = r.nextFloat();
        }
        return result;
    } // End of randomVector()

        /**
     * This method a random vector of floats
     *
     * @param n the number of coordinates
     * @return a random vector with n float coordinates within the unit cube
     */
    public static double[] randomVectorF(int n) {
        var result = new double[n];
        Random r = new Random();
        for(int i=0;i<result.length;i++) {
            result[i] = r.nextDouble();
        }
        return result;
    } // End of randomVectorF()
    

/**
     * This method returns a random vector of ints
     *
     * @param lowerLimit is the minimum random value (inclusive)
     * @param  upperLimit is the maximum random value (exclusive)
     * @return a random vector with three int coordinates within lowerLimit and
     * upperLimit (lowerLimit < upperLimit)
     */
    public static int[] randomVectorI(int lowerLimit,int upperLimit) {
        int range = upperLimit-lowerLimit;
        var result = new int[3];
        Random r = new Random();
        for(int i=0;i<result.length;i++) {
            result[i] = lowerLimit + r.nextInt(range);
        }
        return result;
    } // End of randomVectorI()

/**
     * This method returns a random vector of ints
     *
     * @return a random vector with three int coordinates -10 and 10, both included
     */
    public static int[] randomVectorI() {
        int lowerLimit = -10;
        var result = new int[3];
        Random r = new Random();
        for(int i=0;i<result.length;i++) {
            result[i] = lowerLimit + r.nextInt(21);
        }
        return result;
    } // End of randomVectorI()
    
}
/*

Véanse Jama y Jampack

 */
