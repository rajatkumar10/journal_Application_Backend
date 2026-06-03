package com.journalLatest.Service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private  EmailService service;

    @Test
    public void testSendEmail(){
        service.sendEmail("rajatko614@gmail.com",
                "testing mail",
                "Hi, hello i am here");
    }
}
