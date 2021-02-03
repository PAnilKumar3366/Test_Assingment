package com.example.viewpager_imageloading.data.model.network;

import com.example.viewpager_imageloading.data.model.ResponseObject;
import com.example.viewpager_imageloading.data.model.model.CatsSearch;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BreedApiService {

    @GET("images/search?")
    Observable<ArrayList<ResponseObject>> getBreeds(@Query("limit") int limit);

}
