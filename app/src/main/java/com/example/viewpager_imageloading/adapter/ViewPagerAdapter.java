package com.example.viewpager_imageloading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.viewpager_imageloading.R;
import com.example.viewpager_imageloading.data.model.model.ResponseObject;

import java.util.ArrayList;

/**
 * Created by Anil Kumar on 03,Feb,2021
 */
public class ViewPagerAdapter extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    int[] images;
    ArrayList<ResponseObject> CatsList;
    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPagerAdapter(Context context, ArrayList<ResponseObject> CatsList) {
        this.context = context;
        this.CatsList=CatsList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void updateList(ArrayList<ResponseObject> catsList){
        CatsList=catsList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        // return the number of images
        return CatsList!=null?CatsList.size():0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.image_item, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
        if(CatsList.get(position).getUrl()!=null && !CatsList.get(position).getUrl().isEmpty())
        Glide.with(context).load(CatsList.get(position).getUrl()).into(imageView);
        // setting the image in the imageView

        // Adding the View
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
