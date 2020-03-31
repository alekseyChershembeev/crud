package ru.example.home.crud.entity.user;

import lombok.Data;

@Data
public class User {

    Long id;
    String userName;
    String password;
    Enum<Role> role;
}
