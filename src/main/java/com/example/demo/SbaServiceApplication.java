package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.model.Adresse;
import com.example.demo.model.Buch;
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
		userRepository.save(new User("Steve", "Ngalamo", null, 111111, "steve", adresses.get(0)));
		userRepository.save(new User("Junior", "Wagueu", null, 222222, "junior", adresses.get(0)));
		userRepository.save(new User("Flora", "Goufack", null, 333333, "flora", adresses.get(0)));
		userRepository.save(new User("Dorline", "Damesse", null, 444444, "dorline", adresses.get(0)));
		userRepository.save(new User("Patricia", "Fotso", null, 555555, "patricia", adresses.get(0)));
		
		// BUCH:
		buchRepository.save(new Buch("Das kleine weiße Pferd", "Goudge", "ZEIT-Verlag", 2002, new Date("25.01.2009"), "3-938899-46-8", "Buch", 354, "Gut", null));
	}
}
