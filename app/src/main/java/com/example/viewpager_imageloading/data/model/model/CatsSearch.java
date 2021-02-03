package com.example.viewpager_imageloading.data.model.model;

import com.example.viewpager_imageloading.data.model.Repository;
import com.example.viewpager_imageloading.data.model.ResponseObject;

import java.lang.reflect.Type;
import java.util.List;

public class CatsSearch {
    private List<ResponseObject> repositoryList;

    public List<ResponseObject> getRepositoryList() {
        return repositoryList;
    }

    public void setRepositoryList(List<ResponseObject> repositoryList) {
        this.repositoryList = repositoryList;
    }
}
