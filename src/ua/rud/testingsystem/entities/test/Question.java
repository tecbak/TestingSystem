package ua.rud.testingsystem.entities.test;

import java.util.List;

public class Question {
    private int id;
    private String task;
    private List<Answer> answers;

    /*Constructors*/
    public Question() {
    }

//    public Question(int id, String task, List<Answer> answers) {
//        this.id = id;
//        this.task = task;
//        this.answers = answers;
//    }

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

    public void applyAnswers(List<Integer> answerList) {
        for (Answer answer : answers) {
            answer.applyAnswers(answerList);
        }
    }

    public boolean isCorrect() {
        for (Answer answer : answers) {
            if (!answer.isCorrectlyChecked()) {
                return false;
            }
        }
        return true;
    }

}
