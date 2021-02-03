package com.example.viewpager_imageloading.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;


import android.os.Bundle;
import android.util.Log;

import com.example.viewpager_imageloading.R;
import com.example.viewpager_imageloading.data.model.model.Breed;
import com.example.viewpager_imageloading.databinding.ActivityMainBinding;
import com.example.viewpager_imageloading.viewmodel.BreedsViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    private BreedsViewModel viewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Cats_Breed_List())
                .commit();


    }
}