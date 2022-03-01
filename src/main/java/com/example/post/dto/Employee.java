package com.example.post.dto;

import lombok.Value;

@Value
public class Employee {
    Long id;
    String fullName;
    String phone;
    PostOffice postOffice;
}
