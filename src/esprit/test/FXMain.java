/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.test;

import esprit.gui.FXMLAjoutHotelController;
import esprit.gui.FXMLAjoutHotelController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import sun.util.logging.PlatformLogger;

/**
 *
 * @author ASUS
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button ajout = new Button();
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/esprit/gui/FXMLModifierHotel.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setTitle("Affichage ! !");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger(FXMLAjoutHotelController.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
