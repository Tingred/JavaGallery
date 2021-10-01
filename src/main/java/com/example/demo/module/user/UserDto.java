package com.example.demo.module.user;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
public class UserDto {

    @NotNull
    @Size(min = 3,max = 20)
    private String username;
    @NotNull
    private String password;
}
