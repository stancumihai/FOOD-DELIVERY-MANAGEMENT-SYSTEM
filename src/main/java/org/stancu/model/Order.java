package org.stancu.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {

    private Integer id;
    private Integer clientId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Integer totalPrice;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Order() {
    }

    public Order(Integer clientId, LocalDate orderDate, LocalTime orderTime, Integer totalPrice) {
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
    }

    public Order(Integer id, Integer clientId, LocalDate orderDate, LocalTime orderTime, Integer totalPrice) {
        this.id = id;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        return "Order " +
                "id: " + id +
                ", clientId: " + clientId +
                ", orderDate: " + orderDate +
                ", orderTime: " + orderTime +
                ", totalPrice: " + totalPrice +
                ", menuItems: " + Arrays.toString(menuItems.toArray()) + "\n";
    }
}
