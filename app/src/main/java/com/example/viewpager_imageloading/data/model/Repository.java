package com.example.viewpager_imageloading.data.model;

import com.example.viewpager_imageloading.data.model.network.BreedApiService;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private BreedApiService apiService;
    public Observable<ResponseObject> getBreeds(){
        return apiService.getBreeds();
    }
}
