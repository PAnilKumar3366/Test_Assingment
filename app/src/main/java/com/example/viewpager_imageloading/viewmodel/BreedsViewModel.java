package com.example.viewpager_imageloading.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewpager_imageloading.data.model.Repository;
import com.example.viewpager_imageloading.data.model.ResponseObject;
import com.example.viewpager_imageloading.data.model.model.Breed;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;


public class BreedsViewModel  extends ViewModel {
    private Repository repository;
    private MutableLiveData<ArrayList<ResponseObject>> breedsList = new MutableLiveData<>();

    private static final String TAG = "BreedsViewModel";
    @ViewModelInject
    public BreedsViewModel(Repository repository) {
        this.repository = repository;
       // breedsList=repository.getBreedsList();
    }

    public LiveData<ArrayList<ResponseObject>> getBreedsList() {
        return breedsList;
    }
    public void getBreedList(){
        try {
            repository.getBreedsAPI()
                    .subscribeOn(Schedulers.io())
                    .map(new Function<ArrayList<ResponseObject>, ArrayList<ResponseObject>>() {
                        @Override
                        public ArrayList<ResponseObject> apply(ArrayList<ResponseObject> responseObjects)  {
                            for (ResponseObject result:responseObjects) {
                                Log.d(TAG, "apply: "+result.toString());
                            }
                            return responseObjects;
                        }
                    })

                            /*.map(new Function<ResponseObject, Object>() {

                    }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result->breedsList.setValue(result)),
                    error-> Log.e(TAG, "getBreeds" );*/
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> breedsList.setValue(result),
                            error-> Log.e(TAG, "getBreeds: " + error.getMessage() ));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertBreed(Breed breed){
        repository.insertBreeds(breed);
    }
    public LiveData<List<Breed>> getBreedsLivedata(){
        return repository.getBreedsList();
    }
}
