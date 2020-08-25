package com.example.demo.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceBadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Buch;
import com.example.demo.model.Favory;
import com.example.demo.model.User;
import com.example.demo.repository.FavoryRepository;
import com.example.demo.transfert.FavoryTO;

@Service
@Transactional(rollbackOn = Exception.class)
public class FavoryService {
    
    @Autowired
    private FavoryRepository favoryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BuchService buchService;
    
    
    
    
    /**
     * TO LIST ALL FAVORIES
     * 
     * @return
     */
    public Set<Favory> listAll() {
        return favoryRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    
    /**
     * TO LIST ALL FAVORIES BY USER ID
     * 
     * @return
     */
    public Set<Favory> listAllByUserId(UUID userId) {
        return favoryRepository.findAllByUserId(userId);
    }
    
    
    /**
     * TO GET ONE FAVORY BY ID
     * 
     * @param favoryId
     * @return
     */
    public Favory getOne(UUID favoryId) {
        Optional<Favory> favoryOp = favoryRepository.findById(favoryId);
        if (favoryOp.isPresent()) {
            return favoryOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("A Favory with the id %s does not exist", favoryId.toString()));
        }
    }
    
    
    /**
     * TO AD A NEW FAVORY
     * 
     * @param favoryTO
     * @return
     */
    public Favory create(FavoryTO favoryTO) {
        User user = userService.getOneById(favoryTO.getUserId());
        Buch buch = buchService.getOne(favoryTO.getBuchId());
        
        Favory newFavory = favoryRepository.findOneByUserId(user.getId());
        
        if (newFavory != null) {
            newFavory.getBuecher().add(buch);            
            return favoryRepository.save(newFavory);
        } else {
            newFavory = new Favory();
            newFavory.setId(null);
            newFavory.setUser(user);
            newFavory.getBuecher().add(buch);
            
            return favoryRepository.save(newFavory);
        }
    }
    
    
    /**
     * TO REMOVE A FAVORY
     * 
     * @param favoryTO
     */
    public void delete(FavoryTO favoryTO) {
        User user = userService.getOneById(favoryTO.getUserId());
        Buch buch = buchService.getOne(favoryTO.getBuchId());
        
        Favory favoryFound = favoryRepository.findOneByUserId(user.getId());
        
        if (favoryFound != null) {
            if (favoryFound.getBuecher().size() > 1) {
                favoryFound.getBuecher().remove(buch);            
                favoryRepository.save(favoryFound);
            } else if (favoryFound.getBuecher().size() == 1) {
                favoryRepository.delete(favoryFound);
            }
        } else {
            throw new ResourceBadRequestException(
                    String.format("This user with the id %s does've favories", user.getId().toString()));
        }
    }

}
