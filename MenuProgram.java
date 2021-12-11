package com.tsdv.ntc;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuProgram {
    protected final int EXIT = 0;
    public void run() {
        boolean running = true;
        while (running) {
            try {
                printMenu();
                int choice = getChoice();
                doTask(choice);
                running = choice != EXIT;
            } catch (InputMismatchException e) {
                System.out.println("Try again!");
            }
        }
    }

    protected abstract void printMenu();
    protected abstract void doTask(int choice);

    protected int getChoice() {
        System.out.print("Enter your choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }
}
