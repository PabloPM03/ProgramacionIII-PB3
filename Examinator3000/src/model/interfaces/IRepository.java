package model.interfaces;

import model.Question;

import java.util.List;

public interface IRepository {

    public Question addQuestion(Question q) throws IRepositoryException {

        return q;
    }

    public void removeQuestion(Question q) throws IRepositoryException {

    }

    public Question modifyQuestion(Question q) throws IRepositoryException {

        return q;
    }

    public List<Question> getAllQuestions() throws IRepositoryException {

        return questionList
    }
}
