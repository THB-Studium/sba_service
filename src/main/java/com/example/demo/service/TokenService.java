package com.example.demo.service;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceUnauthorizedException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class TokenService {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private UserRepository userRepository;

    public void revoke(String authorization) {

        if (authorization != null) {
            String token = authorization.trim().replace("Bearer ", "");
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
            tokenStore.removeAccessToken(accessToken);
        } else {
            throw new ResourceUnauthorizedException("The bearer token must not be null");
        }

    }

    public User getConnectedUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            return null;
        } else {
            String username = principal.getName();
            User user = userRepository.findOneByBenutzernummer(username);
            return user;
        }
    }

}