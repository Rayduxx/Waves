package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import tn.esprit.models.Item;
import tn.esprit.services.ServiceItem;

import java.io.IOException;
import java.util.ArrayList;
import controllers.AjouterItem;

public class AfficherItems {

    @FXML
    private TilePane item;

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

    @FXML
    private void initialize() {
        table.refresh();
        ServiceItem si = new ServiceItem();
        ArrayList<Item> items = si.getAll();

        ObservableList<Item> ol = FXCollections.observableArrayList(items);
        table.setItems(ol);

        tableTitle.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        tableDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tableAuth.setCellValueFactory(new PropertyValueFactory<>("Auteur"));
        tablePrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));

        for (Item item : items) {
            TilePane elementTilePane = createTilePaneForElement(item);
            this.item.getChildren().add(elementTilePane);
        }
    }
    private TilePane createTilePaneForElement(Item item) {
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(1);

        Label nom = new Label("Title: " + item.getTitre());
        Label description = new Label("Description: " + item.getDescription());
        Label auteur = new Label("Author: " + item.getAuteur());
        Label prix = new Label("Price: " + item.getPrix());

        tilePane.getChildren().addAll(nom, description, auteur, prix);

        return tilePane;
    }

    @FXML
    void addbtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterItem.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) item.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
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
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) item.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
