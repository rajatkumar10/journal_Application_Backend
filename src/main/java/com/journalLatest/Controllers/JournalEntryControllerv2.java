package com.journalLatest.Controllers;

import com.journalLatest.Entity.JournalEntry;
import com.journalLatest.Service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    private final JournalEntryService journalEntryService;
    @GetMapping("/get")
    public ResponseEntity<List<JournalEntry>>getall() {
        try {
            return new ResponseEntity<>(journalEntryService.getAllEntries(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journalEntry){
        try {
            return new ResponseEntity<>(journalEntryService.saveEntry(journalEntry),HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry>getJournalById(@PathVariable ObjectId myId){
        JournalEntry journalEntry = journalEntryService.findById(myId).orElse(null);
        if (journalEntry!=null){
            return new ResponseEntity<>(journalEntry,HttpStatus.FOUND);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("id/{myId}")
    public Boolean deleteById(@PathVariable ObjectId myId){
        Boolean b = journalEntryService.deleteById(myId);
        if(b){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null).hasBody();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null).hasBody();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
        JournalEntry journalEntry1 = journalEntryService.updateById(id, journalEntry);
        if(journalEntry1!=null){
            return new ResponseEntity<>(journalEntry1,HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
