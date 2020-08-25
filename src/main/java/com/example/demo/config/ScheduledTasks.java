package com.example.demo.config;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.Ausleihe;
import com.example.demo.model.Mahnung;
import com.example.demo.model.Reservierung;
import com.example.demo.repository.AusleiheRepository;
import com.example.demo.repository.MahnungRepository;
import com.example.demo.repository.ReservierungRepository;

@Component
public class ScheduledTasks {
//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private ReservierungRepository reservierungRepository;
    @Autowired
    private AusleiheRepository ausleiheRepository;
    @Autowired
    private MahnungRepository mahnungRepository;
    
    
    /**
     * SHEDULING TO CHECK THE STATUS OF THE RESERVIERUNGEN 
     * 
     */
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    private void checkReservierungStatus() {
        Set<Reservierung> reservierungen = reservierungRepository.findAll().stream().collect(Collectors.toSet());
        for (Reservierung reservierung : reservierungen) {
            Date alhollungsTag = new Date(reservierung.getBis().getTime() + (2 * 24 * 60 * 60 * 1000));
            if (alhollungsTag.before(new Date())) {
                reservierungRepository.delete(reservierung);
            }
        }
    }
    
    
    /**
     * SHEDULING TO CHECK THE STATUS OF THE AUSLEIHE
     * 
     */
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    private void checkAusleiheStatus() {
        Set<Ausleihe> ausleihen = ausleiheRepository.findAll().stream().collect(Collectors.toSet());
        
        for (Ausleihe ausleihe : ausleihen) {
            if (ausleihe.getBis().before(new Date())) {
                Mahnung mahnung = mahnungRepository.findOneByBuchSignatur(ausleihe.getBuch().getSignatur());
                
                if (mahnung == null) {
                    mahnung = new Mahnung(new Date());
                    mahnung.setUser(ausleihe.getUser());
                    mahnung.setBuch(ausleihe.getBuch());
                    mahnung.setMahnungBetrag(0.15);
                    mahnung.setId(null);
                    
                    mahnungRepository.save(mahnung);
                }
            }
        }
    }
    
    
//    /**
//     * SHEDULING TO CHECK THE STATUS OF THE MANUNGEN
//     * 
//     */
//    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
//    @Scheduled(fixedRate = 5000)
//    private void checkMahnungStatus() {
//        Set<Mahnung> mahnungen = mahnungRepository.findAll().stream().collect(Collectors.toSet());
//        long duration = (new Date().getTime() + 5 * 24 * 60 * 60 * 1000) - new Date().getTime();        
//        log.info("duration: " + duration);
//        
//        double days = duration / 5;
//        log.info("days: " + new Date());
//        
//        
//        for (Mahnung mahnung : mahnungen) {
//            long duration = new Date().getTime() - mahnung.getCreatedDate().getTime();
//            if (mahnung.getCreatedDate().before(new Date())) {
//                
//                
////                mahnungRepository.delete(reservierung);
//            }
//        }
//    }
    
    
}
