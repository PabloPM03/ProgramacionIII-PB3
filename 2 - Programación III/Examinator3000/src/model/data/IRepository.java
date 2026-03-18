package model.data;

import model.exceptions.IRepositoryException;
import model.pojos.Question;

import java.util.ArrayList;

public interface IRepository {

    public Question addQuestion(Question q) throws IRepositoryException;
    public void removeQuestion(Question q) throws IRepositoryException;
    public Question modifyQuestion(Question q) throws IRepositoryException;
    public ArrayList<Question> getAllQuestions() throws IRepositoryException;

    void dataSave() throws IRepositoryException;
}
