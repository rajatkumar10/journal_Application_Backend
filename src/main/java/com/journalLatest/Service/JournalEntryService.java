package com.journalLatest.Service;

import com.journalLatest.Entity.JournalEntry;
import com.journalLatest.Entity.User;
import com.journalLatest.Repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {
    private final   JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    @Transactional
    public JournalEntry saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
            return journalEntry;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An eror occured"+e);
        }
    }
    public List<JournalEntry>getAllEntries(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public Boolean deleteById(ObjectId id,String userName){
        User user = userService.findByUsername(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
        return true;
    }
    public JournalEntry updateById(ObjectId id,JournalEntry journalEntry
    ,String userName){
        //User user = userService.findByUsername(userName);
        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if(old!=null) {
            old.setContent(journalEntry.getContent());
            old.setTitle(journalEntry.getTitle());
        }
        journalEntryRepository.save(old);
        return old;
    }
}
