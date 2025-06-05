package com.example.demo.controller;

import com.example.demo.Entity.Entry;
import com.example.demo.Entity.User;
import com.example.demo.service.EntryService;
import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;

import org.bson.types.ObjectId;
import com.example.demo.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;



@RestController
@RequestMapping("/Entries")
public class EntryController {

    @Autowired
    private EntryService entryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Entry> getEntries() {
        return entryService.getEntries();
    }
    @Transactional
    @PostMapping("/{username}")
    public ResponseEntity<Object> addEntry(@RequestBody Entry entry,@PathVariable String username){
        try {
            entry.setDate(LocalDateTime.now());
            entryService.saveEntry(entry);
            User user = userService.findByUserName(username);
            if (user != null) {
                user.getEntries().add(entry);
            }

            userService.saveEntry(user);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("map/{path}")
    public ResponseEntity<?> getEntry(@PathVariable String path) {
        Optional<Entry> entry = entryService.getEntryById(path);
        if(entry.isPresent()) {
            return new ResponseEntity<> (entry,HttpStatus.OK);
        }

        return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("map/{username}/{del}")
    public ResponseEntity<?> deleteEntry(@PathVariable String  del, @PathVariable String username) {
        Optional<Entry> a = Optional.ofNullable(entryService.getEntryById(del).orElse(null));
        User user = userService.findByUserName(username);
        user.getEntries().removeIf(e -> e.getId().equals(del));
        userService.saveEntry(user);
        entryService.deleteEntry( del);
        return new ResponseEntity<> (a,HttpStatus.NO_CONTENT);
    }

    @PutMapping("map/{put1}")
    public ResponseEntity<?> updateEntry(@PathVariable String put1, @RequestBody Entry entry) {
        Entry entry1 = entryService.getEntryById(put1).orElse(null);
        if (entry1 != null) {
            entry1.setTitle(entry.getTitle() != ""  ? entry.getTitle() : entry1.getTitle()) ;
            entry1.setContent(entry.getContent());
            entryService.saveEntry(entry1);
            return ResponseEntity.ok(entry1);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
