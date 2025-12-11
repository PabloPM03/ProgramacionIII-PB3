package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String author;
    private HashSet<String> topicList;
    private String statement;
    private ArrayList<Option> optionList;

    public Question(String author, HashSet<String> topicList, String statement, ArrayList<Option> optionList) {

        this.id = UUID.randomUUID();
        this.author = author;
        this.statement = statement;
        this.optionList = optionList;

        this.topicList = new HashSet<>();
        if (topicList != null) {
            for (String t : topicList) {
                this.topicList.add(t.trim().toUpperCase());
            }
        }
    }

    public String getAuthor() { return author; }
    public UUID getId() { return id; }
    public ArrayList<Option> getOptions() { return optionList; }
    public String getStatement() { return statement; }
    public HashSet<String> getTopics() { return topicList; }

    @Override
    public String toString() {  return "| ID: " + id + " | Autor: " + author + " | Temas: " + topicList + 
                            "\n| Enunciado: " + statement + optionList; }



}
