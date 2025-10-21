package com.coti.tools;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class <b>Rutas</b> creates instances of Path or File that point to the
 * Desktop or Documents<br> folders, as well as to objects in these folders, and
 * even to objects in folders<br> on Desktop or in Documents.
 *
 * Extensively updated on Dec 13th 2020 Tief verbessert am 13. 12. 2020
 *
 * @author coti
 */
public class Rutas {

    private final static String HOME = System.getProperty("user.home");
    private final static FileSystem FGSD = FileSystems.getDefault();
    private final static Path PATH_HOME = FGSD.getPath(HOME);

    /**
     * This method provides de Path of the Home directory
     * 
     * @return the Path of the home directory of the user
     */
    public static Path pathToHome() {
        return FGSD.getPath(HOME);
    }

    /**
     * This method provides the Path of the Home directory
     * 
     * @return the Path of the home directory of the user, when OneDrive is active
     */
    public static Path pathToHomeOD() {
        return Paths.get(HOME, "OneDrive");
    }

    /**
     * This method returns the Path to the Desktop for any platform
     *
     * @return a Path as described
     */
    public static Path pathToDesktop() {
        var p = Paths.get(HOME, "Desktop");
        return p;
    }

    /**
     * This constructor returns the Path to the Desktop for any platform
     *
     * @return a Path as described
     */
    public static Path pathToDesktopOD() {
        var p = Paths.get(HOME, "OneDrive", "Desktop");
        return  p;
    }

    /**
     * This constructor returns the File to the Desktop for any platform
     *
     * @return a File as described
     */
    public static File fileToDesktop() {
        return pathToDesktop().toFile();
    }

    /**
     *This method provides a File to the Desktop
     * 
     * @return the file to the Desktop when OneDrive is active
     */
    public static File fileToDesktopOD() {
        return pathToDesktopOD().toFile();
    }

    /**
     * This method returns a path to a folder on Desktop for any platform
     *
     * @param nameOfFolder the name of the folder whose path on the Desktop is
     * needed
     * @return a Path as described
     */
    public static Path pathToFolderOnDesktop(String nameOfFolder) {
        return FGSD.getPath(Rutas.pathToDesktop().toString(), nameOfFolder);
    } // End of pathToFolderOnDesktop()

    /**
     *
     * @param nameOfFolder
     * @return
     */
    public static Path pathToFolderOnDesktopOD(String nameOfFolder) {
        return FGSD.getPath(Rutas.pathToDesktop().toString(), "OneDrive", nameOfFolder);
    } // End of pathToFolderOnDesktop()

    /**
     * This method returns a File to a folder on Desktop for any platform
     *
     * @param nameOfFolder the name of the folder whose path on the Desktop is
     * needed
     * @return a File as described
     */
    public static File fileToFolderOnDesktop(String nameOfFolder) {
        return pathToFolderOnDesktop(nameOfFolder).toFile();
    } // End of pathToFolderOnDesktop()

    /**
     * This method returns a File to a Folder placed on the Deskotp
     * 
     * @param nameOfFolder
     * @return a File that points to a Folder on the Desktop,when OneDrive is active
     */
    public static File fileToFolderOnDesktopOD(String nameOfFolder) {
        return pathToFolderOnDesktopOD(nameOfFolder).toFile();
    } // End of pathToFolderOnDesktop()

    /**
     * This method gives back the Path to a file on the Desktop
     *
     * @param nameOfFile the name of the file on the desktop whose path is
     * wanted
     * @return a Path as described
     */
    public static Path pathToFileOnDesktop(String nameOfFile) {
        return FGSD.getPath(Rutas.pathToDesktop().toString(), nameOfFile);
    } // End of pathToFileOnDesktop()

    /**
     *
     * @param nameOfFile
     * @return
     */
    public static Path pathToFileOnDesktopOD(String nameOfFile) {
        return FGSD.getPath(Rutas.pathToDesktopOD().toString(), nameOfFile);
    } // End of pathToFileOnDesktop()

