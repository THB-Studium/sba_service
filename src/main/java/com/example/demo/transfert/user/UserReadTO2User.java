package com.example.demo.transfert.user;

import com.example.demo.model.User;

public class UserReadTO2User {

    public static final User apply(UserReadTO in) {
        User out = new User();
        out.setId(in.getId());
        out.setBenutzernummer(in.getBenutzernummer());
        return out;
    }
}
