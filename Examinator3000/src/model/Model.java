package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import model.data.IRepository;
import model.data.JSONQuestionBackupIO;
import model.data.QuestionBackupIO;
import model.exceptions.IRepositoryException;
import model.exceptions.QuestionBackupIOException;
import model.generators.QuestionCreator;
import model.pojos.Exam;
import model.pojos.Question;

public class Model {

    private IRepository repository;
    private QuestionBackupIO backupHandler;
    private ArrayList<QuestionCreator> questionCreators;

    public Model(IRepository repository) {
        this.repository = repository;
        this.backupHandler = new JSONQuestionBackupIO();
        this.questionCreators = new ArrayList<>();
    }




        //Delegaciones a IRepository
    public void addQuestion(Question q) throws IRepositoryException { repository.addQuestion(q); }
    public ArrayList<Question> getAllQuestions() throws IRepositoryException { return repository.getAllQuestions(); }
    public void dataSave() throws IRepositoryException { repository.dataSave(); }




        //Metodos JSON
    public void exportQuestionsToJSON(String fileName) throws IRepositoryException, QuestionBackupIOException {

        ArrayList<Question> questions = repository.getAllQuestions();
        backupHandler.exportQuestions(questions, fileName);
    }

    public void importQuestionsFromJSON(String fileName) throws QuestionBackupIOException, IRepositoryException {

        ArrayList<Question> importedQuestions = backupHandler.importQuestions(fileName);
        ArrayList<Question> currentQuestions = repository.getAllQuestions();

        ArrayList<UUID> existingUUIDs = new ArrayList<>();
        for (Question q : currentQuestions) {
            existingUUIDs.add(q.getId());
        }
        for (Question q : importedQuestions) {
            if (!existingUUIDs.contains(q.getId())) {
                repository.addQuestion(q);
            }
        }
        repository.dataSave();
    }



        //Metodos Modo Examen
    public ArrayList<String> getTopics() throws IRepositoryException {

        ArrayList<Question> questions = repository.getAllQuestions();
        HashSet<String> topicsHashSet = new HashSet<>();

        for (Question q : questions) {
            topicsHashSet.addAll(q.getTopics());
        }

        ArrayList<String> sortedTopics = new ArrayList<>(topicsHashSet);
        Collections.sort(sortedTopics);
        sortedTopics.add(0,"TODOS LOS TEMAS");

        return sortedTopics;
    }

    public int countQuestionByTopic(String topic) throws IRepositoryException {

        ArrayList<Question> questions = repository.getAllQuestions();
        if (topic.equalsIgnoreCase("TODOS LOS TEMAS")) return questions.size();

        int i = 0;
        for (Question q : questions) {
            if (q.getTopics().contains(topic)) i++;
        }

        return i;
    }

    public Exam createExam(String topic, int amount) throws IRepositoryException {

        ArrayList<Question> questions = repository.getAllQuestions();
        ArrayList<Question> filteredQuestions = new ArrayList<>();

        if (topic.equalsIgnoreCase("TODOS LOS TEMAS")) filteredQuestions.addAll(questions);
        else {
            for (Question q : questions) {
                if (q.getTopics().contains(topic)) filteredQuestions.add(q);
            }
        }

        Collections.shuffle(filteredQuestions);

        if (amount > filteredQuestions.size()) amount = filteredQuestions.size();
        if (amount < 1) amount = 1;

        ArrayList<Question> examQuestions = new ArrayList<>(filteredQuestions.subList(0, amount));

        return new Exam(examQuestions);
    }



        //Métodos de Gemini
    public void addGenerator(QuestionCreator questionCreator) {
        this.questionCreators.add(questionCreator);
    }

    public ArrayList<QuestionCreator> getQuestionCreators() {
        return questionCreators;
    }
}
