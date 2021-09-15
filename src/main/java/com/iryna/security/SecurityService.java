package com.iryna.security;

import com.iryna.entity.Session;
import com.iryna.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SecurityService {

    private Map<String, Session> sessionList;

    @Value("${timeToLiveSession}")
    private int timeToLiveSession;

    public String login(User user) {
        return generateAndRegisterToken(user);
    }

    public void logout(String token) {
        sessionList.remove(token);
    }

    public void clearExpiredTokens() {
        sessionList.entrySet().removeIf(sessionEntry -> sessionEntry.getValue().getExpireDate().isAfter(LocalDateTime.now()));
    }

    private String generateAndRegisterToken(User user) {
        String uuid = UUID.randomUUID().toString();
        sessionList.put(uuid, new Session(user, LocalDateTime.now().plusSeconds(timeToLiveSession)));
        return uuid;
    }

    @Autowired
    public void setSessionList(Map<String, Session> sessionList) {
        this.sessionList = sessionList;
    }
}
