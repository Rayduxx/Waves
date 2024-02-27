package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.utils.MyDataBase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class InsriptionContoller {
    @FXML
    private TextField nomreg;
    @FXML
    private TextField prenomreg;
    @FXML
    private TextField emailreg;
    @FXML
    private TextField mdpreg;
    @FXML
    private TextField numtelreg;
    @FXML
    private TextField imagereg;
    @FXML
    private Label reginfo;
    @FXML
    private Button imagebtn;

    private final ServiceUtilisateur UserS = new ServiceUtilisateur();
    private Connection cnx;
    public void ServiceUtilisateur() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    public boolean emailExists(String email) throws SQLException {
        boolean exists = false;
        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    exists = count > 0;
                }
            }
        }
        return exists;
    }
    @FXML
    public void inscription(javafx.event.ActionEvent actionEvent) throws SQLException {
        String NOM = nomreg.getText();
        String PRENOM = prenomreg.getText();
        String EMAIL = emailreg.getText();
        String MDP = mdpreg.getText();
        int NUMTEL = Integer.parseInt(numtelreg.getText());
        String IMAGE = imagereg.getText();
        if (NOM.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(esprit\\.tn|gmail\\.com)$")) {
            if (numtelreg.getText().matches("^\\d{8}$")) {
                if (!emailExists(EMAIL)) {
                    UserS.Add(new Utilisateur(0, NOM, PRENOM, EMAIL, MDP, NUMTEL, "User", IMAGE));
                } else {
                    reginfo.setText("Email email déjà");
                }
            } else {
                reginfo.setText("N° Telephone est invalide");
            }
        } else {
            reginfo.setText("Email est invalide");
        }
    }

    @FXML
    private void uploadImage(javafx.event.ActionEvent actionEvent) {
        String imagePath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Stage stage = (Stage) nomreg.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                Path destinationFolder = Paths.get("src/main/resources/assets/uploads");
                if (!Files.exists(destinationFolder)) {
                    Files.createDirectories(destinationFolder);
                }
                String fileName = UUID.randomUUID().toString() + "_" + selectedFile.getName();
                Path destinationPath = destinationFolder.resolve(fileName);
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                imagePath = destinationPath.toString();
                System.out.println("Image uploaded successfully: " + imagePath);
                imagereg.setText(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void cnscene(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Waves - Connection");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
