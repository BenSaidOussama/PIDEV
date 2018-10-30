/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import esprit.entities.Chambre;
import esprit.entities.Hotel;
import esprit.services.CRUDChambre;
import esprit.services.CRUDHotel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLAjoutchController implements Initializable {

    @FXML
    private TextField adresse;
    @FXML
    private TextField name;
    @FXML
    private ComboBox<Integer> num;
    @FXML
    private Button btn;

    @FXML
    private RadioButton indiv;
    @FXML
    private RadioButton doub;
    @FXML
    private Label label1;
    @FXML
    private AnchorPane page;
    @FXML
    private ToggleGroup type;

    public void setadresse(String adresse) {
        this.adresse.setText(adresse);

    }

    public void setname(String name) {
        this.name.setText(name);

    }

    @FXML
    public void ajouter_ch(ActionEvent e) {
        Hotel h = new Hotel();
        CRUDHotel ch = new CRUDHotel();
        CRUDChambre cc = new CRUDChambre();

        h = ch.return_hotel(adresse.getText(), name.getText());
        Chambre chambre = new Chambre();
        chambre.setNumChambre(num.getValue());

        chambre.setTypeChambre("indiv");

        if (label1.getText().equals("double")) {
            chambre.setTypeChambre("double");
        }
        chambre.setDispo(true);
        cc.ajouterChambre(chambre, h);
        System.out.println("ajouté");
    }

    @FXML
    public void radio_selected(ActionEvent e) {
        if (indiv.isSelected()) {
            label1.setText("indiv");
        }
        if (doub.isSelected()) {
            label1.setText("double");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 100; i++) {
            num.getItems().add(i);

        }

    }

}
