package com.example.viewpager_imageloading.di;


import com.example.viewpager_imageloading.data.model.network.BreedApiService;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;

import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.internal.managers.ApplicationComponentManager;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {
    @Provides
    @Singleton
    public static BreedApiService provideBreedApiService(){
//"X-Api-Key", "DEMO-API-KEY"
        OkHttpClient.Builder client=new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request().newBuilder().addHeader("X-Api-Key", "DEMO-API-KEY").build();
                return chain.proceed(request);
            }
        });



        return  new Retrofit.Builder()
                .baseUrl(" https://api.thecatapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(BreedApiService.class);
    }
}
