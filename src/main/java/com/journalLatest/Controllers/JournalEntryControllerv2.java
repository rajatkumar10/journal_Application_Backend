package com.journalLatest.Controllers;

import com.journalLatest.Entity.JournalEntry;
import com.journalLatest.Entity.User;
import com.journalLatest.Service.JournalEntryService;
import com.journalLatest.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    private final JournalEntryService journalEntryService;
    private final UserService userService;
    @GetMapping("/get_all")
    public ResponseEntity<List<JournalEntry>>getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User byUsername = userService.findByUsername(name);
        List<JournalEntry> journalEntries = byUsername.getJournalEntries();
        try {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry journalEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            return new ResponseEntity<>(journalEntryService.saveEntry(journalEntry,name),HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry>getJournalById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUsername(name);
        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId))
                .collect(Collectors.toList());
        if(!collect.isEmpty()) {
            JournalEntry journalEntry = journalEntryService.findById(myId).orElse(null);
            if (journalEntry!=null){
                return new ResponseEntity<>(journalEntry,HttpStatus.FOUND);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Boolean b = journalEntryService.deleteById(myId, name);
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null).hasBody()
        if(b){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT).hasBody();
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND).hasBody();
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId id,
                                                   @RequestBody JournalEntry journalEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user=userService.findByUsername(name);
        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            JournalEntry journalEntry1 = journalEntryService.updateById(id, journalEntry,name);
            if(journalEntry1!=null){
                return new ResponseEntity<>(journalEntry1,HttpStatus.ACCEPTED);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
