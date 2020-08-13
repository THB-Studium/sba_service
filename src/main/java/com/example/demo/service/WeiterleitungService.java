package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Weiterleitung;
import com.geminiald.aclsmanagement.services.AclsService;

@Service
public class WeiterleitungService {
    
    @Autowired
    private AclsService aclsService;
    
    public Weiterleitung create() {
        Weiterleitung weiterleitung = new Weiterleitung();
        
        return weiterleitung;
    }

}
