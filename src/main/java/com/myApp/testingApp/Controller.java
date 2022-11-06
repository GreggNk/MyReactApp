package com.myApp.testingApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.lang.String;

import org.apache.tomcat.util.json.JSONParser;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.val;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    Console console;

    @Autowired
    ScenarioService scenarioService;

    String[] tempNames;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    // public List<Scenario> scenarios() {
    // List<Scenario> scenarios = new ArrayList<>();
    // scenarios.add(new Scenario("Scenariusz numer 1", "scenario1"));
    // scenarios.add(new Scenario("Scenariusz numer 2", "scenario2"));
    // scenarios.add(new Scenario("Scenariusz numer 3", "scenario3"));
    // return scenarios;
    // }

    // public List<Scenario> getScenarioByKey(String key) {
    // return scenarios().stream().filter(scenario -> scenario.getKey() ==
    // key).collect(Collectors.toList());
    // }
    // @Autowired
    // Scenario scenario;

    String currentScenario = "stop";
    String currentScenarioTemp = "stop";

    @GetMapping("/scenario/stop")
    public String stop() {
        console.init();
        currentScenario = "stop";
        logger.info(currentScenario);
        return currentScenario;
    }

    @GetMapping("/scenario/scenario1")
    public String index() {
        console.init();
        currentScenario = "scenario1";
        logger.info(currentScenario);
        return currentScenario;
    }

    @GetMapping("/scenario/scenario2")
    public String index2() {
        console.init();
        currentScenario = "scenario2";
        logger.info(currentScenario);
        return currentScenario;
    }

    @GetMapping("/scenario/scenario3")
    public String index3() {
        console.init();
        currentScenario = "scenario3";
        logger.info(currentScenario);
        return currentScenario;
    }

    @GetMapping("/addOne")
    public void addOne() {
        console.tick();
        currentScenario = "stop";

    }

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    @GetMapping("/data")
    public DeferredResult<String> data() {
        DeferredResult<String> output = new DeferredResult<>(5000L);
        executor.execute(() -> {
            // try {
            for (int i = 0; i < 50; i++) {
                if (currentScenario != currentScenarioTemp) {
                    output.setResult(currentScenario);
                    currentScenarioTemp = currentScenario;
                    System.out.println("set - operacja nadpisania i odesłania info" +
                            currentScenario + "|" + currentScenarioTemp);
                    currentScenarioTemp = currentScenario;
                    break;
                }
                try {

                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        });
        System.out.println("start data api" + currentScenario + "|" + currentScenarioTemp);
        return output;
    }

    // @GetMapping("/data")
    // public DeferredResult<String> data() {
    // DeferredResult<String> output = new DeferredResult<>(null);
    // executor.execute(() -> {
    // try {
    // // for (int i = 0; i < 40000; i++) {
    // if (currentScenarioTemp != currentScenario) {
    // currentScenarioTemp = currentScenario;
    // output.setResult(currentScenario);
    // System.out.println("|" + currentScenario + "|" + currentScenarioTemp);
    // } else {
    // // setInterval;
    // }
    // // } else {
    // // // try
    // Thread.sleep(1000);
    // // //// } catch (Exception e) {
    // // //// }
    // // //output.setResult(currentScenario);
    // //
    // // // System.out.println(Integer.toString(i) + "|" + currentScenario + "|" +
    // // // currentScenarioTemp);
    // // }
    // // }
    // } catch (Exception e) {
    // }
    // });
    // return output;
    // }
    @GetMapping("/currentScenario")
    public Map<String, Object> currentScenarioValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("value", currentScenario);
        return map;
    }

    @GetMapping("/scenarios")
    public List<Scenario> getScenarios() {
        return scenarioService.scenarios();
    }

    // @GetMapping("/scenario/{id}")
    // public String getScenarioById(@PathVariable("id") int id) {
    // return scenarioService.scenarios().get(id).getName();
    // }

}
