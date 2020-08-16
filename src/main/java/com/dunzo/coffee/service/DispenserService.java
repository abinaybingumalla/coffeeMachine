package com.dunzo.coffee.service;

import com.dunzo.coffee.models.Beverage;

import java.util.HashMap;
import java.util.Map;

public class DispenserService implements Runnable {
    private final Map<String, Integer> sharedIngredientsMap;
    private final BeverageCompositionService beverageCompositionService;
    private String beverageName;


    public DispenserService(Map<String, Integer> sharedIngredientsMap, BeverageCompositionService beverageCompositionService,
                            String beverageName) {
        this.sharedIngredientsMap = sharedIngredientsMap;
        this.beverageCompositionService = beverageCompositionService;
        this.beverageName = beverageName;
    }

    @Override
    public void run() {
        Beverage beverage = beverageCompositionService.getBeverage(beverageName);
        boolean allIngredientsAvailable = true;
        HashMap<String, Integer> blockedIngredientMap = new HashMap<>();
        for (Map.Entry<String, Integer> beverageComposition : beverage.getBeverageComposition().entrySet()) {

            if (sharedIngredientsMap.containsKey(beverageComposition.getKey())) {
                int totalAvailableCount = sharedIngredientsMap.get(beverageComposition.getKey());
                int requiredCount = beverageComposition.getValue();
                if (totalAvailableCount - requiredCount >= 0) {
                    sharedIngredientsMap.compute(beverageComposition.getKey(), (k, v) -> v - requiredCount);
                    blockedIngredientMap.put(beverageComposition.getKey(), requiredCount);
                } else { // If any of ingredient is short in supply we cant prepare beverage
                    allIngredientsAvailable = false;
                    System.out.println(String.format("%s cannot be prepared because item %s is not sufficient", beverageName, beverageComposition.getKey()));
                    break;
                }
            } else { // If Ingredient itself is unavailable print error and return
                allIngredientsAvailable = false;
                System.out.println(String.format("%s cannot be prepared because %s is not available", beverageName, beverageComposition.getKey()));
                break;
            }
        }
        //If all ingredients are available we can dispense beverage
        // else we need to add back the ingredients that we have subtracted
        if (allIngredientsAvailable) {
            System.out.println(beverageName + " is prepared");
            //MapUtil.print(sharedIngredientsMap);
        } else {
            for (Map.Entry<String, Integer> entry : blockedIngredientMap.entrySet()) {
                sharedIngredientsMap.compute(entry.getKey(), (k, v) -> v + entry.getValue());
            }
            // we can add retry logic here
        }
    }

}
