package br.com.alessanderleite.petsapp.api;

import java.util.List;

import br.com.alessanderleite.petsapp.model.Pet;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/pets")
    Call<List<Pet>> getPets();

    @POST("/pets/create")
    @FormUrlEncoded
    Call<Pet> insertPet(
//            @Field("key") String key,
            @Query("name") String name,
            @Query("species") String species,
            @Query("breed") String breed
    );
}
