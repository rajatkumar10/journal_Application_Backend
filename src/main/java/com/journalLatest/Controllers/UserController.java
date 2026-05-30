package com.journalLatest.Controllers;

import com.journalLatest.Entity.User;
import com.journalLatest.Response.WeatherResponse;
import com.journalLatest.Service.UserService;
import com.journalLatest.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WeatherService weatherService;

    @GetMapping("/get_All_Users")
    public List<User>getAllUsers(){
        return userService.getAllUsers();
    }


    @PatchMapping("/update")
    public ResponseEntity<?>updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        User olduser = userService.findByUsername(name);
        User user1 = userService.updateByName(olduser,user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?>deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        User user = userService.findByUsername(name);
        User user1 = userService.deleteByName(user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }
    @GetMapping("get")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelsLike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }
}
