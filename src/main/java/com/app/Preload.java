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
    CommandLineRunner initDatabase(Surveys surveys) {
        preloadData(surveys);

        return args -> {
            log.info("Preloading Complete");
        };
    }

    private void preloadData(Surveys surveys) {
        Survey s1 = new Survey("Where?"); // No Questions
        Survey s2 = new Survey("Why?");   // One Questions
        Survey s3 = new Survey("How?"); // Two Questions
        Survey s4 = new Survey("Sorta real survey"); // Three Questions

        Question q21 = new Question("Favorite Number?"); // No Results

        Question q32 = new Question("Favorite Colour?");// One Results
        Question q33 = new Question("Favorite Game?");  // No Results

        Question q41 = new Question("Favorite Number?");// Two Results
        Question q42 = new Question("Favorite Colour?");// No Results
        Question q43 = new Question("Favorite Game?");  // One Results

        q32.addResult(new Result("7"));
        q41.addResult(new Result("7"));
        q41.addResult(new Result("14"));
        q43.addResult(new Result("Legend of Zelda"));

        s1.setClosed(false);
        s2.setClosed(false);
        s3.setClosed(false);
        s4.setClosed(true);

        s2.addQuestion(q21);

        s3.addQuestion(q32);
        s3.addQuestion(q33);

        s4.addQuestion(q41);
        s4.addQuestion(q42);
        s4.addQuestion(q43);

        surveys.save(s1);
        surveys.save(s2);
        surveys.save(s3);
        surveys.save(s4);
    }
}
