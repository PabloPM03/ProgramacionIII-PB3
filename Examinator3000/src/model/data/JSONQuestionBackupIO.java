package model.data;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.exceptions.QuestionBackupIOException;
import model.pojos.Question;

public class JSONQuestionBackupIO implements QuestionBackupIO {

    private final String JSONPath;
    private final Gson gson;

    public JSONQuestionBackupIO() {

        this.JSONPath = System.getProperty("user.home") + File.separator;
        this.gson = new Gson();
    }

    @Override
    public void exportQuestions(ArrayList<Question> questions, String fileName) throws QuestionBackupIOException {
        
        if (!fileName.endsWith(".json")) {
            fileName +=".json";
        }

        File file = new File(JSONPath + fileName);

        try {
            String json = gson.toJson(questions);
            Files.write(file.toPath(), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new QuestionBackupIOException("Ha surgido un problema al exportar al archibo JSON" + e.getMessage());
        }
    }

    @Override
    public ArrayList<Question> importQuestions(String fileName) throws QuestionBackupIOException {
        
        if (!fileName.endsWith(".json")) {
            fileName +=".json";
        }

        File file = new File(JSONPath + fileName);

        if (!file.exists()) {
            throw new QuestionBackupIOException("El fichero JSON no existe");
        }

        try {

            String json = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            Type tipoDeLista = new TypeToken<ArrayList<Question>>() {}.getType();
            ArrayList<Question> questions = gson.fromJson(json, tipoDeLista);

            if (questions == null) return new ArrayList<>();
            return questions;

        } catch (IOException e) {
            throw new QuestionBackupIOException("Fallo de lectura IO" + e.getMessage());
        } catch (Exception e) {
            throw new QuestionBackupIOException("Fallo en el archivo JSON" + e.getMessage());
        }
    }

}
