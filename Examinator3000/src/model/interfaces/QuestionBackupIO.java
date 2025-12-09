package model.interfaces;

import java.util.List;

public interface QuestionBackupIO {

    public void exportQuestions(List<Question> questionList) throws QuestionBackupIOException {


    }

    public List<Question> impQuestions() throws QuestionBackupIOException {

        return questionList;
    }

    public String getBackupIODescription() throws QuestionBackupIOException {

        return description;
    }
}
