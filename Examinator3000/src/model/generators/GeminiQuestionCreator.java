package model.generators;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.genai.types.Schema;
import es.usal.genai.GenAiConfig;
import es.usal.genai.GenAiFacade;
import es.usal.genai.SimpleSchemas;
import model.exceptions.QuestionCreatorException;
import model.pojos.Question;
import model.pojos.Option;

public class GeminiQuestionCreator implements QuestionCreator {

    private final String modelId;
    private final String apiKey;

    public GeminiQuestionCreator(String modelId, String apiKey) {
        this.modelId = modelId;
        this.apiKey = apiKey;
    }

    @Override
    public String getModelId() {
        return "Gemini AI (" + modelId + ")";
    }

    @Override
    public Question createQuestion(String topic) throws QuestionCreatorException {
        System.setProperty("GOOGLE_API_KEY", this.apiKey);

        String prompt = "Genera una pregunta de examen tipo test sobre el tema: " + topic + ". " +
                        "Debe tener 4 opciones, y SOLO UNA debe ser correcta. " +
                        "Incluye una justificación breve para cada opción.";
    
        try {
            
            GenAiConfig config = GenAiConfig.fromEnv(this.modelId);

            try (GenAiFacade genai = new GenAiFacade(config)) {
                Schema schema = SimpleSchemas.from(QuestionDTO.class);

                QuestionDTO dto = genai.generateJson(prompt, schema, QuestionDTO.class);
                return mapDtoToDomain(dto, topic);
            } 
        } catch (Exception e) {
                throw new QuestionCreatorException("No se pudo generar una pregunta con Gemini" + e.getMessage(), e);
        }
    }

    private Question mapDtoToDomain(QuestionDTO dto, String topic) {

        ArrayList<Option> options = new ArrayList<>();
        HashSet<String> topics = null;

        if (dto.options != null) {
            for (OptionDTO o : dto.options) {
                options.add(new Option(o.text, o.rationale, o.isCorrect));
            }

            topics = new HashSet<>();
            topics.add(topic.toUpperCase());
        }
        return new Question("AIModel: " + modelId, topics, dto.statement, options);
    }

    public static class QuestionDTO {
        public String statement;
        public ArrayList<OptionDTO> options;
    }
    public static class OptionDTO {
        public String text;
        public String rationale;
        public boolean isCorrect;
    }
}
