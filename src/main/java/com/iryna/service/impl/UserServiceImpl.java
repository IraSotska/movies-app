package com.iryna.service.impl;

import com.iryna.entity.User;
import com.iryna.security.SecurityService;
import com.iryna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private SecurityService securityService;

    @Override
    public String loginUser(String userName, String password) {
        return securityService.login(new User(userName, password));
    }

    @Override
    public void logoutUser(String token) {
        securityService.logout(token);
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
