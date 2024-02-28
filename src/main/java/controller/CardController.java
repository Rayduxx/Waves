package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;

import java.io.IOException;
import java.util.Optional;

public class CardController {

private Poste poste=new Poste();
    @FXML
    private Label artisteL;

    @FXML
    private Label descriptionL;

    @FXML
    private Label dureeL;

    @FXML
    private Label genreL;

    @FXML
    private ImageView imageL;

    @FXML
    private Label titreL;

    @FXML
    public Button supprimer;

    @FXML
    private VBox vbox;


    public void setData (Poste poste){
        this.poste=poste;

        titreL.setText(poste.getTitre());
        artisteL.setText(poste.getArtiste());
        genreL.setText(poste.getGenre());
        Image image= new Image (poste.getImage());
        imageL.setImage(image);
        descriptionL.setText(poste.getDescription());

    }

    public void supprimer(ActionEvent actionEvent)throws IOException {
        ServicePoste service = new ServicePoste();


        // Confirmation de la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette poste ?");

        Optional<ButtonType> result = alert.showAndWait(); // Attendre la réponse de l'utilisateur

        // Si l'utilisateur a confirmé la suppression
        if (((Optional<?>) result).isPresent() && result.get() == ButtonType.OK) {
            // Supprimer la poste
            service.delete(poste);

            // Charger la nouvelle vue
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Details.fxml"));
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


    public void edit(ActionEvent actionEvent) throws IOException {
         {
            EditPosteController.poste=poste;
            Parent root = FXMLLoader.load(getClass().getResource("/EditPoste.fxml"));
            Scene tableScene =new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableScene);
            window.show();
        }


    }
}


