package com.example.post.services.exceptions;

public class CustomerWasRegisteredException extends Exception{
    public CustomerWasRegisteredException(String phone){
        super("Customer was registered with phone=" + phone);
    }
}
