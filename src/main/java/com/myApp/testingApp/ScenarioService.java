package com.myApp.testingApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ScenarioService {

    String tempName;

    public List<Scenario> scenarios() {
        List<Scenario> scenarios = new ArrayList<>();
        scenarios.add(new Scenario("stop", ""));
        scenarios.add(new Scenario("scenario1", "Scenariusz numer 1"));
        scenarios.add(new Scenario("scenario2", "Scenariusz numer 2"));
        scenarios.add(new Scenario("scenario3", "Scenariusz numer 3"));
        return scenarios;
    }

}
