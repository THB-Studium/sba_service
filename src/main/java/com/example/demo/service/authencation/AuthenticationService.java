package com.example.demo.service.authencation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppSettings;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceUnauthorizedException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.transfert.user.User2UserReadTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.time.DateUtils;


@Service
@Transactional(rollbackOn = Exception.class)
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppSettings settings;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * create an authentication session in the session repository with full
     * resolution of reachable roles and groups
     *
     * @param username
     * @return the id of the session
     * @throws AuthenticationSessionRepositoryException
     * @throws ResolveAuthoritiesException
     * @throws UserNotFoundException
     * @throws UserDisabledException
     */
    public OAuth2AccessToken createAuthenticationSession(Credentials credentials) {
        // get user information
        User user = userRepository.findOneByBenutzernummer(credentials.getUsername());
        if (user == null) {
            throw new ResourceNotFoundException(
                    String.format("the username %s does not exist", credentials.getUsername()));
        }

        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
            throw new ResourceUnauthorizedException(
                    String.format("The password and the username '%s' do not match", credentials.getUsername()));
        }

        TypeReference<Map<String, Object>> mapType = new TypeReference<Map<String, Object>>() {
        };

        // store the session
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(user.getUsername());
        accessToken.setTokenType("bearer");
        accessToken.setValue(UUID.randomUUID().toString());
        accessToken.setExpiration(DateUtils.addSeconds(new Date(), settings.getAccessTokenValidity()));
        accessToken.setAdditionalInformation(objectMapper.convertValue(User2UserReadTO.apply(user), mapType));

        // define user roles
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        // create authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                authorities);

        String clientId = UUID.randomUUID().toString();

        OAuth2Request request = new OAuth2Request(null, clientId, authorities, true, null, null, null, null, null);

        OAuth2Authentication oauth2Authentication = new OAuth2Authentication(request, authentication);

        tokenStore.storeAccessToken(accessToken, oauth2Authentication);

        return tokenStore.readAccessToken(accessToken.getValue());
    }

    /**
     * delete an authentication session from the session repository
     *
     * @param sessionId
     * @throws AuthenticationSessionRepositoryException
     */
    public void deleteAuthenticationSession(String sessionId) {
        // delete the session id from the session store
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(sessionId);

        try {
            tokenStore.removeAccessToken(accessToken);
        } catch (Exception e) {
            log.warn("could not remove access token for session id {}. Reason: ", e.getMessage());
        }
    }

    /**
     * get an authentication session from the session repository
     * 
     * @param sessionId
     * @return
     * @throws AuthenticationSessionRepositoryException
     * @throws AuthenticationSessionNotFoundException
     */
    public OAuth2AccessToken getAuthenticationSession(String sessionId) {
        // get the authentication session
        return tokenStore.readAccessToken(sessionId);
    }

}
