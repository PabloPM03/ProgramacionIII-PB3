package com.coti.textfiletools;

import java.io.File;
import java.nio.charset.Charset;

/**
 *
 * @author bruegel
 */
public interface TextUtils2 {

    public static final String lineSeparator = System.getProperty("line.separator");
    public static final String fileSeparator = File.separator;

    /**
     * This method reads a whole file as one string, using the given charset.
     *
     * @param f this is the file to be read
     * @param usingThisEncoding this is the Charset to be used
     * @return one string with the contents of the whole file
     */
    public String createStringWithContentsOfFile(File f, Charset usingThisEncoding);

    /**
     * This method writes a string to a file, appending or not, and with the
     * given Charset.
     *
     * @param str this is the string that will be written to a file
     * @param f this is the file to be written to
     * @param encoding the string will be written with this Charset
     * @param appending do we have to add the string at the end, or just
     * truncate?
     * @return true if all went well, false otherwise
     */
    public boolean writeToFile(String str, File f, Charset encoding, boolean appending);

    /**
     * This method writes a String[] to a file, line by line, with the given
     * charset and adding the list of strings at the end or not as instructed.
     *
     * @param list this is the String[] that will be written to the file
     * @param f this is the file that will be written to
     * @param encoding the Charset to be used when writing
     * @param appending this tells us whether to add lines to the end of the
     * file, if it exists, or just truncate the file and write the lines.
     * @return true if all went well, false otherwise
     */
    public boolean writeList(String[] list, File f, Charset encoding, boolean appending);

    /**
     * This method reads all of the lines in a file, using the given Charset.
     *
     * @param f this is the file that will be written to
     * @param encoding the charset to be used when reading
     * @return true if all went well, false otherwise
     */
    public String[] readTextFileAsListOfStrings(File f, Charset encoding);

    /**
     * This method writes a table of strings in delimited format.
     *
     * @param table the table to be written
     * @param delimiter the separator between elements
     * @param f the file to be written to
     * @param encoding the charset to be used
     * @return true if all went well, false otherwise
     */
    public boolean writeDelimitedTable(String[][] table, String delimiter, File f, Charset encoding);

    /**
     * This method reads from disk a table written in delimited format.
     *
     * @param delimiter this is the separator between fields
     * @param f this is the file to be read from
     * @param encoding this is the Charset to be used
     * @return the table of strings we just read
     */
    public String[][] readDelimitedTable(String delimiter, File f, Charset encoding);

    /**
     * This method writes a table to disk using a columnar format
     *
     * @param table this is the table to be written to disk
     * @param lengths these are the widths of the columns. Thus the i-th column
     * has a width of lengths[i] chars.
     * @param f this is the file to be written to
     * @param encoding this is the Charset to be used when writing
     * @return the table of strings we just read
     */
    public boolean writeColumnarTable(String[][] table, int[] lengths, File f, Charset encoding);

    /**
     * This method reads a table of Strings from a disk file; said file is in
     * columnar format.
     *
     * @param field_length these are the widths of the columns one must use when
     * writing. Thus the i-th field has a width of field_length[i] columns.
     * @param f this is the file to be written to
     * @param encoding this is the Charset to be used
     * @return the table of strings we just read
     */
    public String[][] readColumnarTable(int[] field_length, File f, Charset encoding);

    /**
     * This method writes a table to the terminal,using a columnar format with
     * widths given by field_length.
     *
     * @param table this is the table to be written.
     * @param lengths these are the widths of the columns
     * @return true if all goes well, false otherwise
     */
    public boolean writeColumnarTable(String[][] table, int[] lengths);

}
