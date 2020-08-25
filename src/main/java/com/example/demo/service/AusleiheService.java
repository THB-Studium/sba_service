package com.example.demo.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.BuchVerfuegbarkeit;
import com.example.demo.exception.ResourceBadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Ausleihe;
import com.example.demo.model.Buch;
import com.example.demo.model.User;
import com.example.demo.repository.AusleiheRepository;
import com.example.demo.repository.BuchRepository;
import com.example.demo.transfert.AusleiheTO;


@Service
@Transactional(rollbackOn = Exception.class)
public class AusleiheService {
    
    @Autowired
    private AusleiheRepository ausleiheRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BuchService buchService;
    @Autowired
    private BuchRepository buchRepository;
    
    
   
    /**
     * TO LIST ALL AUSLEIHE
     * 
     * @return
     */
    public Set<Ausleihe> listAll() {
        return ausleiheRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    /**
     * TO LIST ALL AUSLEIHE BY USER ID
     * 
     * @param userId
     * @return
     */
    public Set<Ausleihe> listAllByUserId(UUID userId) {
        return ausleiheRepository.findAllByUserId(userId);
    }
    
    /**
     * TO GET ONE AUSLEIHE BY ID
     * 
     * @param ausleiheId
     * @return
     */
    public Ausleihe getOneById(UUID ausleiheId) {
        Optional<Ausleihe> ausleiheOp = ausleiheRepository.findById(ausleiheId);
        if (ausleiheOp.isPresent()) {
            return ausleiheOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Asusleihe with the id %s does not exist", ausleiheId.toString()));
        }
    }
    
    /**
     * TO GET ONE AUSLEIHE BY SIGNATUR
     * 
     * @param ausleiheId
     * @return
     */
    public Ausleihe getOneBySignatur(String signatur) {        
        Ausleihe ausleiheFound = ausleiheRepository.findOneByBuchSignatur(signatur);          
        
        if (ausleiheFound != null) {
            return ausleiheFound;
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Asusleihe with the signatur=%s does not exist", signatur));
        }
    }
    
    /**
     * TO CREATE A NEW AUSLEIHE
     * 
     * @param newAusleihe
     * @return
     */
    public Ausleihe createAusleihe(AusleiheTO ausleiheTO) {
        User user = userService.getOneById(ausleiheTO.getUserId());
        Buch buch = buchService.getOne(ausleiheTO.getBuchId());
        Set<Buch> kopien = buchRepository.findAllByIsbn(buch.getIsbn());
        int ausleihenAnzahl = ausleiheRepository.countByBuchIsbn(buch.getIsbn13());

        Ausleihe newAusleihe = new Ausleihe(new Date());
        newAusleihe.setUser(user);
        newAusleihe.setBuch(buch);
        Date bis = new Date(newAusleihe.getVon().getTime() + (7 * 4 * 24 * 60 * 60 * 1000));
        newAusleihe.setBis(bis);
        newAusleihe.setId(null);
        
        if (kopien.size() > ausleihenAnzahl) {            
            newAusleihe = ausleiheRepository.save(newAusleihe);
        } else if (kopien.size() == ausleihenAnzahl) {
            for (Buch kopie : kopien) {
                kopie.setVerfuegbarkeit(BuchVerfuegbarkeit.ENTLIEHEN.toString().toLowerCase());
                buchRepository.save(kopie);
            }
            newAusleihe = ausleiheRepository.save(newAusleihe);
        } else {
            throw new ResourceBadRequestException(
                    String.format("Warning!!! The number of rentals (Ausleihe = %s)"
                            + " is greater than the number of copies (Kopien = %s).", ausleihenAnzahl, kopien.size()));
        }
        
        return newAusleihe;
    }
    
    /**
     * TO DELETE AN AUSLEIHE
     * 
     * @param ausleiheId
     */
    public void deleteAusleihe(UUID ausleiheId) {
        Ausleihe ausleiheFound = getOneById(ausleiheId);
        Set<Buch> kopien = buchRepository.findAllByIsbn(ausleiheFound.getBuch().getIsbn());
        int ausleihenAnzahl = ausleiheRepository.countByBuchIsbn(ausleiheFound.getBuch().getIsbn());
        
        if (kopien.size() == ausleihenAnzahl) {
            for (Buch kopie : kopien) {
                kopie.setVerfuegbarkeit(BuchVerfuegbarkeit.AUSLIEHBAR.toString().toLowerCase());
                buchRepository.save(kopie);
            }
        }
        
        ausleiheRepository.delete(ausleiheFound);
    }    
    
}
