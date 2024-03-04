package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.models.Event;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceEvent;
import tn.esprit.services.ServiceReservation;
import tn.esprit.utils.MailController;

import javax.mail.MessagingException;
import java.io.IOException;

public class ajouterReservation {
    @FXML
    private TextField date;

    @FXML
    private TextField statut;

    private  int IdUser;
    private  int Eid;
    private  int Id;

    public MailController mail = new MailController() ;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public int getIdEvent() {
        return Eid;
    }

    public void setIdEvent(int Eid) {
        this.Eid = Eid;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }



    private final ServiceReservation ps = new ServiceReservation();




    @FXML
    void ajouterReservation(ActionEvent event) throws MessagingException {

        int eventId = getIdEvent();
        ps.Add(new Reservation(0,0,eventId,date.getText(),statut.getText() ));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText("succes");
        alert.setContentText("Reservation ajouter avec succes");
        alert.showAndWait();
        System.out.println(eventId);
        mail.SendMail("abidmohamedselim@gmail.com");
    }

    @FXML
    void afficherReservation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherReservation.fxml"));
            Parent root = loader.load();
            afficherReservation controller = loader.getController();
            controller.initData(date.getText());
            statut.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        // assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'AjouterReservation.fxml'.";

        // assert statut != null : "fx:id=\"statut\" was not injected: check your FXML file 'AjouterReservation.fxml'.";


        date.setText("aa");
        statut.setText(String.valueOf(getIdEvent()));

    }




}
