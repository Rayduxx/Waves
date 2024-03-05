package tn.esprit.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceEvent;
import tn.esprit.services.ServiceReservation;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Callback;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.IOException;
import java.util.List;

import java.util.List;

public class afficherReservation {
    @FXML
    private TableColumn<Reservation, String> date;
    @FXML
    private TableView<Reservation> eventTable;
    @FXML
    private TableColumn<Reservation, String> statut;
    private final ServiceReservation serviceReservation = new ServiceReservation();
    @FXML
    void initialize() {
        date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate_reservation()));
        statut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatut()));
        afficherReservation();
    }
    public void initData(String statut) {
    }
    @FXML
    void afficherReservation() {
        List<Reservation> reservations = serviceReservation.getAll();
        eventTable.getItems().addAll(reservations);
    }

    @FXML
    void ajouterReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterReservation.fxml"));
            Parent root = loader.load();
            eventTable.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void retour(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEventUser.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Waves - Evenements");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

