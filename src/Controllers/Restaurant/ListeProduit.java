package Controllers.Restaurant;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import models.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ProductService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ListeProduit {

    @FXML
    private ListView<Product> productListView;

    private final ProductService productService;

    public ListeProduit() {
        productService = new ProductService();
    }

    @FXML
    public void initialize() {
        configureListView();
        refreshList();
    }

    private void configureListView() {
        productListView.setCellFactory(param -> new ProductListCell());
        productListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ShowProduits.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        ShowProduits controller = loader.getController();
                        controller.initData(selectedProduct);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void refreshList() {
        List<Product> productList = productService.Read();
        ObservableList<Product> observableProductList = javafx.collections.FXCollections.observableArrayList(productList);
        productListView.setItems(observableProductList);
    }

    @FXML
    public void goToAddPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) productListView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/CreateProduit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Create Produit");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void GoToRestaurant(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) productListView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/UI/ListeRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Liste Restrant");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void OnExport(ActionEvent event) {
        // Create a workbook and a sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Price");
        headerRow.createCell(2).setCellValue("Category");
        headerRow.createCell(3).setCellValue("Restaurant ID");

        // Get product data
        List<Product> productList = productService.Read();

        // Write product data to the sheet
        int rowNum = 1;
        for (Product product : productList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getName());
            row.createCell(1).setCellValue(product.getPrice());
            row.createCell(2).setCellValue(product.getCategory());
            row.createCell(3).setCellValue(product.getRestaurantId());
        }

        try (FileOutputStream fileOut = new FileOutputStream("product_data.xlsx")) {
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void OnStats(ActionEvent event) {
        // Get product data
        List<Product> productList = productService.Read();

        // Calculate statistics
        int totalProducts = productList.size();
        double totalPrice = productList.stream().mapToDouble(Product::getPrice).sum();
        double averagePrice = totalPrice / totalProducts;

        // Create and show alert with statistics
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product Statistics");
        alert.setHeaderText(null);
        alert.setContentText("Total Products: " + totalProducts +
                "\nTotal Price: " + totalPrice +
                "\nAverage Price: " + averagePrice);
        alert.showAndWait();
    }


    static class ProductListCell extends javafx.scene.control.ListCell<Product> {
        @Override
        protected void updateItem(Product product, boolean empty) {
            super.updateItem(product, empty);

            if (empty || product == null) {
                setText(null);
                setGraphic(null);
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ProductCard.fxml"));
                    AnchorPane card = loader.load();
                    ProductCardController controller = loader.getController();
                    controller.setData(product);
                    setGraphic(card);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void OnSort(ActionEvent event) {
        ObservableList<Product> sortedList = productListView.getItems();
        sortedList.sort(Comparator.comparing(Product::getName));
        productListView.setItems(sortedList);
    }

}
