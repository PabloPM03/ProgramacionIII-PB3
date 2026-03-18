package model.generators;

import model.pojos.Question;
import model.exceptions.QuestionCreatorException;

public interface QuestionCreator {

    String getModelId();
    Question createQuestion(String topic) throws QuestionCreatorException;
}
