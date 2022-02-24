package com.example.post.dto;

import lombok.Value;

@Value
public class Employee {
    long id;
    String fullName;
    String phone;
    PostOffice postOffice;
}
