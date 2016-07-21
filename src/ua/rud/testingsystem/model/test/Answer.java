package ua.rud.testingsystem.model.test;

public class Answer {
    private int id;
    private String text;
    private boolean correct;

    /*Constructors*/
    public Answer() {
    }

    public Answer(int id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
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

    /*Methods*/
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(id);
        sb.append(correct);
        sb.append("\t").append(text);
        return sb.toString();
    }
}
