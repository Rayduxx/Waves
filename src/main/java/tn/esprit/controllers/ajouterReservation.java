package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceReservation;

import java.io.IOException;

public class ajouterReservation {
    @FXML
    private TextField date;

    @FXML
    private TextField statut;

    private final ServiceReservation ps = new ServiceReservation();


    @FXML
    void ajouterReservation(ActionEvent event) {

        if (date == null || date.getText().isEmpty()) {
            showAlert("Erreur", "Titre manquant", "Veuillez entrer un titre.");
            return;
        }

        if (statut == null || statut.getText().isEmpty()) {
            showAlert("Erreur", "Acteur manquant", "Veuillez entrer un acteur.");
            return;
        }

        ps.Add(new Reservation(0,0,0,date.getText(),statut.getText() ));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText("succes");
        alert.setContentText("Evenement ajouter avec succes");
        alert.showAndWait();

    }

    @FXML
    void afficherReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReservation.fxml"));
            Parent root = loader.load();
            afficherReservation controller = loader.getController();
            controller.initData(date.getText());
            statut.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'AjouterReservation.fxml'.";

        assert statut != null : "fx:id=\"statut\" was not injected: check your FXML file 'AjouterReservation.fxml'.";

    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
