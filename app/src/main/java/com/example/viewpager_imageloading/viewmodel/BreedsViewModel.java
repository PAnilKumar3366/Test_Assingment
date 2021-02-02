package com.example.viewpager_imageloading.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewpager_imageloading.data.model.Repository;
import com.example.viewpager_imageloading.data.model.ResponseObject;
import com.example.viewpager_imageloading.data.model.model.Breed;
import com.example.viewpager_imageloading.data.model.network.BreedApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;


public class BreedsViewModel  extends ViewModel {
    private Repository repository;
    private MutableLiveData<List<Breed>> breedsList = new MutableLiveData<>();

    private static final String TAG = "BreedsViewModel";
    @ViewModelInject
    public BreedsViewModel(Repository repository) {
        this.repository = repository;

    }

    public MutableLiveData<List<Breed>> getBreedsList() {
        return breedsList;
    }

    public void getBreedList(){
        repository.getBreeds()
                .subscribeOn(Schedulers.io())
                /*.map(new Function<ResponseObject, Object>() {

                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->breedsList.setValue(result)),
                error-> Log.e(TAG, "getBreeds" );*/
                .map(new Function<ResponseObject, List<Breed>>() {
                    @Override
                    public List<Breed> apply(ResponseObject responseObject) throws Throwable {
                        List<Breed> list = responseObject.getBreeds();
                        for(Breed breed : list){
                            String url = breed.getReferenceImageId();
                        }
                        Log.e(TAG, "apply: "+list.get(2).getReferenceImageId());
                        return list;}
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> breedsList.setValue(result),
                        error-> Log.e(TAG, "getBreeds: " + error.getMessage() ));
    }


}
