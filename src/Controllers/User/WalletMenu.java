package Controllers.User;

import Controllers.Restaurant.ListeRestaurant;
import Controllers.Restaurant.ListeRestaurantClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Wallet;
import models.user;
import models.transaction;
import services.TransactionService;
import services.UserService;
import services.WalletService;



import java.io.IOException;
import java.util.*;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javafx.scene.control.Label;


public class WalletMenu {
    private int id;
    @FXML
    private Button transaction;
    private List<Wallet> wallets = new ArrayList<>();
    private Wallet w=new Wallet();


    private WalletService walletService= new WalletService();
    @FXML
    private ListView<transaction> transactionLV;


    @FXML
    private TextField balanceTF;
    private user user;
    private Label createLabel(String labelText, String value) {
        Label label = new Label(labelText + " " + value);
        label.setStyle("-fx-font-weight: bold");
        return label;}

    public void initWallet(user user1,int id)
    {

        this.user=user1;
        this.id=user1.getId();
        wallets=walletService.find(id);
        TransactionService ts=new TransactionService();
        List<transaction> listT=ts.read();
        List<transaction> filter=listT.stream().filter(transaction ->transaction.getIdcurrent()==user.getId()).collect(Collectors.toList());
        ObservableList<transaction> observableList= FXCollections.observableList(filter);
        transactionLV.setItems(observableList);

        transactionLV.setCellFactory(new Callback<ListView<transaction>, ListCell<transaction>>() {
            @Override
            public ListCell<transaction> call(ListView<transaction> param) {
                return new ListCell<transaction>() {
                    @Override
                    protected void updateItem(transaction item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            // Create VBox for vertical layout
                            VBox vbox = new VBox(5);
                            vbox.setPadding(new Insets(5));
                            vbox.setAlignment(Pos.CENTER_LEFT);
                            vbox.setStyle("-fx-background-color: #FFDE59;"); // Set item background color

                            // Create labels for each transaction property
                            Label idtransactionLabel = createLabel("ID Transaction :", Integer.toString(item.getIdtransaction()));
                            Label idwalletLabel = createLabel("ID Wallet :", Integer.toString(item.getIdwallet()));
                            Label datetimeLabel = createLabel("Datetime :", item.getDatetime().toString());
                            Label montantLabel = createLabel("Montant :", Float.toString(item.getMontant()));
                            Label iddestinataireLabel = createLabel("ID Destinataire :", Integer.toString(item.getIddestinataire()));
                            Label idcurrentLabel = createLabel("ID Current :", Integer.toString(item.getIdcurrent()));

                            // Add labels to VBox
                            vbox.getChildren().addAll(idtransactionLabel, idwalletLabel, datetimeLabel, montantLabel, iddestinataireLabel, idcurrentLabel);

                            // Set cell content
                            setGraphic(vbox);
                        }
                    }
                };
            }
        });

// Method to create a label


