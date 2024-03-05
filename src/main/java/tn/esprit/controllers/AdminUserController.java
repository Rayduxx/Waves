package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;
import tn.esprit.utils.MyDataBase;
import tn.esprit.utils.SessionManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

public class AdminUserController implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    public TextField idtf;
    @FXML
    public TextField nomtf;
    @FXML
    public TextField prenomtf;
    @FXML
    public TextField emailtf;
    @FXML
    public TextField mdptf;
    @FXML
    public TextField numteltf;
    @FXML
    public TextField pdptf;
    @FXML
    public ComboBox rolecb;
    @FXML
    public TabPane usertp;
    @FXML
    public Tab gusertab;
    @FXML
    public Tab listusertab;
    @FXML
    public GridPane userContainer;
    @FXML
    private ImageView imagepdp;
    @FXML
    private Label uinfolabel;
    @FXML
    private TextField usersearch;

    ObservableList<String> RoleList = FXCollections.observableArrayList("User", "Admin");
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();
    private Connection cnx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        rolecb.setValue("User");
        rolecb.setItems(RoleList);
    }

    private boolean emailExists(String email) throws SQLException {
        cnx = MyDataBase.getInstance().getCnx();
        String query = "SELECT * FROM `user` WHERE email=?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @FXML
    public void AjouterUser(ActionEvent actionEvent) throws SQLException {
        String NOM = nomtf.getText();
        String PRENOM = prenomtf.getText();
        String EMAIL = emailtf.getText();
        String MDP = mdptf.getText();
        int NUMTEL = Integer.parseInt(numteltf.getText());
        String ROLE = (String) rolecb.getValue();
        String IMAGE = pdptf.getText();
        if (EMAIL.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(esprit\\.tn|gmail\\.com)$")) {
            if (numteltf.getText().matches("\\d{8}")) {
                if (!emailExists(EMAIL)) {
                    UserS.Add(new Utilisateur(0, NOM, PRENOM, EMAIL, MDP, NUMTEL, ROLE, IMAGE));
                    uinfolabel.setText("Ajout Effectue");
                } else {
                    uinfolabel.setText("Email déjà existe");
                }
            } else {
                uinfolabel.setText("N° Telephone est invalide");
            }
        } else {
            uinfolabel.setText("Email est invalide");
        }
    }

    @FXML
    public void ModifierUser(ActionEvent actionEvent) {
        int ID = Integer.parseInt(idtf.getText());
        String NOM = nomtf.getText();
        String PRENOM = prenomtf.getText();
        String EMAIL = emailtf.getText();
        String MDP = mdptf.getText();
        int NUMTEL = Integer.parseInt(numteltf.getText());
        String ROLE = (String) rolecb.getValue();
        String IMAGE = pdptf.getText();
        if (EMAIL.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(esprit\\.tn|gmail\\.com)$")) {
            if (numteltf.getText().matches("\\d{8}")) {
                UserS.Update(new Utilisateur(ID, NOM, PRENOM, EMAIL, MDP, NUMTEL, ROLE, IMAGE));
                uinfolabel.setText("Modification Effectue");
            } else {
                uinfolabel.setText("N° Telephone est invalide");
            }
        } else {
            uinfolabel.setText("Email est invalide");
        }
    }

    @FXML
    public void pdpup(ActionEvent actionEvent) {
        String imagePath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Stage stage = (Stage) nomtf.getScene().getWindow();
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
                pdptf.setText(imagePath);
                if (imagePath != null) {
                    try {
                        File file = new File(imagePath);
                        FileInputStream inputStream = new FileInputStream(file);
                        Image image = new Image(inputStream);
                        imagepdp.setImage(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {
        int column = 0;
        int row = 1;
        try {
            for (Utilisateur user : UserS.afficher()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardUser.fxml"));
                Pane userBox = fxmlLoader.load();
                CardUserController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                userContainer.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void TriNom(ActionEvent actionEvent){
        int column = 0;
        int row = 1;
        try {
            for (Utilisateur user : UserS.TriparNom()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardUser.fxml"));
                Pane userBox = fxmlLoader.load();
                CardUserController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                userContainer.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refresh(ActionEvent actionEvent){
        load();
    }

    @FXML
    public void TriEmail(ActionEvent actionEvent){
        int column = 0;
        int row = 1;
        try {
            for (Utilisateur user : UserS.TriparEmail()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardUser.fxml"));
                Pane userBox = fxmlLoader.load();
                CardUserController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                userContainer.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void RechercheNom(ActionEvent actionEvent) {
        int column = 0;
        int row = 1;
        String recherche = usersearch.getText();
        try {
            userContainer.getChildren().clear();
            for (Utilisateur user : UserS.Rechreche(recherche)){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardUser.fxml"));
                Pane userBox = fxmlLoader.load();
                CardUserController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                userContainer.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Menu1(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Waves - Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Deconnection(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            SessionManager.cleanUserSession();
            stage.setTitle("Waves - Connection");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void toContAdmin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminPoste.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            SessionManager.cleanUserSession();
            stage.setTitle("Waves - Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void toGesEvent(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEventAdmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            SessionManager.cleanUserSession();
            stage.setTitle("Waves - Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
