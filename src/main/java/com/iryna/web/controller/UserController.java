package com.iryna.web.controller;

import com.iryna.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class UserController {

    private UserServiceImpl userService;

    @Value("${timeToLiveSession}")
    private int timeToLiveSession;

    @PostMapping("/login")
    public void login(@RequestParam String userName,
                        @RequestParam String password,
                        HttpServletResponse response) {
        String token = userService.loginUser(userName, password);
        if (token != null) {
            Cookie cookie = new Cookie("user-token", token);
            cookie.setMaxAge(timeToLiveSession);
            response.addCookie(cookie);
            log.info("Login successful");
        }
    }

    @DeleteMapping("/logout")
    public void logout(@CookieValue("user-token") Cookie cookie) {
        userService.logoutUser(cookie.getValue());
            log.info("user logout");
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
