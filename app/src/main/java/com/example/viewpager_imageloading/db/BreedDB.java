package com.example.viewpager_imageloading.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.viewpager_imageloading.data.model.model.Breed;

@Database(entities = {Breed.class},version =1,exportSchema = false)
public abstract class BreedDB extends RoomDatabase {
public abstract BreedDao breedDao();
}
