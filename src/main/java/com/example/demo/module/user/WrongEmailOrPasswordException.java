package com.example.demo.module.user;

public class WrongEmailOrPasswordException extends Exception{
    public WrongEmailOrPasswordException(String message) {
        super(message);
    }
}
