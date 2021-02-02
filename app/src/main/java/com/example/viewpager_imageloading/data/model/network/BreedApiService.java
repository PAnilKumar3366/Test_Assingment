package com.example.viewpager_imageloading.data.model.network;

import com.example.viewpager_imageloading.data.model.ResponseObject;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface BreedApiService {
    @GET("breeds")
    Observable<ResponseObject> getBreeds();
}
