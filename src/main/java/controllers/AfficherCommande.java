package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.models.Item;

import java.io.IOException;
import java.util.List;

public class AfficherCommande {

    @FXML
    private ListView<?> DeleteCell;

    @FXML
    private ListView<String> ItemCell;

    @FXML
    private ListView<Float> PriceCell;

    @FXML
    private Text PriceTotal;

    @FXML
    private Button Retour;

    float total = 0;

    public void initCart(List<Item> userCart) {
        ItemCell.getItems().clear();
        PriceCell.getItems().clear();

        ObservableList<String> itemNames = FXCollections.observableArrayList();
        ObservableList<Float> itemPrices = FXCollections.observableArrayList();

        for (Item item : userCart) {
            itemNames.add(item.getTitre());
            itemPrices.add(item.getPrix());
            total += item.getPrix();
        }

        ItemCell.setItems(itemNames);
        PriceCell.setItems(itemPrices);
        PriceTotal.setText(String.valueOf(total) + " DT");
    }



    @FXML
    void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherItems.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Retour.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
