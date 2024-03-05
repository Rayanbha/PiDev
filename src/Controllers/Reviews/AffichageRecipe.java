package Controllers.Reviews;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.recipe;
import services.recipeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AffichageRecipe {

    @FXML
    private ListView<recipe> recipeLV;

    @FXML
    void addReview() {
        recipe selectedRecipe = recipeLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            openAjouterReview(selectedRecipe);
        } else {
            // Handle case where no recipe is selected
        }
    }

    private void openAjouterReview(recipe selectedRecipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AjouterReview.fxml"));
            Parent root = loader.load();
            AjouterReview controller = loader.getController();
            controller.initData(selectedRecipe); // Pass the selected recipe to AjouterReview controller
            Stage stage = new Stage();
            stage.setTitle("Add Review");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AjouterRecipe.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Review");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private TextField searchField;

    private final recipeService rc = new recipeService();
    private ObservableList<recipe> recipesObservableList;

    public void initialize() {
        refreshDisplay();
    }

    @FXML
    void handleSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            recipeLV.setItems(recipesObservableList);
        } else {
            ObservableList<recipe> filteredRecipes = FXCollections.observableArrayList();
            for (recipe recipe : recipesObservableList) {
                if (recipe.getName().toLowerCase().contains(searchText)) {
                    filteredRecipes.add(recipe);
                }
            }
            recipeLV.setItems(filteredRecipes);
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        recipe selectedRecipe = recipeLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Supprimer la recette");
            alert.setContentText("Voulez-vous vraiment supprimer cette recette ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                recipesObservableList.remove(selectedRecipe);
                rc.supprimer(selectedRecipe.getIdrecp());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune recette sélectionnée");
            alert.setContentText("Veuillez sélectionner une recette à supprimer.");
            alert.showAndWait();
        }
    }

    @FXML
    void Update(ActionEvent event) {
        recipe selectedRecipe = recipeLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            showUpdateRecipeUI(selectedRecipe);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune recette sélectionnée");
            alert.setContentText("Veuillez sélectionner une recette à mettre à jour.");
            alert.showAndWait();
        }
    }

    @FXML
    void AddReview(ActionEvent event) {
        recipe selectedRecipe = recipeLV.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            showAddReviewUI(selectedRecipe);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune recette sélectionnée");
            alert.setContentText("Veuillez sélectionner une recette pour ajouter une critique.");
            alert.showAndWait();
        }
    }

    private void showUpdateRecipeUI(recipe selectedRecipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateRecipe.fxml"));
            Parent root = loader.load();
            UpdateRecipeController controller = loader.getController();
            controller.initData(selectedRecipe, this);
            Stage stage = new Stage();
            stage.setTitle("Mise à jour de la recette");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAddReviewUI(recipe selectedRecipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AjouterReview.fxml"));
            Parent root = loader.load();
            AjouterReview controller = loader.getController();
            controller.initData(selectedRecipe);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un avis");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void refreshDisplay() {
        try {
            List<recipe> recipeList = rc.fetch();
            recipesObservableList = FXCollections.observableArrayList(recipeList);
            recipeLV.setItems(recipesObservableList);
            recipeLV.setOnMouseClicked(event -> {
                recipe selectedItem = recipeLV.getSelectionModel().getSelectedItem();
                if (selectedItem != null && selectedItem.getImageUrl() != null) {
                    Image image = new Image(selectedItem.getImageUrl());
                    ImageView imageView = new ImageView(image);
                    imageView.setPreserveRatio(true);
                    imageView.isResizable();

                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(imageView);
                    imageView.fitWidthProperty().bind(dialogVbox.widthProperty());
                    imageView.fitHeightProperty().bind(dialogVbox.heightProperty());
                    Scene dialogScene = new Scene(dialogVbox, 400, 300);
                    dialog.setScene(dialogScene);
                    dialog.showAndWait();
                }
            });
            recipeLV.setCellFactory(param -> new ListCell<recipe>() {
                @Override
                protected void updateItem(recipe item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.getName() + " - " + item.getIngrs() + " - " + item.getInstrs());
                        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
                            ImageView imageView = new ImageView(new Image(item.getImageUrl()));
                            imageView.setFitWidth(100);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}