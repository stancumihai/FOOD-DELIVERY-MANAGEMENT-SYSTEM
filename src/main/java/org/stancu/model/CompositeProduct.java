package org.stancu.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {

    private List<MenuItem> menuItems = new ArrayList<>();

    public CompositeProduct() {
    }

    public CompositeProduct(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "menuItems=" + menuItems +
                '}';
    }
}
