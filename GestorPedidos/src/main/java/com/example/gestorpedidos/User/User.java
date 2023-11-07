package com.example.gestorpedidos.User;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {

    private int id;
    private String Username;
    private String password;
    private String email;

}
