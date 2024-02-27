package tn.esprit.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.esprit.models.Personne;
import tn.esprit.services.ServicePersonne;

public class AjouterPersonnesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ageTF;

    @FXML
    private TextField nomtf;

    @FXML
    private TextField prenomTF;
    private final ServicePersonne ps = new ServicePersonne();

    @FXML
    void AjouterPersonne(ActionEvent event) {
        ps.add(new Personne(0,nomtf.getText(),prenomTF.getText(),Integer.parseInt(ageTF.getText())));

    }
    @FXML
    void afficherPersonne(ActionEvent event) {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/AfficherPersonne.fxml"));
        try {
            Parent root=loader.load();
            AfficherPersonneController dc= loader.getController();
            dc.setLbname(nomtf.getText());
            ageTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {
        assert ageTF != null : "fx:id=\"ageTF\" was not injected: check your FXML file 'AjouterPersonnes.fxml'.";

        assert prenomTF != null : "fx:id=\"prenomTF\" was not injected: check your FXML file 'AjouterPersonnes.fxml'.";

    }

}
