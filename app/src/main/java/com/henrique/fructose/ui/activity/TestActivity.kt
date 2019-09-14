package com.henrique.fructose.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.henrique.fructose.R.layout.activity_test
import com.henrique.fructose.R.string.api_response_sucess
import com.henrique.fructose.api.ApiClient
import com.henrique.fructose.api.ApiService
import com.henrique.fructose.model.Collections
import com.henrique.fructose.model.category.CategoryResponse
import com.henrique.fructose.model.citie.CityResponse
import com.henrique.fructose.model.colection.CollectionResponse
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers.io


class TestActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_test)
        apiService = ApiClient.getClient(applicationContext).create(ApiService::class.java)
        //requestCategories()
        //requestCities()
        //requestCollection()
    }

    @SuppressLint("CheckResult")
    private fun requestCategories() {
        apiService.fetchAllCategories()
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(object : DisposableSingleObserver<CategoryResponse>() {
                    override fun onSuccess(categorias: CategoryResponse) {
                        // Received all notes
                        for (ctg in categorias.categories) {
                            Log.d("Categoria", ctg.categories.name)
                        }
                    }

                    override fun onError(e: Throwable) {
                        // Network error
                        Log.d("Erro", e.localizedMessage.toString())
                    }
                })
    }

    @SuppressLint("CheckResult")
    private fun requestCities() {
        apiService.fetchAllCities("riodejaneiro")
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(object : DisposableSingleObserver<CityResponse>() {
                    // Received all notes

                    override fun onSuccess(city: CityResponse) {
                        if (city.status.equals(getString(api_response_sucess))) {
                            Log.d("Cidade", city.toString())
                        }
                    }

                    override fun onError(e: Throwable) {
                        // Network error
                        Log.d("Erro", e.localizedMessage.toString())
                    }
                })
    }

    @SuppressLint("CheckResult")
    private fun requestCollection() {//73 => Rio de janeiro
        apiService.fetchAllCollections(73)
                .subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(object : DisposableSingleObserver<CollectionResponse>() {
                    override fun onError(e: Throwable) {
                        Log.d("Erro", e.localizedMessage.toString())
                    }

                    override fun onSuccess(t: CollectionResponse) {
                        Toast.makeText(
                                this@TestActivity,
                                "Sucesso",Toast.LENGTH_LONG).show()

                    }
                })
    }
}
