package controllers;

import entities.Formation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import service.FormationService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class DetailsController implements Initializable {
    @FXML
    private HBox boxes;
   public FormationService ps =new FormationService();
    @Override
    public void initialize(URL url, ResourceBundle resources) {

        //SportService sportService=new SportService();
        List<Formation> formations = ps.getAll();
        try {
            for (int i=0;i<formations.size();i++)
            {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation((getClass().getResource("/card.fxml")));
                AnchorPane cardBox= loader.load();
                CardController cardController=loader.getController();
                cardController.setData(formations.get(i));
                boxes.getChildren().add(cardBox);


            }
        } catch (IOException e) {
            System.out.println("error");
        }



    }
}
