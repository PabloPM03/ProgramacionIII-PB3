package com.coti.textfiletools;

/*
 *@author coti
 *@version 1.6 - not final (will it ever be?)
 * 12/12/11 17:12
 *
        .-.
     ,-(  _)-.
  .-(_    (_  )-.
 (__            _)
    `-(______)-'
       `:::\
         `::\
           `:\
           /::'    IMPORTANT CHANGE
          /:'      ----------------
          `:\      This library will no longer append
            `\     data when writing. It will overwrite
              \    existing files.
                   This is in accordance with user
                   expectations; no-one wants a database
                   to be duplicated when saving after
                   changes have been made.

	However, appending is possible.
 */
// Last compilation date
//
// 9/2/16 13:20
//
import java.io.File;
import java.util.*;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.*;

public class AuxTextHi implements TextUtils, Serializable {

    private Component owner;

    public AuxTextHi(Component o) {
        owner = o;
    }

    public void errMess(String message, String title) {
        JOptionPane.showMessageDialog(owner, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * @param f the File whose contents are to be read as a single String
     */
    @Override
    public String readString(File f) {
        Scanner s;
        String resultado;
        try {
            s = new Scanner(new BufferedReader(new FileReader(f))).useDelimiter("\\Z");
        } catch (Exception e) {
            errMess("Could not open file", "AuxTextHi:readString");
            return null;
        }
        try {
            resultado = s.next();
        } catch (Exception e) {
            s.close();
            resultado = null;
        }
        return resultado;
    }

    /**
     *
     * @param str the String to be written
     * @param f the file to which the string should be written
     * @return the contents of the file as a single String
     */
    @Override
    public boolean writeString(String str, File f) {
        try {
            PrintWriter pw = new PrintWriter(f);
            pw.print(str);
            pw.close();
            return true;
        } catch (Exception e) {
            errMess(e.toString(), "AuxTextHi: Error in boolean writeString(String, File)");
            return false;
        }
    }

    public boolean writeStringAppending(String str, File f) {
        String s = null;
        try {
            if (f.exists()) {
                s = readString(f);
            }
            PrintWriter pw = new PrintWriter(f);
            if (null != s) {
                pw.print(s);
            }
            pw.print(str);
            pw.close();
            return true;
        } catch (Exception e) {
            errMess(e.toString(), "AuxTextHi: Error in boolean writeStringAppending(String, File)");
            return false;
        }
    }

    /**
     *
     * @param f the file from which a list of lines (Strings) must be read
     * @return the contents of the file as a list of Strings (the lines)
     */
    @Override
    public String[] readList(File f) {
        List<String> list = new LinkedList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(f));
        } catch (Exception e) {
            errMess("Could not open file", "AuxTextHi:readList");
            return null;
        }
        try {
            while (true) {
                list.add(sc.nextLine());
            }
        } catch (Exception e) {
            sc.close();
        }
        return list.toArray(new String[0]);
    }

    /**
     *
     * @param list the list of Strings to be written
     * @param f the while to write the list of Strings to
     * @return true if writing succeeded, false otherwise
     */
    @Override
    public boolean writeList(String[] list, File f) {
        try {
            PrintWriter pw = new PrintWriter(f);
            for (String s : list) {
                pw.println(s);
            }
            pw.close();
            return true;
        } catch (Exception e) {
            errMess(e.toString(), "AuxTextHi: Error in boolean writeList(String[], File)");
            return false;
        }
    }

    /**
     *
     * @param list the list of Strings to be appended
     * @param f the file to append the list of Strings to
     * @return boolean if the list of Strings was appended successfully
     */
    @Override
    public boolean writeListAppending(String[] list, File f) {
        try {
            PrintWriter pw = new PrintWriter(f);
            pw.append("");
            for (String s : list) {
                pw.println(s);
            }
            pw.close();
            return true;
        } catch (Exception e) {
            errMess(e.toString(), "AuxTextHi: Error in boolean writeListAppending(String[], File)");
            return false;
        }
    }

