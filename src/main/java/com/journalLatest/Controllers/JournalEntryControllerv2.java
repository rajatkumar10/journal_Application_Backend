package com.journalLatest.Controllers;

import com.journalLatest.Entity.JournalEntry;
import com.journalLatest.Service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
    public List<JournalEntry>getall() {
        return journalEntryService.getAllEntries();
    }
    @PostMapping("/create")
    public JournalEntry createJournal(@RequestBody JournalEntry journalEntry){
        return journalEntryService.saveEntry(journalEntry);
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public Boolean deleteById(@PathVariable ObjectId myId){
        return journalEntryService.deleteById(myId);
    }
    @PatchMapping("/update/{id}")
    public JournalEntry updateById(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
        return journalEntryService.updateById(id,journalEntry);
    }
}
