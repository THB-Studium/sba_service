package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration(exclude = { SolrAutoConfiguration.class })
@EntityScan(basePackages = { "com.example.demo.model" })
public class SbaServiceApplication {
	public static void main(String[] args) {
		
		/*** create application ***/
        SpringApplication app = new SpringApplication(SbaServiceApplication.class);

        /*** add pid / port file writers ***/
        app.addListeners(new ApplicationPidFileWriter());

        /*** run application ***/
        app.run(args);		
		
	}
}