    /**
     * This method gives back the File to a file on the Desktop
     *
     * @param nameOfFile the name of the file on the desktop whose path is
     * wanted
     * @return a File as described
     */
    public static File fileToFileOnDesktop(String nameOfFile) {
        return pathToFileOnDesktop(nameOfFile).toFile();
    } // End of fileToFileOnDesktop()

    /**
     * This method returns a File to a file on the Desktop
     * 
     * @param nameOfFile wanted on the Desktop
     * @return a File that points to the nameOfFile,placed on the Desktop when OneDrive is active
     */
    public static File fileToFileOnDesktopOD(String nameOfFile) {
        return pathToFileOnDesktopOD(nameOfFile).toFile();
    } // End of fileToFileOnDesktop()

    /**
     * This method gives back the Path to a file in a folder on the Desktop
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder on the
     * Desktop
     * @return a Path as described
     */
    public static Path pathToFileInFolderOnDesktop(String nameOfFolder, String nameOfFile) {
        return FGSD.getPath(Rutas.pathToFolderOnDesktop(nameOfFolder).toString(), nameOfFile);
    } // En of pathToFileInFolderOnDesktop()

    /**
     * This method gives back the Path to a file in a folder on the Desktop
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder on the
     * Desktop
     * @return a Path as described
     */
    public static Path pathToFileInFolderOnDesktopOD(String nameOfFolder, String nameOfFile) {
        return FGSD.getPath(Rutas.pathToFolderOnDesktopOD(nameOfFolder).toString(), nameOfFile);
    } // En of pathToFileInFolderOnDesktop()

    /**
     * This method gives back the File to a file in a folder on the Desktop
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder on the
     * Desktop
     * @return a File as described
     */
    public static File fileToFileInFolderOnDesktop(String nameOfFolder, String nameOfFile) {
        return pathToFileInFolderOnDesktop(nameOfFolder, nameOfFile).toFile();
    } // En of pathToFileInFolderOnDesktop()

    /**
     * This method gives back the File to a file in a folder on the Desktop
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder on the
     * Desktop
     * @return a File as described
     */
    public static File fileToFileInFolderOnDesktopOD(String nameOfFolder, String nameOfFile) {
        return pathToFileInFolderOnDesktopOD(nameOfFolder, nameOfFile).toFile();
    } // En of pathToFileInFolderOnDesktop()

    /**
     * This method returns the path to Documents
     *
     * @return a Path as described
     */
    public static Path pathToDocuments() {
        Path p = Paths.get(HOME, "Documents");
        return p.toFile().exists() ? p : Paths.get(HOME, "Documentos");
    } // End of pathToDocuments()

    /**
     * This method returns the path to Documents when using OneDdrive
     *
     * @return a Path as described
     */
    public static Path pathToDocumentsOD() {
        Path p = Paths.get(HOME, "OneDrive", "Documents");
        return p.toFile().exists() ? p : Paths.get(HOME, "OneDrive", "Documentos");
    } // End of pathToDocuments()

    /**
     * This method returns the File to Documents
     *
     * @return a File as described
     */
    public static File fileToDocuments() {
        return pathToDocuments().toFile();
    } // End of fileToDocuments()

    /**
     * This method returns the File to Documents for any platform
     *
     * @return a File as described
     */
    public static File fileToDocumentsOD() {
        return pathToDocumentsOD().toFile();
    } // End of fileToDocuments()

    /**
     * This method returns a path to a folder in Documents for any platform
     *
     * @param nameOfFolder the name of a folder in Documents whose path is
     * wanted
     * @return a Path as described
     */
    public static Path pathToFolderInDocuments(String nameOfFolder) {
        return FGSD.getPath(Rutas.pathToDocuments().toString(), nameOfFolder);
    } // End of pathToFolderInDocuments()

    /**
     * This method returns a path to a folder in Documents for any platform
     *
     * @param nameOfFolder the name of a folder in Documents whose path is
     * wanted
     * @return a Path as described
     */
    public static Path pathToFolderInDocumentsOD(String nameOfFolder) {
        return FGSD.getPath(Rutas.pathToDocumentsOD().toString(), nameOfFolder);
    } // End of pathToFolderInDocuments()

