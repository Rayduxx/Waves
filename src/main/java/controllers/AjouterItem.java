package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.models.Item;
import tn.esprit.services.ServiceItem;

import java.io.IOException;


public class AjouterItem {

    ServiceItem si = new ServiceItem();
    
    @FXML
    private TextField Auth;

    @FXML
    private TextField Desc;

    @FXML
    private TextField Titre;

    @FXML
    private TextField prix;

    @FXML
    private Button mpButton;

    @FXML
    void Add(ActionEvent event) {
        si.Add(new Item(Titre.getText(),Desc.getText(),Auth.getText(),Float.parseFloat(prix.getText())));
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
}
