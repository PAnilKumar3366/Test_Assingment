package com.example.viewpager_imageloading.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.viewpager_imageloading.R;
import com.example.viewpager_imageloading.data.model.model.Breed;
import com.example.viewpager_imageloading.databinding.ActivityMainBinding;
import com.example.viewpager_imageloading.viewmodel.BreedsViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.InstallIn;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Anil Kumar on 02,Feb,2021
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    private static final String TAG = "MainActivity";

    BreedsViewModel viewModel;
    boolean isListSelected=false;
    boolean isGridSelected=false;
    boolean isSlideViewSelected=true;
    FragmentManager fm;
    Bundle bundle;
    Cats_Breed_List fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mBinding=ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(mBinding.getRoot());
            fragment=new Cats_Breed_List();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: Exception", e);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if(isSlideViewSelected)
            menu.findItem(R.id.action_viewPager).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }*/

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_grid:
                bundle=new Bundle();
                bundle.putBoolean("isGridSelected",true);
                bundle.putBoolean("isListSelected",false);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment)
                        .commit();
                break;
            case R.id.action_list:
                bundle=new Bundle();
                bundle.putBoolean("isGridSelected",false);
                bundle.putBoolean("isListSelected",true);
                Cats_Breed_List fragment=new Cats_Breed_List();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment)
                        .commit();
                break;
            case R.id.action_viewPager:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ImageSlidingFragment())
                        .commit();
                Toast.makeText(this, "ViewPager Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}