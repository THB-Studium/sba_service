package com.example.demo.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Mahnung;
import com.example.demo.repository.MahnungRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class MahnungService {
    
    @Autowired
    private MahnungRepository mahnungRepository;
    
    /**
     * TO GET ALL MAHNUNGEN
     * 
     * @return
     */
    public Set<Mahnung> listAll() {
        return mahnungRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    /**
     * TO GET ALL MAHNUNGEN BY USER ID
     * 
     * @param userId
     * @return
     */
    public Set<Mahnung> listAllByUserId(UUID userId) {
        return mahnungRepository.findAllByUserId(userId);
    }
    
    
    /**
     * TO GET ONE MAHNUNG BY MAHNUNG´S ID
     * 
     * @param mahnungId
     * @return
     */
    public Mahnung getOne(UUID mahnungId) {
        Optional<Mahnung> mahnungOp = mahnungRepository.findById(mahnungId);
        if (mahnungOp.isPresent()) {
            return mahnungOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Mahnung with the id %s does not exist", mahnungId.toString()));
        }
    }
    
    /**
     * TO DELETE AN AUSLEIHE
     * 
     * @param mahnungId
     */
    public void delete(UUID mahnungId) {
        Mahnung manungFound = getOne(mahnungId);
        mahnungRepository.delete(manungFound);
    }

}
