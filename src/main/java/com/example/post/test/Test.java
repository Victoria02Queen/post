package com.example.post.test;

import com.example.post.dao.PackageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Test {
    PackageDao packageDao;

    public Test(PackageDao packageDao) {
        this.packageDao = packageDao;
        log.error(packageDao.getPackagesByPhone("1").toString());
    }
}
