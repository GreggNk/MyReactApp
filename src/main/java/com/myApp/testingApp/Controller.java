package com.myApp.testingApp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.String;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    Console console;

    @Autowired
    ScenarioService scenarioService;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    String currentScenario = "stop";

    @GetMapping("/scenario/stop")
    public String stop() {
        console.init();
        currentScenario = "stop";
        logger.info(currentScenario);
        return currentScenario;
    }

    @GetMapping("/scenario/{currScenario}")
    public String index(@PathVariable(name = "currScenario") String currScenario) {
        console.init();
        currentScenario = currScenario;
        logger.info(currentScenario);
        return currentScenario;
    }

    // test backend stop scenario
    @GetMapping("/addOne")
    public void addOne() {
        console.tick();
        currentScenario = "stop";
    }

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    @GetMapping("/data/{prevScenario}")
    public DeferredResult<String> data(@PathVariable(name = "prevScenario") String prevScenario) {
        DeferredResult<String> output = new DeferredResult<>(10000L);
        executor.execute(() -> {
            for (int i = 0; i < 500; i++) {
                if (!currentScenario.equals(prevScenario)) {
                    output.setResult(currentScenario);
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
        });
        return output;
    }

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
}
