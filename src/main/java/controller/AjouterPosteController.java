package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterPosteController implements Initializable
{

    private final ServicePoste sp = new ServicePoste();

    @FXML
    private TextField acteurTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField dureeTF;

    @FXML
    private ChoiceBox <String> genreTF;

    private final String[] genre = {"Tech House" , "Funky Techno" , "Minimal" ,"Acid Techno" };

    @FXML
    private TextField imageTF;

    @FXML
    private TextField titreTF;

    @FXML
    void ajouter(ActionEvent event)  {
        try {
            Poste p = new Poste( titreTF.getText(), acteurTF.getText(), genreTF.getValue(), imageTF.getText(), Integer.parseInt(dureeTF.getText()), descriptionTF.getText());
            sp.add(p);
            System.out.println(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("poste ajouté");
            alert.showAndWait();
        } catch ( SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("failed");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


        }

    @FXML
    void Afficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPoste.fxml"));
            titreTF.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }



    public void getGenre (ActionEvent event){
        String Genre = genreTF.getValue();
       // genreTF.setText(Genre);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreTF.getItems().addAll(genre);
        genreTF.setOnAction(this::getGenre);
    }
}


