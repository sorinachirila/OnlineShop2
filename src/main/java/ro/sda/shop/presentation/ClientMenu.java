package ro.sda.shop.presentation;

import ro.sda.shop.storage.ClientDAO;
import ro.sda.shop.model.Client;

import java.util.Scanner;

public class ClientMenu extends AbstractMenu {
    private ClientDAO clientDAO = new ClientDAO();
    private ClientReader reader = new ClientReader();
    private ClientWriter writer = new ClientWriter();


    protected void displayOptions() {
        System.out.println("\nClients menu");
        System.out.println("1 - View all clients");
        System.out.println("2 - View client details");
        System.out.println("3 - Edit client");
        System.out.println("4 - Add new client");
        System.out.println("5 - Delete client");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(clientDAO.findAll());
                break;
            case 2:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available!");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.println("Please, select client to view:");
                    displayClientDetails();
                }
                break;
            case 3:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available!");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.println("Please, select client to edit:");
                    editAddress();
                }
                break;
            case 4:
                Client newClient = reader.read();
                clientDAO.add(newClient);
                System.out.println("Client added.");
                break;
            case 5:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available!");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.println("Please, select client to delete:");
                    String inputMessage = "Client ID: ";
                    String invalidMessage = "Invalid Client ID. Please, retry!";
                    Long id = ConsoleUtil.readLong(inputMessage, invalidMessage);
                    boolean isDeleted = clientDAO.deleteById(id);
                    if (!isDeleted) {
                        System.out.println("Client not found!");
                    } else {
                        System.out.println("Client deleted!");
                    }
                }
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option!");

        }
    }

    private void editAddress() {
        System.out.println("Select client to edit: ");
        Long id = new Scanner(System.in).nextLong();
        System.out.println("Enter new address: ");
        String address = new Scanner(System.in).nextLine();
        Client client = clientDAO.findById(id);
        client.setAddress(address);
        clientDAO.update(client);
    }

    private void displayClientDetails() {
        System.out.println("Choose client by id: ");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        Client searchedClient = clientDAO.findById(id);
        writer.write(searchedClient);
    }
}