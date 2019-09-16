package com.henrique.fructose.viewmodel.repository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.henrique.fructose.api.ApiClient;
import com.henrique.fructose.api.ApiService;
import com.henrique.fructose.model.category.CategoryResponse;
import com.henrique.fructose.model.restaurant.RestaurantResponse;
import com.henrique.fructose.util.LoadingHelper;

import io.reactivex.observers.DisposableSingleObserver;

import static com.henrique.fructose.util.LoadingHelper.*;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class RestaurantRepository {

    private static RestaurantRepository categoryRepository;
    private ApiService apiService;
    Activity contexto;

    public RestaurantRepository(Activity context) {
        contexto = context;
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    public static RestaurantRepository getInstance(Activity context) {
        if (categoryRepository == null) {
            categoryRepository = new RestaurantRepository(context);
        }
        return categoryRepository;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<RestaurantResponse> getRestaurantes() {
        MutableLiveData<RestaurantResponse> restaurantData = new MutableLiveData<>();
        apiService.fetchAllRestaurantes("rating").subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new DisposableSingleObserver<RestaurantResponse>() {
                    @Override
                    public void onSuccess(RestaurantResponse restaurantResponse) {
                        restaurantData.setValue(restaurantResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        restaurantData.setValue(null);
                        Log.d("MVVM", "Deu ruim ao recuperar restaurantes");
                        failure(contexto);
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                       // LoadingHelper.start(contexto);
                    }
                });
        return restaurantData;
    }


    @SuppressLint("CheckResult")
    public MutableLiveData<RestaurantResponse> getRestaurantesReverse() {
        MutableLiveData<RestaurantResponse> restaurantData = new MutableLiveData<>();
        apiService.fetchAllRestaurantes("cost").subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new DisposableSingleObserver<RestaurantResponse>() {
                    @Override
                    public void onSuccess(RestaurantResponse restaurantResponse) {
                        restaurantData.setValue(restaurantResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        restaurantData.setValue(null);
                        Log.d("MVVM", "Deu ruim ao recuperar restaurantes");
                        //failure(contexto);
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                       // LoadingHelper.start(contexto);
                    }
                });
        return restaurantData;
    }
}
