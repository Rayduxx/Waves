package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    public void admin(ActionEvent actionEvent) throws IOException {
        loadScene("/adminPoste.fxml",actionEvent);

    }

    public void user(ActionEvent actionEvent) throws IOException {
        loadScene("/AjouterPersonnes.fxml",actionEvent);


    }




        private void loadScene(String scenePath,ActionEvent actionEvent) throws IOException {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource(scenePath));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
    }

