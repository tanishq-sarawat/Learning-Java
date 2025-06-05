package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.service.EntryService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EntryService entryService;

    @GetMapping
    public List<User> getEntries() {
        return userService.getEntries();
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        userService.saveEntry(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public  User getUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        if (user != null) {
            new ResponseEntity<>(user, HttpStatus.FOUND);
            return user;
        }
        new ResponseEntity<>("nahi ha merpe", HttpStatus.BAD_REQUEST);
        return null;
    }

    @DeleteMapping("Delete/{username}")
    public ResponseEntity<?> deleteuser(@PathVariable String username) {
        User a1 = getUser(username);
        if (a1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteEntry(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("Update/{username}")
    public ResponseEntity<?> updateuser(@PathVariable String username,@RequestBody User user){
        User a1 = getUser(username);
        if (a1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        a1.setUserName(user.getUserName());
        a1.setPassword(user.getPassword());
        userService.saveEntry(a1);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
