package com.example.viewpager_imageloading.di;


import com.example.viewpager_imageloading.data.model.network.BreedApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;

import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.internal.managers.ApplicationComponentManager;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {
    @Provides
    @Singleton
    public static BreedApiService provideBreedApiService(){

        return  new Retrofit.Builder()
                .baseUrl(" https://api.thecatapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(BreedApiService.class);
    }
}
