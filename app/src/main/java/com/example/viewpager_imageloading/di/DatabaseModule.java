package com.example.viewpager_imageloading.di;

import android.app.Application;

import androidx.room.Room;

import com.example.viewpager_imageloading.db.BreedDB;
import com.example.viewpager_imageloading.db.BreedDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public static BreedDB providePokemonDB(Application application){
        return Room.databaseBuilder(application,BreedDB.class,"Breed Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static BreedDao providePokeDao(BreedDB breedDB){
        return breedDB.breedDao();
    }

}
