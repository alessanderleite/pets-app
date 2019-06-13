package br.com.alessanderleite.petsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pet {

    @SerializedName("petId")
    @Expose
    private Integer petId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("breed")
    @Expose
    private String breed;
    @SerializedName("picture")
    @Expose
    private String picture;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}