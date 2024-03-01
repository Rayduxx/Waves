package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();

    int uid,unumtel;
    String unom, uprenom, uemail, umdp, urole;

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

        uprenom = user.getPrenom();
        uid = user.getId();
        unom = user.getNom();
        uemail = user.getEmail();
        umdp = user.getPassword();
        urole = user.getRole();
        unumtel = user.getNumtel();
    }

    public void suppuser(ActionEvent actionEvent) {
        UserS.DeleteByID(uid);
    }

    public void modifuser(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminUser.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            AdminUserController AUC = loader.getController();
            AUC.idtf.setText(String.valueOf(uid));
            AUC.numteltf.setText(String.valueOf(unumtel));
            AUC.nomtf.setText(unom);
            AUC.prenomtf.setText(uprenom);
            AUC.emailtf.setText(uemail);
            AUC.mdptf.setText(umdp);
            AUC.rolecb.setValue(urole);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
