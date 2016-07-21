package ua.rud.testingsystem.model.test;

import java.util.List;

public class Question {
    private int id;
    private String task;
    private List<Answer> answers;

    /*Constructors*/
    public Question() {
    }

    public Question(int id, String task, List<Answer> answers) {
        this.id = id;
        this.task = task;
        this.answers = answers;
    }

    /*Getter and setter*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /*Methods*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(task);
        for (Answer answer : answers) {
            sb.append("\n\t").append(answer);
        }
        return sb.toString();
    }
}
