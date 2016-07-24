package ua.rud.testingsystem.model.menu;

import java.util.ArrayList;
import java.util.Map;

public class Subject {
    private int id;
    private String name;
    private Map<Integer, String> tests;
    private ArrayList<TestInfo> testInfos;

    /*Constructors*/
    public Subject() {
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
