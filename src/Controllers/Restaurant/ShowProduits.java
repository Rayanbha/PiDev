package Controllers.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import  models.Product;
import services.ProductService;

import java.io.IOException;

public class ShowProduits {

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label restaurantIdLabel;

    private Product product;
    private ProductService productService = new ProductService();

    public void initData(Product product) {
        this.product = product;
        nameLabel.setText(product.getName());
        priceLabel.setText(String.valueOf(product.getPrice()));
        categoryLabel.setText(product.getCategory());
        restaurantIdLabel.setText(String.valueOf(product.getRestaurantId()));
    }

    @FXML
    public void onDelete(ActionEvent actionEvent) throws IOException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this product?");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                productService.Delete(product.getProductId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product Deleted Successfully.");
                alert.showAndWait();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeProduit.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) idLabel.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML
    public void onEdit(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/EditProduit.fxml"));
            Parent root = loader.load();
            EditProduit controller = loader.getController();
            controller.initData(product);
            Stage stage = (Stage) idLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
