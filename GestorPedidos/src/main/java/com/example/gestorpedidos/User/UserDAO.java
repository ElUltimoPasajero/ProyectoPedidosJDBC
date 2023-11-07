package com.example.gestorpedidos.User;

import java.util.ArrayList;

public interface UserDAO {

    ArrayList<User> getAll();

    public User get();

    public User validateUser(String usuario, String password);

}
