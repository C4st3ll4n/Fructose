package com.henrique.fructose.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.henrique.fructose.model.category.CategoryResponse;
import com.henrique.fructose.model.restaurant.RestaurantResponse;
import com.henrique.fructose.viewmodel.repository.CategoryRepository;
import com.henrique.fructose.viewmodel.repository.RestaurantRepository;

public class MainFragmentViewModel extends ViewModel {
    MutableLiveData<CategoryResponse> categoryResponseMutableLiveData;
    CategoryRepository categoryRepository;

    MutableLiveData<RestaurantResponse> restaurantResponseMutableLiveData;
    RestaurantRepository restaurantRepository;

    public void init(Activity context) {
        if (categoryResponseMutableLiveData != null) {
            return;
        }
        categoryRepository = CategoryRepository.getInstance(context);
        categoryResponseMutableLiveData = categoryRepository.getCategory();

        restaurantRepository = RestaurantRepository.getInstance(context);
        restaurantResponseMutableLiveData = restaurantRepository.getRestaurantes();
    }


    public LiveData<CategoryResponse> getCategoryRepository() {
        return categoryResponseMutableLiveData;
    }

    public LiveData<RestaurantResponse> getRestauranteRepository() {
        return restaurantResponseMutableLiveData;
    }
}