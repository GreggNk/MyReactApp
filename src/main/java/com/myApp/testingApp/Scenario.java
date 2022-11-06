package com.myApp.testingApp;

import lombok.Data;

import java.lang.String;

@Data
public class Scenario {
    private String name;
    private String key;

    public Scenario(String key, String name) {
        this.name = name;
        this.key = key;
    }

}
