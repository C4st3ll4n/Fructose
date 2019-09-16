package com.henrique.fructose.viewmodel.repository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.henrique.fructose.api.ApiClient;
import com.henrique.fructose.api.ApiService;
import com.henrique.fructose.model.category.CategoryResponse;
import com.henrique.fructose.util.LoadingHelper;

import io.reactivex.observers.DisposableSingleObserver;

import static com.henrique.fructose.util.LoadingHelper.*;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class CategoryRepository {

    private static CategoryRepository categoryRepository;
    private ApiService apiService;
    private Activity contexto;
    public CategoryRepository(Activity context) {
        apiService = ApiClient.getClient(context).create(ApiService.class);
        contexto = context;
    }

    public static CategoryRepository getInstance(Activity context) {
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
                        failure(contexto);
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        /*start(contexto, "Caregando categorias...",
                                "Aguarde enquanto estamos configurando as categorias disponíveis.");*/
                    }
                });
        return categoryData;
    }
}
