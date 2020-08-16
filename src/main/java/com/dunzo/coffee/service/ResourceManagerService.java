package com.dunzo.coffee.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceManagerService {
    private static final String INGREDIENT_FILE_NAME = "ingredients.txt";
    private final int LOW_INDICATOR = 10;
    private final Map<String, Integer> ingredientMap;
    private final FileReaderService fileReaderService;

    public ResourceManagerService(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
        this.ingredientMap = new ConcurrentHashMap<>();
        loadInitialIngredients();
    }

    public Map<String, Integer> getIngredientMap() {
        return ingredientMap;
    }

    private void loadInitialIngredients() {
        List<String> ingredientData = fileReaderService.loadData(INGREDIENT_FILE_NAME);
        for (String data : ingredientData) {
            String[] ingredients = data.split(",");
            String ingrtedientName = ingredients[0].trim().toLowerCase();
            int quantity = Integer.parseInt(ingredients[1].trim());
            ingredientMap.put(ingrtedientName, quantity);
        }
    }

    public void refill(String ingredientName, int quantity) {
        ingredientMap.computeIfPresent(ingredientName, (k, v) -> v + quantity);
    }

    public void lowIndicator() {
        for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {
            if (entry.getValue() < LOW_INDICATOR) {
                System.out.println("=====================================");
                System.out.println(entry.getKey() + " is low, Please refill");
                System.out.println("=====================================");
            }
        }
    }
}
