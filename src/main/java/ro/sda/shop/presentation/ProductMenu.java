package ro.sda.shop.presentation;


import ro.sda.shop.model.Product;
import ro.sda.shop.storage.ProductDAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductMenu extends AbstractMenu {
    private ProductDAO productDAO = new ProductDAO();
    private ProductReader reader = new ProductReader();
    private ProductWriter writer = new ProductWriter();

    protected void displayOptions() {
        System.out.println("Products menu: ");
        System.out.println("1 - View all products");
        System.out.println("2 - View product details");
        System.out.println("3 - Edit product");
        System.out.println("4 - Add new product");
        System.out.println("5 - Delete product");
        System.out.println("0 - Exit");
    }


    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(productDAO.findAll());
                break;
            case 2:
                if (productDAO.findAll().isEmpty()) {
                    System.out.println("No products available!");
                } else {
                    writer.writeAll(productDAO.findAll());
                    System.out.println("Please, select product, by ID, to see it's details: ");
                    displayProductDetails();
                }
                break;
            case 3:
                if (productDAO.findAll().isEmpty()) {
                    System.out.println("No products available!");
                } else {
                    writer.writeAll(productDAO.findAll());
                    System.out.println("Please, select a product, by id, to edit: ");
                    editProduct();
                }
                break;
            case 4:
                Product newProduct = reader.read();
                productDAO.add(newProduct);
                System.out.println("Product added with success!");
                break;
            case 5:
                if (productDAO.findAll().isEmpty()) {
                    System.out.println("No products available!");
                } else {
                    writer.writeAll(productDAO.findAll());
                    System.out.println("Select product to delete: ");
                    String inputMessage = "Product ID: ";
                    String invalidMessage = "Invalid Product ID. Please, retry!";
                    Long id = ConsoleUtil.readLong(inputMessage, invalidMessage);
                    boolean isDeleted = productDAO.deleteById(id);
                    if (!isDeleted) {
                        System.out.println("Product not found!");
                    } else {
                        System.out.println("Product deleted with success!");
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

    private void editProduct() {
        Scanner scanner = new Scanner(System.in);
        String inputMessage = "Product ID: ";
        String invalidMessage = "Invalid Product ID. Please, retry!";
        Long id = ConsoleUtil.readLong(inputMessage, invalidMessage);
        Product foundProduct = productDAO.findById(id);
        if (foundProduct == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("Enter new name: ");
            foundProduct.setName(scanner.next());
            System.out.println("Enter new description: ");
            foundProduct.setDescription(scanner.next());
            System.out.println("Enter new price: ");
            try {
                foundProduct.setPrice(scanner.nextDouble());
            } catch (InputMismatchException exception) {
                System.out.println("Price not changed!");
            }
        }
        productDAO.update(foundProduct);
        System.out.println("Product updated with success!");
    }

    private void displayProductDetails() {
        String inputMessage = "Product ID: ";
        String invalidMessage = "Invalid Product ID. Please, retry!";
        Long id = ConsoleUtil.readLong(inputMessage, invalidMessage);
        Product searchedProduct = productDAO.findById(id);
        if (searchedProduct == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("Product details: ");
            writer.write(searchedProduct);
        }
    }
}
