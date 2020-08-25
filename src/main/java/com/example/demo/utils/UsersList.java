package com.example.demo.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Adresse;
import com.example.demo.model.User;
import com.example.demo.repository.AdresseRepository;
import com.example.demo.repository.UserRepository;

@Component
public class UsersList {
    private static final Logger log = LoggerFactory.getLogger(UsersList.class);
    
    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UsersList() { }
    
    public void buildUser() {
        log.info("------------- User build started ------------");
        
        // ADRESSE:
        List<Adresse> adresses = adresseRepository.findAll();
        if (adresses.isEmpty() || adresses == null) {
            adresseRepository.save(new Adresse("August-Bebel-Str. 15", 14770, "Brandenburg a.d.H", "Deutschland"));
            adresses = adresseRepository.findAll();
        }
        
        log.info("------------- Adresse is done ------------");

        // USER:
        User[] users = {
                new User("Steve", "Ngalamo", "musterMail@muster.com", "11111111111", passwordEncoder.encode("password"), adresses.get(0)),
                new User("Junior", "Wagueu", "musterMail@muster.com", "22222222222", passwordEncoder.encode("password"), adresses.get(0)),
                new User("Flora", "Goufack", "musterMail@muster.com", "33333333333", passwordEncoder.encode("password"), adresses.get(0)),
                new User("Dorline", "Damesse", "musterMail@muster.com", "4444444444", passwordEncoder.encode("password"), adresses.get(0)),
                new User("Patricia", "Fotso", "musterMail@muster.com", "5555555555", passwordEncoder.encode("password"), adresses.get(0))
        };

        for (User user : users) {
            User userFound = userRepository.findOneByBenutzernummer(user.getBenutzernummer());
            if (userFound == null) {
                userRepository.save(user);
            }
        }
        
        log.info("------------- User build is done ------------");
    }
}
