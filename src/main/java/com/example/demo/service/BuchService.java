package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Buch;
import com.example.demo.repository.BuchRepository;
import com.example.demo.search.criteria.BuchCriteria;
import com.example.demo.search.specs.BuchSpecs;

@Service
@Transactional(rollbackOn = Exception.class)
public class BuchService {
    
    @Autowired
    private BuchRepository buchRepository;
    
    /**
     * TO GET ALL BOOKS
     * 
     * @return
     */
    public Set<Buch> listAll(){
        return buchRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    
    /**
     * TO GET ONE BOOK BY ID
     * 
     * @param buchId
     * @return
     */
    public Buch getOne(UUID buchId){
        Optional<Buch> buchOp = buchRepository.findById(buchId);
        if (buchOp.isPresent()) {
            return buchOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("A buch with the id %s does not exist", buchId.toString()));
        }
    }
    
    /**
     * TO SEARCH A BOOK BY CRITERIA
     * 
     * @param buchCriteria
     * @return
     */
    public Set<Buch> search(BuchCriteria buchCriteria) {
        BuchSpecs specs = new BuchSpecs(buchCriteria);
        return buchRepository.findAll(specs).stream().collect(Collectors.toSet());
    } 

}
