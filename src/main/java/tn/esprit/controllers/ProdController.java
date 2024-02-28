package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tn.esprit.models.Production;
import tn.esprit.services.ServiceProduction;

public class ProdController {
    @FXML
    private TextArea desctf;
    @FXML
    private TextField genretf;
    @FXML
    private TextField nomtf;
    @FXML
    private RadioButton radbonh;
    @FXML
    private RadioButton radcalm;
    @FXML
    private RadioButton radenergie;
    @FXML
    private RadioButton radsent;
    private final ServiceProduction ProdS = new ServiceProduction();
    @FXML
    public void ajouter(ActionEvent actionEvent) {
        String NOM = nomtf.getText();
        String DESC = desctf.getText();
        String GENRE = genretf.getText();
        String TAG = null;
        if (radbonh.isPickOnBounds()){TAG="Bonheur";}
        if (radcalm.isPickOnBounds()){TAG="Calme";}
        if (radsent.isPickOnBounds()){TAG="Sentimental";}
        if (radenergie.isPickOnBounds()){TAG="Energie";}
        if (NOM.matches("^[_A-Za-z-\\+ ]+$") && GENRE.matches("^[_A-Za-z-\\+ ]+$")){
            ProdS.Add(new Production(0, NOM, GENRE, DESC, TAG));
        }
    }
}
