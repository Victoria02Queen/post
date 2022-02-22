package com.example.post.services;

import com.example.post.dao.PackageDao;

@org.springframework.stereotype.Service
public class Service {
    PackageDao packageDao;

    public Service(PackageDao packageDao) {
        this.packageDao = packageDao;
        doSmth();
    }

    private void doSmth(){
        packageDao.addCustomer("sad", "asd", "sad");
    }
}
