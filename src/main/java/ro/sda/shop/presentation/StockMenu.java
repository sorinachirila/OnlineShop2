package ro.sda.shop.presentation;

import ro.sda.shop.model.Stock;
import ro.sda.shop.storage.StockDAO;

import java.util.Scanner;

public class StockMenu extends AbstractMenu {
    private StockDAO stockDAO = new StockDAO();
    private StockReader reader = new StockReader();
    private StockWriter writer = new StockWriter();

    protected void displayOptions() {
        System.out.println("1 - View all stocks");
        System.out.println("2 - View stock details");
        System.out.println("3 - Edit stock");
        System.out.println("4 - Add new stock");
        System.out.println("5 - Delete stock");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(stockDAO.findAll());
                break;
            case 2:
                if (stockDAO.findAll().isEmpty()) {
                    System.out.println("No stocks available!");
                } else {
                    writer.writeAll(stockDAO.findAll());
                    //System.out.println("Please select a stock to view: ");
                    displayStockDetails();
                }
                break;
            case 3:
                if (stockDAO.findAll().isEmpty()) {
                    System.out.println("No stocks available!");
                } else {
                    writer.writeAll(stockDAO.findAll());
                    editLocation();
                }
                break;
            case 4:
                Stock newStock = reader.read();
                if (newStock == null) {
                    System.out.println("No products available!");
                } else {
                    stockDAO.add(newStock);
                    System.out.println("Stock added with success!");
                }
                break;
            case 5:
                if (stockDAO.findAll().isEmpty()) {
                    System.out.println("No stocks available!");
                } else {
                    writer.writeAll(stockDAO.findAll());
                    System.out.println("Please, select stock to delete: ");
                    String inputMessage = "Stock ID: ";
                    String invalidMessage = "Invalid Stock ID. Please, retry!";
                    boolean isDeleted = stockDAO.deleteById(ConsoleUtil.readLong(inputMessage, invalidMessage));
                    if (!isDeleted) {
                        System.out.println("Stock not found!");
                    } else {
                        System.out.println("Stock deleted with success!");
                    }
                }
                break;
            case 0:
                System.out.println("Exiting to main menu...");
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    private void editLocation() {
        System.out.println("Select stock, by id, to edit: ");
        Long id = new Scanner(System.in).nextLong();
        System.out.println("Enter new location: ");
        String location = new Scanner(System.in).nextLine();
        Stock stock = stockDAO.findById(id);
        stock.setLocation(location);
        stockDAO.update(stock);
    }

    private void displayStockDetails() {
        System.out.println("Please, choose stock, by id: ");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        Stock searchedStock = stockDAO.findById(id);
        writer.write(searchedStock);
    }
}
