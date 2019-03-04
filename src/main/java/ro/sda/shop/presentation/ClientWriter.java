package ro.sda.shop.presentation;

import ro.sda.shop.model.Client;
import ro.sda.shop.model.Order;

import java.util.List;

public class ClientWriter implements ConsoleWriter<Client> {

    public void write(Client client) {
        System.out.println("Id: " + client.getId());
        System.out.println("Name: " + client.getName());
        System.out.println("Phone Number: " + client.getPhoneNumber());
        System.out.println("Social ID: " + client.getSocialId());
        System.out.println("Address: " + client.getAddress());
        if (client.getOrders() != null) {
            System.out.println("Orders:");
            for (Order order : client.getOrders()) {
                System.out.println(order.getId() + ", ");
            }
        }
    }

    public void writeAll(List<Client> clients) {
        if (clients.size() == 0) {
            System.out.println("No clients available!");
        } else {
            System.out.println("Clients list: ");
            for (Client client : clients) {
                writeSummary(client);
            }
        }
    }

    private void writeSummary(Client client) {
        System.out.print("Id: " + client.getId());
        System.out.print("  Name: " + client.getName());
        System.out.print("  Phone Number: " + client.getPhoneNumber());
        System.out.print("  Social ID: " + client.getSocialId());
        System.out.println("  Address: " + client.getAddress());
    }
}
