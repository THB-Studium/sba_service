package com.example.demo;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.exception.ResourceUnauthorizedException;
import com.example.demo.model.Adresse;
import com.example.demo.model.User;
import com.example.demo.repository.AdresseRepository;
import com.example.demo.repository.BuchRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration(exclude = { SolrAutoConfiguration.class })
@EntityScan(basePackages = { "com.example.demo.model" })
public class SbaServiceApplication implements CommandLineRunner {

    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuchRepository buchRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder,
            UserRepository repository)
            throws Exception {

        builder.userDetailsService(new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = repository.findOneByBenutzernummer(username);
                if (user == null) {
                    throw new ResourceUnauthorizedException(String.format("The user %s does not exists", username));
                }

                return new User(repository.findOneByBenutzernummer(username));
            }
        });
    }

    public static void main(String[] args) {

        /*** create application ***/
        SpringApplication app = new SpringApplication(SbaServiceApplication.class);

        /*** add pid / port file writers ***/
        app.addListeners(new ApplicationPidFileWriter());

        /*** run application ***/
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        // ADRESSE:
        adresseRepository.save(new Adresse("August-Bebel-Str. 15", 14770, "Brandenburg a.d.H", "Deutschland"));
        List<Adresse> adresses = adresseRepository.findAll();

        // USER:
        userRepository.save(new User("Steve", "Ngalamo", null, "111111", passwordEncoder.encode("steve"), adresses.get(0)));
        userRepository.save(new User("Junior", "Wagueu", null, "222222", passwordEncoder.encode("junior"), adresses.get(0)));
        userRepository.save(new User("Flora", "Goufack", null, "333333", passwordEncoder.encode("flora"), adresses.get(0)));
        userRepository.save(new User("Dorline", "Damesse", null, "444444", passwordEncoder.encode("dorline"), adresses.get(0)));
        userRepository.save(new User("Patricia", "Fotso", null, "555555", passwordEncoder.encode("patricia"), adresses.get(0)));

        // BUCH:
        // buchRepository.save(new Buch("Das kleine weiße Pferd", "Goudge",
        // "ZEIT-Verlag", 2002, new Date("25.01.2009"), "3-938899-46-8", "Buch",
        // 354, "Gut", null));
    }
}
