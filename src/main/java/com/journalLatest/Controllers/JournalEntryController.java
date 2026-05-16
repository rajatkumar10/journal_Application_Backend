package com.journalLatest.Controllers;

import com.journalLatest.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long,JournalEntry>journalEntries=new HashMap<>();

    @GetMapping("/get")
    public List<JournalEntry>getall() {
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping("/create")
    public JournalEntry createJournal(@RequestBody JournalEntry journalEntry){
        journalEntries.put(journalEntry.getId(),journalEntry);
        return journalEntry;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getJournalById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }
    @DeleteMapping("id/{myId}")
    public Boolean deleteById(@PathVariable Long myId){
        journalEntries.remove(myId);
        return true;
    }
    @PatchMapping("/update/{id}")
    public JournalEntry updateById(@PathVariable Long id,@RequestBody JournalEntry journalEntry){
        JournalEntry journalEntry1 = journalEntries.get(id);
        journalEntry1.setTitle(journalEntry.getTitle());
        journalEntry1.setContent(journalEntry.getContent());
        journalEntries.put(id,journalEntry1);
        return journalEntry1;
    }
}
