package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.IOException;

public class ajouterEvent {

    @FXML
    private TextField Nom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField date;

    @FXML
    private TextField desc;





    private final ServiceEvent ps = new ServiceEvent();


    @FXML
    void ajouterEvent(ActionEvent event) {
        if (Nom == null || Nom.getText().isEmpty()) {
            showAlert("Erreur", "Nom manquant", "Veuillez entrer un nom.");
            return;
        }
        if (adresse == null || adresse.getText().isEmpty()) {
            showAlert("Erreur", "Adresse manquante", "Veuillez entrer une adresse.");
            return;
        }
        if (desc.getText().length() < 10) {
            showAlert("Erreur", "Description trop courte", "La description doit comporter au moins 10 caractères.");
            return;
        }
        ps.Add(new Event(0,Nom.getText(),adresse.getText(),date.getText(),desc.getText() ));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText("succes");
        alert.setContentText("Evenement ajouter avec succes");
        alert.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEventAdmin.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void afficherEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEvent.fxml"));
            Parent root = loader.load();
            afficherEvent controller = loader.getController();
            controller.initData(Nom.getText());
            adresse.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'AjouterEvent.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'AjouterEvent.fxml'.";
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
