package com.example.viewpager_imageloading.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.viewpager_imageloading.data.model.model.ResponseObject;
import com.example.viewpager_imageloading.databinding.ListItemBinding;

import java.util.ArrayList;

/**
 * Created by Anil Kumar on 02,Feb,2021
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CatsViewHolder> {
    ListItemBinding listItemBinding;
    private Context context;
    private ArrayList<ResponseObject> catsList;
    private static final String TAG = "RecyclerViewAdapter";
    public RecyclerViewAdapter(Context context, ArrayList<ResponseObject> catsList){
        this.context=context;
        this.catsList=catsList;
    }
    @NonNull
    @Override
    public CatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            LayoutInflater inflater=LayoutInflater.from(context);
            listItemBinding=ListItemBinding.inflate(inflater);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onCreateViewHolder: ",e );
        }
        return new CatsViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CatsViewHolder holder, int position) {
        ResponseObject cat=catsList.get(position);
        holder.itemBinding.catName.setText(cat.getId());
        Glide.with(context).load(cat.getUrl()).into(holder.itemBinding.catImage);
    }

    @Override
    public int getItemCount() {
        return catsList!=null?catsList.size():0;
    }
    public void updateList(ArrayList<ResponseObject> list){
        catsList=list;
        notifyDataSetChanged();
    }
    public class CatsViewHolder extends RecyclerView.ViewHolder{
        ListItemBinding itemBinding;
        public CatsViewHolder(@NonNull ListItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding=itemView;
        }
    }
}
