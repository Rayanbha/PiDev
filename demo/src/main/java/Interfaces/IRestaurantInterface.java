package Interfaces;

import models.Restaurant;

import java.util.List;

public interface IRestaurantInterface {
    int Create(Restaurant R);
    List<Restaurant> Read();
    int Update(Restaurant R,int id);
    int Delete(int R);
    Restaurant ReadById(int id);
    Restaurant ReadByName(String name);
    Restaurant ReadByLocation(String Location);
    Restaurant ReadByCategory(String category);
}
