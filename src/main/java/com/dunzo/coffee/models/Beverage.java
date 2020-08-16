package com.dunzo.coffee.models;

import java.util.Map;

public class Beverage {
    String beverageName;
    Map<String, Integer> beverageComposition;

    public Beverage(String beverageName, Map<String, Integer> beverageComposition) {
        this.beverageName = beverageName;
        this.beverageComposition = beverageComposition;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public Map<String, Integer> getBeverageComposition() {
        return beverageComposition;
    }

    @Override
    public String toString() {
        for (Map.Entry<String, Integer> entry : beverageComposition.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return "";
    }
}
