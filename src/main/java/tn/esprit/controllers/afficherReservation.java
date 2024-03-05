package tn.esprit.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import tn.esprit.utils.PdfController;

import java.io.IOException;
import java.util.List;

import java.util.List;

public class afficherReservation {

    @FXML
    private TableColumn<Reservation, String> date;

    @FXML
    private TableColumn<Reservation, String> nom;

    @FXML
    private TableColumn<Reservation, String> prenom;

    @FXML
    private TableColumn<Reservation, String> email;

    @FXML
    private TableColumn<Reservation, Integer> event;





    @FXML
    private TableView<Reservation> eventTable;

    @FXML
    private TableColumn<Reservation, String> statut;

    private final ServiceReservation serviceReservation = new ServiceReservation();
    private final ServiceEvent sr = new ServiceEvent();
    @FXML
    void initialize() {
        // Initialise les colonnes de la table


        date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate_reservation()));
        statut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatut()));
        nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        prenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));


        // Charge les données des événements dans la table
        afficherReservation();
    }

    public void initData(String statut) {
        // Utilisez le nom pour configurer la vue, par exemple :
        // labelNom.setText(nom);
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
            eventTable.getScene().setRoot(root); // Utilisez n'importe quel nœud de votre interface actuelle
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button exportButton;

    @FXML
    void handleExportButton(ActionEvent event) {
        // Récupérez la liste des réservations à exporter (par exemple, depuis une table)
        ObservableList<Reservation> reservations = eventTable.getItems();

        // Appelez la méthode exportToPDF pour exporter les réservations vers un fichier PDF
        PdfController pdfController = new PdfController();
        pdfController.exportToPDF(reservations);
    }

}
