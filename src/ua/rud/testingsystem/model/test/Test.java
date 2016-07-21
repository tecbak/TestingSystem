package ua.rud.testingsystem.model.test;

import java.util.List;

public class Test {
    private int id;
    private String caption;
    private List<Question> questions;

    /*Constructors*/
    public Test() {
    }

    public Test(int id, String caption, List<Question> questions) {
        this.id = id;
        this.caption = caption;
        this.questions = questions;
    }

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
}
