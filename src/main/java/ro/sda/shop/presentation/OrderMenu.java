package ro.sda.shop.presentation;

import ro.sda.shop.model.Order;
import ro.sda.shop.storage.OrderDAO;

import java.util.Scanner;

public class OrderMenu extends AbstractMenu {
    //    ClientDAO clientDAO = new ClientDAO();
//    ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderReader reader = new OrderReader();
    private OrderWriter writer = new OrderWriter();

    protected void displayOptions() {
        System.out.println("Orders menu");
        System.out.println("1 - View all orders");
        System.out.println("2 - View order details");
        System.out.println("3 - Edit order");
        System.out.println("4 - Add new order");
        System.out.println("5 - Delete order");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(orderDAO.findAll());
                break;
            case 2:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available!");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.println("Please, select order to view: ");
                    displayOrderDetails();
                }
                break;
            case 3:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available!");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.println("Please, select order to edit:");
                    editActualPrice();
                }
                break;
            case 4:
                Order newOrder = reader.read();
                if (newOrder == null) {
                    System.out.println("No products/clients available!");
                } else {
                    orderDAO.add(newOrder);
                    System.out.println("Order added!");
                }
                break;
            case 5:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available!");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.println("Please, select order, by id, to delete: ");
                    String inputMessage = "Order ID: ";
                    String invalidMessage = "Invalid Order ID. Please, retry!";
                    boolean isDeleted = orderDAO.deleteById(ConsoleUtil.readLong(inputMessage, invalidMessage));
                    if (!isDeleted) {
                        System.out.println("Order not found!");
                    } else {
                        System.out.println("Order deleted!");
                    }
                }
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void editActualPrice() {
        System.out.println("Please, select order, by id, to edit: ");
        Long id = new Scanner(System.in).nextLong();
        System.out.println("Enter new actual price: ");
        Double actualPrice = new Scanner(System.in).nextDouble();
        Order order = orderDAO.findById(id);
        order.setFinalPrice(actualPrice);
        orderDAO.update(order);
    }

    private void displayOrderDetails() {
        String inputMessage = "Order ID: ";
        String invalidMessage = "Invalid Order ID. Please, retry!";
        Long id = ConsoleUtil.readLong(inputMessage, invalidMessage);
        Order searchedOrder = orderDAO.findById(id);
        if (searchedOrder == null) {
            System.out.println("Order not found!");
        } else {
            System.out.println("Order details are:");
            writer.write(searchedOrder);
        }
    }
}
