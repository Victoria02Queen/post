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
        //packageDao.addCustomer("Сергей", "89688616168", "звездный бульвар");
       // packageDao.addPostOffice("Post 1", "Moscow, Pushkin, 1");
        //packageDao.addEmployee("Анатолий", "89123456313", "Post 12");
        packageDao.addPackage("Package 1", "8asd", "zzz", "89688616168", "89688616168");


    }
}
