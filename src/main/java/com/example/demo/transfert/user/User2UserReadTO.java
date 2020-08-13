package com.example.demo.transfert.user;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.User;

public class User2UserReadTO {

    public static final UserReadTO apply(User in) {
        UserReadTO out = new UserReadTO();
        out.setId(in.getId());
        out.setBenutzernummer(in.getBenutzernummer());
        out.roles(in.getRoles());
        return out;
    }

    public static final List<UserReadTO> apply(List<User> in) {
        return in.stream().map(user -> User2UserReadTO.apply(user)).collect(Collectors.toList());
    }
}
