package com.example.post.services;

import com.example.post.dao.PackageDao;
import com.example.post.dto.Customer;
import com.example.post.dto.Package;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    PackageDao packageDao;

    public PackageService(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    public List<Package> getPackagesForCustomer(Customer customer){
        return packageDao.getPackagesByPhone(customer.getPhone());
    }
}
