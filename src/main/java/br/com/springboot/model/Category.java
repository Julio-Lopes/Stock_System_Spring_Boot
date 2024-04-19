package br.com.springboot.model;

public enum Category {

    CELLPHONE("Celulares"),
    HOUSEHOLDAPPLICANCE("Eletrodoméstico"),
    COMPUTING("Informática"),
    FURNITURE("Móveis");

    private String description;

    Category(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
