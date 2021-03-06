package ua.rud.testingsystem.entities.test;

import java.util.List;

/**
 * A separate answer to question.
 * Contains information whether it is correct or not
 * and if it is checked by user
 */
public class Answer {

    /*Answers ID*/
    private int id;

    /*Text of answer*/
    private String text;

    /*Is a correct answer to question*/
    private boolean correct;

    /*Is checked by user*/
    private boolean checked;

    /*Constructor*/
    public Answer() {
        this.id = -1;           //id -1 means that this answer isn't saved in database
        this.text = "";
        this.correct = false;
        this.checked = false;
    }

    /*Getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /*Methods*/
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("" + id);
        sb.append(correct);
        sb.append("\t").append(text);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (correct != answer.correct) return false;
        if (checked != answer.checked) return false;
        return text != null ? text.equals(answer.text) : answer.text == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (correct ? 1 : 0);
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }

    /**
     * Look through the {@link List} of user's answer ids
     * and mark this answers if the list contains its id
     *
     * @param answerList a {@link List} as answer ids
     */
    public void applyAnswers(List<Integer> answerList) {
        checked = answerList.contains(id);
    }

    public boolean isCorrectlyChecked() {
        return checked == correct;
    }
}
