package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Preload {
    private static final Logger log = LoggerFactory.getLogger(Preload.class);

    @Bean
    CommandLineRunner initDatabase(AddressBook addressBook) {
        return args -> {
            log.info("Preloading " + addressBook.save(new BuddyInfo("Homer Simpson", "742 Evergreen Terrance", "111-111-1111")));
        };
    }
}
