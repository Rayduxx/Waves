package tn.esprit.controllers;

import atlantafx.base.controls.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminUserController implements Initializable {
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
    @FXML
    private GridPane userContainer;

    ObservableList<Utilisateur> platList = FXCollections.observableArrayList();
    ObservableList<String> RoleList = FXCollections.observableArrayList("User", "Admin");
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            for (Utilisateur user : UserS.afficher()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardUser.fxml"));
                HBox userBox = fxmlLoader.load();
                CardUserController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 7) {
                    column = 0;
                    ++row;
                }
                userContainer.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        String IMAGE = "Empty for now";
        UserS.Add(new Utilisateur(0, NOM, PRENOM, EMAIL, MDP, NUMTEL, ROLE, IMAGE));
    }
}
