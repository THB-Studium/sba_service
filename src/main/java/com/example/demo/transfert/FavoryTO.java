package com.example.demo.transfert;

import java.util.UUID;

public class FavoryTO {
    private UUID userId;
    private UUID buchId;
    
    
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public UUID getBuchId() {
        return buchId;
    }
    public void setBuchId(UUID buchId) {
        this.buchId = buchId;
    }    
}
