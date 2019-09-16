package com.henrique.fructose.api;

import com.henrique.fructose.model.Collections;
import com.henrique.fructose.model.Cuisine;
import com.henrique.fructose.model.Estabelecimento;
import com.henrique.fructose.model.category.CategoryResponse;
import com.henrique.fructose.model.citie.CityResponse;
import com.henrique.fructose.model.colection.CollectionResponse;
import com.henrique.fructose.model.restaurant.RestaurantResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.henrique.fructose.api.ApiConfig.BUSCA_ZOMATO;
import static com.henrique.fructose.api.ApiConfig.CATEGORIAS;
import static com.henrique.fructose.api.ApiConfig.CIDADES;
import static com.henrique.fructose.api.ApiConfig.ESTABELECIMENTOS;
import static com.henrique.fructose.api.ApiConfig.collections;
import static com.henrique.fructose.api.ApiConfig.cuisines;

public interface  ApiService {

    @GET(CATEGORIAS)
    Single<CategoryResponse> fetchAllCategories();

    @GET(CIDADES)
    Single<CityResponse> fetchAllCities(@Query("q") String nomeCidade);

    @GET(collections)
    Single<CollectionResponse> fetchAllCollections(@Query("city_id") int idCidade);

    @GET(cuisines)
    Single<List<Cuisine>> fetchAllCuisines();

    @GET(ESTABELECIMENTOS)
    Single<Estabelecimento> fetchAllEstabelecimentos();

    @GET(BUSCA_ZOMATO)
    Single<RestaurantResponse> fetchAllRestaurantes(@Query("sort") String sortType);

    @GET(BUSCA_ZOMATO)
    Single<RestaurantResponse> fetchAllRestaurantes(@Query("sort") String sortType,
                                                    @Query("order") String order);


}
