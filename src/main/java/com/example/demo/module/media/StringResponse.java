package com.example.demo.module.media;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringResponse {

    private String response;

    public StringResponse(String s) {
        this.response = s;
    }
}