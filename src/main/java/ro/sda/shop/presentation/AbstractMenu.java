package ro.sda.shop.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AbstractMenu {

    public void displayMenu() {
        Integer option = Integer.MAX_VALUE;
        while (option != 0) {
            displayOptions();
            option = readOption();
            executeCmd(option);
        }
    }

    protected abstract void displayOptions();//specific for every menu

    protected abstract void executeCmd(Integer option);//specific for every menu

    private Integer readOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your option is: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }

    }


}