    /**
     *
     * @param numRows the number of rows of the created table
     * @param numColumns the number of columns of the created table
     * @return a String[][] with the given number of rows and columns (all positions are null)
     */
    public String[][] createTable(int numRows, int numColumns) {
        String[][] result = new String[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                result[i][j] = "a[" + i + "][" + j + "]";
            }
        }
        return result;
    }

    /**
     *
     * @param table the table to be written to a file
     * @param delimiter the delimiter to be used when writing the table
     * @param f the file to write the table to
     * @return true if the table was written successfully, false otherwise
     */
    @Override
    public boolean writeDelimitedTable(String[][] table, String delimiter, File f) {
        final String withFinalDelim = "%s" + delimiter;
        final String withFinalLineSep = "%s" + lineSeparator;
        StringBuilder s = new StringBuilder(8192);
        PrintWriter pw = null;
        try {
            int number_of_rows = table.length;
            int number_of_columns = table[0].length;
            int lastCol = number_of_columns - 1;
            pw = new PrintWriter(f);
            for (int i = 0; i < number_of_rows; i++) {
                for (int j = 0; j < number_of_columns - 1; j++) {
                    s.append(table[i][j]).append(delimiter);
                }
                s.append(table[i][lastCol]);
                pw.println(s.toString());
                s.delete(0, s.length());
            }
            pw.close();
            return true;
        } catch (Exception e) {
            errMess(e.toString(), "AuxTextHi: error in writeDelimitedTable(String[][],String,File");
            if (pw != null) {
                pw.close();
            }
            return false;
        }
    }

    /**
     *
     * @param field_length the list of field lengths in this columnar file
     * @param f the columnar text file to read from
     * @return a String[][] each of whose items is a trimmed field in a line
     */
    @Override
    public String[][] readColumnarTable(int[] field_length, File f) {
        StringBuilder temp = null;
        String[] list = null;
        String[][] table = null;
        int number_of_rows = 0, number_of_columns = 0;
        int number_of_fields = 0;
        list = readList(f);
        if (null == list) {
            errMess("Could not read null file", "AuxTextHi:readColumnarTable");
            return null;
        }
        number_of_rows = list.length;
        /* As many rows as lines we have read */
        if (0 == number_of_rows) {
            errMess("0 rows in table", "AuxTextHi:readColumnarTable");
            return null;
        }
        number_of_columns = field_length.length;
        /* This is the number of fields */
        if (0 == number_of_columns) {
            errMess("no field widths given", "AuxTextHi:readColumnarTable");
            return null;
        }
        table = new String[number_of_rows][number_of_columns];
        int field_start = 0;
        temp = new StringBuilder(8192);
        for (int i = 0; i < list.length; i++) {
            field_start = 0;
            temp.append(list[i]);
            for (int j = 0; j < number_of_columns; j++) {
                try {
                    table[i][j] = temp.substring(field_start, field_start + field_length[j]).trim();
                    field_start += field_length[j];
                } catch (Exception e) {
                    errMess("Line " + i + " is too short for field " + j + ". Field left empty.", "AuxTextHi:readColumnarTable");
                    table[i][j] = "";
                }
            }
            temp.delete(0, temp.length());
        }
        return table;
    }

    /**
     *
     * @param table the table of strings to be written with columnar format
     * @param lengths the length of each column
     * @param f the file to write to
     * @return true if the table was successfully written
     */
    @Override
    public boolean writeColumnarTable(String[][] table, int[] lengths, File f) {
        try {
            PrintWriter pw = new PrintWriter(f);
            int number_of_rows = table.length;
            int number_of_columns = table[0].length;
            StringBuilder temp = new StringBuilder(8192);
            String[] format = new String[number_of_columns];
            for (int i = 0; i < number_of_columns; i++) {
                format[i] = "%-" + lengths[i] + "s";
            }
            for (int i = 0; i < number_of_rows; i++) {
                for (int j = 0; j < number_of_columns; j++) {
                    pw.printf(format[j], table[i][j]);
                }
                pw.printf("%s", lineSeparator);
            }
            pw.close();
            return true;
        } catch (Exception e) {
            errMess(e.toString(), "AuxTextHi:writeColumnarTable(String[][],int[],File");
            return false;
        }
    }

    /**
     *
     * @param table the table to be shown in columns on the screen
     * @param lengths the widths of the columns
     * @return true if successfully shown on the screen
     */
    @Override
    public boolean writeColumnarTable(String[][] table, int[] lengths) {
        try {
            PrintWriter pw = new PrintWriter(System.out);
            int number_of_rows = table.length;
            int number_of_columns = table[0].length;
            String[] format = new String[number_of_columns];
            for (int i = 0; i < number_of_columns; i++) {
                format[i] = "%-" + i + "s";
            }
            for (int i = 0; i < number_of_rows; i++) {
                for (int j = 0; j < number_of_columns; j++) {
                    pw.printf(format[j], table[i][j]);
                }
                pw.printf("%s", lineSeparator);
            }
            pw.close();
            return true;
        } catch (Exception e) {
            errMess(e.toString(), "AuxTextHi:writeColumnsaTable(String[][],int[]");
            return false;
        }
    }

    /**
     *
     * @param delimiter the delimiter between fields
     * @param f the file to read from
     * @return a String[][] each of whose positions is a field in a line of the file
     */
    @Override
    public String[][] readDelimitedTable(String delimiter, File f) {
        int numRows, numCols;
        String[] rowOfFields;
        String[] temp = readList(f);
        numRows = temp.length;
        rowOfFields = temp[0].split(delimiter);
        numCols = rowOfFields.length;
        String[][] table = new String[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            table[i] = temp[i].split(delimiter);
        }
        return table;
    }
}
