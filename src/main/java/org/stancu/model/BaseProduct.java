package org.stancu.model;

import java.io.Serializable;
import java.util.Objects;

public class BaseProduct extends MenuItem implements Serializable {

    private Integer id;
    private String title;
    private Double rating;
    private Integer calories;
    private Integer protein;
    private Integer sodium;
    private Integer fat;
    private Integer price;

    public BaseProduct() {
    }

    public BaseProduct(String title, Double rating, Integer calories, Integer protein, Integer sodium,
                       Integer fat, Integer price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.sodium = sodium;
        this.fat = fat;
        this.price = price;
    }

    public BaseProduct(Integer id, String title, Double rating, Integer calories, Integer protein, Integer sodium, Integer fat, Integer price) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.sodium = sodium;
        this.fat = fat;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseProduct)) return false;
        BaseProduct that = (BaseProduct) o;
        return getTitle().equals(that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    public BaseProduct(String s) {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Double getRating() {
        return rating;
    }

    @Override
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public Integer getCalories() {
        return calories;
    }

    @Override
    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public Integer getProtein() {
        return protein;
    }

    @Override
    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    @Override
    public Integer getSodium() {
        return sodium;
    }

    @Override
    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    @Override
    public Integer getFat() {
        return fat;
    }

    @Override
    public void setFat(Integer fat) {
        this.fat = fat;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "BaseProduct: " +
                "id: " + id +
                ", title: " + title +
                ", rating: " + rating +
                ", calories: " + calories +
                ", protein: " + protein +
                ", sodium: " + sodium +
                ", fat: " + fat +
                ", price: " + price + "\n";
    }
}
