package com.dunzo.coffee;

import com.dunzo.coffee.service.BeverageCompositionService;
import com.dunzo.coffee.service.DispenserService;
import com.dunzo.coffee.service.FileReaderService;
import com.dunzo.coffee.service.ResourceManagerService;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Application {
    private static final int OUTLET_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        //Init all services and load initial/default values
        FileReaderService fileReaderService = new FileReaderService();
        BeverageCompositionService beverageCompositionService = new BeverageCompositionService(fileReaderService);
        ResourceManagerService resourceManagerService = new ResourceManagerService(fileReaderService);

        Map<String, Integer> sharedMap = resourceManagerService.getIngredientMap();

        ExecutorService executorService = Executors.newFixedThreadPool(OUTLET_COUNT);

        executorService.submit(new DispenserService(sharedMap, beverageCompositionService, "hot_tea"));
        executorService.submit(new DispenserService(sharedMap, beverageCompositionService, "hot_coffee"));
        executorService.submit(new DispenserService(sharedMap, beverageCompositionService, "green_tea"));
        executorService.submit(new DispenserService(sharedMap, beverageCompositionService, "black_tea"));

        // This is required to simulate low indicator
        sleep(1000);
        resourceManagerService.lowIndicator();

        resourceManagerService.refill("hot_milk", 800);
        executorService.submit(new DispenserService(sharedMap, beverageCompositionService, "hot_tea"));

        executorService.shutdown();
    }
}
