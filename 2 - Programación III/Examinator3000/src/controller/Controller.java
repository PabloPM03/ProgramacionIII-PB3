package controller;

import java.util.ArrayList;

import model.Model;
import model.exceptions.IRepositoryException;
import model.exceptions.QuestionBackupIOException;
import model.exceptions.QuestionCreatorException;
import model.generators.QuestionCreator;
import model.pojos.Exam;
import model.pojos.Question;
import model.pojos.Option;
import view.BaseView;

public class Controller {

    private Model model;
    private BaseView view;

    public Controller(Model model, BaseView view) {

        this.view = view;
        this.model = model;
    }



        //Delegaciones InteractiveView
    public void start() { view.init(); }
    public void end() {
        try {
            model.dataSave();
            view.showMessage("Datos guardados correctamente en el fichero questions.bin.");
        } catch (IRepositoryException e) {
            view.showErrorMessage("No se pudieron guardar los datos en el fichero questions.bin. " + e.getMessage());
        }
        view.end();
    }

        //Métodos IRepository
    public void newQuestion(Question q) {
        try {
            model.addQuestion(q);
            view.showMessage("Pregunta añadida correctamente");
        }
        catch (IRepositoryException e){
            view.showErrorMessage("model.addQuestion -> No se pudo añadir la pregunta: " + e.getMessage());
        }
    }   

    public ArrayList<Question> getAllQuestions() {
        try {
            return model.getAllQuestions();
        }
        catch (IRepositoryException e) {
            view.showErrorMessage("model.getAllQuestions -> No se pudo obtener la lista de preguntas: " + e.getMessage());
            return null;
        }
    }


        //Métodos JSON
    public void exportQuestionsToJSON(String fileName) {
        try {
            model.exportQuestionsToJSON(fileName);
            view.showMessage("Preguntas exportadas correctamente a " + fileName);
        } catch (QuestionBackupIOException | IRepositoryException e) {
            view.showErrorMessage("Error al exportar del archivo " + fileName + ": " + e.getMessage());
        }
    }

    public void importQuestionsFromJSON(String fileName) {
        try {
            model.importQuestionsFromJSON(fileName);
            view.showMessage("Preguntas del fichero " + fileName + " importadas correctamente.");
        } catch (QuestionBackupIOException | IRepositoryException e) {
            view.showErrorMessage("Error al importar del archivo " + fileName + ": " + e.getMessage());
        }
    }


        //Métodos Modo Exámen
    public void examMode() {

        try {
            ArrayList<String> topics = model.getTopics();
            if (topics.size() <= 1) { 
                view.showErrorMessage("No hay preguntas, por tanto no hay temas disponibles. Crea alguna pregunta"); 
                return; 
            }

            String selectedTopic = view.selectTopic(topics);

            int availableQuestionsNumber = model.countQuestionByTopic(selectedTopic);
            if (availableQuestionsNumber == 0) {
                view.showErrorMessage("No hay preguntas disponibles para el tema seleccionado"); 
                return;
            }

            int amount = view.askNumberOfCuestions(availableQuestionsNumber);
            Exam exam = model.createExam(selectedTopic, amount);
            exam.startExam();

            ArrayList<Question> questions = exam.getQuestions();
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);

                int answer = view.askExamQuestion(q, (i+1), questions.size());

                if (answer == -1) {
                    exam.addSkippedAnswers();
                    view.showMessage("Pregunta saltada");
                } else {
                    Option selectedOption = q.getOptions().get(answer);

                    if (selectedOption.getIsCorrect()) {
                        exam.addCorrectAnswers();
                        view.showAnswerResult(true, selectedOption.getRationale());
                    } else {
                        exam.addIncorrectAnswers();
                        view.showAnswerResult(false, selectedOption.getRationale());
                    }
                }
            }

            exam.endExam();
            view.showExamResults(exam);

        } catch (IRepositoryException e) {
            view.showErrorMessage("Error iniciando el examen: " + e.getMessage());
        }
    }


        //Métodos Gemini
    public void generateQuestion() throws IRepositoryException {

        ArrayList<QuestionCreator> questionCreators = model.getQuestionCreators();

        if (questionCreators.isEmpty()) {
            view.showErrorMessage("No hay generadores de IA activos. Reinicia la aplicación");
            return;
        }

        String topic = view.askForTopic();
        if (topic.isBlank()) return;

        QuestionCreator creator = questionCreators.get(0);
        
        try {
            
            Question q = creator.createQuestion(topic);

            boolean save = view.showConfirmQuestions(q);
            if (save) {
                model.addQuestion(q);
                view.showMessage("Pregunta añadida correctamente.");
            } else view.showMessage("Pregunta descartada");

        } catch (QuestionCreatorException e) {
            view.showErrorMessage("Fallo en la generación de pregunta con IA");
        }
        
    }
}