package com.henrique.fructose.ui.fragment.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.henrique.fructose.api.ApiClient;
import com.henrique.fructose.api.ApiService;
import com.henrique.fructose.model.category.CategoryResponse;

import io.reactivex.observers.DisposableSingleObserver;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class CategoryRepository {

    private static CategoryRepository categoryRepository;
    private ApiService apiService;

    public CategoryRepository(Context context) {
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    public static CategoryRepository getInstance(Context context) {
        if (categoryRepository == null) {
            categoryRepository = new CategoryRepository(context);
        }
        return categoryRepository;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<CategoryResponse> getCategory() {
        MutableLiveData<CategoryResponse> categoryData = new MutableLiveData<>();
        apiService.fetchAllCategories().subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new DisposableSingleObserver<CategoryResponse>() {
                    @Override
                    public void onSuccess(CategoryResponse categoryResponse) {
                        categoryData.setValue(categoryResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        categoryData.setValue(null);
                        Log.d("MVVM", "Deu ruim ao recuperar categorias");
                    }
                });
        return categoryData;
    }
}
