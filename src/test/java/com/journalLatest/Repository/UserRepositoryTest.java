package com.journalLatest.Repository;

import com.journalLatest.Service.UserDetailServiceIMpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    public void testSave(){
        userRepository.getUserForSA();
    }
}
