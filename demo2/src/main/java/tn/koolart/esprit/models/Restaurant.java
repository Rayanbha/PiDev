/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.koolart.esprit.models;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String location;
    private String category;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String name, String location, String category) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.category = category;
    }

    public Restaurant(String name, String location, String category) {
        this.name = name;
        this.location = location;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
