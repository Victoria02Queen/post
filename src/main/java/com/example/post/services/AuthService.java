package com.example.post.services;

import com.example.post.dao.PackageDao;
import com.example.post.dto.Customer;
import com.example.post.services.exceptions.CustomerWasRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class AuthService {
    PackageDao packageDao;

    public AuthService(PackageDao packageDao) throws CustomerWasRegisteredException {
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

    @Nullable
    public Customer auth(String phone, String password){
        Customer customer = packageDao.findCustomerByPhone(phone);

        if (customer == null) return null;

        return customer.getPhone().equals(phone) ? customer : null;
    }

    @Nullable
    public Customer getLoggedCustomer(HttpSession httpSession){
        Customer customer = (Customer) httpSession.getAttribute("customer");

        return customer;
    }

    private boolean isCustomerExists(Customer customer){
        return packageDao.findCustomerByPhone(customer.getPhone()) != null;
    }
}
