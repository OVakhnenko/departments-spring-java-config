package com.vakhnenko.departments.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
