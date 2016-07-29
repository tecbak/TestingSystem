package ua.rud.testingsystem.entities;


import java.util.HashMap;
import java.util.Map;


public class Subject {
    private int id;
    private String name;

    /*This map's keys are tests' ids and values are their captions*/
    private Map<Integer, String> tests;

    /*Constructor*/
    public Subject() {
        this.id = -1;           // id -1 means that this subject isn't saved in database
        this.name = "";
        this.tests = new HashMap<>();
    }

    /*Getters and setters*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, String> getTests() {
        return tests;
    }

    public void setTests(Map<Integer, String> tests) {
        this.tests = tests;
    }

    public void addTest(int testId, String caption) {
        tests.put(testId, caption);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subject{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", tests=").append(tests);
        sb.append('}');
        return sb.toString();
    }
}
