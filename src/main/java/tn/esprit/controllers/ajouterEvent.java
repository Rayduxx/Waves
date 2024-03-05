package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ajouterEvent {

    @FXML
    private TextField Nom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField date;

    @FXML
    private TextField desc;

    private String cheminImage ;

    @FXML
    public void importerImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo de profil");

        // Filtrer uniquement les fichiers d'images
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
        );

        // Afficher la boîte de dialogue pour sélectionner un fichier
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        // Vérifier si un fichier a été sélectionné
        if (selectedFile != null) {
            cheminImage = selectedFile.getAbsolutePath(); // Récupérer le chemin de l'image sélectionnée


        }
    }



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

        // Convertir la durée en un entier


        if (desc.getText().length() < 10) {
            showAlert("Erreur", "Description trop courte", "La description doit comporter au moins 10 caractères.");
            return;
        }
        ps.Add(new Event(0,Nom.getText(),adresse.getText(),date.getText(),desc.getText(),cheminImage ));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText("succes");
        alert.setContentText("Evenement ajouter avec succes");
        alert.showAndWait();

        // Redirection vers la page d'affichage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEventAdmin.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception si le chargement de la page échoue
        }


    }





    @FXML
    void afficherEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEventAdmin.fxml"));
            Parent root = loader.load();
            DetailsAdmin controller = loader.getController();
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
