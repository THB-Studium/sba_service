package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sq")
public class AppSettings {
    private String authorizationUsername;

    private String authorizationResourceId;

    private String authorizationSecret;

    private int accessTokenValidity;

    public String getAuthorizationUsername() {
        return authorizationUsername;
    }

    public void setAuthorizationUsername(String authorizationUsername) {
        this.authorizationUsername = authorizationUsername;
    }

    public String getAuthorizationResourceId() {
        return authorizationResourceId;
    }

    public void setAuthorizationResourceId(String authorizationResourceId) {
        this.authorizationResourceId = authorizationResourceId;
    }

    public String getAuthorizationSecret() {
        return authorizationSecret;
    }

    public void setAuthorizationSecret(String authorizationSecret) {
        this.authorizationSecret = authorizationSecret;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(int accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

}
