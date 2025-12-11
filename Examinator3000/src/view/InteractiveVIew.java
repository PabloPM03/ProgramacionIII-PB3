package view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import model.exceptions.IRepositoryException;
import model.pojos.Exam;
import model.pojos.Option;
import model.pojos.Question;

public class InteractiveVIew extends BaseView {

    private Scanner scan;

    public InteractiveVIew() {
        this.scan = new Scanner(System.in);
    }

        //Métodos con Pantallas de menú
    @Override
    public void init() {

        boolean exit = false;

        while (!exit) {
            System.out.printf("\n\n\n|------------------------------------------------|");
            System.out.printf("\n|          BIENVENIDO A EXAMINATOR 3000          |");
            System.out.printf("\n|------------------------------------------------|");
            System.out.printf("\n| 1 -> CRUD Preguntas                            |");
            System.out.printf("\n| 2 -> Modo EXAMEN                               |");
            System.out.printf("\n| 3 -> Salir                                     |");
            System.out.printf("\n|------------------------------------------------|");
            System.out.printf("\n  > ");
            String option = scan.nextLine();

            switch (option) {
                case "1":
                    try {
                        menuCud();
                    } catch (IRepositoryException e) {
                        showErrorMessage("No se pudo generar una pregunta con IA" + e.getMessage());
                    }
                    break;
                case "2":
                    controller.examMode();
                    break;
                case "3":
                    exit = true;
                    controller.end();
                    break;
                default:
                    showErrorMessage("startMenu -> Opción introducida no válida");
                    break;
            }

        }
    }

    public void menuCud() throws IRepositoryException {

        try {
            System.out.printf("\n\n\n|------------------------------------------------|");
            System.out.printf("\n|         GESTIÓN DEL BANCO DE PREGUNTAS         |");
            System.out.printf("\n|------------------------------------------------|");
            System.out.printf("\n| 1 -> Crear Pregunta                            |");
            System.out.printf("\n| 2 -> Listar Preguntas                          |");
            System.out.printf("\n| 3 -> Importar preguntas del archivo JSON       |");
            System.out.printf("\n| 4 -> Exportar preguntas a archivo JSON         |");
            System.out.printf("\n| 5 -> Generar pregunta con Gemini               |");
            System.out.printf("\n| 6 -> Salir                                     |");
            System.out.printf("\n|------------------------------------------------|");
            System.out.printf("\n  > ");
            String option = scan.nextLine();

            switch (option) {
                case "1": newQuestion(); break;
                case "2": listQuestions(); break;
                case "3": importQuestionsFromJSON(); break;
                case "4": exportQuestionsToJSON(); break;
                case "5": controller.generateQuestion(); break;
                case "6": /*POR AQUI EL PROGRAMA VUELVE AL MENU INICIAL*/ break;
                default: showErrorMessage("menuCrud -> Opción introducida no válida"); break;
        }
            
        } catch (IRepositoryException e) {
        }
    }
       

        //Métodos de IRepository
    private void newQuestion() {
        try {
            System.out.println("Introduzca los siguientes campos de la nueva pregunta:");
            System.out.println("Enunciado > ");
            String statement = scan.nextLine();

            System.out.println("Temas (separados mediante comas)");
            String[] topicsArray = scan.nextLine().split(",");
            HashSet<String> topicList = new HashSet<>();
            for (String t : topicsArray) {
                if (!t.trim().isEmpty()) topicList.add(t.trim());
            }
            
            ArrayList<Option> optionList = new ArrayList<>();
            boolean hasCorrect = false;

            for (int i = 1; i <= 4; i++) {
                System.out.println("\nOpcion " + i);
                System.out.println("Texto > ");
                String text = scan.nextLine();
                System.out.println("Justificación > ");
                String rationale = scan.nextLine();

                boolean isCorrect = false;
                boolean secureInput = false;
                if (!hasCorrect) {
                    while (!secureInput) {
                        System.out.println("¿Esta opción es la correcta? (s/n)");
                        String letraOpcion = scan.nextLine();

                        switch (letraOpcion) {
                            case "s": case "S": secureInput = true; hasCorrect = true; isCorrect = true; break;
                            case "n": case "N":secureInput = true; isCorrect = false;  break;
                            default: showErrorMessage("newQuestion -> Opción incorrecta"); break;
                        }
                    }
                }
                else System.out.println("Opción marcada automáticamente como falsa (Una de las opciones anteriores es la correcta)");

                optionList.add(new Option(text, rationale, isCorrect));
            }

            if (!hasCorrect) {
                showErrorMessage("newQuestion -> Debe marcar una opcion correcta. NUEVA PREGUNTA NO GUARDADA");
                return;
            }
            else {
                Question q = new Question("Humano", topicList, statement, optionList);
                controller.newQuestion(q);
            }
        }
        catch (Exception e) {
            showErrorMessage("newQuestion -> Error al crear pregunta (" + e.getMessage() + ")");
        }
    }

