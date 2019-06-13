package br.com.alessanderleite.petsapp.api;

import java.util.List;

import br.com.alessanderleite.petsapp.model.Pet;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/pets")
    Call<List<Pet>> getPets();

}
