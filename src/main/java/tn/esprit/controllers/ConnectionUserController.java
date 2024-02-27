package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

import javafx.scene.*;
import javafx.stage.*;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;
import tn.esprit.utils.MyDataBase;

import javax.naming.spi.StateFactory;
import java.io.IOException;

public class ConnectionUserController implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField email_login;
    @FXML
    private TextField password_login;
    @FXML
    private Button connecter;

    private final ServiceUtilisateur UserS = new ServiceUtilisateur();
    private Connection cnx;
    private ResultSet result;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void connecter(ActionEvent actionEvent) throws IOException {
        String qry = "SELECT * FROM `user` WHERE email=? AND password=?";
        cnx = MyDataBase.getInstance().getCnx();
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, email_login.getText());
            stm.setString(2, password_login.getText());
            ResultSet rs = stm.executeQuery();
            Utilisateur user;

            if (rs.next()) {
                String role = rs.getString("role");
                if (role.equals("Admin")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminUser.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (role.equals("User")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    public void inscription(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
