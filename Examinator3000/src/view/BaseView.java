package view;

import java.util.ArrayList;

import controller.Controller;
import model.pojos.Exam;
import model.pojos.Question;

public abstract class BaseView {

    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public abstract void init();  //Inicia la Vista y desencadena la lógica de la vista
    public abstract void showMessage(String msg);    //Permite notificar de un mensaje al usuario
    public abstract void showErrorMessage(String msg);    //Permite notificar de un mensaje de error al usuario
    public abstract void end();    //Finaliza la vista ordenadamente

    public abstract String selectTopic(ArrayList<String> topics);
    public abstract int askNumberOfCuestions(int availableQuestionsNumber);
    public abstract int askExamQuestion(Question q, int currentQuestion, int totalQuestions);
    public abstract void showAnswerResult(boolean isCorrect, String rationale);
    public abstract void showExamResults(Exam exam);

    public abstract String askForTopic();
    public abstract boolean showConfirmQuestions(Question q);
}
