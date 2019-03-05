package ro.sda.shop.presentation;

import ro.sda.shop.model.Client;

import java.util.Scanner;

public class ClientReader implements ConsoleReader<Client> {

    public Client read() {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        readName(client, scanner);
        readPhoneNumber(client, scanner);
        readSocialID(client, scanner);
        readAddress(client, scanner);
        return client;
    }

    private void readAddress(Client client, Scanner scanner) {
        System.out.println("Please, write client address (must start with upper letter): ");
        String address = scanner.nextLine();
        while (!address.matches("^[A-Z]{1}([a-z0-9\\s]){2,}")) {
            System.out.println("Invalid address! Please provide correct address:");
            address = scanner.nextLine();
        }
        client.setAddress(address);
    }

    private void readSocialID(Client client, Scanner scanner) {
        System.out.print("Please, write client social ID (must have 13 digits): ");
        String socialId = scanner.nextLine();
        while (!socialId.matches("[1|2]{1}[0-9]{12}")) {
            System.out.println("Invalid social ID! Please provide correct social ID: ");
            socialId = scanner.nextLine();
        }
        client.setSocialId(socialId);
    }

    private void readPhoneNumber(Client client, Scanner scanner) {
        System.out.print("Please, write client phone number(must have 10 digits): ");
        String phoneNumber = scanner.nextLine();
        while (!phoneNumber.matches("0[0-9]{9}")) {
            System.out.println("Invalid phone number! Please provide correct phone number:");
            phoneNumber = scanner.nextLine();
        }
        client.setPhoneNumber(phoneNumber);
    }

    private void readName(Client client, Scanner scanner) {
        System.out.print("Please, write client name(must start with upper letter): ");
        String name = scanner.nextLine();
        while (!name.matches("^[A-Z]{1}[a-z]+$")) {
            System.out.println("Invalid name! Please provide correct name:");
            name = scanner.nextLine();
        }
        client.setName(name);
    }
}
