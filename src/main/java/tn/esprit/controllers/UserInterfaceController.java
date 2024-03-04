package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInterfaceController implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private GridPane userContainer;
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }
    public void load() {
        int column = 0;
        int row = 1;
        try {
            for (Utilisateur user : UserS.afficher()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardUser1.fxml"));
                Pane userPane = fxmlLoader.load();
                CardUser1Controller cardC = fxmlLoader.getController();
                cardC.setData1(user);
                if (column == 5) {
                    column = 0;
                    ++row;
                }
                userContainer.add(userPane, column++, row);
                GridPane.setMargin(userPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
