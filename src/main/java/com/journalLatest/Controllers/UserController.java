package com.journalLatest.Controllers;

import com.journalLatest.Entity.User;
import com.journalLatest.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public List<User>getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.saveEntry(user);
    }

    @PatchMapping("/update")
    public ResponseEntity<?>updateUser(@RequestBody User user){
        User user1 = userService.updateByName(user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }

}
