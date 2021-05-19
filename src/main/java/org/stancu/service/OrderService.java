package org.stancu.service;


import org.stancu.dao.OrderDao;
import org.stancu.model.Order;

import java.util.Map;

/***
 * The business logic for the Order Class
 */
public class OrderService {

    public static OrderDao orderDataAccessService = new OrderDao();
    public static OrderService orderService = null;

    private OrderService(OrderDao orderDataAccessService) {
        OrderService.orderDataAccessService = orderDataAccessService;
    }

    /***
     *
     * @return it returns singleton instance of OrderService
     */
    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService(orderDataAccessService);
        }
        return orderService;
    }
    /**
     * @param id  to search in orders
     * @post return != null
     */
    public Order findById(Integer id) {
        return orderDataAccessService.findById(id);
    }
    /**
     * @param id  to update in orders
     * @post return != null
     */
    public Order update(Integer id, Order model) {
        return orderDataAccessService.update(model, id);
    }
    /**
     * @param model  to insert in orders
     * @post return != null
     */
    public void insert(Order model) {
        orderDataAccessService.insert(model);
    }
    /**
     * @param id  to search in orders
     * @post return != null
     */
    public Order delete(Integer id) {
        return orderDataAccessService.delete(id);
    }

    public Map<Integer, Order> selectAll() {
        return orderDataAccessService.selectAll();
    }

    public Integer getLastId() {
        return orderDataAccessService.getLastId();
    }
}
