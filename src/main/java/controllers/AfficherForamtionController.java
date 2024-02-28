package controllers;

import entities.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.FormationService;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class AfficherForamtionController {
    private final FormationService fs=new FormationService();
    @FXML
    private TextField Taffiche;

    @FXML
    private TextField Tdescription;

    @FXML
    private TextField Ttitre;

    @FXML
    private TextField Tvideo;

    @FXML
    private TableColumn<Formation, String > raffiche;

    @FXML
    private TableColumn<Formation, String> rdescription;

    @FXML
    private TableColumn<Formation, String> rtitre;

    @FXML
    private TableColumn<Formation, String> rvideo;

    @FXML
    private TableView<Formation> tableView;

    @FXML
    private TextField recherche;

    @FXML
    void initialize() {
        List<Formation> formations = fs.getAll();

        ObservableList<Formation> observableList = FXCollections.observableList(formations);
        tableView.setItems(observableList);
        System.out.println(observableList);
        rtitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        rdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        raffiche.setCellValueFactory(new PropertyValueFactory<>("affiche"));
        rvideo.setCellValueFactory(new PropertyValueFactory<>("video"));
    }
    @FXML
    void selection(MouseEvent event) {
        Formation selectedFormation;
        selectedFormation = tableView.getSelectionModel().getSelectedItem();
        if (selectedFormation != null) {

            Ttitre.setText(selectedFormation.getTitre());
            Tdescription.setText(selectedFormation.getDescription());
            Taffiche.setText(selectedFormation.getAffiche());
            Tvideo.setText(selectedFormation.getVideo());
        }
    }
    @FXML
     void supprimerLigne(Formation formation) {
        tableView.getItems().remove(formation);
        fs.delete(formation);


    }


    public void supprimer(javafx.event.ActionEvent actionEvent) {
        Formation formation = tableView.getSelectionModel().getSelectedItem();

        if (formation != null) {
            supprimerLigne(formation);
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
        Formation formationSelectionne = tableView.getSelectionModel().getSelectedItem();
        if (formationSelectionne != null) {
            // Récupérer les valeurs des champs de texte
            String titre = Ttitre.getText();
            String description = Tdescription.getText();
            String affiche = Taffiche.getText();
            String video = Tvideo.getText();


            // Mettre à jour les informations du tournoi sélectionné

            formationSelectionne.setTitre(titre);
            formationSelectionne.setDescription(description);
            formationSelectionne.setAffiche(affiche);
            formationSelectionne.setVideo(video);


            // Mettre à jour la TableView avec les modifications
            tableView.refresh();

            // Vous pouvez également appeler votre méthode de mise à jour dans le service ici
            FormationService ss = new FormationService();
            ss.update(formationSelectionne);

            // Réinitialiser les champs de texte après la modification

            Ttitre.clear();
           Tdescription.clear();
           Taffiche.clear();
            Tvideo.clear();


        } else {
            // Afficher un message d'erreur si aucun élément n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un élément à modifier.");
            alert.showAndWait();
        }
    }

    public void trier(ActionEvent actionEvent) {
        List<Formation> formations = fs.triFormationtBytitre();
        ObservableList<Formation> observableList = FXCollections.observableList(formations);
        tableView.setItems(observableList);

        rtitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        rdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        raffiche.setCellValueFactory(new PropertyValueFactory<>("affiche"));
        rvideo.setCellValueFactory(new PropertyValueFactory<>("video"));


    }
    @FXML
    public void recherche(ActionEvent actionEvent) {
        String nomRecherche = recherche.getText(); // Obtenir le nom à rechercher depuis le champ de texte
        List<Formation> resultats = fs.recherchePartitre(String.valueOf(nomRecherche)); // Appel de la méthode rechercheParNom
        ObservableList<Formation> observableResultats = FXCollections.observableList(resultats);
        tableView.setItems(observableResultats);
    }
}

