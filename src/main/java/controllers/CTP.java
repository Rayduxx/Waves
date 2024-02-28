package controllers;

import tn.esprit.models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

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
    }

    public TilePane getCustomTilePane() {
        return customTilePane;
    }

}
