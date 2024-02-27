package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import tn.esprit.models.Utilisateur;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    @FXML
    private HBox CardBox;

    private String [] colors = {"#FFB5E8", "#FF9CEE", "#FFCCF9", "#FCC2FF", "#F6A6FF", "#B28DFF", "#C5A3FF", "#D5AAFF", "#ECD4FF", "#FBE4FF", "#DCD3FF", "#A79AFF", "#B5B9FF", "#97A2FF",
            "#AFCBFF", "#AFF8DB", "C4FAF8", "#85E3FF", "#ACE7FF", "#6EB5FF", "#BFFCC6", "#DBFFD6", "#F3FFE3", "#E7FFAC", "#FFFFD1", "#FFC9DE", "#FFABAB", "#FFBEBC", "#FFCBC1", "#FFF5BA"};

    public void setData(Utilisateur user) {
        String imagePath = user.getImage();
        if (imagePath != null) {
            try {
                File file = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                cardimage.setImage(image);
            } catch (FileNotFoundException e) {
                System.err.println("Image file not found: " + imagePath);
            }
        } else {
            System.err.println("Image path is null for user: " + user);
        }
        cardnameprename.setText(user.getNom() + " " + user.getPrenom());
        cardemail.setText(user.getEmail());
        cardrole.setText(user.getRole());
        cardnumtel.setText(String.valueOf(user.getNumtel()));
        CardBox.setBackground(Background.fill(Color.web(colors[(int)(Math.random()* colors.length)])));
    }
}
