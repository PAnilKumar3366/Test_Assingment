package com.example.viewpager_imageloading.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.viewpager_imageloading.data.model.model.Breed;

import java.util.List;
/**
 * Created by Anil Kumar on 02,Feb,2021
 */
@Dao
public interface BreedDao {
    @Insert
    void insertBreeds(Breed pokemon);
    @Query("SELECT * FROM breed_table")
    LiveData<List<Breed>> getBreedsList();

}
