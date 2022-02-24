package com.example.post.services;

import com.example.post.dao.PackageDao;
import com.example.post.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationService {
    PackageDao packageDao;

    public RegistrationService(PackageDao packageDao) {
        this.packageDao = packageDao;
        register(new Customer(1, "Alexander", "123", "asda", "asdas2"));
    }

    public void register(Customer customer){
        log.debug("user = {} existing = {}" , customer.getPhone(), isCustomerExists(customer));
    }

    private boolean isCustomerExists(Customer customer){
        return packageDao.findCustomerByName(customer.getPhone()) != null;
    }
}