        populateFields();
            //-------------------------------------------------CHOICEBOX---------------------------------------------------------------
        ObservableList<Integer> walletCountList= FXCollections.observableArrayList();
        int count=walletService.count(id);
        int l=1;
        for(int i=0;i<count;i++)
        {
            walletCountList.add(l);
            l++;
        }
        Walletnumber.setItems(walletCountList);
        Walletnumber.setOnAction(this::getnumber);
        //------------------------------------------------------------------------------------------------------------------------------
    }

    private void getnumber(ActionEvent actionEvent) {
        int choice=Walletnumber.getValue();
        int count=walletService.count(id);
        int l=0;
        for(int i=0;i<count+1;i++)
        {
            if(choice==i)
            {
                for (Wallet wallet:wallets)
                {   l++;
                    if(l==i)
                    {
                        w=wallet;
                        balanceTF.setText(String.valueOf(wallet.getBalance()));
                        break;

                    }
                }

            }
        }


    }


    private void populateFields() {
        balanceTF.setText(String.valueOf(w.getBalance()));
    }
    @FXML
    private Button AddWallet;

    @FXML
    private ChoiceBox<Integer> Walletnumber;
    @FXML
    void AddWallet(ActionEvent event) {


        try {
            Wallet wallet=new Wallet(0,id);
            walletService.add(wallet);


            Stage current=(Stage) AddWallet.getScene().getWindow();
            current.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
            Parent root = loader.load();
            WalletMenu walletc=loader.getController();
            walletc.initWallet(user,id);
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Wallet");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private Button ChangeBal;
    @FXML
    private TextField newBalTF;
    @FXML
    void ChangeBal(ActionEvent event) {
        float jdid;
        float newbal=Float.parseFloat(newBalTF.getText());
        jdid=w.getBalance()+newbal;
        w.setBalance(jdid);
        walletService.update(w);

        Stage current=(Stage) ChangeBal.getScene().getWindow();
        current.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WalletMenu walletc=loader.getController();
        walletc.initWallet(user,id);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Wallet");
        stage.setScene(scene);
        stage.show();
        balanceTF.setText(String.valueOf(jdid));

    }

    @FXML
    private Button DeleteWallet;

    @FXML
    void DeleteWallet(ActionEvent event) {
        if (Walletnumber.getValue() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete User");
            alert.setContentText("Are you sure you want to delete Wallet ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Call your UserService to delete the user
               walletService.delete(w);

                Stage current=(Stage) DeleteWallet.getScene().getWindow();
                current.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/WalletMenu.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                WalletMenu walletc=loader.getController();
                walletc.initWallet(user,id);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Wallet");
                stage.setScene(scene);
                stage.show();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No user selected!");
            alert.showAndWait();
        }

    }

    @FXML
    private Label Compte;
    @FXML
    void Compte(MouseEvent event) {
        try {
            Stage currentStage=(Stage)Compte.getScene().getWindow();
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UpdateUser.fxml"));
            Parent root = loader.load();
            UpdateUser userToUpdate=loader.getController();
            userToUpdate.initData(user);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void wallet(MouseEvent mouseEvent) {
    }


    @FXML
    void transaction(ActionEvent event) {
        if (Walletnumber.getValue() == null) {
            showAlert("Error", "Selectionner Wallet");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Transaction.fxml"));
                Parent root = loader.load();
                Transaction transactionController = loader.getController();
                transactionController.initData(user, w);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);

                // Set a listener to refresh the ListView when the stage is closed
                stage.setOnHiding((e) -> {
                    // Update the list of transactions
                    TransactionService ts = new TransactionService();
                    List<transaction> listT = ts.read();
                    List<transaction> filter = listT.stream()
                            .filter(transaction -> transaction.getIdcurrent() == user.getId())
                            .collect(Collectors.toList());
                    ObservableList<transaction> observableList = FXCollections.observableList(filter);

                    // Set the items of the ListView
                    transactionLV.setItems(observableList);
                });

                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private Label compte;
    @FXML
    void compte(MouseEvent event) {
        if (u.getRole().equals("Client")) {
            try {

                Stage stage=(Stage)compte.getScene().getWindow();
                stage.close();
                Stage primaryStage=new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/CompteUser.fxml"));
                Parent root = loader.load();
                CompteUser compte=loader.getController();
                compte.initData(u);
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageUser.fxml"));
                Parent root = loader.load();
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void forum(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AjouterPost.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    Preferences prefs = Preferences.userNodeForPackage(this.getClass());

    UserService us=new UserService();

    @FXML
    void recette(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AffichageRecipe.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    user u = us.findcin(Integer.parseInt(prefs.get("cin", "not found")));

    @FXML
    void restaurant(MouseEvent event) {
        if (u.getRole().equals("Admin")) {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurant.fxml"));
                Parent root = loader.load();

                // Get the controller instance from the loader
                ListeRestaurant controller = loader.getController();

                // Pass the user information to the controller
                controller.InitUser(Integer.parseInt(prefs.get("cin", "not found"))); // Parse the integer value from preferences

                // Set up the stage and scene
                primaryStage.setTitle("Restaurant List");
                primaryStage.setScene(new Scene(root));

                // Show the stage
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ListeRestaurantClient.fxml"));
                Parent root = loader.load();

                // Get the controller instance from the loader
                ListeRestaurantClient controller = loader.getController();

                // Pass the user information to the controller
                controller.InitUser(Integer.parseInt(prefs.get("cin", "not found"))); // Parse the integer value from preferences

                // Set up the stage and scene
                primaryStage.setTitle("Restaurant List");
                primaryStage.setScene(new Scene(root));

                // Show the stage
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void acceuil(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/HomePage.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Add");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void event(MouseEvent event) {
        if (u.getRole().equals("Admin")) {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/events.fxml"));
                Parent root = loader.load();
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/eventFront.fxml"));
                Parent root = loader.load();
                primaryStage.setTitle("Add");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
