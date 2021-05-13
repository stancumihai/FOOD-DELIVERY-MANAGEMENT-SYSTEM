package org.stancu.model;

import java.util.List;

public abstract class MenuItem {

    public void computePrice(MenuItem menuItem) {
        throw new UnsupportedOperationException();
    }

    public Integer getId() {
        throw new UnsupportedOperationException();
    }

    public String getTitle() {
        throw new UnsupportedOperationException();
    }

    public Double getRating() {
        throw new UnsupportedOperationException();
    }

    public Integer getCalories() {
        throw new UnsupportedOperationException();
    }

    public Integer getProtein() {
        throw new UnsupportedOperationException();
    }

    public Integer getSodium() {
        throw new UnsupportedOperationException();
    }

    public Integer getFat() {
        throw new UnsupportedOperationException();
    }

    public Integer getPrice() {
        throw new UnsupportedOperationException();
    }

    public void setId(Integer id) {
        System.out.println("Go to id in menuItem");
        throw new UnsupportedOperationException();
    }

    public void setTitle(String title) {
        throw new UnsupportedOperationException();
    }

    public void setRating(Double rating) {
        throw new UnsupportedOperationException();
    }

    public void setCalories(Integer calories) {
        throw new UnsupportedOperationException();
    }

    public void setProtein(Integer protein) {
        throw new UnsupportedOperationException();
    }

    public void setSodium(Integer sodium) {
        throw new UnsupportedOperationException();
    }

    public void setFat(Integer fat) {
        throw new UnsupportedOperationException();
    }

    public void setPrice(Integer price) {
        throw new UnsupportedOperationException();
    }

    public List<MenuItem> getMenuItems() {
        throw new UnsupportedOperationException();
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        throw new UnsupportedOperationException();
    }

    public String toString2() {
        return "BaseProduct: " +
                " title: " + getTitle() +
                " price: " + getPrice();
    }

}
