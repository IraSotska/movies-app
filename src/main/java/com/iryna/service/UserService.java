package com.iryna.service;

public interface UserService {

    String loginUser(String userName, String password);
    void logoutUser(String userName);
}
