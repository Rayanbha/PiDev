/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasetup;

import Entité.Product;
import Entité.Restaurant;
import java.sql.SQLException;
import service.Connexion_database;
import service.ProductService;
import service.RestaurantService;

/**
 *
 * @author user
 */
public class JavaSetup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        RestaurantService service = new RestaurantService();
        Restaurant rs = new Restaurant("chefoumaima","fdf","vsdfsd");
        Restaurant rs1 = new Restaurant("chemi","azur","fastfood");
        Restaurant rs3 = new Restaurant("oumaimaaaa","geant","foodfood");
         //service.Delete(3);

        //service.Create(rs3);
        System.out.println(service.ReadById(6));
        // service.Update(rs3,5);
        //System.out.println(service.Read());



        // System.out.println(service.ReadById(1));
        ProductService productService = new ProductService();
        Product p = new Product("name",2.0,"test",1);
        Product p1 = new Product("food",5.0,"tasty",6);
        //productService.Delete(1);
      //  productService.Create(p1);
       // System.out.println(productService.ReadById(5));

    }
    
}
