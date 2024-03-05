package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;
import tn.esprit.utils.SessionManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private TableColumn<Poste, String> Ctitre;

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

    @FXML
    private BarChart<String, Integer> genreChart;

    private final ServicePoste ps = new ServicePoste();

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

        // Afficher les statistiques sur les genres
        afficherStatistiquesGenres(postes);
    }

    private void afficherStatistiquesGenres(List<Poste> postes) {
        Map<String, Integer> genreCounts = new HashMap<>();
        for (Poste poste : postes) {
            String genre = poste.getGenre();
            genreCounts.put(genre, genreCounts.getOrDefault(genre, 0) + 1);
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        genreCounts.forEach((genre, count) -> series.getData().add(new XYChart.Data<>(genre, count)));
        genreChart.getData().add(series);

        // Définir la largeur des barres de statistiques
        for (XYChart.Data<String, Integer> data : series.getData()) {
            data.getNode().setStyle(data.getNode().getStyle() + "-fx-bar-width: 5px;");
            data.getNode().setStyle(data.getNode().getStyle() + "-fx-bar-fill: #B469FF;");
            genreChart.setBarGap(50);
            genreChart.setCategoryGap(10);
            genreChart.setLegendSide(Side.TOP);
        }
    }
    @FXML
    void supprimer(ActionEvent actionEvent) {
        Poste poste = Ctable.getSelectionModel().getSelectedItem();

        if (poste != null) {
            supprimerLigne(poste);
        } else {
            afficherAlerteErreur("Veuillez sélectionner une ligne à supprimer.");
        }
    }

    private void supprimerLigne(Poste poste) {
        Ctable.getItems().remove(poste);
        ps.delete(poste);
    }

    @FXML
    void modifier(ActionEvent actionEvent) {
        Poste posteSelectionne = Ctable.getSelectionModel().getSelectedItem();
        if (posteSelectionne != null) {
            String titre = titreT.getText();
            String artiste = artisteT.getText();
            String genre = genreT.getValue();
            String image = imageT.getText();
            String morceau = morceauT.getText();
            String description = descriptionT.getText();

            posteSelectionne.setTitre(titre);
            posteSelectionne.setArtiste(artiste);
            posteSelectionne.setGenre(genre);
            posteSelectionne.setImage(image);
            posteSelectionne.setDescription(description);

            Ctable.refresh();

            ps.update(posteSelectionne);

            titreT.clear();
            artisteT.clear();
            genreT.getSelectionModel().clearSelection();
            imageT.clear();
            morceauT.clear();
            descriptionT.clear();
        } else {
            afficherAlerteErreur("Veuillez sélectionner un élément à modifier.");
        }
    }

    private void afficherAlerteErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void selection(javafx.scene.input.MouseEvent mouseEvent) {
        Poste selectedPoste = Ctable.getSelectionModel().getSelectedItem();
        if (selectedPoste != null) {
            titreT.setText(selectedPoste.getTitre());
            artisteT.setText(selectedPoste.getArtiste());
            genreT.setValue(selectedPoste.getGenre());
            imageT.setText(selectedPoste.getImage());
            morceauT.setText(selectedPoste.getMorceau());
            descriptionT.setText(selectedPoste.getDescription());
        }
    }

    public void trier(ActionEvent actionEvent) {
    }

    public void recherche(ActionEvent actionEvent) {
    }

    private void loadScene(String scenePath,ActionEvent actionEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource(scenePath));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    public void toContentAdmin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminPoste.fxml"));
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
    public void Deconnection(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            SessionManager.cleanUserSession();
            stage.setTitle("Waves - Connection");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Menu1(ActionEvent actionEvent) {
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
    public void toUserAdmin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminUser.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Waves - Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void toeventadmin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminUser.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Waves - Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void toProdAdmin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProdAdmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            SessionManager.cleanUserSession();
            stage.setTitle("Waves - Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
