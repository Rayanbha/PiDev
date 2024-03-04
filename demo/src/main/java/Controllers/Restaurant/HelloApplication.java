package Controllers.Restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Admin
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ListeRestaurant.fxml"));
        //Client
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ListeRestaurantClient.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("KOOLart!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}