package com.myApp.testingApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Console {
    Logger logger = LoggerFactory.getLogger(Console.class);
    private String data = "testowa wiadomość dla fe";
    public Integer valueTest = 0;

    public void init() {
        logger.info("runner");
    }

    public String getData() {
        if (valueTest % 2 == 0) {
            return "true";
        }
        return "false";
    }

    public Scenario scenario() {
        return new Scenario("Scenario1", "Scenario2");
    }

    // @Scheduled(fixedDelay = 1000)
    public void tick() {
        this.valueTest++;
    }

}
