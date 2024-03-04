package Controllers.Restaurant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Product;

public class ProductCardController {

    @FXML
    private Label productNameLabel;

    @FXML
    private Label productPriceLabel;

    @FXML
    private Label productCategoryLabel;

    public void setData(Product product) {
        productNameLabel.setText(product.getName());
        productPriceLabel.setText(String.valueOf(product.getPrice()));
        productCategoryLabel.setText(product.getCategory());
    }
}
