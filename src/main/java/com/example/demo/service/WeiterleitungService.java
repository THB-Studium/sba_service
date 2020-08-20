package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Weiterleitung;

@Service
public class WeiterleitungService {
    
    public Weiterleitung create() {
        Weiterleitung weiterleitung = new Weiterleitung();
        
        return weiterleitung;
    }

}
