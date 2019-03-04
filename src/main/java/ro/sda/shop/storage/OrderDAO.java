package ro.sda.shop.storage;

import ro.sda.shop.model.Order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends GenericDAO<Order> {
    private static List<Order> orders = new ArrayList<>();

    @Override
    protected List<Order> getItems() {
        return orders;
    }

    public List<Order> findAllByClientId(Long clientId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : getItems()) {
            if (order.getClient().getId().equals(clientId)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public List<Order> findAllBetweenDates(Timestamp start, Timestamp end) {
        List<Order> orders = new ArrayList<>();
        for (Order order : getItems()) {
            if (isBetween(order, start, end)) {
                orders.add(order);
            }
        }
        return orders;
    }

    private boolean isBetween(Order order, Timestamp start, Timestamp end) {
        return order.getTimestamp().after(start) && order.getTimestamp().before(end);
    }
    /*public List<Order> findAll() {
        return orders;
    }

    public Order findById(Long id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public void update(Order order) {
        delete(order);
        add(order);
    }

    public Order add(Order order) {
        if(order.getId() == null){
            order.setId(generateNewId());
        }
        orders.add(order);
        return order;
    }
    public void delete(Order order) {
        deleteById(order.getId());
    }

    public boolean deleteById(Long id) {
        Order deleteOrder = null;
        for(Order order : orders){
            if(order.getId().equals(id)){
                deleteOrder = order;
            }
        }
        return orders.remove(deleteOrder);
    }

    private Long generateNewId() {
        return findMaxId() +1;
    }

    private Long findMaxId() {
        Long max = -1L;
        for(Order order : orders){
            if(max<order.getId()){
                max = order.getId();
            }
        }
        return max;
    }*/
}
