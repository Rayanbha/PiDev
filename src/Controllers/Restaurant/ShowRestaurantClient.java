package Controllers.Restaurant;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.sothawo.mapjfx.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import models.Restaurant;

public class ShowRestaurantClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label idLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private GridPane gridmapView;


    @FXML
    private MapView mapView;
    private static final int ZOOM_DEFAULT = 14;


    private final Coordinate centerCoordiante = new Coordinate(36.8064948, 10.1815316);
    public Coordinate coordKarlsruheHarbour;
    private final Marker markerClick;
    private Restaurant restaurant;

    @FXML
    void initialize() {
        assert categoryLabel != null : "fx:id=\"categoryLabel\" was not injected: check your FXML file '/UI/ShowRestaurantClient.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file '/UI/ShowRestaurantClient.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file '/UI/ShowRestaurantClient.fxml'.";
        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file '/UI/ShowRestaurantClient.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file '/UI/ShowRestaurantClient.fxml'.";

    }

    private void showMap(String coordinatesString) {
        String[] coordinates = coordinatesString.split(", ");

        // Parse latitude and longitude as doubles
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);
        this.coordKarlsruheHarbour = new Coordinate(latitude, longitude);
        initMapAndControls(this.coordKarlsruheHarbour);

    }


    public ShowRestaurantClient() {
        this.markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);
    }

    public void initMapAndControls(Coordinate resto) {
        mapView.setVisible(true);
        mapView.setCustomMapviewCssURL(Objects.requireNonNull(getClass().getResource("/resources/UI/custom_mapview.css")));
        System.out.println("Init map Controls");

        mapView.setZoom(ZOOM_DEFAULT);
        mapView.setCenter(centerCoordiante);
        mapView.initialize(Configuration.builder()
                .projection(Projection.WEB_MERCATOR)
                .showZoomControls(false)
                .build());
        gridmapView.add(mapView, 0, 0);
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mapView.setZoom(ZOOM_DEFAULT);
                System.out.println(centerCoordiante);
                mapView.setCenter(centerCoordiante);
            }
            else {
                mapView.setCenter(centerCoordiante);

            }
        });
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterMapIsInitialized(resto);
            }

        });    }
    private void afterMapIsInitialized(Coordinate resto) {

        // start at the harbour with default zoom
        mapView.setZoom(ZOOM_DEFAULT);
        mapView.setCenter(coordKarlsruheHarbour);
        System.out.println("maps"+resto);
        // add the markers to the map - they are still invisible

        Marker markerResto = Marker.createProvided(Marker.Provided.GREEN).setPosition(resto).setVisible(true);
        System.out.println("marker"+markerResto);
        mapView.addMarker(markerResto);
        System.out.println("Marker Coordinates: " + resto);
        System.out.println("MapView Zoom Level: " + mapView.getZoom());

    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        nameLabel.setText(restaurant.getName());
        locationLabel.setText(restaurant.getLocation());
        categoryLabel.setText(restaurant.getCategory());

        if (restaurant.getImage() != null && !restaurant.getImage().isEmpty()) {
            try {
                String imageUrl = restaurant.getImage();
                System.out.println("Image URL: " + imageUrl);
                imageView.setImage(new Image(imageUrl));
            } catch (Exception e) {
                System.out.println("No Image Found");
            }
        }
        showMap(restaurant.getLocation());
    }
}
