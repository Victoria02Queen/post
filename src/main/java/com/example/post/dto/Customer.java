package com.example.post.dto;

import lombok.Value;

@Value
public class Customer {
    long id;
    String fullName;
    String phone;
    String address;
}
