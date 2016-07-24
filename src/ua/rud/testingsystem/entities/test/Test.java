package ua.rud.testingsystem.entities.test;

import java.util.List;

public class Test {
    private int id;
    private String caption;
    private List<Question> questions;
    private boolean completed;

    /*Constructors*/
    public Test() {
    }

//    public Test(int id, String caption, List<Question> questions) {
//        this.id = id;
//        this.caption = caption;
//        this.questions = questions;
//        this.completed = false;
//    }

    /*Getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isCompleted() {
        return completed;
    }

    /*Methods*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(caption);
        sb.append("\n");
        for (Question question : questions) {
            sb.append("\n").append(question);
        }
        return sb.toString();
    }

    public void applyAnswers(List<Integer> answerList) {
        for (Question question : questions) {
            question.applyAnswers(answerList);
        }
        completed = true;
    }

    public int getRate() {
        int n = 0;
        for (Question question : questions) {
            if (question.isCorrect()) {
                n++;
            }
        }
        return n * 100 / questions.size();
    }


}