    /**
     * This method returns a file to a folder in Documents for any platform
     *
     * @param nameOfFolder the name of a folder in Documents whose path is
     * wanted
     * @return a File as described
     */
    public static File fileToFolderInDocuments(String nameOfFolder) {
        return pathToFolderInDocuments(nameOfFolder).toFile();
    } // End of pathToFolderInDocuments()

    /**
     * This method returns a file to a folder in Documents for any platform
     *
     * @param nameOfFolder the name of a folder in Documents whose path is
     * wanted
     * @return a File as described
     */
    public static File fileToFolderInDocumentsOD(String nameOfFolder) {
        return pathToFolderInDocumentsOD(nameOfFolder).toFile();
    } // End of pathToFolderInDocuments()

    /**
     * This method gives back the Path to a file in Documents
     *
     * @param nameOfFile the name of a file in Documents whose path is wanted
     * @return a Path as described
     */
    public static Path pathToFileInDocuments(String nameOfFile) {
        return FGSD.getPath(Rutas.pathToDocuments().toString(), nameOfFile);
    } // End of pathToFileInDocuments()

    /**
     * This method gives back the Path to a file in Documents
     *
     * @param nameOfFile the name of a file in Documents whose path is wanted
     * @return a Path as described
     */
    public static Path pathToFileInDocumentsOD(String nameOfFile) {
        return FGSD.getPath(Rutas.pathToDocumentsOD().toString(), nameOfFile);
    } // End of pathToFileInDocuments()

    /**
     * This method gives back the File to a file in Documents
     *
     * @param nameOfFile the name of a file in Documents whose path is wanted
     * @return a File as described
     */
    public static File fileToFileInDocuments(String nameOfFile) {
        return pathToFileInDocuments(nameOfFile).toFile();
    } // End of pathToFileInDocuments()

    /**
     * This method gives back the File to a file in Documents
     *
     * @param nameOfFile the name of a file in Documents whose path is wanted
     * @return a File as described
     */
    public static File fileToFileInDocumentsOD(String nameOfFile) {
        return pathToFileInDocumentsOD(nameOfFile).toFile();
    } // End of pathToFileInDocuments()

    /**
     * This method gives back the Path to a file in a folder in Documents
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder on the
     * Desktop
     * @return a Path as described
     */
    public static Path pathToFileInFolderInDocuments(String nameOfFolder, String nameOfFile) {
        return FGSD.getPath(Rutas.pathToFolderInDocuments(nameOfFolder).toString(), nameOfFile);
    } // En of pathToFileInFolderInDocuments()

    /**
     * This method gives back the Path to a file in a folder in Documents
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder on the
     * Desktop
     * @return a Path as described
     */
    public static Path pathToFileInFolderInDocumentsOD(String nameOfFolder, String nameOfFile) {
        return FGSD.getPath(Rutas.pathToFolderInDocumentsOD(nameOfFolder).toString(), nameOfFile);
    } // En of pathToFileInFolderInDocuments()

    /**
     * This method gives back the File to a file in a folder in Documents
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder in folder
     * Documents
     * @return a File as described
     */
    public static File fileToFileInFolderInDocuments(String nameOfFolder, String nameOfFile) {
        return pathToFileInFolderInDocuments(nameOfFolder, nameOfFile).toFile();
    } // En of pathToFileInFolderInDocuments()

    /**
     * This method gives back the File to a file in a folder in Documents
     *
     * @param nameOfFolder - the name of the folder in which the file is placed
     * @param nameOfFile - the name of the file placed inside a folder in folder
     * Documents
     * @return a File as described
     */
    public static File fileToFileInFolderInDocumentsOD(String nameOfFolder, String nameOfFile) {
        return pathToFileInFolderInDocumentsOD(nameOfFolder, nameOfFile).toFile();
    } // En of pathToFileInFolderInDocuments()

}
