/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import esprit.entities.Hotel;
import esprit.services.CRUDHotel;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLModifierHotelController implements Initializable {

    @FXML
    private JFXComboBox<String> ville;
    @FXML
    private TableView<Hotel> table;
    @FXML
    private TableColumn<Hotel, String> nom;
    @FXML
    private TableColumn<Hotel, Integer> nb_etoile;
    @FXML
    private TableColumn<Hotel, Integer> p_indiv;
    @FXML
    private TableColumn<Hotel, Integer> p_double;
    @FXML
    private Button btn;
    @FXML
    private JFXTextField t_nom;
    @FXML
    private JFXComboBox<?> t_nb;
    @FXML
    private JFXTextField t_p_indiv;
    @FXML
    private JFXTextField t_p_double;
    @FXML
    private Label label1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ville.getItems().addAll("Nabeul", "Tunis", "Bizerte", "Hammamet");

    }    

    @FXML
    private void afficher_data(ActionEvent event) {
         CRUDHotel ps = new CRUDHotel();
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {

            hotels = ps.listHotel((String) ville.getValue());

            ObservableList<Hotel> obsl = FXCollections.observableArrayList(hotels);

            table.setItems(obsl);

            nom.setCellValueFactory(new PropertyValueFactory<>("nameHotel"));
          nb_etoile.setCellValueFactory(new PropertyValueFactory<>("typeHotel"));
             p_indiv.setCellValueFactory(new PropertyValueFactory<>("prix_indiv"));
             p_double.setCellValueFactory(new PropertyValueFactory<>("prix_double"));
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Affichage");
            alert.setHeaderText(null);
            alert.setContentText("il n'y a pas des hotels dans cette ville !!!");
            alert.show();
        }
    }

    @FXML
    private void transfere_data(MouseEvent event) {
       // label1.setText("erreur !");
           CRUDHotel ch= new CRUDHotel();
        
        Hotel h=table.getSelectionModel().getSelectedItems().get(0);
       t_nom.setText(h.getNameHotel());
      ArrayList<String> comboItems = new ArrayList<>(); 
       comboItems.add(Integer.toString(h.getTypeHotel()));
       
     //  t_nb.getItems().addAll(comboItems);
       t_p_double.setText(Integer.toString(h.getPrix_double()));
       t_p_indiv.setText(Integer.toString(h.getPrix_indiv()));
        
    }

    @FXML
    private void Enregistrer_modif(ActionEvent event) {
        CRUDHotel ch= new CRUDHotel();
        
        Hotel h=table.getSelectionModel().getSelectedItems().get(0);
        ch.updateHotel(t_nom.getText(), Integer.parseInt(t_p_indiv.getText()), Integer.parseInt(t_p_double.getText()), ch.recupererId(h));
        label1.setText("Modification faite !");
        ArrayList<Hotel> hotels = new ArrayList<>();
        

            hotels = ch.listHotel((String) ville.getValue());

            ObservableList<Hotel> obsl = FXCollections.observableArrayList(hotels);

            table.setItems(obsl);

            nom.setCellValueFactory(new PropertyValueFactory<>("nameHotel"));
          nb_etoile.setCellValueFactory(new PropertyValueFactory<>("typeHotel"));
             p_indiv.setCellValueFactory(new PropertyValueFactory<>("prix_indiv"));
             p_double.setCellValueFactory(new PropertyValueFactory<>("prix_double"));
    }
    
}
