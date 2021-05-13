package org.stancu.model;

public class Dummy {

    private String title;
    private Integer price;

    public Dummy() {
    }

    public Dummy(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Title : " + title +
                        " price :" + price + "\n";
    }
}
