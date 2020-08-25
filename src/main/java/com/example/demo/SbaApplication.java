package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.example.demo.model.Adresse;
import com.example.demo.model.Buch;
import com.example.demo.model.User;
import com.example.demo.repository.AdresseRepository;
import com.example.demo.repository.BuchRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.rest.ausleihe.AusleiheRootResource;

@SpringBootApplication
@EnableJpaAuditing
@EnableResourceServer
@EnableScheduling
@EnableJpaRepositories(basePackages = { "com.example.demo.repository" })
@EntityScan(basePackages = { "com.example.demo.model" })
public class SbaApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(AusleiheRootResource.class);

    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuchRepository buchRepository;
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
//        buildUser();
//        buildTest();
    }

    private void buildUser() {

        log.info("---------------------  START BUILD USER -----------------------------------------------------------------");
        // ADRESSE:
        List<Adresse> adresses = adresseRepository.findAll();
        if (adresses.isEmpty()) {
            adresseRepository.save(new Adresse("August-Bebel-Str. 15", 14770, "Brandenburg a.d.H", "Deutschland"));
            adresses = adresseRepository.findAll();
        }

        // USER:
        User[] users = {
                new User("Steve", "Ngalamo", "musterMail_1@muster.com", "11111111111", passwordEncoder.encode("password"),
                        adresses.get(0)),
                new User("Junior", "Wagueu", "musterMail_2@muster.com", "22222222222", passwordEncoder.encode("password"),
                        adresses.get(0)),
                new User("Flora", "Goufack", "musterMail_3@muster.com", "33333333333", passwordEncoder.encode("password"),
                        adresses.get(0)),
                new User("Dorline", "Damesse", "musterMail_4@muster.com", "4444444444", passwordEncoder.encode("password"),
                        adresses.get(0)),
                new User("Patricia", "Fotso", "musterMail_5@muster.com", "5555555555", passwordEncoder.encode("password"),
                        adresses.get(0)),
                new User("MusterNickname1", "MusterName1", "musterMail_4@muster.com", "66666666666", passwordEncoder.encode("password"),
                        adresses.get(0)),
                new User("MusterNickname2", "MusterNickname2", "musterMail_5@muster.com", "77777777777", passwordEncoder.encode("password"),
                        adresses.get(0))
        };

        for (User user : users) {
            User userFound = userRepository.findOneByBenutzernummer(user.getBenutzernummer());
            if (userFound == null) {
                userRepository.save(user);
            }
        }

        log.info("---------------------  END -----------------------------------------------------------------");
    }

    private void buildBuch() throws IOException {
        log.info("---------------------  START BUILD TEST ---------------------------------------------------");

        String path = "C:/Users/egoue/Documents/book.xlsx";
        File excelFile = new File(path);
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        Buch currentBuch = new Buch();

        int i = 0;

        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            if (i > 0 && i < 150) {

                // iterate on cells for the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    if (i < 100) {
                        Cell cell = cellIterator.next();

                        if (cell.getAddress().toString().contains("B")) {
                            currentBuch.setTitel(cell.toString());
                        }

                        if (cell.getAddress().toString().contains("C")) {
                            currentBuch.setAutor(cell.toString());
                        }

                        if (cell.getAddress().toString().contains("N")) {
                            currentBuch.setErscheinungsdatum(cell.toString().replace(".", ""));
                        }

                        if (cell.getAddress().toString().contains("I")) {
                            currentBuch.setIsbn13(cell.toString().split("E")[0].replace(".", ""));
                        }

                        if (cell.getAddress().toString().contains("J")) {
                            currentBuch.setIsbn10(cell.toString().split("E")[0].replace(".", ""));
                        }

                        if (cell.getAddress().toString().contains("I")) {
                            currentBuch.setIsbn(cell.toString().split("E")[0].replace(".", ""));
                        }

                        if (cell.getAddress().toString().contains("K")) {
                            currentBuch.setPreis(cell.toString());
                        }

                        if (cell.getAddress().toString().contains("M")) {
                            currentBuch.setVerlag(cell.toString());
                        }

                        if (cell.getAddress().toString().contains("N")) {
                            currentBuch.setErscheinungsjahr(Long.parseLong(cell.toString().split("-")[2]));
                        }

                        if (cell.getAddress().toString().contains("O")) {
                            currentBuch.setAusgabe(cell.toString());
                        }

                        if (cell.getAddress().toString().contains("R")) {
                            currentBuch.setSeiten(Long.parseLong(cell.toString().replace(".", "")));
                        }

                        if (cell.getAddress().toString().contains("T")) {
                            currentBuch.setUebersicht(cell.toString());
                        }

                        if (cell.getAddress().toString().contains("P")) {
                            currentBuch.setKategorie(cell.toString());
                        }

                        if (cell.getAddress().toString().contains("V")) {
                            currentBuch.setAnmerkungen(cell.toString().replace("<p>", "").replace("</p>", "").replace("<P>", "")
                                    .replace("</P>", ""));
                        }

                        currentBuch.setVerfuegbarkeit(setVerfuegbarkeit());
                        currentBuch.setMedienart(setMedienArt());
                        currentBuch.setZustand("neu");
                        currentBuch.setSprache("eng");
                        currentBuch.setExemplare(setExemplare());

                        for (int c = 0; c <= currentBuch.getExemplare(); c++) {
                            String signatur = String.format("%s_Cp%s", currentBuch.getIsbn10(), c + 1);
                            currentBuch.setId(null);
                            currentBuch.setSignatur(signatur);
                            buchRepository.save(currentBuch);
                        }
                    }
                }
            }
            i++;
        }

        workbook.close();
        fis.close();
        log.info(currentBuch.toString());
        log.info("------------------------------- END ------------------------- ");
    }

    private String setMedienArt() {
        String medienArt = "";
        Random coin = new Random();
        int toss;
        toss = coin.nextInt(2);
        switch (toss) {
        case 0:
            medienArt = "Buch";
            break;
        case 1:
            medienArt = "E-Buch";
            break;
        case 2:
            medienArt = "Compact-disc";
            break;
        }
        return medienArt;
    }

    private String setVerfuegbarkeit() {
        String verfuegbarkeit = "";
        Random coin = new Random();
        int toss;
        toss = coin.nextInt(2);
        switch (toss) {
        case 0:
            verfuegbarkeit = "entliehen";
            break;
        case 1:
            verfuegbarkeit = "ausliehbar";
            break;
        default:
            verfuegbarkeit = "entliehen";
            break;

        }
        return verfuegbarkeit;
    }

    private long setExemplare() {
        return (new Random().nextInt(5) + 1);
    }

}
