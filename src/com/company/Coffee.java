package com.company;

public class Coffee {
    private final int water;
    private final int milk;
    private final int coffeeBeans;
    private final int cost;

    public Coffee(int water, int milk, int coffeeBeans, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getCost() {
        return cost;
    }
}
