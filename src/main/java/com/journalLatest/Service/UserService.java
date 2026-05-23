package com.journalLatest.Service;

import com.journalLatest.Entity.JournalEntry;
import com.journalLatest.Entity.User;
import com.journalLatest.Repository.JournalEntryRepository;
import com.journalLatest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public User saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return user;
    }
    public void saveNewUser(User user){
        userRepository.save(user);
    }
    public List<User>getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public Boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }
    public User updateByName(User old,User user){
        old.setUserName(user.getUserName());
        old.setPassword(user.getPassword());
        userRepository.save(old);
        return old;
    }
    public User findByUsername(String userName){
        return userRepository.findByUserName(userName);
    }

    public User deleteByName(User user) {
        return userRepository.deleteByUserName(user.getUserName());
    }
}
