package com.example.demo.transfert;

import java.util.UUID;

public class UserTO {
    private UUID userUserId;

    // KONSTRUCTOR:

    public UserTO(UUID userUserId) {
        super();
        this.userUserId = userUserId;
    }

    // SETTER & GETTER:

    public UUID getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(UUID userUserId) {
        this.userUserId = userUserId;
    }

}
