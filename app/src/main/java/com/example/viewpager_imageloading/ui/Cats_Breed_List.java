package com.example.viewpager_imageloading.ui;

import android.app.ProgressDialog;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpager_imageloading.R;
import com.example.viewpager_imageloading.adapter.RecyclerViewAdapter;
import com.example.viewpager_imageloading.adapter.ViewPagerAdapter;
import com.example.viewpager_imageloading.data.model.model.ResponseObject;
import com.example.viewpager_imageloading.databinding.FragmentHomeBinding;
import com.example.viewpager_imageloading.viewmodel.BreedsViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Anil Kumar on 02,Feb,2021
 */

@AndroidEntryPoint
public class Cats_Breed_List extends Fragment {
    private static final String TAG = "Home";
    private FragmentHomeBinding binding;
    private BreedsViewModel viewModel;
    private RecyclerViewAdapter adapter;
    private ArrayList<ResponseObject> catsList;
    boolean isListSelected=false;
    boolean isGridSelected=false;
    boolean isSlideViewSelected=true;
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    Menu menu;
    ProgressDialog pDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        pDialog=new ProgressDialog(getActivity());
        pDialog.setMessage("PleaseWait!!!!");
        catsList=new ArrayList<ResponseObject>();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this).get(BreedsViewModel.class);
        pDialog.show();
        setupViewPager();
        /*setupGridLayout();
        initRecyclerView();*/
        binding.refreshFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getBreedList();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
           inflater.inflate(R.menu.menu, menu);
           this.menu=menu;
        if(isSlideViewSelected)
            menu.findItem(R.id.action_viewPager).setVisible(false);
        if(isGridSelected)
            menu.findItem(R.id.action_grid).setVisible(false);
        if(isListSelected)
            menu.findItem(R.id.action_list).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_grid:
                if(isGridSelected)
                    break;
                isGridSelected=true;
                isListSelected=false;
                isSlideViewSelected=false;
                setupGridLayout();
                binding.viewPagerMain.setVisibility(View.GONE);
                binding.catsRecyclerView.setVisibility(View.VISIBLE);
                item.setVisible(false);
                if(menu!=null){
                    menu.findItem(R.id.action_list).setVisible(true);
                    menu.findItem(R.id.action_viewPager).setVisible(true);
                }
                //item.setVisible(false);
                break;
            case R.id.action_list:
                if(isListSelected)
                    break;
                initRecyclerView();
                isListSelected=true;
                isGridSelected=false;
                isSlideViewSelected=false;
                if(menu!=null){
                    menu.findItem(R.id.action_grid).setVisible(true);
                    menu.findItem(R.id.action_viewPager).setVisible(true);
                }
                item.setVisible(false);
                break;
            case R.id.action_viewPager:
                if(isSlideViewSelected)
                    break;
                setupViewPager();
                isSlideViewSelected=true;
                isListSelected=false;
                isGridSelected=false;
                if(menu!=null){
                    menu.findItem(R.id.action_grid).setVisible(true);
                    menu.findItem(R.id.action_list).setVisible(true);
                }
                item.setVisible(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
     //   viewModel.getBreedList();
        binding.catsRecyclerView.setVisibility(View.GONE);
        binding.viewPagerMain.setVisibility(View.VISIBLE);
        mViewPagerAdapter=new ViewPagerAdapter(getContext(),catsList);
        binding.viewPagerMain.setAdapter(mViewPagerAdapter);
    }

    private void initRecyclerView() {
        if(catsList.size()==0)
            viewModel.getBreedList();
        binding.catsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.catsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new RecyclerViewAdapter(getActivity(),catsList);
        binding.catsRecyclerView.setAdapter(adapter);
        binding.catsRecyclerView.setVisibility(View.VISIBLE);
        binding.viewPagerMain.setVisibility(View.GONE);
    }
    private void setupGridLayout() {
        if(catsList.size()==0)
            viewModel.getBreedList();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        binding.viewPagerMain.setVisibility(View.GONE);
        binding.catsRecyclerView.setVisibility(View.VISIBLE);
        binding.catsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.catsRecyclerView.setAdapter(adapter);
    }
    private void ShowSnackBar(){
        Snackbar snackbar=Snackbar.make(binding.getRoot(),"No Records found!! Please try again", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.show();
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getBreedList();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initRecyclerView();
        observeOnlineData();
        viewModel.getBreedList();
    }



    private void observeOnlineData() {
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ShowSnackBar();
            }
        });
      viewModel.getBreedsList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ResponseObject>>() {
          @Override
          public void onChanged(ArrayList<ResponseObject> responseObjects) {
              if(pDialog!=null && pDialog.isShowing())
                  pDialog.dismiss();
              if(responseObjects.size()==0)
                  ShowSnackBar();
              catsList=responseObjects;
              if(isSlideViewSelected){
                  binding.catsRecyclerView.setVisibility(View.GONE);
                  mViewPagerAdapter.updateList(catsList);
                  //binding.viewPagerMain.setAdapter(mViewPagerAdapter);
                  binding.viewPagerMain.setVisibility(View.VISIBLE);
              }
              else
              {
                    binding.viewPagerMain.setVisibility(View.GONE);
                    binding.catsRecyclerView.setVisibility(View.VISIBLE);
                    adapter.updateList(responseObjects);
              }
          }
      });
    }


}
