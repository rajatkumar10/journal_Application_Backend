package com.journalLatest.Controllers;

import com.journalLatest.Entity.User;
import com.journalLatest.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    private final UserService userService;
    @GetMapping("/health")
    public String healthCheck(){
        return "Hello i am healthy";
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.saveEntry(user);
    }
}
