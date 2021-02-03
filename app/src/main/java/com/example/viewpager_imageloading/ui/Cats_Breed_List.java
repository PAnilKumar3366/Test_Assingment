package com.example.viewpager_imageloading.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpager_imageloading.data.model.ResponseObject;
import com.example.viewpager_imageloading.data.model.model.Breed;
import com.example.viewpager_imageloading.databinding.FragmentHomeBinding;
import com.example.viewpager_imageloading.viewmodel.BreedsViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 17,June,2020
 */

@AndroidEntryPoint
public class Cats_Breed_List extends Fragment {
    private static final String TAG = "Home";
    private FragmentHomeBinding binding;
    private BreedsViewModel viewModel;
    //private PokemonAdapter adapter;
    private ArrayList<Breed> pokemonList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.refreshFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getBreedList();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BreedsViewModel.class);

        initRecyclerView();
        observeData();
        //setUpItemTouchHelper();
        viewModel.getBreedList();
    }

    private void setUpItemTouchHelper() {

    }


    private void observeData() {
        viewModel.getBreedsList().observe(getViewLifecycleOwner(), new Observer<List<ResponseObject>>() {
            @Override
            public void onChanged(List<ResponseObject> responseObjects) {

            }
        });
    }

    private void initRecyclerView() {
        binding.catsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
