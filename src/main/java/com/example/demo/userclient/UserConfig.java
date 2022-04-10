package com.example.demo.userclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository repository){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return args -> {
            UserClient userClient1 = new UserClient(
                    1L,
                    "User1",
                    "user1@gmail.com",
                    "123",
                    LocalDateTime.parse("2022-04-05 08:30:00", formatter),
                    LocalDateTime.parse("2022-04-05 08:30:00", formatter)
            );
            repository.save(userClient1);
        };
    }
}