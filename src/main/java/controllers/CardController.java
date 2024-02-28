package controllers;

import entities.Formation;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;

public class CardController {


    @FXML
    private Label descriptionT;

    @FXML
    private ImageView imageT;

    @FXML
    private Label titreT;

    @FXML
    private Label videoT;
   private Formation formation=new Formation();
    public void setData(Formation formation) {
        this.formation=formation;
        titreT.setText(formation.getTitre());
        descriptionT.setText(formation.getDescription());
        Image image = new Image(formation.getAffiche());
        imageT.setImage(image);
        videoT.setText(formation.getVideo());


    }


}