    private void listQuestions() {

        ArrayList<Question> questions = controller.getAllQuestions();
        if (questions.isEmpty()) showMessage("El banco de preguntas está vacío");
        else {
            System.out.println("\n\n\n");
            for (Question question : questions) {
                System.out.println("|--------------------------------------------------------------------------------------------------|");
                System.out.println(question);
            }
            System.out.println("|--------------------------------------------------------------------------------------------------|");
        }
    }


        //Métodos de JSON
    private void importQuestionsFromJSON() {
        System.out.println("Introduzca el nombre del fichero JSON que quiere importar (backup / backup.json)");
        String fileName = scan.nextLine();
        controller.importQuestionsFromJSON(fileName);
    }

    private void exportQuestionsToJSON() {
        System.out.println("Introduzca el nombre del fichero JSON que quiere exportar (backup / backup.json)");
        String fileName = scan.nextLine();
        controller.exportQuestionsToJSON(fileName);
    }


        //Métodos Modo Examen
    public String selectTopic(ArrayList<String> topics) {

        System.out.printf("\n|------------------------------------------------|");
        System.out.printf("\n|        SELECCION DE TEMA PARA EL EXÁMEN        |");
        System.out.printf("\n|------------------------------------------------|");
        for (int i = 0; i < topics.size(); i++) {
            System.out.printf("\n| %d -> %-41s |", i+1, topics.get(i));
        }
        System.out.printf("\n|------------------------------------------------|");
        
        int selection = -1;
        while (selection < 1 || selection > topics.size()) {
            System.out.printf("\nElija un tema (número) > ");

            try {
                selection = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                showErrorMessage("Introduzca un número válido");
            }
        }
        return topics.get(selection - 1);
    }

    public int askNumberOfCuestions(int availableQuestionsNumber) {

        System.out.println("De este tema hay " + availableQuestionsNumber + "preguntas disponibles.");

        int questionNumber = -1;
        while (questionNumber < 1 || questionNumber > availableQuestionsNumber) {

            System.out.println("¿De cuantas preguntas quieres examinarte? > ");
            try {
                questionNumber = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                showErrorMessage("Introduzca un número");
            }
        }
        return questionNumber;
    }

    public int askExamQuestion(Question q, int currentQuestion, int totalQuestions) {

        System.out.printf("\n|-------------------------------------------------------------------|");
        System.out.printf("\n| PREGUNTA -> %d de %-48d |", currentQuestion, totalQuestions);
        System.out.printf("\n|-------------------------------------------------------------------|");

        ArrayList<Option> options = q.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("\n| %d -> %-60s |", (i+1), options.get(i).getText());
        }
        System.out.printf("\n| S -> %-60s |", "SALTAR PREGUNTA");
        System.out.printf("\n|-------------------------------------------------------------------|");

        while (true) {

            System.out.printf("\nIntroduzca una respuesta > ");
            String input = scan.nextLine();

            if (input.equals("s") || input.equals("S")) return -1;

            try {
                int answer = Integer.parseInt(input) -1;
                if (answer >= 0 && answer < options.size()) return answer;
            } catch (NumberFormatException e) {
                showErrorMessage("Introduzca una opción válida");
            }
        }
    }

    public void showAnswerResult(boolean isCorrect, String rationale) {

        if (isCorrect) System.out.println("¡RESPUESTA CORRECTA!");
        else System.out.println("¡RESPUESTA INCORRECTA!");

        System.out.println("Explicación -> " + rationale);

        System.out.println("Presione ENTER para continuar con el exámen > ");
        scan.nextLine();
    }

    public void showExamResults(Exam exam) {
        
        System.out.printf("\n|-----------------------------|");
        System.out.printf("\n|    RESULTADOS DEL EXAMEN    |");
        System.out.printf("\n|-----------------------------|");
        System.out.printf("\n| NOTA FINAL -> %13.2f |", exam.getGrade());
        System.out.printf("\n|-----------------------------|");
        System.out.printf("\n| Tiempo empleado -> %8s |", exam.getDuration());
        System.out.printf("\n| Aciertos -> %15s |", exam.getCorrectAnswers());
        System.out.printf("\n| Fallos -> %17s |", exam.getIncorrectAnswers());
        System.out.printf("\n| Sin Responder -> %10s |", exam.getSkippedAnswers());
        System.out.printf("\n|-----------------------------|");
    }


        //Métodos Gemini
    @Override
    public String askForTopic() {
        System.out.println("\nIntroduzca un tema para recibir una pregunta generada mediante IA");
        return scan.nextLine().trim();
    }

    @Override
    public boolean showConfirmQuestions(Question q) {
        System.out.println("--------------------PREGUNTA GENERADA--------------------");
        System.out.println(q.toString());
        System.out.println("---------------------------------------------------------");

        while (true) {
            System.out.print("Deseas guardar esta pregunta? (s/n) > ");
            String input = scan.nextLine().trim().toLowerCase();
            if (input.equals("s")) return true;
            if (input.equals("n")) return false;
        }
    }




        //Override de Métodos de BaseView
    @Override
    public void showMessage(String msg) { System.out.println("[ INFO ] " + msg); }

    @Override
    public void showErrorMessage(String msg) { System.out.println("[ ERROR ] " + msg); }

    @Override
    public void end() { System.out.println("Saliendo de la aplicación... ¡Hasta la próxima!"); }
}
