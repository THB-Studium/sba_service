package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.example.demo.model.Adresse;
import com.example.demo.model.User;
import com.example.demo.repository.AdresseRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableResourceServer
@EnableJpaRepositories(basePackages = {"com.example.demo.repository"})
@EntityScan(basePackages = {"com.example.demo.model"})
public class SbaApplication implements CommandLineRunner {

    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // private BuchRepository buchRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        /*** create application ***/
        SpringApplication app = new SpringApplication(SbaApplication.class);

        /*** add pid / port file writers ***/
        app.addListeners(new ApplicationPidFileWriter());

        /*** run application ***/
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        // ADRESSE:
        List<Adresse> adresses = adresseRepository.findAll();
        if (adresses.isEmpty()) {
            adresseRepository.save(new Adresse("August-Bebel-Str. 15", 14770, "Brandenburg a.d.H", "Deutschland"));
            adresses = adresseRepository.findAll();
        }

        // USER:
        User[] users = {
                new User("Steve", "Ngalamo", null, "11111111111", passwordEncoder.encode("steve"), adresses.get(0)),
                new User("Junior", "Wagueu", null, "22222222222", passwordEncoder.encode("junior"), adresses.get(0)),
                new User("Flora", "Goufack", null, "33333333333", passwordEncoder.encode("flora"), adresses.get(0)),
                new User("Dorline", "Damesse", null, "4444444444", passwordEncoder.encode("dorline"), adresses.get(0)),
                new User("Patricia", "Fotso", null, "5555555555", passwordEncoder.encode("patricia"), adresses.get(0))
        };

        for (User user : users) {
            User userFound = userRepository.findOneByBenutzernummer(user.getBenutzernummer());
            if (userFound == null) {
                userRepository.save(user);
            }
        }

        // BUCH:
        // buchRepository.save(new Buch("Das kleine weiße Pferd", "Goudge",
        // "ZEIT-Verlag", 2002, new Date("25.01.2009"), "3-938899-46-8", "Buch",
        // 354, "Gut", null));
    }
}
