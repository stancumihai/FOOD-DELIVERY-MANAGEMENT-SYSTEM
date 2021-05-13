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

    public Order findById(Integer id) {
        return orderDataAccessService.findById(id);
    }

    public Order update(Integer id, Order model) {
        return orderDataAccessService.update(model, id);
    }

    public void insert(Order model) {
        orderDataAccessService.insert(model);
    }

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
