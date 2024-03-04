package Controllers.Restaurant;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import models.Restaurant;
import services.ProductService;
import services.RestaurantService;

import java.util.List;

public class StatsController {

    @FXML
    private AnchorPane chartPane;

    private final RestaurantService restaurantService = new RestaurantService();
    private final ProductService productService = new ProductService();

    @FXML
    void initialize() {
        List<Restaurant> restaurants = restaurantService.Read();

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Number of Products par Restaurant");

        for (Restaurant restaurant : restaurants) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(restaurant.getName());
            int numberOfProducts = productService.countProductsByRestaurant(restaurant.getRestaurantId());

            series.getData().add(new XYChart.Data<>(restaurant.getRestaurantId(), numberOfProducts));
            lineChart.getData().add(series);
        }

        AnchorPane.setTopAnchor(lineChart, 0.0);
        AnchorPane.setBottomAnchor(lineChart, 0.0);
        AnchorPane.setLeftAnchor(lineChart, 0.0);
        AnchorPane.setRightAnchor(lineChart, 0.0);
        chartPane.getChildren().add(lineChart);
    }
}
