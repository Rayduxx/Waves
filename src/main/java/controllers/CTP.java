package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import tn.esprit.models.Commande;
import tn.esprit.models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.services.ServiceCommande;

import java.io.IOException;
import java.sql.Timestamp;

public class CTP {

    @FXML
    private Label Tile_Auteur;

    @FXML
    private Label Tile_Description;

    @FXML
    private Label Tile_Prix;

    @FXML
    private Label Tile_Titre;

    @FXML
    private Button CartAdd;

    @FXML
    private TilePane customTilePane;

    public void setTitre(String titre) {
        Tile_Titre.setText(titre);
    }

    public void setDescription(String description) {
        Tile_Description.setText(description);
    }

    public void setAuteur(String auteur) {
        Tile_Auteur.setText(auteur);
    }

    public void setPrix(double prix) {
        Tile_Prix.setText(String.valueOf(prix) + " DT");
    }

    public void setId(int id) {
        CartAdd.setId(String.valueOf(id));
    }

    public void initData(Item item) {
        setTitre(item.getTitre());
        setDescription(item.getDescription());
        setAuteur(item.getAuteur());
        setPrix(item.getPrix());
        setId(item.getItemID());
        customTilePane.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewItem.fxml"));
                Parent root = loader.load();
                ViewItem controller = loader.getController();
                controller.initData(item);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void addToCart(ActionEvent event) {
        Button buttonClicked = (Button) event.getSource();
        int itemId = Integer.parseInt(buttonClicked.getId());

        int userId = 31;

        float totalPrice = 0.0f;
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

        Commande newCommande = new Commande(userId, itemId, totalPrice, currentDate);

        ServiceCommande serviceCommande = new ServiceCommande();
        serviceCommande.AddC(newCommande);

        System.out.println("Article ajouté au panier avec succès !");
    }

    public TilePane getCustomTilePane() {
        return customTilePane;
    }

}
