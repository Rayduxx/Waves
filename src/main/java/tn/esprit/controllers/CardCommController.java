package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.models.Commentaire;
import tn.esprit.models.Poste;
import tn.esprit.services.ServiceCommentaire;
import tn.esprit.services.ServicePoste;

import java.io.IOException;
import java.util.Optional;

public class CardCommController {

    @FXML
    private Label com;
    Commentaire commentaire;

    public void setData(Commentaire commentaire)
    {

        this.commentaire=commentaire;
        com.setText(commentaire.getComment());
    }


    public void upd(ActionEvent actionEvent) {


    }

    public void supp(ActionEvent actionEvent) {
        ServiceCommentaire service = new ServiceCommentaire();

        // Confirmation de la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce Commentaire ?");

        Optional<ButtonType> result = alert.showAndWait(); // Attendre la réponse de l'utilisateur

        // Si l'utilisateur a confirmé la suppression
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer le sport
            service.delete(commentaire);

            // Charger la nouvelle vue
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPersonne.fxml"));
                Parent tableViewParent = loader.load();
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
