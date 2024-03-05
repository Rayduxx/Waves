package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.models.Commande;
import tn.esprit.models.Item;
import tn.esprit.services.ServiceCommande;
import tn.esprit.services.ServiceItem;

import java.io.IOException;
import java.util.List;

public class AfficherCommande {

    @FXML
    private ListView<Button> DeleteCell;

    @FXML
    private ListView<String> ItemCell;

    @FXML
    private ListView<Float> PriceCell;

    @FXML
    private Text PriceTotal;

    @FXML
    private Button Retour;

    ObservableList<String> itemNames = FXCollections.observableArrayList();
    ObservableList<Float> itemPrices = FXCollections.observableArrayList();
    private List<Commande> userCart;


    public void initCart(List<Commande> userCart) {

        this.userCart = userCart;

        ItemCell.getItems().clear();
        PriceCell.getItems().clear();
        DeleteCell.getItems().clear();

        ObservableList<Button> deleteButtons = FXCollections.observableArrayList();

        float total = 0.0f;

        ServiceItem serviceItem = new ServiceItem();
        for (Commande commande : userCart) {
            Item item = serviceItem.getItemById(commande.getIdItem());
            if (item != null) {
                itemNames.add(item.getTitre());
                itemPrices.add(item.getPrix());
                Button deleteButton = new Button("-");
                deleteButtons.add(deleteButton);
                total += item.getPrix();
            }
        }
        ItemCell.setItems(itemNames);
        PriceCell.setItems(itemPrices);
        DeleteCell.setItems(deleteButtons);
        PriceTotal.setText(String.valueOf(total) + " DT");

        for (int i = 0; i < deleteButtons.size(); i++) {
            int finalI = i; // Pour utilisation dans la lambda expression
            deleteButtons.get(i).setOnAction(event -> deleteItemFromCart(finalI));
        }

    }

    private void deleteItemFromCart(int index) {
        Commande commandeToDelete = userCart.remove(index);
        ServiceItem si = new ServiceItem();
        DeleteCell.getItems().remove(index);
        updateUI(si);
        updateCart(commandeToDelete); // Passer l'objet Commande à supprimer
    }


    private void updateUI(ServiceItem si) {
        // Réinitialisez les listes d'articles et de prix
        itemNames.clear();
        itemPrices.clear();

        // Reconstruisez les listes d'articles et de prix avec les éléments restants de la commande
        float total = 0.0f;
        for (Commande commande : userCart) {
            Item item = si.getItemById(commande.getIdItem());
            if (item != null) {
                itemNames.add(item.getTitre());
                itemPrices.add(item.getPrix());
                total += item.getPrix();
            }
        }

        // Mettez à jour les listes et le total des prix dans l'interface utilisateur
        ItemCell.setItems(itemNames);
        PriceCell.setItems(itemPrices);
        PriceTotal.setText(String.valueOf(total) + " DT");
    }

    @FXML
    void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherItems.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Retour.getScene().getWindow();
            stage.setScene(scene);
            updateCart(null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateCart(Commande commandeToDelete) {
        ServiceCommande serviceCommande = new ServiceCommande();
        serviceCommande.DeleteC(commandeToDelete); // Supprimer la commande correspondante
    }
}
