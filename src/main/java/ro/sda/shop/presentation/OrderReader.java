package ro.sda.shop.presentation;

import ro.sda.shop.model.Client;
import ro.sda.shop.model.Order;
import ro.sda.shop.model.Product;
import ro.sda.shop.model.OrderStatus;
import ro.sda.shop.storage.ClientDAO;
import ro.sda.shop.storage.ProductDAO;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderReader implements ConsoleReader<Order> {
    private ClientDAO clientDAO = new ClientDAO();
    private ProductDAO productDAO = new ProductDAO();

    public Order read() {
        if (productDAO.findAll().isEmpty() || clientDAO.findAll().isEmpty()) {
            return null;
        }
        Order order = new Order();
        new ClientWriter().writeAll(clientDAO.findAll());
        String inputMessage = "Client ID: ";
        String invalidMessage = "Invalid Client ID. Please, retry!";
        Client selectedClient = clientDAO.findById(ConsoleUtil.readLong(inputMessage, invalidMessage));
        while (selectedClient == null) {
            System.out.println("Client not found. Please, select again: ");
            selectedClient = clientDAO.findById(ConsoleUtil.readLong(inputMessage, invalidMessage));
        }
        new ProductWriter().writeAll(productDAO.findAll());
        String inpMessage = "Number of products: ";
        String invMessage = "Invalid number. Please, retry!";
        Long noOfProducts = ConsoleUtil.readLong(inpMessage, invMessage);
        while (noOfProducts <= 0) {
            System.out.println("Incorrect number. Please, insert again: ");
            noOfProducts = ConsoleUtil.readLong(inpMessage, invMessage);
        }

        List<Product> productList = getProducts(noOfProducts);
        Double actualPrice = ConsoleUtil.getPrice();//OrderService.computePrice(productList);
        order.setClient(selectedClient);
        order.setOrderedProducts(productList);
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setFinalPrice(actualPrice);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return order;
    }

/*    private Double getActualPrice(List<Product> productList) {
        //System.out.print("Actual price: ");
        Double actualPrice = 0d;
        for (Product product : productList) {
            actualPrice += product.getPrice();
        }
        return actualPrice;
    }*/

    private List<Product> getProducts(Long noOfProducts) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < noOfProducts; ) {
            System.out.println("Product #" + (i + 1) + ": ");
            String inpMessage = "Product ID";
            String invMessage = "Invalid Product ID. Please, retry!";
            Product product = productDAO.findById(ConsoleUtil.readLong(inpMessage, invMessage));
            if (product != null) {
                productList.add(product);
                i++;
            }
        }
        return productList;
    }

    /*public void setOrderToClient(Order order){
        Client client = order.getClient();
        List<Order> orders = client.getOrders();
        orders.add(order);
        client.setOrders(orders);
    }*/
}
