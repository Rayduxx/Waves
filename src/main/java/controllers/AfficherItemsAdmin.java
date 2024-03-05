package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.models.Item;
import tn.esprit.services.ServiceItem;

import java.io.IOException;
import java.util.ArrayList;

public class AfficherItemsAdmin {

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

        ObservableList<Item> obl = FXCollections.observableArrayList(items);
        table.setItems(obl);

        tableTitle.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        tableDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tableAuth.setCellValueFactory(new PropertyValueFactory<>("Auteur"));
        tablePrix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
    }

    @FXML
    void addbtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterItem.fxml"));
        try {
            Parent root = loader.load();
            AjouterItem controller = loader.getController();
            controller.setIntValue(1);
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
            initialize();
        }
    }

    @FXML
    void modifyItem(ActionEvent event) {
        Item si = table.getSelectionModel().getSelectedItem();
        if(si != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierItem.fxml"));
            try {
                Parent root = loader.load();
                ModifierItem controller = loader.getController();
                controller.initData(si);
                Scene scene = new Scene(root);
                Stage stage = (Stage) table.getScene().getWindow();
                stage.setScene(scene);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
