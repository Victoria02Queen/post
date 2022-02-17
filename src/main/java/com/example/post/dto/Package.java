package com.example.post.dto;

import lombok.Value;

@Value
public class Package {
    String name;
    String phone;
    String trackNumber;
    User recipient;
    User sender;
    String location;
}
