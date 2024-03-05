package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceEvent;

import java.io.IOException;
import java.util.List;

public class DetailsUser {
    @FXML
    private GridPane eventGrid;   // Utilisez un GridPane pour organiser les cartes

    private final ServiceEvent serviceEvent = new ServiceEvent();

    // Utilisez un GridPane pour organiser les cartes

    @FXML
    void initialize() {
        // Charger les données des événements depuis le service
        List<Event> events = serviceEvent.getAll();

        int column = 0;
        int row = 0;

        // Pour chaque événement, créer une carte et l'ajouter au GridPane
        for (Event event : events) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardU.fxml"));
                Node cardNode = loader.load();
                CardcontrollerU cardController = loader.getController();
                cardController.initData(event); // Initialise les données de la carte avec l'événement
                eventGrid.add(cardNode, column, row); // Ajoute la carte au GridPane

                // Passer à la colonne suivante et passer à la ligne suivante si nécessaire
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace(); // Gérer les exceptions
            }
        }
    }

    public void initData(String text) {
    }
}
