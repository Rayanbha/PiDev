package Controllers.Restaurant;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Restaurant;
import models.user;
import services.RestaurantService;
import services.UserService;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class ListeRestaurant {
    @FXML
    private TextField searchField;
    @FXML
    private ListView<Restaurant> restaurantListView;

    private final RestaurantService service = new RestaurantService();
    private user user=new user();

    public void InitUser(int id)
    {
        UserService us=new UserService();
        this.user=us.findcin(id);
    }
    @FXML
    void initialize() {
        configureListView();
        refreshList();
    }

    private void configureListView() {
        restaurantListView.setCellFactory(param -> new RestaurantCardCell());
        restaurantListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ShowRestaurant.fxml"));
                    Parent root = loader.load();
                    ShowRestaurant controller = loader.getController();
                    controller.setRestaurant(newValue);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("Opened");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void refreshList() {
        List<Restaurant> list = service.Read();
        ObservableList<Restaurant> observableList = FXCollections.observableArrayList(list);
        restaurantListView.setItems(observableList);
    }

    @FXML
    void onClick(ActionEvent event) {
        refreshList();
    }

    @FXML
    void onAjouter(ActionEvent event) throws IOException {
        Stage stage = (Stage) restaurantListView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/CreateRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Create Resto");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToProduit(ActionEvent event) throws IOException {
        Stage stage = (Stage) restaurantListView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/ListeProduit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Liste Produit");
        stage.setScene(scene);
        stage.show();
    }

    public void onSort(ActionEvent actionEvent) {
        ObservableList<Restaurant> items = restaurantListView.getItems();
        items.sort(Comparator.comparing(Restaurant::getName));
        restaurantListView.setItems(items);
    }

    public void onStats(ActionEvent actionEvent) throws IOException {
        List<Restaurant> list = restaurantListView.getItems();
        int totalRestaurants = list.size();

        Map<String, Integer> categoryCountMap = new HashMap<>();
        for (Restaurant restaurant : list) {
            String category = restaurant.getCategory();
            categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
        }
        String mostPopularCategory = Collections.max(categoryCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Statistics");
        alert.setHeaderText(null);
        alert.setContentText("Total number of restaurants: " + totalRestaurants +
                "\nMost popular category: " + mostPopularCategory);
        alert.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Stats.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onRecherche(ActionEvent actionEvent) throws IOException {
        String searchText = searchField.getText().toLowerCase().trim();
        if (searchText.isEmpty()) {
            refreshList();
        } else {
            Restaurant newValue = service.ReadByName(searchText);
            if(newValue != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ShowRestaurant.fxml"));
                Parent root = loader.load();
                ShowRestaurant controller = loader.getController();
                controller.setRestaurant(newValue);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Doesn't Exist");
                alert.setHeaderText(null);
                alert.setContentText("Restaurant with that name doesn't exist yet");
                alert.show();
            }
        }
    }


    static class RestaurantCardCell extends ListCell<Restaurant> {
        @Override
        protected void updateItem(Restaurant restaurant, boolean empty) {
            super.updateItem(restaurant, empty);
            if (empty || restaurant == null) {
                setText(null);
                setGraphic(null);
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/RestaurantCard.fxml"));
                    Parent root = loader.load();
                    RestaurantCardController controller = loader.getController();
                    controller.setRestaurant(restaurant);
                    setGraphic(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void PrintPdf(ActionEvent event) {
        List<Restaurant> list = restaurantListView.getItems();
        int totalRestaurants = list.size();

        Map<String, Integer> categoryCountMap = new HashMap<>();
        for (Restaurant restaurant : list) {
            String category = restaurant.getCategory();
            categoryCountMap.put(category, categoryCountMap.getOrDefault(category, 0) + 1);
        }
        String mostPopularCategory = Collections.max(categoryCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        // Create PDF document
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("statistics.pdf"));
            document.open();


            document.add(new Paragraph("Statistics"));
            document.add(new Paragraph("Total number of restaurants: " + totalRestaurants));
            document.add(new Paragraph("Most popular category: " + mostPopularCategory));

            // Draw a simple chart (you can customize this based on your needs)
            for (Map.Entry<String, Integer> entry : categoryCountMap.entrySet()) {
                String category = entry.getKey();
                int count = entry.getValue();

                // Draw a simple bar chart
                document.add(new Paragraph(category + ": " + drawBarChart(count)));
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }

        try {
            File pdfFile = new File("statistics.pdf");
            Desktop.getDesktop().open(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String drawBarChart(int count) {
        StringBuilder chart = new StringBuilder();
        for (int i = 0; i < count; i++) {
            chart.append("*");
        }
        return chart.toString();
    }
}
