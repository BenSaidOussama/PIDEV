/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLSupprimerController implements Initializable {

    @FXML
    private TableView<Hotel> table;
    @FXML
    private TableColumn<Hotel, Integer> nbre_chambre;
    @FXML
    private TableColumn<Hotel, String> nameHotel;
    @FXML
    private TableColumn<Hotel, Integer> typeHotel;
    @FXML
    private TableColumn<Hotel, Integer> nbre_de_chambre_despo;
    @FXML
    private ComboBox<String> ville;
    @FXML
    private Button btn;
    
    ObservableList<Hotel> obsl = FXCollections.observableArrayList();

    @FXML
    private void afficher_click(ActionEvent e) {
        CRUDHotel ps = new CRUDHotel();
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {

            hotels = ps.listHotel((String) ville.getValue());

            ObservableList<Hotel> obsl = FXCollections.observableArrayList(hotels);

            table.setItems(obsl);

            nbre_de_chambre_despo.setCellValueFactory(new PropertyValueFactory<>("nbre_de_chambre_despo"));
            nbre_chambre.setCellValueFactory(new PropertyValueFactory<>("nbreChambre"));

            nameHotel.setCellValueFactory(new PropertyValueFactory<>("nameHotel"));
            typeHotel.setCellValueFactory(new PropertyValueFactory<>("typeHotel"));

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Affichage");
            alert.setHeaderText(null);
            alert.setContentText("No data found!!!");
            alert.show();
        }
    }

    @FXML
    private void selection(MouseEvent e) {
        btn.setDisable(false);
    }

    @FXML
    public void supprimer_click(ActionEvent event) {
        CRUDHotel crd = new CRUDHotel();
        crd.supprimerHotel(table.getSelectionModel().getSelectedItems().get(0));
        
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
         
        //table.refresh();
        
 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ville.getItems().addAll("Nabeul", "Tunis", "Bizerte", "Hammamet");
    }

}
