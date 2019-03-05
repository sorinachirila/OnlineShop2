package ro.sda.shop.presentation;

import ro.sda.shop.model.Product;

import java.util.Scanner;

public class ProductReader implements ConsoleReader<Product> {
    public Product read() {
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        readProductName(product, scanner);
        readProductDescription(product, scanner);
        System.out.println("Please, write product's price: ");
        Double price = ConsoleUtil.getPrice();
        product.setPrice(price);
        return product;
    }

    private void readProductDescription(Product product, Scanner scanner) {
        System.out.print("Please, write product's description(must start with upper letter):  ");
        String productDescription = scanner.nextLine();
        while (!productDescription.matches("^[A-Z]{1}([a-z0-9\\s]){2,}")) {
            System.out.println("Invalid product description! Please provide correct description:");
            productDescription = scanner.nextLine();

        }
        product.setDescription(productDescription);
    }

    private void readProductName(Product product, Scanner scanner) {
        System.out.print("Please, write product name(must start with upper letter): ");
        String productName = scanner.nextLine();
        while (!productName.matches("^[A-Z]{1}[a-z0-9]+$")) {
            System.out.println("Invalid product name! Please provide correct name:");
            productName = scanner.nextLine();
        }
        product.setName(productName);
    }
}
