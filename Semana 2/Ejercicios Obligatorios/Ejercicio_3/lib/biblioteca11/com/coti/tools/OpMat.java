package com.coti.tools;

import static com.coti.tools.Esdia.readDouble;
import static com.coti.tools.Esdia.readFloat;
import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString_ne;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import static java.lang.System.err;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/*
 * Grado en Ingeniería Informática - Universidad de Salamanca
 * Asignatura : Programación III
 * Copyright (C) J.R. García-Bermejo Giner
 *
 * Now under Git control in Github/bruegel/com (Oct 2018)
 *
 * Test rig is ln-based
 *
 * Last updated on Nov 23rd 2018
 *
 * Last updated on Dec 6th 2019
 * Last updated on Nov 15th 2020 (importFromDisk() now throws Exception)
 * Last updated on Dec 13th 2020, updated to Java 14 and enhancements made
 * Further, Javadoc comments have been reviewed
 *
This is branch V_1
Last updated Sept 26 2021. Methods enhanced,
should now read its own disk output (text).
 */
/**
 * Class <b>OpMat</b> offers a list of static methods that deal with atomic and
 * String<br> arrays; their purpose is to read from the keyboard, print to
 * screen, import from disk <br>(CSV and columnar), export to disk (CSV and
 * columnar), serialize (save) to disk,<br> read from disk, sum, multiply and
 * join (only String arrays).
 *
 * @author coti
 */
public class OpMat {

  /**
   * This private method returns a random alphanumeric string with an
   * internally-defined pool of characters and length.
   *
   * @param maxLength Is the max length of the returned string
   * @return a random string that contains items found in alphabet.
   */
  private static String randomString(int maxLength) {
    final String alphabet = "abcdefghijklmnñopqrstuvwxyz"
            + "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
            + "áéíóúÁÉÍÓÚ";
    final int ALPHABET_LENGTH = alphabet.length();
    Random r = new Random();
    int actualLength = r.nextInt(maxLength) + 1;
    int randomIndex;
    String result = "";
    for (int i = 0; i < actualLength; i++) {
      randomIndex = r.nextInt(ALPHABET_LENGTH);
      result += alphabet.charAt(randomIndex);
    }
    return result;
  }

