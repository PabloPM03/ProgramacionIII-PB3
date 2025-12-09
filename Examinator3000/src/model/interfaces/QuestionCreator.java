package model.interfaces;

import model.Question;

public interface QuestionCreator {

    Question createQuestion(String topic) throws GeminiQuestionCreatorException {
        
        return question;
    }

    String getQuestionCreatorDescription() {

        return creator;
    }
}
