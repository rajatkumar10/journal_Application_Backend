package com.journalLatest.Service;

import com.journalLatest.Entity.JournalEntry;
import com.journalLatest.Repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {
    private final   JournalEntryRepository journalEntryRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
        return journalEntry;
    }
    public List<JournalEntry>getAllEntries(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public Boolean deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
        return true;
    }
    public JournalEntry updateById(ObjectId id,JournalEntry journalEntry){
        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if(old!=null) {
            old.setContent(journalEntry.getContent());
            old.setTitle(journalEntry.getTitle());
        }
        journalEntryRepository.save(old);
        return old;
    }
}
