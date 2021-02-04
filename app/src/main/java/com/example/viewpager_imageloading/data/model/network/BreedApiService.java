package com.example.viewpager_imageloading.data.model.network;

import com.example.viewpager_imageloading.data.model.model.ResponseObject;


import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anil Kumar on 02,Feb,2021
 */
public interface BreedApiService {

    @GET("images/search?")
    Observable<ArrayList<ResponseObject>> getBreeds(@Query("limit") int limit);

}
