package com.example.viewpager_imageloading.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpager_imageloading.data.model.model.ResponseObject;
import com.example.viewpager_imageloading.adapter.ViewPagerAdapter;
import com.example.viewpager_imageloading.databinding.ImageslideFragmentBinding;
import com.example.viewpager_imageloading.viewmodel.BreedsViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Anil Kumar on 02,Feb,2021
 */
@AndroidEntryPoint
public class ImageSlidingFragment extends Fragment {
    ImageslideFragmentBinding mBinding;
    BreedsViewModel viewModel;
    private ArrayList<ResponseObject> catsList;
    ViewPager mViewPager;
    private static final String TAG = "ImageSlidingFragment";
    ViewPagerAdapter mViewPagerAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=ImageslideFragmentBinding.inflate(getLayoutInflater());
        mViewPager = mBinding.viewPagerMain;
        // Initializing the ViewPagerAdapter

        // Adding the Adapter to the ViewPager
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(BreedsViewModel.class);
        viewModel.getBreedList();
        viewModel.getBreedsList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ResponseObject>>() {
            @Override
            public void onChanged(ArrayList<ResponseObject> results) {
                catsList=results;
                for(ResponseObject result:results)
                    Log.d(TAG, "onChanged: "+result.getId());
                mViewPagerAdapter = new ViewPagerAdapter(getActivity(), catsList);
                mViewPager.setAdapter(mViewPagerAdapter);
            }
        });
    }
}
