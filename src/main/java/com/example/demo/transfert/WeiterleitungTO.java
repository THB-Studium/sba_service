package com.example.demo.transfert;

import java.util.UUID;

public class WeiterleitungTO {
    private UUID currentUserId;
    private String buchSignatur;
    
    
    public UUID getCurrentUserId() {
        return currentUserId;
    }
    public void setCurrentUserId(UUID currentUserId) {
        this.currentUserId = currentUserId;
    }
    public String getBuchSignatur() {
        return buchSignatur;
    }
    public void setBuchSignatur(String buchSignatur) {
        this.buchSignatur = buchSignatur;
    }
    
    
}
