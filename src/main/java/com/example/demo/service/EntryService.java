package com.example.demo.service;

import com.example.demo.Entity.Entry;
import com.example.demo.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;



    public void saveEntry(Entry entry){
        entryRepository.save(entry);
    }

    public List<Entry> getEntries(){
        return entryRepository.findAll();
    }

    public Optional<Entry> getEntryById(String id){
        return entryRepository.findById(id);
    }
    public boolean deleteEntry(String id){
        entryRepository.deleteById(id);
        return true;
    }
}
