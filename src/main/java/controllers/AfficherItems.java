package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import tn.esprit.models.Commande;
import tn.esprit.models.Item;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AfficherItems {

    @FXML
    private TilePane MainTile;

    @FXML
    private TilePane TilePane;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, String> tableAuth;

    @FXML
    private TableColumn<Item, String> tableDesc;

    @FXML
    private TableColumn<Item, String> tablePrix;

    @FXML
    private TableColumn<Item, String> tableTitle;

    private int selectedItemId;

    private List<Commande> userCart = new ArrayList<>();

    @FXML
    private void getUserFromDatabase() {
        int userIdToFind = 31;
        ServiceUtilisateur su = new ServiceUtilisateur();
        ArrayList<Utilisateur> users = su.getAll();
        boolean userFound = false;
    
        if (users != null) {
            for (Utilisateur user : users) {
                if (user.getId() == userIdToFind) {
                    userFound = true;
                    System.out.println("User found: " + user.getNom());
                    break;
                }
            }
        }
    
        if (!userFound) {
            System.out.println("User with ID " + userIdToFind + " not found");
        }
    }
    
    @FXML
    private void initialize() {
        table.refresh();
        ServiceItem si = new ServiceItem();
        ArrayList<Item> items = si.getAll();
        getUserFromDatabase();

        ObservableList<Item> ol = FXCollections.observableArrayList(items);
        table.setItems(ol);

        tableTitle.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        tableDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tableAuth.setCellValueFactory(new PropertyValueFactory<>("Auteur"));
        tablePrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));

        for (Item item : items) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomTilePane.fxml"));
            try {
                Parent root = loader.load();
                CTP controller = loader.getController();
                controller.initData(item);
                TilePane customTilePane = controller.getCustomTilePane();
                MainTile.setHgap(15);
                MainTile.setVgap(10);
                MainTile.setPadding(new Insets(15));
                MainTile.getChildren().add(customTilePane);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void addbtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterItem.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) table.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void deleteItem(ActionEvent event) {
        Item selectedItem = table.getSelectionModel().getSelectedItem();
        ServiceItem si = new ServiceItem();
        if(selectedItem != null) {
            si.Delete(selectedItem);
            table.getItems().remove(selectedItem);
            ArrayList<Item> items = si.getAll();
            ObservableList<Item> ol = FXCollections.observableArrayList(items);
            table.setItems(ol);
            table.refresh();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherItems.fxml"));
            MainTile.getChildren().clear();
            initialize();
        }
    }

    @FXML
    void modifyItem(ActionEvent event) {
        Item selectedItem = table.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierItem.fxml"));
            try {
                Parent root = loader.load();
                ModifierItem controller = loader.getController();
                controller.initData(selectedItem);
                Scene scene = new Scene(root);
                Stage stage = (Stage) table.getScene().getWindow();
                stage.setScene(scene);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void ViewCart(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCommande.fxml"));
        try {
            Parent root = loader.load();
            AfficherCommande controller = loader.getController();
            // Récupérez les commandes de l'utilisateur
            ServiceCommande serviceCommande = new ServiceCommande();
            userCart = serviceCommande.getAll(); // Assurez-vous d'implémenter correctement la méthode getAll() dans ServiceCommande
            controller.initCart(userCart);
            Scene scene = new Scene(root);
            Stage stage = (Stage) table.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createAndFillCommand() {
        if (selectedItemId != 0) {
            int userId = 31; // Utilisateur actuellement connecté (ID fixe pour l'instant)
            float totalPrice = 0.0f; // Définissez le prix total, si nécessaire
            Timestamp currentDate = new Timestamp(System.currentTimeMillis()); // Obtenez la date actuelle

            // Créez un nouvel objet Commande en utilisant l'ID de l'item sélectionné
            Commande newCommande = new Commande(userId, selectedItemId, totalPrice, currentDate);

            // Ajoutez la nouvelle commande à la liste des commandes de l'utilisateur
            userCart.add(newCommande);
        }
    }
}
