package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.models.Utilisateur;

import java.io.InputStream;
import java.util.List;

public class CardUserController {
    @FXML
    private Label cardemail;
    @FXML
    private ImageView cardimage;
    @FXML
    private Label cardnameprename;
    @FXML
    private Label cardnumtel;
    @FXML
    private Label cardrole;

    public void setData(Utilisateur user) {
        String imagePath = user.getImage();
        if (imagePath != null) {
            InputStream inputStream = getClass().getResourceAsStream(imagePath);
            if (inputStream != null) {
                Image image = new Image(inputStream);
                cardimage.setImage(image);
            } else {
                System.err.println("Input stream for image '" + imagePath + "' is null.");
            }
        } else {
            System.err.println("Image path is null for user: " + user);
        }
        cardnameprename.setText(user.getNom() + " " + user.getPrenom());
        cardemail.setText(user.getEmail());
        cardrole.setText(user.getRole());
        cardnumtel.setText(String.valueOf(user.getNumtel()));
    }
}