  /**
   * This method returns a String[][] with the given number of rows and columns;
   * each item is a random string of characters that belong to the
   * internally-defined alphabet.
   *
   * @param numRows the number of rows of the returned String[][]
   * @param numColumns the number of columns of the returned String[][]
   * @return
   */
  public static String[][] randomArrayOfString(int numRows, int numColumns) {
    String[][] tmp = new String[numRows][numColumns];
    final int MAX_RANDOM_STRING_LENGTH = 8;
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numColumns; col++) {
        tmp[row][col] = randomString(MAX_RANDOM_STRING_LENGTH);
      }
    }
    return tmp;
  }

  /**
   * This method returns a String[][] with the given number of rows and columns;
   * each item is a random string of characters that belong to the
   * internally-defined alphabet.
   *
   * @param numRows the number of rows of the returned String[][]
   * @param numColumns the number of columns of the returned String[][]
   * @return
   */
  public static int[][] randomArrayOfInt(int numRows, int numColumns) {
    int[][] tmp = new int[numRows][numColumns];
    final Random r = new Random();
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numColumns; col++) {
        tmp[row][col] = r.nextInt();
      }
    }
    return tmp;
  }

  /**
   * This method returns a String[][] with the given number of rows and columns;
   * each item is a random string of characters that belong to the
   * internally-defined alphabet.
   *
   * @param numRows the number of rows of the returned String[][]
   * @param numColumns the number of columns of the returned String[][]
   * @return
   */
  public static float[][] randomArrayOfFloat(int numRows, int numColumns) {
    float[][] tmp = new float[numRows][numColumns];
    final Random r = new Random();
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numColumns; col++) {
        tmp[row][col] = r.nextFloat();
      }
    }
    return tmp;
  }

  /**
   * This method returns a String[][] with the given number of rows and columns;
   * each item is a random string of characters that belong to the
   * internally-defined alphabet.
   *
   * @param numRows the number of rows of the returned String[][]
   * @param numColumns the number of columns of the returned String[][]
   * @return
   */
  public static double[][] randomArrayOfDouble(int numRows, int numColumns) {
    double[][] tmp = new double[numRows][numColumns];
    Random r = new Random();
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numColumns; col++) {
        tmp[row][col] = r.nextDouble();
      }
    }
    return tmp;
  }

  /**
   * This method reads from the keyboard a double[][]
   *
   * @param matrix - the double[][] after reading
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void inputMat(double[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to inputMat a null array of double");
    }
    int numRows = matrix.length;
    int numColumns = matrix[0].length;
    String tmp;
    for (int i = 0; i < numRows; ++i) {
      for (int j = 0; j < numColumns; ++j) {
        tmp = String.format("m[%d][%d] = ", i, j);
        matrix[i][j] = readDouble(tmp);
      }
    }
  }

  /**
   * This method reads from the keyboard a float[][]
   *
   * @param matrix - the float[][] after reading
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void inputMat(float[][] matrix) throws Exception {
    if (null == matrix) {
      err.printf("OpMat - null float array");
      throw new Exception("Attempt to inputMat a null array of float");
    }
    int numRows = matrix.length;
    int numColumns = matrix[0].length;
    String tmp;
    for (int i = 0; i < numRows; ++i) {
      for (int j = 0; j < numColumns; ++j) {
        tmp = String.format("m[%d][%d] = ", i, j);
        matrix[i][j] = readFloat(tmp);
      }
    }
  }

  /**
   * This method reads from the keyboard an int[][]
   *
   * @param matrix - the int[][] after reading
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void inputMat(int[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to inputMat a null array of int");
    }
    int numRows = matrix.length;
    int numColumns = matrix[0].length;
    String tmp;
    for (int i = 0; i < numRows; ++i) {
      for (int j = 0; j < numColumns; ++j) {
        tmp = String.format("m[%d][%d] = ", i, j);
        matrix[i][j] = readInt(tmp);
      }
    }
  }

  /**
   * This method reads from the keyboard a String[][]
   *
   * @param matrix - the String[][] after reading
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void inputMat(String[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to inputMat a null array of String");
    }
    int numRows = matrix.length;
    int numColumns = matrix[0].length;
    String tmp;
    for (int i = 0; i < numRows; ++i) {
      for (int j = 0; j < numColumns; ++j) {
        tmp = String.format("m[%d][%d] = ", i, j);
        matrix[i][j] = readString_ne(tmp);
      }
    }
  }

  /**
   * This method prints to screen a String[][] with fixed column widths (20)
   *
   * @param matrix - the String[][] whtich will be printed with a fixed column
   * width of 20 columns. No trimming is done; too long items will produce an
   * ugly display.
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void printToScreen(String[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToScreen a null array of String");
    }
    for (String[] row : matrix) {
      for (String value : row) {
        out.printf(" | %20s ", value);
      }
      out.printf("|%n");
    }
  }

  /**
   * This method prints to screen a String[][] with uniform column widths.The
   * width is calculated as the maximum width of any column in any row, hence
   * some columns could be printed using too many columns, but no item will
   * cause a row whose length is not different from that of the other rows.
   *
   * @param matrix - the String[][] to be printed with equal width columns
   * @throws java.lang.Exception
   */
  public static void printToScreen2(String[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToScreen2 a null array of String");
    }
    int maxColumnWidth = 0;
    for (String[] row : matrix) {
      for (String col : row) {
        if (col.length() > maxColumnWidth) {
          maxColumnWidth = col.length();
        }
      }
    }
    String format = "| %" + maxColumnWidth + "s ";
    for (String[] row : matrix) {
      for (String value : row) {
        out.printf(format, value);
      }
      out.printf("|%n");
    }
  }

  /**
   * This method prints to screen a String[][] with minimum and possibly
   * different widths for each column. No column separators ("|") are shown.
   *
   * @param matrix - the String[][]
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void printToScreen3(String[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToScreen3 a null array of String");
    }
    int maxNumberOfColumns = matrix[0].length;
    for (String[] row : matrix) {
      if (row.length >= maxNumberOfColumns) {
        maxNumberOfColumns = row.length;
      }
    }
    int[] maxColumnWidth = new int[maxNumberOfColumns];
    final int NUM_ROWS = matrix.length;
    final int NUM_COLUMNS = maxNumberOfColumns;
    Arrays.fill(maxColumnWidth, 0);
    //
    // Calculate max width for each column
    //
    for (int numRow = 0; numRow < NUM_ROWS; numRow++) {
      for (int numColumn = 0; numColumn < NUM_COLUMNS; numColumn++) {
        if (matrix[numRow][numColumn].length() > maxColumnWidth[numColumn]) {
          maxColumnWidth[numColumn] = matrix[numRow][numColumn].length();
        }
      }
    }
    //
    // Print each column with it proper width
    //
    String format;
    for (int numRow = 0; numRow < NUM_ROWS; numRow++) {
      for (int numCol = 0; numCol < NUM_COLUMNS; numCol++) {
        format = "| %" + maxColumnWidth[numCol] + "s ";
        out.printf(format, matrix[numRow][numCol]);
      } // End of for each column
      out.printf("|%n");
    } // End of for each row

  } // End of printoScreen3()

  /**
   * This method prints to screen a String[][] with minimum and possibly
   * different widths for each column. Column separators ("|") are shown, adding
   * numColumns + 1 to total row witdh.
   *
   * @param matrix - the String[][]
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void printToScreen4(String[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToScreen4 a null array of String");
    }
    int maxNumberOfColumns = matrix[0].length;
    for (String[] row : matrix) {
      if (row.length >= maxNumberOfColumns) {
        maxNumberOfColumns = row.length;
      }
    }
    int[] maxColumnWidth = new int[maxNumberOfColumns];
    final int NUM_ROWS = matrix.length;
    final int NUM_COLUMNS = matrix[0].length;
    Arrays.fill(maxColumnWidth, 0);
    //
    // Calculate max width for each column
    //
    for (int numRow = 0; numRow < NUM_ROWS; numRow++) {
      for (int numColumn = 0; numColumn < NUM_COLUMNS; numColumn++) {
        if (matrix[numRow][numColumn].length() > maxColumnWidth[numColumn]) {
          maxColumnWidth[numColumn] = matrix[numRow][numColumn].length();
        }
      }
    }
    //
    // Print each column with is proper width
    //
    String format;
    for (int numRow = 0; numRow < NUM_ROWS; numRow++) {
      for (int numCol = 0; numCol < NUM_COLUMNS; numCol++) {
        format = "| %" + maxColumnWidth[numCol] + "s ";
        out.printf(format, matrix[numRow][numCol]);
      } // End of for each column
      out.printf("|%n");
    } // End of for each row

  } // End of printoScreen3()

  /**
   * This method admits a String[][] and returns it as a String á la
   * printToScreen3() with different widths for each column
   *
   * @param matrix - the String[][]
   * @return
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static String printToString3(String[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToString3 a null array of String");
    }
    int[] maxColumnWidth = new int[matrix[0].length];
    final int NUM_ROWS = matrix.length;
    final int NUM_COLUMNS = matrix[0].length;
    Arrays.fill(maxColumnWidth, 0);
    //
    // Calculate max width for each column
    //
    for (int numRow = 0; numRow < NUM_ROWS; numRow++) {
      for (int numColumn = 0; numColumn < NUM_COLUMNS; numColumn++) {
        if (matrix[numRow][numColumn].length() > maxColumnWidth[numColumn]) {
          maxColumnWidth[numColumn] = matrix[numRow][numColumn].length();
        }
      }
    }
    //
    // Print each column with its proper width
    //
    String format;
    StringBuilder sb = new StringBuilder();
    String tmp;
    for (int numRow = 0; numRow < NUM_ROWS; numRow++) {
      for (int numCol = 0; numCol < NUM_COLUMNS; numCol++) {
        format = "| %" + maxColumnWidth[numCol] + "s ";
        tmp = String.format(format, matrix[numRow][numCol]);
        sb.append(tmp);
      } // End of for each column
      sb.append(String.format("|%n"));
    } // End of for each row

    return sb.toString();
  } // End of printoScreen3()

  /**
   * This method prints to screen a double[][] with fixed (8.3) column and
   * decimal widths
   *
   * @param matrix - the double[][]
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void printToScreen(double[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToScreen a null array of double");
    }
    for (double[] row : matrix) {
      out.print("| ");
      for (double value : row) {
        out.printf("%8.3f", value);
      }
      out.println(" |");
    }
  }

  /**
   * This method prints to screen a float[][] with fixed (8.3) column and
   * decimal widths
   *
   * @param matrix - the float[][]
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void printToScreen(float[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToScreen a null array of float");
    }
    for (float[] row : matrix) {
      out.print("| ");
      for (float value : row) {
        out.printf("%8.3f", value);
      }
      out.println(" |");
    }
  }

  /**
   * This method prints to screen an int[][] with fixed (8) column widths
   *
   * @param matrix - the int[][]
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   */
  public static void printToScreen(int[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to printToScreen a null array of int");
    }
    for (int[] row : matrix) {
      out.print("| ");
      for (int value : row) {
        out.printf("%12d", value);
      }
      out.println(" |");
    }
  }

  /**
   * This method writes an double[][] to disk, with binary format
   *
   * @param f - the file to write to
   * @param matrix - an double[][]
   * @throws Exception - thrown in case of IO error
   */
  public static void saveToDisk(File f, double[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to saveToDisk a null array of double");
    }
    ObjectOutputStream oos = new ObjectOutputStream(
            new BufferedOutputStream(
                    new FileOutputStream(f)));
    oos.writeObject(matrix);
    oos.close();
  }

  /**
   * This method writes a float[][] to disk, with binary format
   *
   * @param f - the file to write to
   * @param matrix - a float[][]
   * @throws Exception - thrown in case of IO error
   */
  public static void saveToDisk(File f, float[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to saveToDisk a null array of float");
    }
    FileOutputStream fos = new FileOutputStream(f);
    try ( ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(matrix);
    }
  }

  /**
   * This method writes an int[][] to disk, with binary format
   *
   * @param f - the file to write to
   * @param matrix - an int[][]
   * @throws Exception - thrown in case of IO error
   */
  public static void saveToDisk(File f, int[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to saveToDisk a null array of int");
    }
    FileOutputStream fos = new FileOutputStream(f);
    try ( ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(matrix);
    }
  }

  /**
   * This method reads from disk an double[][] written with binary format
   *
   * @param f - the file to read from
   * @param dummy - the necessary change in signature
   * @return - a double[][]
   * @throws Exception - in case of failure when reading
   */
  public static double[][] loadFromDisk(File f, double dummy) throws Exception {
    double[][] result;
    FileInputStream fis = new FileInputStream(f);
    try ( ObjectInputStream ois = new ObjectInputStream(fis)) {
      result = (double[][]) ois.readObject();
    }
    return result;
  }

  /**
   * This method reads from disk an float[][] written with binary format
   *
   * @param f - the file to read from
   * @param dummy - the necessary change in signature
   * @return - a float[][]
   * @throws Exception - in case of failure when reading
   */
  public static float[][] loadFromDisk(File f, float dummy) throws Exception {
    float[][] result;
    FileInputStream fis = new FileInputStream(f);
    try ( ObjectInputStream ois = new ObjectInputStream(fis)) {
      result = (float[][]) ois.readObject();
    }
    return result;
  }

  /**
   * This method reads from disk an int[][] written with binary format
   *
   * @param f - the file to read from
   * @param dummy - the necessary change in signature
   * @return - a int[][]
   * @throws Exception - in case of failure when reading
   */
  public static int[][] loadFromDisk(File f, int dummy) throws Exception {
    int[][] result;
    FileInputStream fis = new FileInputStream(f);
    try ( ObjectInputStream ois = new ObjectInputStream(fis)) {
      result = (int[][]) ois.readObject();
    }
    return result;
  }

  /**
   * This method imports from disk a String[][] written with delimited format
   *
   * @param f - the file to read from
   * @param delimiter - the delimiter placed between consecutive fields
   * @return - a String[][]
   * @throws Exception - in case of failure when reading
   */
  public static String[][] importFromDisk(File f, String delimiter) throws Exception {
    String[][] result = null;
    if (!f.exists()) {
      var message = String.format("OpMat.importFromDisk()%n%n================================%nERROR: El archivo%n%n%s%n%n    NO EXISTE!!%n%n================================%n", f.toString());
      throw new IOException(message);
    }
    List<String> lines = Files.readAllLines(f.toPath());
    List<String> nonEmptyLines = new ArrayList<>();
    for (String s : lines) {
      if (!s.isBlank()) {
        nonEmptyLines.add(s);
      }
    }
    int numRows = nonEmptyLines.size();
    result = new String[numRows][];
    for (int row = 0; row < numRows; row++) {
      result[row] = nonEmptyLines.get(row).split(delimiter);
    }
    return result;
  }

  /**
   * This method imports an int[][] from a delimited file
   *
   * @param f - Is the File to read from
   * @param delimiter - the delimiter to be inserted between consecutive fields
   * @param dummy - the necessary change in signature
   * @return an int[][]
   * @throws Exception in case of failure when reading or parsing
   */
  public static int[][] importFromDisk(File f, String delimiter, int dummy) throws Exception {
    String[][] tmp = importFromDisk(f, delimiter);
    int numRows = tmp.length;
    int numCols = tmp[0].length;
    int[][] result = new int[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        result[i][j] = Integer.parseInt(tmp[i][j]);
      }
    }
    return result;
  }

  /**
   * This method imports a float[][] from a delimited file
   *
   * @param f - Is the File to read from
   * @param delimiter - the delimiter to be inserted between consecutive fields
   * @param dummy - the necessary change in signature
   * @return a float[][]
   * @throws Exception in case of failure when reading or parsing
   */
  public static float[][] importFromDisk(File f, String delimiter, float dummy) throws Exception {
    String[][] tmp = importFromDisk(f, delimiter);
    int numRows = tmp.length;
    int numCols = tmp[0].length;
    float[][] result = new float[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        result[i][j] = Float.parseFloat(tmp[i][j]);
      }
    }
    return result;
  }

  /**
   * This method imports a double[][] from a delimited file
   *
   * @param f - Is the File to read from
   * @param delimiter - the delimiter to be inserted between consecutive fields
   * @param dummy - the necessary change in signature
   * @return a double[][]
   * @throws Exception in case of failure when reading or parsing
   */
  public static double[][] importFromDisk(File f, String delimiter, double dummy) throws Exception {
    String[][] tmp = importFromDisk(f, delimiter);
    int numRows = tmp.length;
    int numCols = tmp[0].length;
    double[][] result = new double[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        result[i][j] = Double.parseDouble(tmp[i][j]);
      }
    }
    return result;
  }

  /**
   * This method exports a String[][] with delimited format and can throw an
   * exception
   *
   * @param matrix - the array that will be exported
   * @param f - the File that points to a file
   * @param delimiter - the delimiter (like \t, ",", "#" etc.) to be used
   * @throws Exception - the exception produce if writing was not possible
   */
  public static void exportToDisk(String[][] matrix, File f, String delimiter) throws Exception {
    StringBuilder sb = new StringBuilder();
    int numRows = matrix.length;
    for (int row = 0; row < numRows; row++) {
      int numCols = matrix[row].length;
      sb.append(String.format("%s", matrix[row][0]));
      for (int col = 1; col < numCols; col++) {
        sb.append(String.format("%s%s", delimiter, matrix[row][col]));
      }
      sb.append("\n");
    }
    try ( PrintWriter pw = new PrintWriter(f)) {
      pw.print(sb.toString());
    }
  }

  /**
   * This method exports an int[][] to disk with delimited format
   *
   * @param matrix - the array that will be exported
   * @param f - the File that points to a file
   * @param delimiter - the delimiter (like \t, ",", "#" etc.) to be used
   */
  public static void exportToDisk(int[][] matrix, File f, String delimiter) {
    // First translate int[][] into ArrayList<String> with delimited format
    StringBuilder sb = new StringBuilder();
    ArrayList<String> listOfString = new ArrayList<>();
    for (int[] row : matrix) {
      sb.setLength(0);
      sb.append(Integer.toString(row[0]));
      for (int n = 1; n < row.length; n++) {
        sb.append(delimiter + Integer.toString(row[n]));
      }
      listOfString.add(sb.toString());
    } // End of for
    try {
      // Now write to disk
      Files.write(f.toPath(), listOfString, StandardOpenOption.CREATE);
    } catch (IOException ex) {
      err.printf("%nexportToDisk(int[][]) failed - %s", ex.toString());
    }
  }

  /**
   * This method exports a float[][] to disk with delimited format
   *
   * @param matrix - the array that will be exported
   * @param f - the File that points to a file
   * @param delimiter - the delimiter (like \t, ",", "#" etc.) to be used
   */
  public static void exportToDisk(float[][] matrix, File f, String delimiter) {
    // First translate int[][] into ArrayList<String> with delimited format
    StringBuilder sb = new StringBuilder();
    ArrayList<String> listOfString = new ArrayList<>();
    for (float[] row : matrix) {
      sb.setLength(0);
      sb.append(Float.toString(row[0]));
      for (int n = 1; n < row.length; n++) {
        sb.append(delimiter + Float.toString(row[n]));
      }
      listOfString.add(sb.toString());
    } // End of for
    try {
      // Now write to disk
      Files.write(f.toPath(), listOfString, StandardOpenOption.CREATE);
    } catch (IOException ex) {
      err.printf("%nexportToDisk(float[][]) failed - %s", ex.toString());
    }
  }

  /**
   * This method exports a double[][] to disk with delimited format
   *
   * @param matrix a double[][] to be written to a file
   * @param f the file to write the table to
   * @param delimiter the delimiter to be used when writing the table (CSV-like)
   */
  public static void exportToDisk(double[][] matrix, File f, String delimiter) {
    // First translate double[][] into ArrayList<String> with delimited format
    StringBuilder sb = new StringBuilder();
    ArrayList<String> listOfString = new ArrayList<>();
    for (double[] row : matrix) {
      sb.setLength(0);
      sb.append(Double.toString(row[0]));
      for (int n = 1; n < row.length; n++) {
        sb.append(delimiter + Double.toString(row[n]));
      }
      listOfString.add(sb.toString());
    } // End of for
    try {
      // Now write to disk
      Files.write(f.toPath(), listOfString, StandardOpenOption.CREATE);
    } catch (IOException ex) {
      err.printf("%nexportToDisk(double[][]) failed - %s", ex.toString());
    }
  }

  /**
   * This method exports a double[][] to disk with delimited format
   *
   * @param matrix a double[][] to be written to a file
   * @param f the file to write the table to
   * @param delimiter the delimiter to be used when writing the table (CSV-like)
   */
  public static void exportToDisk2(double[][] matrix, File f, String delimiter) {
    final String LINE_SEPARATOR = System.lineSeparator();
    //StringBuilder sb = new StringBuilder(37750000);
    StringBuilder sb = new StringBuilder();
    for (double[] row : matrix) {
      sb.append(row[0]);
      for (int n = 1; n < row.length; n++) {
        sb.append(delimiter).append(row[n]);
      }
      sb.append(LINE_SEPARATOR);
    } // End of for
    try {
      // Now write to disk
      Files.writeString(f.toPath(), sb, StandardOpenOption.CREATE);
    } catch (IOException ex) {
      err.printf("%nexportToDisk(double[][]) failed - %s", ex.toString());
    }
    //System.out.printf("The capacity of this sb is  " + sb.capacity());
  }

  /**
   * This method returns the sum of its two int[][] arguments
   *
   * @param a - first factor
   * @param b - second factor
   * @return - the sum of arrays a and b
   */
  public static int[][] sum(int[][] a, int[][] b) {
    if (a.length != b.length || a[0].length != b[0].length) {
      err.printf("%nsum(int[][], int[][]) failed, unequal arrays");
      return null;
    }
    int numRows = a.length;
    int numCols = b.length;
    int[][] result = new int[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        result[i][j] = a[i][j] + b[i][j];
      }
    }
    return result;
  }

  /**
   * This method returns the sum of its two float[][] arguments
   *
   * @param a - first factor
   * @param b - second factor
   * @return - the sum of arrays a and b
   */
  public static float[][] sum(float[][] a, float[][] b) {
    if (a.length != b.length || a[0].length != b[0].length) {
      err.printf("%nsum(float[][], float[][]) failed, unequal arrays");
      return null;
    }
    int numRows = a.length;
    int numCols = b.length;
    float[][] result = new float[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        result[i][j] = a[i][j] + b[i][j];
      }
    }
    return result;
  }

  /**
   * This method returns the sum of its two double[][] arguments
   *
   * @param a - first factor
   * @param b - second factor
   * @return - the sum of arrays a and b
   */
  public static double[][] sum(double[][] a, double[][] b) {
    if (a.length != b.length || a[0].length != b[0].length) {
      err.printf("%nsum(double[][], double[][]) failed, unequal arrays");
      return null;
    }
    int numRows = a.length;
    int numCols = b.length;
    double[][] result = new double[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        result[i][j] = a[i][j] + b[i][j];
      }
    }
    return result;
  }

  /**
   * This method returns the product of its two float[][] arguments
   *
   * @param a - first factor
   * @param b - second factor
   * @return - the product of arrays a and b
   */
  public static float[][] product(float[][] a, float[][] b) {
    if (a[0].length != b.length) {
      err.printf("%nproduct(float[][], float[][]) failed, incompatible arrays");
      return null;
    }
    int numRows = a.length;
    int numCols = b[0].length;
    float[][] result = new float[numRows][numCols];
    float tmp;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        tmp = 0.0f;
        for (int k = 0; k < b.length; k++) {
          tmp += a[i][k] * b[k][j];
        }
        result[i][j] = tmp;
      }
    }
    return result;
  }

  /**
   * This method returns the product of its two int[][] arguments
   *
   * @param a - first factor
   * @param b - second factor
   * @return - the product of arrays a and b
   */
  public static int[][] product(int[][] a, int[][] b) {
    if (a[0].length != b.length) {
      err.printf("%nproduct(int[][], int[][]) failed, incompatible arrays");
      return null;
    }
    int numRows = a.length;
    int numCols = b[0].length;
    int[][] result = new int[numRows][numCols];
    int tmp;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        tmp = 0;
        for (int k = 0; k < b.length; k++) {
          tmp += a[i][k] * b[k][j];
        }
        result[i][j] = tmp;
      }
    }
    return result;
  }

  /**
   * This method returns the product of its two double[][] arguments
   *
   * @param a - first factor
   * @param b - second factor
   * @return - the product of arrays a and b
   */
  public static double[][] product(double[][] a, double[][] b) {
    if (a[0].length != b.length) {
      err.printf("%nproduct(double[][], double[][]) failed, incompatible arrays");
      return null;
    }
    int numRows = a.length;
    int numCols = b[0].length;
    double[][] result = new double[numRows][numCols];
    double tmp;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        tmp = 0.0;
        for (int k = 0; k < b.length; k++) {
          tmp += a[i][k] * b[k][j];
        }
        result[i][j] = tmp;
      }
    }
    return result;
  }

  /**
   * This method returns the sum of the items placed in the main diagonal for
   * the given array.
   *
   * @param matrix - the double[][]
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   * @return The sum of elements on the diagonal, fires exception on null array
   */
  public static double sumMainDiagonal(double[][] matrix) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to calculate sum(diag) on a null array");
    }

    final int NUM_ROWS = matrix.length;
    final int NUM_COLUMNS = matrix[0].length;
    if (NUM_ROWS != NUM_COLUMNS) {
      throw new Exception("Attempt to calculate sum(diag) of non-square array");
    }
    double result = 0.0;
    for (int i = 0; i < NUM_ROWS; i++) {
      result += matrix[i][i];
    }
    return result;
  } // End of sunMainDiagonal()

  /**
   * This method returns the sum of the items placed in columnNumber for the
   * given array.
   *
   * @param matrix - the double[][]
   * @param columnNumber The number of the colum whose sum is wanted
   * @throws Exception - Thrown in case the the method is provided with a null
   * array
   * @return The sum of elements on columnNumber-th column, fires exception on
   * null or out of bounds columnNumber array
   */
  public static double sumColumn(double[][] matrix, int columnNumber) throws Exception {
    if (null == matrix) {
      throw new Exception("Attempt to calculate sumColumn(m,colN)) on a null array");
    }

    final int NUM_ROWS = matrix.length;
    final int NUM_COLUMNS = matrix[0].length;
    if (columnNumber > NUM_COLUMNS - 1) {
      throw new Exception("Attempt to calculate sumColumn(m,colN)) on a out of bounds column");
    }
    double result = 0.0;
    for (int i = 0; i < NUM_ROWS; i++) {
      result += matrix[i][columnNumber];
    }
    return result;
  } // End of sunMainDiagonal()

  /**
   * This method returns the join of its two String[][] arguments. Both arrays
   * are supposed to be have an equal number of rows; otherwise an error message
   * is shown and null is returned.
   *
   * @param a - first joined array
   * @param b - second joined array
   * @return - the result of joining both arrays. First array is on the left
   * hand side, second array is on the right hand side.
   */
  public static String[][] join(String[][] a, String[][] b) {
    if (a == null || b == null) {
      err.printf("%n%nOpMat - null array in join()%n%n");
      return null;
    }
    if (a.length != b.length) {
      err.printf("%n%nOpMat - cannot join arrays with != number of rows%n%n");
      return null;
    }
    int numRows = a.length;
    int numCols1 = a[0].length;
    int numCols2 = b[0].length;
    ArrayList<String> row_as_list = new ArrayList<>();
    String[][] result = new String[numRows][numCols1 + numCols2];
    for (int numRow = 0; numRow < numRows; numRow++) {
      row_as_list.addAll(Arrays.asList(a[numRow]));
      row_as_list.addAll(Arrays.asList(b[numRow]));
      result[numRow] = row_as_list.toArray(new String[0]);
      row_as_list.clear();
    }

    return result;
  }

  /**
   * This method returns the join of a String[][] and a String[]. Both arrays
   * are supposed to be have an equal number of rows; otherwise an error message
   * is shown and null is returned.
   *
   * @param a - first joined array (String[][])
   * @param b - second joined array (String[])
   * @return - the result of joining both arrays. First array is on the left
   * hand side, second array is on the right hand side.
   */
  public static String[][] join(String[][] a, String[] b) {
    if (a == null || b == null) {
      err.printf("%n%nOpMat - null array in join()%n%n");
      return null;
    }
    if (a.length != b.length) {
      err.printf("%n%nOpMat - cannot join arrays with != number of rows%n%n");
      return null;
    }
    int numRows = a.length;
    int numCols1 = a[0].length;
    int numCols2 = 1;
    ArrayList<String> row_as_list = new ArrayList<>();
    String[][] result = new String[numRows][numCols1 + numCols2];
    for (int numRow = 0; numRow < numRows; numRow++) {
      row_as_list.addAll(Arrays.asList(a[numRow]));
      row_as_list.add(b[numRow]); // Add just one more item
      result[numRow] = row_as_list.toArray(new String[0]);
      row_as_list.clear();
    }

    return result;
  }

  /**
   * This method returns the join of a String[] and a String[][]. Both arrays
   * are supposed to be have an equal number of rows; otherwise an error message
   * is shown and null is returned.
   *
   * @param a - first joined array (String[])
   * @param b - second joined array (String[][])
   * @return - the result of joining both arrays. First array (column) is on the
   * left hand side, second array is on the right hand side.
   */
  public static String[][] join(String[] a, String[][] b) {
    if (a == null || b == null) {
      err.printf("%n%nOpMat - null array in join()%n%n");
      return null;
    }
    if (a.length != b.length) {
      err.printf("%n%nOpMat - cannot join arrays with != number of rows%n%n");
      return null;
    }
    int numRows = a.length;
    int numCols1 = 1;
    int numCols2 = b[0].length;
    ArrayList<String> row_as_list = new ArrayList<>();
    String[][] result = new String[numRows][numCols1 + numCols2];
    for (int numRow = 0; numRow < numRows; numRow++) {

      // Add numRow-th item of a to the temp list
      row_as_list.add(a[numRow]);
      // Add numRow-th item of b (a list, b[numRow]) to the temp list
      row_as_list.addAll(Arrays.asList(b[numRow])); // Add just one more item

      // Now transform the whole list (the whole row as lst) into a list
      // and assign as numRow-th row of the resulting array.
      result[numRow] = row_as_list.toArray(new String[0]);
      row_as_list.clear();
    }

    return result;
  }

  /**
   * This method returns the concatenation of two String[]
   *
   * @param a - first concatenated array (String[)
   * @param b - second concatenated array (String[])
   * @return - the result of joining both arrays. First array is on the left
   * hand side, second array is on the right hand side.
   */
  public static String[] join(String[] a, String[] b) {

    List<String> tmp = new ArrayList<>();
    Collections.addAll(tmp, a);
    Collections.addAll(tmp, b);
    return tmp.toArray(new String[0]);

  } // End of join(String[], String[])

  /**
   * This method returns the concatenation of two String[][]
   *
   * @param a - first concatenated array (String[][])
   * @param b - second concatenated array (String[][])
   * @return - the result of joining both tables. First array is on top, second
   * array is placed immediately below.
   */
  public static String[][] concat(String[][] a, String[][] b) {

    int numRows_a = a.length;
    int numRows_b = b.length;
    String[][] tmp = new String[numRows_a + numRows_b][];
    System.arraycopy(a, 0, tmp, 0, numRows_a);
    System.arraycopy(b, 0, tmp, numRows_a, numRows_b);
    return tmp;

  } // End of method concat(String[][], String[][])
 /**
   * This method returns the concatenation of two String[][]
   *
   * @param a - first row, to array (String[])
   * @param b - second concatenated array (String[][])
   * @return - the result of joining both tables. First row is on top, second
   * array is placed immediately below.
   */
  public static String[][] concat(String[] a, String[][] b) {

    int numRows_b = b.length;
    String[][] tmp = new String[1 + numRows_b][];
    // System.arraycopy(source,sourcepos,destination,destinationpos,numrowsofsourcecopied)
    tmp[0] = a;
    System.arraycopy(b, 0, tmp, 1, numRows_b);
    return tmp;

  } // End of method concat(String[][], String[][])

} // End of class OpMat
