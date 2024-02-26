package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminUserController  implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField mdptf;
    @FXML
    private TextField numteltf;
    @FXML
    private ComboBox rolecb;

    ObservableList<Utilisateur> platList = FXCollections.observableArrayList();
    ObservableList<String> RoleList = FXCollections.observableArrayList("User", "Admin");
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rolecb.setValue("User");
        rolecb.setItems(RoleList);
    }

    @FXML
    public void AjouterUser(javafx.event.ActionEvent actionEvent) {
        String NOM = nomtf.getText();
        String PRENOM = prenomtf.getText();
        String EMAIL = emailtf.getText();
        String MDP = mdptf.getText();
        int NUMTEL = Integer.parseInt(numteltf.getText());
        String ROLE = (String) rolecb.getValue();
        UserS.Add(new Utilisateur(0, NOM, PRENOM, EMAIL, MDP, NUMTEL, ROLE));
    }
}
