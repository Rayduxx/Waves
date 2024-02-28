package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.esprit.models.Poste;
import tn.esprit.services.ServicePoste;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    @FXML
    public HBox boxes;
    @FXML
    private TextField recherche;
    @FXML
    private VBox vbox;
    ServicePoste sp = new ServicePoste();


    public void initialize(URL location, ResourceBundle resources) {
        List<Poste> postes = sp.getAll();
        try {
            for (int i = 0; i < postes.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation((getClass().getResource("/card.fxml")));

                AnchorPane cardBox = loader.load();
                CardController cardController = loader.getController();
                cardController.setData(postes.get(i));

                boxes.getChildren().add(cardBox);


            }
        } catch (IOException e) {
            System.out.println("error");
        }


    }}


   // public void recherche(javafx.event.ActionEvent actionEvent) {

        /*String nomRecherche = recherche.getText(); // Obtenir le nom à rechercher depuis le champ de texte
        List<Poste> resultats = sp.rechercheParArtiste(String.valueOf(nomRecherche)); // Appel de la méthode rechercheParNom
        boxes.getChildren().clear();

        // Ajouter les résultats de la recherche sous forme de VBox au HBox
        for (Poste poste : resultats) {
            String image = poste.getImage();
            ImageView imageView = new ImageView(new Image(image));
            imageView.setFitWidth(100); // Définir la largeur de l'image (ajustez selon vos besoins)
            imageView.setPreserveRatio(true);



            // Créer un Label pour afficher les autres attributs du poste
            Label detailsLabel = new Label(" TITRE: " + poste.getTitre());
            Label detailsLabel1 = new Label (" ARTISTE: " + poste.getArtiste());
            Label detailsLabel2 = new Label (" GENRE: " + poste.getGenre());
            Label detailsLabel3 = new Label (" DESCRIPTION: " + poste.getDescription());

            // Créer un VBox pour chaque poste et y ajouter l'ImageView et le Label
            VBox posteBox = new VBox();
            posteBox.setAlignment(Pos.CENTER);
            posteBox.getChildren().addAll(imageView, detailsLabel, detailsLabel1, detailsLabel2, detailsLabel3);

            // Ajouter le VBox au HBox
            boxes.getChildren().add(posteBox);
        }
        }
    }*/
