package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Item;
import tn.esprit.services.ServiceItem;

import java.io.IOException;

public class ModifierItem {

    private int id;

    @FXML
    private TextField Auth;

    @FXML
    private TextField Desc;

    @FXML
    private Button ModifierItem;

    @FXML
    private TextField Titre;

    @FXML
    private Button mpButton;

    @FXML
    private TextField prix;

    @FXML
    void Modify(ActionEvent event) {
        ServiceItem si = new ServiceItem();
        si.Update(new Item(id,Titre.getText(),Desc.getText(),Auth.getText(),Float.parseFloat(prix.getText())));
    }

    @FXML
    void mp(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherItems.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mpButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initData(Item selectedItem) {
        Titre.setText(selectedItem.getTitre());
        Desc.setText(selectedItem.getDescription());
        Auth.setText(selectedItem.getAuteur());
        prix.setText(String.valueOf(selectedItem.getPrix()));
        id = selectedItem.getItemID();
    }
}

