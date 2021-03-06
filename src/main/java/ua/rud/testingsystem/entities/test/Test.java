package ua.rud.testingsystem.entities.test;

import java.util.ArrayList;
import java.util.List;

/**
 * A test with a questions
 */
public class Test {
    private int id;
    private String caption;
    private List<Question> questions;
    private boolean completed;

    /*Constructor*/
    public Test() {
        this.id = -1;                   //id -1 means that this test isn't saved in database
        this.caption = "";
        this.questions = new ArrayList<>();
        this.completed = false;
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

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public boolean isCompleted() {
        return completed;
    }

    /*Methods*/

    /**
     * Find question by its id and return it if exists, return {@code null} otherwise
     *
     * @param id question id
     * @return question if there is one with specified id
     * or {@code null} otherwise
     */
    public Question getQuestionById(int id) {

        for (Question question : questions) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(caption);
        sb.append("\n");
        for (Question question : questions) {
            sb.append("\n").append(question);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        if (completed != test.completed) return false;
        if (caption != null ? !caption.equals(test.caption) : test.caption != null) return false;
        return questions != null ? questions.equals(test.questions) : test.questions == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (caption != null ? caption.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }

    /**
     * Invoke method applyAnswers for all {@link Answer}s of this question
     *
     * @param answerList a {@link List} of chosen answers
     */
    public void applyAnswers(List<Integer> answerList) {
        for (Question question : questions) {
            question.applyAnswers(answerList);
        }
        completed = true;
    }

    /**
     * Calculate a percent of right {@link Answer}s
     *
     * @return a percent of right {@link Answer}s
     */
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