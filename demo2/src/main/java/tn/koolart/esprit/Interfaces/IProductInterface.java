package tn.koolart.esprit.Interfaces;

import tn.koolart.esprit.models.Product;

import java.util.List;

public interface IProductInterface {
    int Create(Product P);
    List<Product> Read();
    int Update(Product P,int id);
    void Delete(int P);
    Product ReadById(int id);
    Product ReadByNom(String Nom);
    Product ReadByCategory(String Category);
    Product ReadByRestaurantId(int id);
}
