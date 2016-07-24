package ua.rud.testingsystem.entities.sbj;

import java.util.List;

public class TestInfo {
    private int id;
    private String caption;
    private List<Integer> rates;

    /*Constructor*/
    public TestInfo() {
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

    public List<Integer> getRates() {
        return rates;
    }

    public void setRates(List<Integer> rates) {
        this.rates = rates;
    }
}
