package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AfficherPersonneController {
    @FXML
    private TextField lbname;

    public TextField getLbname() {
        return lbname;
    }

    public void setLbname(String lbname) {
        this.lbname.setText(lbname);
    }
}
