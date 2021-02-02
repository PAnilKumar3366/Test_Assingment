package com.example.viewpager_imageloading.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.viewpager_imageloading.data.model.model.Breed;

import java.util.List;

@Dao
public interface BreedDao {
    @Insert
    void insertBreeds(Breed pokemon);
    @Query("SELECT * FROM breed_table")
    LiveData<List<Breed>> getBreedsList();

}
