package com.henrique.fructose.api;

import com.henrique.fructose.model.picture.Pexels;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.henrique.fructose.api.ApiConfig.BUSCA;


public interface ApiPexelsService {

    @GET(BUSCA)
    Single<Pexels> searchPic(@Query("query") String nome, @Query("per_page") int quantidade);


}
