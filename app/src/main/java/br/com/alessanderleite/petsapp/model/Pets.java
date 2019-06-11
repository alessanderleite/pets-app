package br.com.alessanderleite.petsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pets {

    @SerializedName("petId")
    @Expose
    private Integer petId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("species")
    @Expose
    private String species;

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

}