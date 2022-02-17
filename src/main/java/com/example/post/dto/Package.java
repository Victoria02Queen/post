package com.example.post.dto;

import lombok.Value;

@Value
public class Package {
    String name;
    String phone;
    String trackNumber;
    Customer recipient;
    Customer sender;
    String location;
}
