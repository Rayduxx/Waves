package tn.esprit.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.models.Utilisateur;
import tn.esprit.services.ServiceUtilisateur;
import tn.esprit.utils.MyDataBase;

public class UserAdmin implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField nomTF;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField mdpTF;
    @FXML
    private TextField numtelTF;
    @FXML
    private ComboBox roleCB;
    @FXML
    private TableView<Utilisateur> tableusers;
    @FXML
    private TableColumn<Utilisateur, Integer> TCid;
    @FXML
    private TableColumn<Utilisateur, String> TCnom;
    @FXML
    private TableColumn<Utilisateur, String> TCprenom;
    @FXML
    private TableColumn<Utilisateur, String> TCemail;
    @FXML
    private TableColumn<Utilisateur, String> TCtel;
    @FXML
    private TableColumn<Utilisateur, String> TCmdp;

    ObservableList<Utilisateur> platList = FXCollections.observableArrayList();
    ObservableList<String> RoleList = FXCollections.observableArrayList("User", "Admin");
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();
    private Connection cnx;
    ResultSet resultSet = null;
    public void ServiceUtilisateur() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleCB.setValue("User");
        roleCB.setItems(RoleList);
        load();
        refreshusers();
    }

    @FXML
    void AjouterUser(ActionEvent event) {
        String NOM = nomTF.getText();
        String PRENOM = nomTF.getText();
        String EMAIL = nomTF.getText();
        String MDP = nomTF.getText();
        int NUMTEL = Integer.parseInt(numtelTF.getText());
        String ROLE = (String) roleCB.getValue();

        UserS.Add(new Utilisateur(0, NOM, PRENOM, EMAIL, MDP, NUMTEL, ROLE));
        load();
        refreshusers();
    }
    @FXML
    private void onclicked(MouseEvent event) {
        try {
            Utilisateur user = tableusers.getSelectionModel().getSelectedItem();
            nomTF.setText(String.valueOf(user.getNom()));
            prenomTF.setText(String.valueOf(user.getPrenom()));
            emailTF.setText(String.valueOf(user.getEmail()));
            mdpTF.setText(String.valueOf(user.getPassword()));
            numtelTF.setText(String.valueOf(user.getNumtel()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void RefreshrUsers(ActionEvent event) {
        load();
        refreshusers();
    }

    private void load() {
        ServiceUtilisateur UserS = new ServiceUtilisateur();
        Connection cnx = MyDataBase.getInstance().getCnx();
        TCid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TCnom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        TCprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        TCemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        TCtel.setCellValueFactory(new PropertyValueFactory<>("Numtel"));
        TCmdp.setCellValueFactory(new PropertyValueFactory<>("Password"));
    }

    private void refreshusers() {
        platList.clear();
        ServiceUtilisateur SS = new ServiceUtilisateur();
        platList = SS.afficher();
        tableusers.setItems(platList);
    }

}
