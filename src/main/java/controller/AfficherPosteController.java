package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;

import java.util.List;

public  class AfficherPosteController implements initialize {

    private final ServicePoste sp = new ServicePoste();
    @FXML
    private TableColumn<Poste,String> Cartiste;

    @FXML
    private TableColumn<Poste,String> Cdescription;

    @FXML
    private TableColumn<Poste,Integer> Cduree;

    @FXML
    private TableColumn<Poste,String> Cgenre;

    @FXML
    private TableColumn<Poste,String> Cimage;

    @FXML
    private TableColumn<Poste,String> Ctitre;

    @FXML
    private TableView<Poste> Ctable;

    @FXML
    private TextField artisteT;

    @FXML
    private TextField descriptionT;

    @FXML
    private TextField dureeT;

    @FXML
    private ChoiceBox<String> genreT;
    @FXML
    private TextField imageT;

    @FXML
    private TextField titreT;

    @FXML
    private TextField recherche;

    private final String[] genre = {"Tech House" , "Funky Techno" , "Minimal" ,"Acid Techno" };





    @FXML
     void initialize() {
        List<Poste> poste = sp.getAll();
        ObservableList<Poste> observableList = FXCollections.observableList(poste);
        Ctable.setItems(observableList);

        Ctitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Cartiste.setCellValueFactory(new PropertyValueFactory<>("artiste"));
        Cgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        Cduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        Cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        genreT.getItems().addAll(genre);
        genreT.setOnAction(this::getGenre);
    }

    public void getGenre (ActionEvent event){
        String Genre = genreT.getValue();
        // genreTF.setText(Genre);
    }



    @FXML
    public void selection(javafx.scene.input.MouseEvent mouseEvent) {
        Poste selectedPoste = Ctable.getSelectionModel().getSelectedItem();
        if (selectedPoste != null) {

            titreT.setText(selectedPoste.getTitre());
            artisteT.setText(selectedPoste.getArtiste());
            genreT.setValue(selectedPoste.getGenre());
            imageT.setText(selectedPoste.getImage());
            dureeT.setText(String.valueOf(selectedPoste.getDuree()));
            descriptionT.setText(selectedPoste.getDescription());
    }
    }
    @FXML
    void supprimerLigne(Poste poste) {
        Ctable.getItems().remove(poste);
        sp.delete(poste);
    }

    public void supprimer(ActionEvent actionEvent) {
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

    public void modifier(ActionEvent actionEvent) {
        // Vérifier si un élément est sélectionné dans la TableView
        Poste posteSelectionne = Ctable.getSelectionModel().getSelectedItem();
        if (posteSelectionne != null) {
            // Récupérer les valeurs des champs de texte
            String titre = titreT.getText();
            String artiste = artisteT.getText();
            String genre = genreT.getValue();
            String image = imageT.getText();
            String duree = dureeT.getText();
            String description = descriptionT.getText();



            // Mettre à jour les informations du tournoi sélectionné
            posteSelectionne.setTitre(titre);
            posteSelectionne.setArtiste(artiste);
            posteSelectionne.setGenre(genre);
            posteSelectionne.setImage(image);
            posteSelectionne.setDuree(Integer.parseInt(duree));
            posteSelectionne.setDescription(description);



            // Mettre à jour la TableView avec les modifications
            Ctable.refresh();

            // Vous pouvez également appeler votre méthode de mise à jour dans le service ici
            ServicePoste sp = new ServicePoste();
            sp.update(posteSelectionne);

            // Réinitialiser les champs de texte après la modification

            titreT.clear();
            artisteT.clear();
            genreT.getItems().clear();
            imageT.clear();
            dureeT.clear();
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
    @FXML
    public void trier (ActionEvent actionEvent){
        List<Poste> poste = sp.triPosteByArtiste();
        ObservableList<Poste> observableList = FXCollections.observableList(poste);
        Ctable.setItems(observableList);

        Ctitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Cartiste.setCellValueFactory(new PropertyValueFactory<>("artiste"));
        Cgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        Cduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        Cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }


    public void recherche(ActionEvent actionEvent) {
        String nomRecherche = recherche.getText(); // Obtenir le nom à rechercher depuis le champ de texte
        List<Poste> resultats = sp.rechercheParArtiste(String.valueOf(nomRecherche)); // Appel de la méthode rechercheParNom
        ObservableList<Poste> observableResultats = FXCollections.observableList(resultats);
        Ctable.setItems(observableResultats);

    }
}
