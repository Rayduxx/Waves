package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

        ps.Add(new Reservation(0,0,0,date.getText(),statut.getText() ));
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


}
