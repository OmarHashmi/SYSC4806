package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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

        SingleQuestion q21 = new SingleQuestion("Favorite Number?"); // No Results

        SingleQuestion q32 = new SingleQuestion("Favorite Colour?");// One Results
        SingleQuestion q33 = new SingleQuestion("Favorite Game?");  // No Results

        SingleQuestion q41 = new SingleQuestion("Favorite Number?");// Two Results
        SingleQuestion q42 = new SingleQuestion("Favorite Colour?");// No Results
        SingleQuestion q43 = new SingleQuestion("Favorite Game?");  // One Results
        String answers[] = {"PlayStation", "Xbox", "PC", "Nintendo", "Phone"};
        OptionsQuestion q44 = new OptionsQuestion(true, "What systems do you own?", new ArrayList<>(List.of(answers)));

        String answers2[] = {"Nike", "Asus", "Nintendo", "Microsoft"};
        OptionsQuestion q45 = new OptionsQuestion(false, "Brand do you like most?", new ArrayList<>(List.of(answers2)));

        q32.addResult(new Result("7"));
        q41.addResult(new Result("7"));
        q41.addResult(new Result("14"));
        q43.addResult(new Result("Legend of Zelda"));

        s1.setClosed(true);
        s2.setClosed(false);
        s3.setClosed(false);
        s4.setClosed(false);

        s2.addQuestion(q21);

        s3.addQuestion(q32);
        s3.addQuestion(q33);

        s4.addQuestion(q41);
        s4.addQuestion(q42);
        s4.addQuestion(q43);
        s4.addQuestion(q44);
        s4.addQuestion(q45);

        surveys.save(s1);
        surveys.save(s2);
        surveys.save(s4);
        surveys.save(s3);

    }
}
