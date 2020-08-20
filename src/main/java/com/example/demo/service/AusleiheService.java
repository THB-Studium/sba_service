package com.example.demo.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ausleihen;
import com.example.demo.model.Buch;
import com.example.demo.repository.AusleiheRepository;
import com.example.demo.repository.BuchRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class AusleiheService {
    
    @Autowired
    private AusleiheRepository ausleiheRepository;
    @Autowired
    private BuchRepository buchRepository;
    @Autowired
    private BuchService buchService;
    
    private Ausleihen addAusleihe(UUID userId, String signatur) {
        buchService.getOne(userId);
        Buch buchFound = buchRepository.findOneBySignatur(signatur);
        if (buchFound != null) {
            Ausleihen newAusleihe = new Ausleihen();
            return ausleiheRepository.save(newAusleihe);
        } else {
            throw new ResourceClosedException(
                    String.format("A book with the signatur %s does not exist", signatur));
        }
    }
}
