package ua.rud.testingsystem.entities.test;

import java.util.ArrayList;
import java.util.List;

/**
 * A question with answers for it
 */
public class Question {

    /*Question's id*/
    private int id;

    /*Text of question's task*/
    private String task;

    /*List of variants to answer */
    private List<Answer> answers;

    /*Constructors*/
    public Question() {
        this.id = -1;           //id -1 means that this question isn't saved in database
        this.task = "";
        this.answers = new ArrayList<>();
    }

    /*Getters and setters*/
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

    public void addAnswer(Answer answer) {
        answers.add(answer);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (task != null ? !task.equals(question.task) : question.task != null) return false;
        return answers != null ? answers.equals(question.answers) : question.answers == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }

    /**
     * Mark all answers of this question checked or unchecked by user
     *
     * @param answerList a {@link List} as answer ids, which user's selected
     */
    public void applyAnswers(List<Integer> answerList) {
        for (Answer answer : answers) {
            answer.applyAnswers(answerList);
        }
    }

    /**
     * If the question is correctly answered
     *
     * @return {@code true} if user's checked all correct and no incorrect answers,
     * and {@code false} if user hasn't checked one or more correct answer
     * and/or checked one or more incorrect ones,
     * also return {@code false} if user doesn't answer the question
     */
    public boolean isCorrect() {
        for (Answer answer : answers) {
            if (!answer.isCorrectlyChecked()) {
                return false;
            }
        }
        return true;
    }

}
