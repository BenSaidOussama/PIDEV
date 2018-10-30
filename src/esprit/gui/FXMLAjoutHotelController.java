/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import esprit.entities.Hotel;
import esprit.services.CRUDChambre;
import esprit.services.CRUDHotel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//import sun.util.logging.PlatformLogger;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLAjoutHotelController implements Initializable {

    @FXML
    private Button ajout;
    @FXML
    private Button ajoutChambre;
    @FXML
    private JFXTextField name;
    @FXML
    private ComboBox<String> adresse;
    @FXML
    private ComboBox<Integer> nbre_etoile;
    
    @FXML
    private ImageView btnclose;
    @FXML
    private ImageView btnminimize;
    @FXML
    private AnchorPane ecran;
    @FXML
    private AnchorPane nb_ch_tot;
    @FXML
    private JFXButton imgl;
    @FXML
    private JFXButton img2;
    @FXML
    private JFXButton img3;
    @FXML
    private JFXButton img4;
    @FXML
    private JFXButton img5;
    @FXML
    private ImageView vimg1;
    @FXML
    private ImageView vimg2;
    @FXML
    private ImageView vimg3;
    @FXML
    private ImageView vimg4;
    @FXML
    private ImageView vimg5;
    @FXML
    private TextField pindiv;
    @FXML
    private TextField pdoub;

    @FXML
    public void ajouter(ActionEvent event) {
        if (event.getSource() == ajout) {
            CRUDHotel ch = new CRUDHotel();
           // Hotel h = new Hotel(name.getText(), (String) adresse.getValue(), nbre_etoile.getValue(),Integer.parseInt(pindiv.getText()),Integer.parseInt(pdoub.getText()));
            Hotel h1=new Hotel(Integer.parseInt(pdoub.getText()), Integer.parseInt(pindiv.getText()), name.getText(), (String) adresse.getValue(),nbre_etoile.getValue() );
            // System.out.println("erreur");
            ch.ajouterHotel(h1);
            //Parent root = FXMLLoader.load(getClass().getResource("FXMLAjoutHotel.fxml"));
            //name.getScene().setRoot(root);
            /*  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Ajout Mrigil!!!!");
            alert.show();*/
            ajoutChambre.setDisable(false);
        }

    }

    @FXML
    public void closnd(MouseEvent event) {
        System.out.println("close clicked");
        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnminimize(MouseEvent event) {
        System.out.println("minimize clicked");
        minimizeStageOfNode((Node) event.getSource());
    }

    private void minimizeStageOfNode(Node node) {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnclose.setVisible(true);
        
        adresse.getItems().addAll("Nabeul", "Tunis", "Bizerte", "Hammamet");
        nbre_etoile.getItems().addAll(1, 2, 3, 4, 5, 7);

    }

    @FXML
    public void autrePage(ActionEvent event) {

        String nom = name.getText();
        String adresse=this.adresse.getValue();
       // int nbre_etoile=this.nbre_etoile.getValue();
       // int nb_ch=this.nb_ch.getValue();
       // int nb_ch = this.nb_ch.getValue();
       // int nb_etoile=this.nbre_etoile.getValue();

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/gui/FXMLAjoutch.fxml"));
            Parent root = (Parent) loader.load();

            FXMLAjoutchController secController = loader.getController();
            System.out.println(name.getText());
            
            secController.setname(nom);
            secController.setadresse(adresse);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } 
catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getHotel() {
        String h = name.getText();
        return h;
    }

}
