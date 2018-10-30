/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import esprit.entities.Client;
import esprit.entities.Hotel;
import esprit.services.CRUDClient;
import esprit.services.CRUDHotel;
import esprit.services.CRUDReservation;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLAfficherHotelController implements Initializable {

    @FXML
    private TableView<Hotel> array;

    @FXML
    private TableColumn<Hotel, String> nameHotel;
    @FXML
    private TableColumn<Hotel, Integer> typeHotel;
    @FXML
    private ComboBox<String> ville;
    @FXML
    private AnchorPane ecran;
    @FXML
    private AnchorPane table;
    @FXML
    private WebView webview;
    @FXML
    private Button reserver;
    @FXML
    private Button annuler;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private AnchorPane reserv;
    @FXML
    private JFXComboBox<Integer> doub;
    @FXML
    private JFXComboBox<Integer> indiv;
    @FXML
    private AnchorPane non_reserv;
    @FXML
    private Button btn_annuler;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private TableColumn<Hotel, Integer> prix_double;
       @FXML
    private TableColumn<Hotel, Integer> prix_indiv;
    @FXML
    private JFXTextField prixtot;

    @FXML
    private void handleAfficher(ActionEvent e) {
        CRUDHotel ps = new CRUDHotel();
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {

            hotels = ps.listHotel((String) ville.getValue());

            ObservableList<Hotel> obsl = FXCollections.observableArrayList(hotels);

            array.setItems(obsl);

            //adresseHotel.setCellValueFactory(new PropertyValueFactory<>("adresseHotel"));
            nameHotel.setCellValueFactory(new PropertyValueFactory<>("nameHotel"));
            typeHotel.setCellValueFactory(new PropertyValueFactory<>("typeHotel"));
             prix_indiv.setCellValueFactory(new PropertyValueFactory<>("prix_indiv"));
              prix_double.setCellValueFactory(new PropertyValueFactory<>("prix_double"));
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Affichage");
            alert.setHeaderText(null);
            alert.setContentText("No data found!!!");
            alert.show();
        }
        // TODO
    }

    public void initialize(URL url, ResourceBundle rb) {
        ville.getItems().addAll("Nabeul", "Tunis", "Bizerte", "Hammamet");
        WebEngine engine = webview.getEngine();
        engine.load("https://www.youtube.com/watch?v=3f9-9QNdNWY");
        //btn.setDisable(true);

        // btn.setDisable(false);
        reserver.setVisible(false);
        annuler.setVisible(false);
        reserv.setVisible(false);
        for (int i = 0; i < 4; i++) {
            indiv.getItems().addAll(i);
        }
        for (int i = 0; i < 4; i++) {
            doub.getItems().addAll(i);
        }
        non_reserv.setVisible(false);
    }

    @FXML
    private void reserver_click(ActionEvent event) {
        reserv.setVisible(true);
         non_reserv.setVisible(false);
        
    }

    @FXML
    private void annuler_click(ActionEvent event) {
        non_reserv.setVisible(true);
        reserv.setVisible(false);

    }

    @FXML
    private void afficher_button(MouseEvent event) {
        reserver.setVisible(true);
        annuler.setVisible(true);

    }

    @FXML
    private void enregistrer_reservation(ActionEvent event) {
        reserv.setVisible(false);
        non_reserv.setVisible(false);
        String n = nom.getText();
        String p = prenom.getText();
        String e = email.getText();
        int i = indiv.getValue();
        int d = doub.getValue();
        CRUDClient cc = new CRUDClient();
        Client c = cc.rechercher_client(n, p, e);
        Hotel h = array.getSelectionModel().getSelectedItems().get(0);
        CRUDReservation cr = new CRUDReservation();
        if(cr.recupererId_reserv(h, c)==-1){
        cr.reserverHotel(h, c, i, d);
label4.setText("Votre réservation est bien enregistrée !");
    }
        else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur lors de l'enregistrement!");
            alert.setHeaderText(null);
            alert.setContentText("Désolé Vous avez déja une réservation !!");
            alert.show();
        }}

    @FXML
    private void supp_reserv(ActionEvent event) {

        String n = nom.getText();
        String p = prenom.getText();
        String e = email.getText();

        CRUDClient cc = new CRUDClient();
        Client c = cc.rechercher_client(n, p, e);
        Hotel h = array.getSelectionModel().getSelectedItems().get(0);
        CRUDReservation cr = new CRUDReservation();
        if (cr.recupererId_reserv(h, c) != -1) {
            cr.annulerReservation(h, c);
            label3.setText("Réservation annulée !");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur lors de l'annulation de réservation !");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas de réservation !!");
            alert.show();

        }
    }

    @FXML
    private void afficher_prix(ActionEvent event) {
         Hotel h = array.getSelectionModel().getSelectedItems().get(0);
        prixtot.setText(Integer.toString(h.getPrix_indiv()*indiv.getValue()+h.getPrix_double()*doub.getValue()));
        
    }
}
