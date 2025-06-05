package com.example.demo.service;

import com.example.demo.Entity.Entry;
import com.example.demo.Entity.User;
import com.example.demo.repository.EntryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getEntries(){
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(String id){
        return userRepository.findById(id);
    }
    public boolean deleteEntry(String username){

        userRepository.deleteById(username);
        return true;
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
