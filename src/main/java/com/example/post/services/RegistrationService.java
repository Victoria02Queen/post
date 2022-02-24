package com.example.post.services;

import com.example.post.dao.PackageDao;
import com.example.post.dto.Customer;
import com.example.post.services.exceptions.CustomerWasRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegistrationService {
    PackageDao packageDao;

    public RegistrationService(PackageDao packageDao) throws CustomerWasRegisteredException {
        this.packageDao = packageDao;
    }

    public void register(Customer customer) throws CustomerWasRegisteredException {
        if (isCustomerExists(customer)){
            throw new CustomerWasRegisteredException(customer.getPhone());
        }

        packageDao.addCustomer(
                customer.getFullName(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getPassword()
        );
    }

    private boolean isCustomerExists(Customer customer){
        return packageDao.findCustomerByPhone(customer.getPhone()) != null;
    }
}
