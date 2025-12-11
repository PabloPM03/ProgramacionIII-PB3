package model.exceptions;

public class QuestionBackupIOException extends Exception{

    public QuestionBackupIOException (String message) {
        super(message);
    }
    public QuestionBackupIOException (String message, Throwable cause) {
        super(message, cause);
    }

}
