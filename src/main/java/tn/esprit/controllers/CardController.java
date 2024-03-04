package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Commentaire;
import tn.esprit.models.Poste;
import tn.esprit.services.ServiceCommentaire;
import tn.esprit.services.ServicePoste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



public class CardController implements Initializable {
    @FXML
    private Label artisteL;

    @FXML
    private Label descriptionL;

    @FXML
    private Label genreL;

    @FXML
    private ImageView imageL;

    @FXML
    private Label titreL;

    @FXML
    private VBox vbox;
    @FXML
    private VBox Box;
    @FXML
    private Button upload;
    @FXML
    private TextField AJ;

    private Poste poste;

   // private final ServicePoste PosteS = new ServicePoste();
  private  ServiceCommentaire sc=new ServiceCommentaire();
   // private String [] colors = {"#FFB5E8", "#FF9CEE", "#FFCCF9", "#FCC2FF", "#F6A6FF", "#B28DFF", "#C5A3FF", "#D5AAFF", "#ECD4FF", "#FBE4FF", "#DCD3FF", "#A79AFF", "#B5B9FF", "#97A2FF",
           // "#AFCBFF", "#AFF8DB", "C4FAF8", "#85E3FF", "#ACE7FF", "#6EB5FF", "#BFFCC6", "#DBFFD6", "#F3FFE3", "#E7FFAC", "#FFFFD1", "#FFC9DE", "#FFABAB", "#FFBEBC", "#FFCBC1", "#FFF5BA"};
    public void setData(Poste poste) {
        this.poste=poste;

       // String imagePath = poste.getImage();
       /* if (imagePath != null) {
            try {
                File file = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                imageL.setImage(image);
            } catch (FileNotFoundException e) {
                System.err.println("Image file not found: " + imagePath);
            }
        } else {
            System.err.println("Image path is null for post: " + poste);
        }*/
        titreL.setText(poste.getTitre());
        artisteL.setText(poste.getArtiste());
        genreL.setText(poste.getGenre());
        descriptionL.setText(poste.getDescription());
        Image image =new Image(poste.getImage());
        imageL.setImage(image);
        List<Commentaire> commentaires = sc.getAllCommentairesByPoste(poste);
        for (Commentaire commentaire : commentaires) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation((getClass().getResource("/cardComm.fxml")));

                AnchorPane commentCard = loader.load();
                CardCommController commentCardController = loader.getController();
                commentCardController.setData(commentaire);

                Box.getChildren().add(commentCard);
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement des cartes de commentaire : " + e.getMessage());
            }
        }








       // vbox.setBackground(Background.fill(Color.web(colors[(int)(Math.random()* colors.length)])));
    }

    public void edit(ActionEvent actionEvent) throws IOException {
        {
            editPosteController.poste=poste;
            Parent root = FXMLLoader.load(getClass().getResource("/editPoste.fxml"));
            Scene tableScene =new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(tableScene);
            window.show();
        }
    }

    public void AjouterCom(ActionEvent actionEvent) {
        String commentaireText = AJ.getText().trim();
        if (!commentaireText.isEmpty()) {
            Commentaire commentaire = new Commentaire();
            commentaire.setComment(commentaireText);
            commentaire.setPoste(poste); // Assurez-vous d'avoir une référence au poste approprié

            // Ajouter le commentaire à la base de données
            ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
            serviceCommentaire.addComm(commentaire,poste);

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

            // Mise à jour de l'affichage des postes
            // Cela peut être un appel à une méthode dans le contrôleur parent pour rafraîchir l'affichage des postes
            // Par exemple, si votre contrôleur parent est un contrôleur de liste de postes, appelez une méthode de ce contrôleur pour rafraîchir la liste des postes
        } else {
            // Affichez une alerte si le champ de commentaire est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ vide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir votre commentaire.");
            alert.showAndWait();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*List<Commentaire> commentaires = sc.getAllCommentairesByPoste(poste);
        try {
            for (int i = 0; i < commentaires.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation((getClass().getResource("/cardComm.fxml")));

                AnchorPane cardBox = loader.load();
                CardCommController cardController = loader.getController();
                cardController.setData(commentaires.get(i),poste);

                Box.getChildren().add(cardBox);


            }
        } catch (IOException e) {
            System.out.println("error");

    }*/


    }
    @FXML
    public void supprime(ActionEvent actionEvent) {
        ServicePoste service = new ServicePoste();

        // Confirmation de la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce Sport ?");

        Optional<ButtonType> result = alert.showAndWait(); // Attendre la réponse de l'utilisateur

        // Si l'utilisateur a confirmé la suppression
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer le sport
            service.delete(poste);

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







   /* private void afficherCommentaires(List<Commentaire> commentaires) {
       // Boxes.getChildren().clear(); // Effacer le contenu précédent (au cas où)
        for (Commentaire commentaire : commentaires) {
            // Créez une nouvelle instance de CardCommentaire
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardComm.fxml"));
            try {
                Parent cardCommentaire = loader.load();
                CardCommController controller = loader.getController();
                // Définissez les données du commentaire dans le CardCommentaire
                controller.setData(commentaire);
                // Ajoutez le CardCommentaire à votre VBox
                Box.getChildren().add(cardCommentaire);
            } catch (IOException e) {
                e.printStackTrace(); // Gérer l'erreur d'ouverture du fichier FXML
            }
        }}*/
}

