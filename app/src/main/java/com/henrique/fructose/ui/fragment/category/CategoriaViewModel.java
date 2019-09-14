package com.henrique.fructose.ui.fragment.category;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.henrique.fructose.model.category.CategoryResponse;

public class CategoriaViewModel extends ViewModel {
    MutableLiveData<CategoryResponse> mutableLiveData;
    CategoryRepository categoryRepository;

    public void init(Context context) {
        if (mutableLiveData != null) {
            return;
        }
        categoryRepository = CategoryRepository.getInstance(context);
        mutableLiveData = categoryRepository.getCategory();
    }


    public LiveData<CategoryResponse> getCategoryRepository() {
        return mutableLiveData;
    }

}