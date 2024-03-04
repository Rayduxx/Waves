package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherPosteController implements Initializable {
    @FXML
    public HBox Boxes;
    @FXML
    private TextField recherche;

   private final ServicePoste sp = new ServicePoste();


    public void initialize(URL location, ResourceBundle resources) {
        /*List<Poste> postes = sp.getAll();
        try {
            for (int i = 0; i < postes.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation((getClass().getResource("/card.fxml")));

                AnchorPane cardBox = loader.load();
                CardController cardController = loader.getController();
                cardController.setData(postes.get(i));

                Boxes.getChildren().add(cardBox);


            }
        } catch (IOException e) {
            System.out.println("error");
        }*/
        List<Poste> postes = sp.getAll();
        setGridPostes(postes);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                Boxes.getChildren().clear();
                List<Poste> filteredPostes = sp.recherchePoste(recherche.getText());
                setGridPostes(filteredPostes);
            }
            else { // Si le champ de recherche est vide
                Boxes.getChildren().clear(); // Effacez le contenu actuel
                List<Poste> postes1 = sp.getAll(); // Récupérez toutes les données initiales
                setGridPostes(postes1); // Affichez à nouveau toutes les données initiales
            }
        });


        }
    public void setGridPostes(List<Poste> postes) {
        try {
            for (Poste poste : postes) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
                AnchorPane cardBox = loader.load();
                CardController cardController = loader.getController();
                cardController.setData(poste);
                Boxes.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void retour(ActionEvent actionEvent) {
        try {
            // Récupérer n'importe quel élément de votre scène pour obtenir la scène actuelle
            Node sn = (Node) actionEvent.getSource();
            Scene currentScene = sn.getScene();

            // Charger la nouvelle vue depuis le fichier FXML "AjouterPersonne.fxml"
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterPoste.fxml"));

            // Remplacer la racine de la scène actuelle par la nouvelle vue
            currentScene.setRoot(root);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la vue AjouterPersonne : " + e.getMessage());
        }
    }
    @FXML
    public void Menu(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Waves - Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ajouterposte(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterPoste.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Waves - Ajouter poste");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





