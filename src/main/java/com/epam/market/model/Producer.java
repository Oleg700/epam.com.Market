package com.epam.market.model;

public class Producer {
    private int producerId;
    private String title;

    public Producer() {
    }

    public int getId() {
        return producerId;
    }

    public void setId(int id) {
        this.producerId = id;
    }

    public String getTitle() {
        return title;
    }

    public Producer setTitle(String title) {
        this.title = title;
        return this;
    }


}
