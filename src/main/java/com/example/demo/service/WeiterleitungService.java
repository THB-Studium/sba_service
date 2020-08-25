package com.example.demo.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Ausleihe;
import com.example.demo.model.Buch;
import com.example.demo.model.User;
import com.example.demo.model.Weiterleitung;
import com.example.demo.repository.AusleiheRepository;
import com.example.demo.repository.WeiterleitungRepository;
import com.example.demo.transfert.WeiterleitungTO;

@Service
@Transactional(rollbackOn = Exception.class)
public class WeiterleitungService {
    
    @Autowired
    private WeiterleitungRepository weiterleitungRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AusleiheRepository ausleiheRepository;
    @Autowired
    private AusleiheService ausleiheService;
    
    
    
    /**
     * TO LIST ALL WEITERLEITUNG
     * 
     * @return
     */
    public Set<Weiterleitung> listAll() {
        return weiterleitungRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    
    /**
     * TO GET ONE WEITERLEITUNG BY ID
     * 
     * @param weiterleitungId
     * @return
     */
    public Weiterleitung getOne(UUID weiterleitungId) {
        Optional<Weiterleitung> weiterleitungOp = weiterleitungRepository.findById(weiterleitungId);
        if (weiterleitungOp.isPresent()) {
            return weiterleitungOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Weiterleitung with the id %s does not exist", weiterleitungId.toString()));
        }
    }   
    
    
    /**
     * TO ADD A NEW WEITERLEITUNG
     * 
     * @param weiterleitungTO
     * @return
     */
    public Weiterleitung create(WeiterleitungTO weiterleitungTO) {
        Ausleihe altAusleihe = ausleiheService.getOneBySignatur(weiterleitungTO.getBuchSignatur());
        Buch buch = altAusleihe.getBuch();
        
        User altBesitzer = userService.getOneById(altAusleihe.getUser().getId());
        User neuBesitzer = userService.getOneById(weiterleitungTO.getCurrentUserId());
        

        Ausleihe newAusleihe = new Ausleihe(new Date());
        Date bis = new Date(newAusleihe.getVon().getTime() + (3 * 7 * 24 * 60 * 60 * 1000));
        newAusleihe.setId(null);
        newAusleihe.setUser(neuBesitzer);
        newAusleihe.setBuch(buch);
        newAusleihe.setBis(bis);        
        
        ausleiheService.deleteAusleihe(altAusleihe.getId());
        ausleiheRepository.save(newAusleihe);
        

        Weiterleitung weiterleitung = new Weiterleitung();
        weiterleitung.setId(null);
        weiterleitung.setAltBesitzer(altBesitzer);
        weiterleitung.setNeuBesitzer(neuBesitzer);
        weiterleitung.setBuch(buch);
        weiterleitung.setDatum(new Date());
        
        weiterleitung = weiterleitungRepository.save(weiterleitung);
        
        return weiterleitung;
    }

}
