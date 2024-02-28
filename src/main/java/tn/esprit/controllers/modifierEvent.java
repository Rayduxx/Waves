package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.IOException;

public class modifierEvent {

    @FXML
    private TextField adresse;

    @FXML
    private TextField date;

    @FXML
    private TextField desc;

    @FXML
    private TextField nom;

    private String NOM;
    private String Date;
    private String ADR;
    private String DESC;

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
        nom.setText(NOM);
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date=Date;
        date.setText(Date);

    }

    public String getADR() {
        return ADR;
    }

    public void setADR(String ADR) {
        this.ADR = ADR;
        adresse.setText(ADR);
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
        desc.setText(DESC);
    }

    private final ServiceEvent ps = new ServiceEvent();


    @FXML
    void modifierEvent(ActionEvent event) {
    ps.Update(new Event(0,nom.getText(),adresse.getText(),date.getText(),desc.getText()));
    }

    void initialize() {
        nom.setText(NOM);
        date.setText(Date);
        adresse.setText(ADR);
        desc.setText(DESC);
    }

    @FXML
    void afficherEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEvent.fxml"));
            Parent root = loader.load();
            afficherEvent controller = loader.getController();
            controller.initData(date.getText());
            adresse.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
