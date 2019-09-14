package com.henrique.fructose.api;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.TextUtils.isEmpty;
import static com.henrique.fructose.api.ApiConfig.API_KEY;
import static com.henrique.fructose.api.ApiConfig.API_PEXELS_KEY;
import static com.henrique.fructose.api.ApiConfig.BASE_URL;
import static com.henrique.fructose.api.ApiConfig.PEXELS_BASE_URL;
import static com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create;
import static okhttp3.Request.Builder;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class ApiPexelsClient {
    private static Retrofit retrofit = null;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient okHttpClient;
 
    public static Retrofit getClient(Context context) {
 
        if (okHttpClient == null)
            initOkHttp(context);
 
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(PEXELS_BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
 
    private static void initOkHttp(final Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
 
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);
 
        httpClient.addInterceptor(interceptor);
 
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json");

            // Adding Authorization token (API Key)
            // Requests will be denied without API key
            if (!isEmpty(API_PEXELS_KEY)) {
                requestBuilder.addHeader("Authorization", API_PEXELS_KEY);
                //requestBuilder.addHeader("Authorization", API_PEXELS_KEY);
            }

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
 
        okHttpClient = httpClient.build();
    }
}