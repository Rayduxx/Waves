package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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
            showAlert("Erreur", "Titre manquant", "Veuillez entrer un titre.");
            return;
        }

        if (adresse == null || adresse.getText().isEmpty()) {
            showAlert("Erreur", "Acteur manquant", "Veuillez entrer un acteur.");
            return;
        }

        // Convertir la durée en un entier


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

        // Si toutes les validations passent, ajoutez le poste

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


    // Méthode pour afficher une boîte de dialogue d'alerte
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }




}
