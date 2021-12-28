package com.icarlosalbertojr.forum.services;

import com.icarlosalbertojr.forum.exceptions.DataNotFoundException;
import com.icarlosalbertojr.forum.models.User;
import com.icarlosalbertojr.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
    }

}
