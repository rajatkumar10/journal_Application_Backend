package com.journalLatest.Controllers;

import com.journalLatest.Entity.JournalEntry;
import com.journalLatest.Entity.User;
import com.journalLatest.Service.JournalEntryService;
import com.journalLatest.Service.UserService;
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
    private final UserService userService;
    @GetMapping("/get/{userName}")
    public ResponseEntity<List<JournalEntry>>getAllJournalEntriesOfUser(@PathVariable String userName) {
        User byUsername = userService.findByUsername(userName);
        List<JournalEntry> journalEntries = byUsername.getJournalEntries();
        try {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create/{userName}")
    public ResponseEntity<JournalEntry> createJournal(@PathVariable String userName,@RequestBody JournalEntry journalEntry){
        try {
            return new ResponseEntity<>(journalEntryService.saveEntry(journalEntry,userName),HttpStatus.CREATED);
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

    @DeleteMapping("id/{myId}/{userName}")
    public Boolean deleteById(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryService.deleteById(myId,userName);
       // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null).hasBody()
        return true;
    }

    @PatchMapping("/update/{id}/{userName}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id,
                                                   @RequestBody JournalEntry journalEntry,
                                                   @PathVariable String userName){
        JournalEntry journalEntry1 = journalEntryService.updateById(id, journalEntry,userName);
        if(journalEntry1!=null){
            return new ResponseEntity<>(journalEntry1,HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
