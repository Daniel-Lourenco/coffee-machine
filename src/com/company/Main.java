package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);
        while (coffeeMachine.isTurnedOn()) {
            coffeeMachine.input(scanner.nextLine());
        }
    }
}
