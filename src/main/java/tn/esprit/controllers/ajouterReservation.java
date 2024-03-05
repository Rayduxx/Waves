package tn.esprit.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceEvent;
import tn.esprit.services.ServiceReservation;
import tn.esprit.utlis.MailController;
import tn.esprit.utils.PdfController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

import static java.lang.Float.parseFloat;

public class ajouterReservation {
    @FXML
    private TextField date;

    @FXML
    private TextField statut;

    @FXML
    private ComboBox<Integer> nb;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private Button exportPDFButton;

    @FXML
    private Button AJ;

    @FXML
    private Button payer;

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

    private final ServiceEvent rs = new ServiceEvent();

    public void handleExportPDF() {
        // Récupérez les informations de la nouvelle réservation ajoutée
        int eventId = getIdEvent();

        String event = rs.getNomById(eventId);
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String Email = email.getText();
        String dateReservation = date.getText();
        String statutReservation = statut.getText();

        // Exportez ces informations vers un fichier PDF
        PdfController pdfController = new PdfController();
        pdfController.exportToPDF2(Nom, Prenom, Email, event, dateReservation, statutReservation);
    }

    @FXML
    void ajouterReservation(ActionEvent event) throws IOException, MessagingException {

        int eventId = getIdEvent();
        ps.Add(new Reservation(0,0,eventId,date.getText(),statut.getText(),nom.getText(),prenom.getText(),nb.getValue(),email.getText() ));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText("succes");
        alert.setContentText("Reservation ajouter avec succes");
        alert.showAndWait();
        System.out.println(eventId);


        exportPDFButton.setVisible(true);
        payer.setVisible(true);
        AJ.setVisible(false);

        mail.SendMail(email.getText());
    }

    public void processPayment() {

        try {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Paiment");
            confirmationAlert.setHeaderText("Confirmation de paiment");
            confirmationAlert.setContentText("Voulez-vous payer avec cette carte");
            confirmationAlert.showAndWait();

// Set your secret key here
            Stripe.apiKey = "sk_test_51Or0FWIG2J3RtgQuCc7dZ4rAAiapfB42HPWC43wraAo8UKiE52xyjb3DtTFwZ7UJcYBGjKiKtRjw3Hl9LUmTyvvh00aeSs2Lw0";

// Create a PaymentIntent with other payment details
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) parseFloat("100000")) // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);






// If the payment was successful, display a success message
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
// If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
        }
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
    void AfficherEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherEventUser.fxml"));
            Parent root = loader.load();
            DetailsUser controller = loader.getController();
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



        // Créer une liste observable pour contenir les nombres de 1 à 10
        ObservableList<Integer> numeros = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++) {
            numeros.add(i);
        }

        // Définir les éléments du ComboBox en utilisant la liste observable
        nb.setItems(numeros);
    }

}





