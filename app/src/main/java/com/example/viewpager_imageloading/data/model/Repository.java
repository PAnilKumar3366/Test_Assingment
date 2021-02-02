package com.example.viewpager_imageloading.data.model;

import androidx.lifecycle.LiveData;

import com.example.viewpager_imageloading.data.model.model.Breed;
import com.example.viewpager_imageloading.data.model.network.BreedApiService;
import com.example.viewpager_imageloading.db.BreedDao;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.InstallIn;
import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private BreedApiService apiService;
    private BreedDao breedDao;

    @Inject
public Repository(BreedDao breedDao,BreedApiService breedApiService){
        this.breedDao=breedDao;
        this.apiService=breedApiService;
    }
    public Observable<ResponseObject> getBreeds(){
        return apiService.getBreeds();
    }
    public void insertBreeds(Breed breed){breedDao.insertBreeds(breed);}
    public LiveData<List<Breed>> getBreedsList(){return breedDao.getBreedsList();}
}
