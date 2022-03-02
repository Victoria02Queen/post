package com.example.post.dto;

import lombok.Data;

@Data
public class Customer {
    Long id;
    String fullName;
    String phone;
    String address;
    String password;

    public Customer(Long id, String fullName, String phone, String address, String password) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public Customer() {

    }
}
