package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import tn.esprit.models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
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
    private Button addCommande;

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

    public void initData(Item item) {
        setTitre(item.getTitre());
        setDescription(item.getDescription());
        setAuteur(item.getAuteur());
        setPrix(item.getPrix());
        /*customTilePane.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewItem.fxml"));
                Parent root = loader.load();
                ViewItemController controller = loader.getController();
                controller.initData(item);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    @FXML
    void viewItem(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewItem.fxml"));
            Parent root = loader.load();
            ViewItem controller = loader.getController();
            controller.initData(new Item());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TilePane getCustomTilePane() {
        return customTilePane;
    }

}
