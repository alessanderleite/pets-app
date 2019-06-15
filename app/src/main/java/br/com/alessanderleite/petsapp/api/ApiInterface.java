package br.com.alessanderleite.petsapp.api;

import java.util.List;

import br.com.alessanderleite.petsapp.model.Pet;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/pets")
    Call<List<Pet>> getPets();

    @POST("/create")
    @FormUrlEncoded
    Call<Pet> insertPet(
            @Field("name") String name,
            @Field("species") String species,
            @Field("breed") String breed
    );
}
