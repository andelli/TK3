package com.andeli.tk3;

public class DataModel {
    private String id;
    private String name;
    private String description;

    // Constructor
    public DataModel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getter for ID
    public String getId() {
        return id;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Getter for Description
    public String getDescription() {
        return description;
    }
}
