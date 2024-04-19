package br.com.springboot.model;

public enum Gender {
    
    male("Male"),
    female("Female");

    private String description;

    Gender(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
