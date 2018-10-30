/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class FXMLHomeController implements Initializable {

   @FXML
   private HBox hotel;
   @FXML
private AnchorPane ecran;
   
   @FXML
   public void choisirHotel(MouseEvent event)
   {
       try {
          
            AnchorPane page = FXMLLoader.load(getClass().getResource("/esprit/gui/FXMLAfficherHotel.fxml"));
           
           ecran.getChildren().setAll(page);
                        // System.out.println("erreur");

       } catch (IOException ex) {
           Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
