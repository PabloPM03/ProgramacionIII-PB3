package model.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.exceptions.IRepositoryException;
import model.pojos.Question;

public class BinaryRepository implements IRepository{

    private ArrayList<Question> questions;
    private final String filePath;

    public BinaryRepository() {

        this.questions = new ArrayList<Question>();
        this.filePath = System.getProperty("user.home") + File.separator + "questions.bin";

        try {
            dataLoad();
        } catch (IRepositoryException e) {
            System.err.println("QUE PASA AQUI????"); e.printStackTrace();
            this.questions = new ArrayList<>();
        }
    }


    @Override
    public Question addQuestion(Question q) throws IRepositoryException {
        
        questions.add(q);
        dataSave();
        return q;
    }

    @Override
    public void removeQuestion(Question q) throws IRepositoryException {
        questions.remove(q);
    }

    @Override
    public Question modifyQuestion(Question q) throws IRepositoryException {

        dataSave();
        return null;
    }

    @Override
    public ArrayList<Question> getAllQuestions() throws IRepositoryException {
        return questions;
    }

    @Override
    public void dataSave() throws IRepositoryException {

        ObjectOutputStream objectOutputStream = null;

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(this.filePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            objectOutputStream.writeObject(this.questions);
            
        } catch (IOException e) {
            throw new IRepositoryException("Error al guardar en el fichero." + e.getMessage(), e);
        } finally {

            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    throw new IRepositoryException("Error al cerrar el flujo de salida. " + e.getMessage());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void dataLoad() throws IRepositoryException {
        
        File finalFile = new File(this.filePath);

        if (finalFile.exists()) {

            ObjectInputStream objectInputStream = null;

            try {
                FileInputStream fileInputStream = new FileInputStream(finalFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                objectInputStream = new ObjectInputStream(bufferedInputStream);

                this.questions = (ArrayList<Question>) objectInputStream.readObject();

            } catch (IOException | ClassNotFoundException e) {

                this.questions = new ArrayList<>();
                throw new IRepositoryException("Error al cargar el fichero questions.bin" + e.getMessage());

            } finally {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        throw new IRepositoryException("No se pudo cerrar el flujo de entrada" + e.getMessage());
                    }
                }
            }
        }
    }

}
