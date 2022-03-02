package com.example.post.dto;

import lombok.Value;

@Value
public class Package {
    String name;
    String trackNumber;
    Customer recipient;
    Employee employee;
    Customer sender;
    String location;
}
