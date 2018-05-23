package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.User;
import com.example.utcn.medpat.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {

    @Inject
    UserRepository userRepository;

    public User login(String username, String password) {
        User user = userRepository.findUserById(username);

        if(password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }

    public User getUser(String username) {
        return userRepository.findUserById(username);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
