package model.data;

import model.exceptions.QuestionBackupIOException;
import model.pojos.Question;

import java.util.ArrayList;

public interface QuestionBackupIO {

    void exportQuestions(ArrayList<Question> questions, String fileName) throws QuestionBackupIOException;
    ArrayList<Question> importQuestions(String fileName) throws QuestionBackupIOException;

}
