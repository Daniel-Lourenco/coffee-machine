package com.company;

import java.util.ArrayList;

public class CoffeeMachine {
    private int waterOnMachine = 400;
    private int milkOnMachine = 540;
    private int beansOnMachine = 120;
    private int disposibleCups = 9;
    private int cash = 550;
    private static final Coffee espresso = new Coffee(250, 0, 16, 4);
    private static final Coffee latte = new Coffee(350, 75, 20, 7);
    private static final Coffee cappuccino = new Coffee(200, 100, 12, 6);

    private enum State {
        ACTION_MENU, CHOOSING_ACTION, CHOOSING_COFFEE, FILLING_WATER, FILLING_MILK, FILLING_BEANS, FILLING_CUPS
    }

    private static State state;

    private boolean turnedOn;

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public CoffeeMachine() {
        turnedOn = true;
        menu();
    }

    public void menu() {
        state = State.CHOOSING_ACTION;
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
    }

    public void chooseAction(String input) {
        switch (input) {
            case "buy" -> {
                state = State.CHOOSING_COFFEE;
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
            }
            case "fill" -> fillWater();
            case "take" -> take();
            case "remaining" -> remaining();
            case "exit" -> turnedOn = false;
            default -> menu();
        }

    }

    public void chooseCoffee(String input) {
        state = State.CHOOSING_COFFEE;
        switch (input) {
            case "1" -> buy(espresso);
            case "2" -> buy(latte);
            case "3" -> buy(cappuccino);
            default -> menu();
        }
    }

    public void buy(Coffee coffee) {
        state = State.CHOOSING_COFFEE;
        if (hasEnoughSupply(coffee)) {
            System.out.println("I have enough resources, making you a coffee!");
            this.waterOnMachine -= coffee.getWater();
            this.milkOnMachine -= coffee.getMilk();
            this.beansOnMachine -= coffee.getCoffeeBeans();
            this.cash += coffee.getCost();
            this.disposibleCups--;
        }
        menu();
    }

    private boolean hasEnoughSupply(Coffee coffee) {
        ArrayList<String> hasNotEnough = new ArrayList<String>();
        if (waterOnMachine < coffee.getWater()) hasNotEnough.add("water");

        if (milkOnMachine < coffee.getMilk()) hasNotEnough.add("milk");

        if (beansOnMachine < coffee.getCoffeeBeans()) hasNotEnough.add("coffee beans");

        if (disposibleCups < 1) hasNotEnough.add("cups");

        if (!hasNotEnough.isEmpty()) {
            System.out.println("Sorry, not enough " + hasNotEnough.toString() + "!");
            return false;
        }
        return true;
    }

    public void fillWater() {
        state = State.FILLING_WATER;
        System.out.print("\nWrite how many ml of water you want to add:");
    }

    public void fillMilk() {
        state = State.FILLING_MILK;
        System.out.print("Write how many ml of milk you want to add:");
    }

    public void fillBeans() {
        state = State.FILLING_BEANS;
        System.out.print("Write how many g of coffee beans you want to add:");
    }

    public void fillCups() {
        state = State.FILLING_CUPS;
        System.out.print("Write how many cups you want to add:");
    }

    public void take() {
        System.out.println("\nI gave you $" + this.cash);
        this.cash = 0;
        menu();
    }

    public void remaining() {
        System.out.println(
                "\nThe coffee machine has: \n" +
                        waterOnMachine + " ml of water\n" +
                        milkOnMachine + " ml of milk\n" +
                        beansOnMachine + " g of coffee beans\n" +
                        disposibleCups + " disposible cups\n" +
                        "$" + cash + " of money\n"
        );
        menu();
    }
    public void input(String input) {
        switch (state) {
            case ACTION_MENU -> menu();
            case CHOOSING_ACTION -> chooseAction(input);
            case CHOOSING_COFFEE -> chooseCoffee(input);
            case FILLING_WATER -> {
                this.waterOnMachine += Integer.parseInt(input);
                fillMilk();
            }
            case FILLING_MILK -> {
                this.milkOnMachine += Integer.parseInt(input);
                fillBeans();
            }
            case FILLING_BEANS -> {
                this.beansOnMachine += Integer.parseInt(input);
                fillCups();
            }
            case FILLING_CUPS -> {
                this.disposibleCups += Integer.parseInt(input);
                menu();
            }
        }

    }

}
