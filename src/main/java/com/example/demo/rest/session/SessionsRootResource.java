package com.example.demo.rest.session;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceUnauthorizedException;
import com.example.demo.rest.ApiConstants;
import com.example.demo.service.authencation.AuthenticationService;
import com.example.demo.service.authencation.AuthenticationSessionRepositoryException;
import com.example.demo.service.authencation.Credentials;
import com.example.demo.utils.UrlUtils;

@RestController
@RequestMapping(ApiConstants.SESSIONS_COLLECTION)
public class SessionsRootResource {

    private static final Logger log = LoggerFactory.getLogger(SessionsRootResource.class);

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST)
    public OAuth2AccessToken login(@RequestHeader HttpHeaders headers) {

        @SuppressWarnings("static-access")
        List<String> authorizations = headers.get(headers.AUTHORIZATION);

        if (authorizations.isEmpty()) {
            throw new ResourceUnauthorizedException("an authorization credential is required");
        }

        String authorization = authorizations.get(0);

        Credentials credentials = UrlUtils.extractCredentialsFromBasicAuthorization(authorization);
        if (credentials == null) {
            throw new ResourceUnauthorizedException(
                    String.format("failed to extract credentials from auth header '%s'", authorization));
        }

        // get username
        String username = credentials.getUsername();
        if (username == null || username.isEmpty()) {
            throw new RuntimeException("the username must not be null and not be empty");
        }
        log.debug("attempt to login as '{}'", username);

        // create session
        return authenticationService.createAuthenticationSession(credentials);
    }

    @RequestMapping(path = "/{sessionId}", method = RequestMethod.DELETE)
    public void logout(@PathVariable("sessionId") String sessionId) throws AuthenticationSessionRepositoryException {
        authenticationService.deleteAuthenticationSession(sessionId);
        log.debug("session '{}' deleted", sessionId);
    }

    @RequestMapping(path = "/{sessionId}/user_details", method = RequestMethod.GET)
    public OAuth2AccessToken getUserDetails(@PathVariable("sessionId") String sessionId) {
        // get the session
        return getSession(sessionId);
    }

    private OAuth2AccessToken getSession(String sessionId) {
        return authenticationService.getAuthenticationSession(sessionId);
    }
}
