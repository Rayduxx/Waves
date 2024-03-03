 package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;



public class adminPosteController {
    @FXML
    private TableColumn<Poste, String> Cartiste;

    @FXML
    private TableColumn<Poste, String> Cdescription;

    @FXML
    private TableColumn<Poste, String> Cgenre;

    @FXML
    private TableColumn<Poste, String> Cimage;

    @FXML
    private Button Cmodif;

    @FXML
    private TableColumn<Poste, String> Cmorceau;

    @FXML
    private Button Crecherche;

    @FXML
    private Button Csupp;

    @FXML
    private TableView<Poste> Ctable;

    @FXML
    private TableColumn<Poste,String> Ctitre;

    @FXML
    private Button nomrech;
    @FXML
    private Button Ctrier;

    @FXML
    private TextField artisteT;

    @FXML
    private TextField descriptionT;

    @FXML
    private ChoiceBox<String> genreT;

    @FXML
    private TextField imageT;

    @FXML
    private TextField morceauT;

    @FXML
    private TextField recherche;

    @FXML
    private TextField titreT;


    private final ServicePoste ps=new ServicePoste();


    @FXML
    void initialize() {
        List<Poste> postes = ps.getAll();
        ObservableList<Poste> observableList = FXCollections.observableList(postes);
        Ctable.setItems(observableList);

        Ctitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        Cartiste.setCellValueFactory(new PropertyValueFactory<>("Artiste"));
        Cgenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        Cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        Cmorceau.setCellValueFactory(new PropertyValueFactory<>("morceau"));
        Cdescription.setCellValueFactory(new PropertyValueFactory<>("Desscription"));

    }








    @FXML
    void supprimerLigne(Poste poste) {
        Ctable.getItems().remove(poste);
        ps.delete(poste);
    }

    public void supprimer(javafx.event.ActionEvent actionEvent) {
        Poste poste = Ctable.getSelectionModel().getSelectedItem();

        if (poste != null) {
            supprimerLigne(poste);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
            alert.showAndWait();
        }
    }



    public void recherche(javafx.event.ActionEvent actionEvent) {
       //List<Poste> resultats = ps.rechercheParNom(String.valueOf(nomRecherche)); // Appel de la méthode rechercheParNom
        // ObservableList<Sport> observableResultats = FXCollections.observableList(resultats);
        // tableView.setItems(observableResultats);
    }

    public void trier(javafx.event.ActionEvent actionEvent) {
    }

    public void modifier(javafx.event.ActionEvent actionEvent) {
        // Vérifier si un élément est sélectionné dans la TableView
        Poste posteiSelectionne = Ctable.getSelectionModel().getSelectedItem();
        if (posteiSelectionne != null) {
            // Récupérer les valeurs des champs de texte
            String titre = titreT.getText();
            String artiste = artisteT.getText();
            String genre = genreT.getValue();
            String image = imageT.getText();
            String morceau = morceauT.getText();
            String description = descriptionT.getText();



            // Mettre à jour les informations du tournoi sélectionné
            posteiSelectionne.setTitre(titre);
            posteiSelectionne.setArtiste(artiste);
            posteiSelectionne.setGenre(genre);
            posteiSelectionne.setImage(image);
            posteiSelectionne.setDescription(description);
            posteiSelectionne.setDescription(description);



            // Mettre à jour la TableView avec les modifications
            Ctable.refresh();

            // Vous pouvez également appeler votre méthode de mise à jour dans le service ici
            ServicePoste ss = new ServicePoste();
            ss.update(posteiSelectionne);

            // Réinitialiser les champs de texte après la modification

            titreT.clear();
            artisteT.clear();
            genreT.getSelectionModel().clearSelection();
            imageT.clear();
            morceauT.clear();
            descriptionT.clear();


        } else {
            // Afficher un message d'erreur si aucun élément n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un élément à modifier.");
            alert.showAndWait();
        }
    }

    public void selection(javafx.scene.input.MouseEvent mouseEvent) {
        Poste selectedPoste = Ctable.getSelectionModel().getSelectedItem();
        if (selectedPoste != null) {

            titreT.setText(selectedPoste.getTitre());
            artisteT.setText(selectedPoste.getArtiste());
            genreT.setValue(selectedPoste.getGenre());
            imageT.setText(selectedPoste.getImage());
            morceauT.setText(selectedPoste.getMorceau());
            descriptionT.setText(selectedPoste.getDescription());
    }
}}


