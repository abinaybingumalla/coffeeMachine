package com.dunzo.coffee.service;

import com.dunzo.coffee.exception.BeverageNotFoundException;
import com.dunzo.coffee.models.Beverage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeverageCompositionService {
    private static final String BEVERAGE_DATA_FILE_NAME = "beverageList.txt";
    private final FileReaderService fileReaderService;
    private final Map<String, Beverage> beverageMap;

    public BeverageCompositionService(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
        this.beverageMap = new HashMap<>();
        loadBeverageComposition();
    }

    //Loads all beverages data
    public void loadBeverageComposition() {
        List<String> beverageData = fileReaderService.loadData(BEVERAGE_DATA_FILE_NAME);
        for (int i = 0; i < beverageData.size(); i++) {
            String[] data = beverageData.get(i).split(",");
            String beverageName = data[0].trim().toLowerCase();
            Map<String, Integer> beverageComposition = new HashMap<>();
            for (int k = 1; k < data.length; k = k + 2) {
                String ingredientName = data[k].trim().toLowerCase();
                int ingredientAmount = Integer.parseInt(data[k + 1].trim());
                beverageComposition.put(ingredientName, ingredientAmount);
            }
            beverageMap.put(beverageName, new Beverage(beverageName, beverageComposition));
        }
    }

    //Get beverage if present
    public Beverage getBeverage(String beverageName) {
        beverageName = beverageName.toLowerCase();
        if (beverageMap.containsKey(beverageName)) {
            return beverageMap.get(beverageName);
        }
        throw new BeverageNotFoundException("Beverage not found. BeverageName: " + beverageName);
    }
}
