package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    private Button imagebtn;

    private final ServiceUtilisateur UserS = new ServiceUtilisateur();
    @FXML
    public void inscription(javafx.event.ActionEvent actionEvent) {

        String NOM = nomreg.getText();
        String PRENOM = prenomreg.getText();
        String EMAIL = emailreg.getText();
        String MDP = mdpreg.getText();
        int NUMTEL = Integer.parseInt(numtelreg.getText());
        String IMAGE = imagereg.getText();
        UserS.Add(new Utilisateur(0, NOM, PRENOM, EMAIL, MDP, NUMTEL, "User", IMAGE));
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
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}