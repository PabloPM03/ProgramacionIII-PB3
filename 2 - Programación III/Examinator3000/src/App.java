import controller.Controller;
import model.Model;
import model.data.BinaryRepository;
import model.data.IRepository;
import model.generators.GeminiQuestionCreator;
import view.BaseView;
import view.InteractiveVIew;

public class App {
    public static void main(String[] args) throws Exception {

        IRepository repository = new BinaryRepository();

        Model model = new Model(repository);
        BaseView view = new InteractiveVIew();

        if (args.length >= 3) {
            if (args[0].equalsIgnoreCase("-question-creator")) {
                String modelId = args[1];
                String apiKey = args[2];

                System.out.println("[SISTEMA] Activando el generador de preguntas con Gemini (" + modelId + ")");
                model.addGenerator(new GeminiQuestionCreator(modelId, apiKey));
            }
        }


        Controller controller = new Controller(model, view);

        view.setController(controller);

        controller.start();
    }
}
