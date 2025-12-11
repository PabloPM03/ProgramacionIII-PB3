package model.pojos;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;


public class Exam {

    private ArrayList<Question> questions= new ArrayList<Question>();
    private int correctAnswers, incorrectAnswers, skippedAnswers;
    private Instant startTime, endTime;

    public Exam(ArrayList<Question> questions) {
        this.questions = questions;
        this.correctAnswers = this.incorrectAnswers = this.skippedAnswers = 0;
    }



    public void startExam() {
        this.startTime = Instant.now();
    }
    public void endExam() {
        this.endTime = Instant.now();
    }

    public String getDuration() {

        if (startTime == null || endTime == null) return "00:00";
        Duration duration = Duration.between(startTime, endTime);

        return String.format("%02d:%02d", duration.toMinutes(), duration.toSeconds());
    }


    public double getGrade() {

        if (questions.isEmpty()) return 0.0;

        double score = correctAnswers - (incorrectAnswers / 3);
        if (score < 0) score = 0;

        score = (score / questions.size()) * 10;
        return score;
    }

        //Getters y Setters
    public int getCorrectAnswers() { return correctAnswers; }
    public int getIncorrectAnswers() { return incorrectAnswers; }
    public int getSkippedAnswers() { return skippedAnswers; }
    public ArrayList<Question> getQuestions() { return questions; }

    public void addCorrectAnswers() { this.correctAnswers++; }
    public void addIncorrectAnswers() { this.incorrectAnswers++; }
    public void addSkippedAnswers() { this.skippedAnswers++; }



}
