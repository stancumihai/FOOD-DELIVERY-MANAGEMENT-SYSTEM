package org.stancu.dao;

import org.stancu.model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderDao {

    private final Map<Integer, Order> orders = new HashMap<>();

    public Order findById(Integer id) {
        for (Integer myId : orders.keySet()) {
            if (myId.equals(id)) {
                return orders.get(myId);
            }
        }
        return null;
    }

    public Map<Integer, Order> selectAll() {
        return orders;
    }

    public Order delete(Integer id) {
        Order order = findById(id);
        if (order != null) {
            orders.remove(id);
            return order;
        } else return null;
    }


    public Order update(Order model, Integer id) {
        Order order = findById(id);
        if (order != null) {
            for (Order order1 : orders.values()) {
                if (order1.equals(order)) {
                    orders.replace(id, order1, model);
                    return model;
                }
            }
        }
        return null;
    }


    public Order insert(Order model) {
        if (orders.size() == 0) {
            model.setId(1);
        } else {
            model.setId(getLastId() + 1);
        }
        orders.put(getLastId() + 1, model);
        return model;
    }

    public Integer getLastId() {
        return orders.size();
    }
}
