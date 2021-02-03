package com.example.viewpager_imageloading.data.model;

import com.example.viewpager_imageloading.data.model.model.Breed;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {
    private ArrayList<Breed> breeds = null;
    private String id;
    private String url;
    private int width;
    private int height;

    public ArrayList<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(ArrayList<Breed> breeds) {
        this.breeds = breeds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "breeds=" + breeds +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
