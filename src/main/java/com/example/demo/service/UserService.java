package com.example.demo.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * TO GET ALL USERS
     * 
     * @return
     */
    public Set<User> listAll() {
        return userRepository.findAll().stream().collect(Collectors.toSet());
    }
    
    
    /**
     * TO GET ONE USER BY USER´S ID
     * 
     * @param userId
     * @return
     */
    public User getOneById(UUID userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            return userOp.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("An user with the id %s does not exist", userId.toString()));
        }
    }
    
//    /**
//     * TO GET ONE USER BY USER´S ID
//     * 
//     * @param userId
//     * @return
//     */
//    public User getOneByThoken(UUID thoken) {
//        Optional<User> userOp = userRepository.findById(userId);
//        if (userOp.isPresent()) {
//            return userOp.get();
//        } else {
//            throw new ResourceNotFoundException(
//                    String.format("An user with the id %s does not exist", userId.toString()));
//        }
//    }

}
