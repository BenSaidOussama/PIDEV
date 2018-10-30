package esprit.test;

import esprit.entities.Chambre;
import esprit.entities.Client;
import esprit.entities.Hotel;
import esprit.entities.Reservation_hotel;
import esprit.entities.VerificationToken;
import esprit.services.CRUDChambre;
import esprit.services.CRUDClient;
import esprit.services.CRUDHotel;
import esprit.services.CRUDReservation;
import esprit.utils.ConnectDB;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import javax.mail.MessagingException;

public class Main {

    public static void main(String[] args) throws MessagingException {
        Connection con = ConnectDB.getInstance().getConnection();
        CRUDHotel ht2 = new CRUDHotel();
      
        CRUDChambre ch2 = new CRUDChambre();
         CRUDClient cc = new CRUDClient();
        CRUDReservation cr = new CRUDReservation();
        
       // Hotel h= new Hotel("vienna", "aryena", "superlux", 20, 20);
      //  Hotel h3=new Hotel("touu", "dar", "cha", 10);
       Chambre c=new Chambre(false,"single", 1);
       Hotel h=new Hotel("sssssss", "Tunis", 0);
      // ht2.ajouterHotel(h);
       ch2.ajouterChambre(c, h);
        Client client=new Client("fara", "bsaid", "ouss@esprit", "1111", "homme");
        Reservation_hotel r=new Reservation_hotel();
       // Hotel h=new Hotel(100, 100, "sirine","Bizerte", 2);
       // ht2.ajouterHotel(h);
     //   System.out.println(ht2.listHotel("Bizerte"));
      //  System.out.println(ht2.recupererId(h));
     // ht2.listHotel("metline");
       // System.out.println(ht2.recupererId(h));
       //cc.ajouterClient(client);
        //System.out.println(ht2.listHotel("metline"));
      //cr.reserverHotel(h1, client, 1, 1);
       // System.out.println(cr.existe(client, h1));
     // cr.annulerReservation(h1, client);
      //ch2.supprimer_tout(h);
    // ht2.ajouterHotel(h);
       // System.out.println(ht2.listHotel("metline"));
       // System.out.println( cc.rechercher_client("sta", "yassine","yas3@esprit.tn"));
        //System.out.println(h.getIdHotel());
       
//        System.out.println(ch2.existe(c, h));
    // ch2.ajouterChambre(c, h3);
    // ch2.supprimerChambre(c, h1);
     // ch2.updateChambre(c,h1);
      //
     // System.out.println(ht2.afficherHotel(h));
        //System.out.println(ch2.afficherChambre());
      //System.out.println(ht2.rechercherHotel(h));
       // System.out.println("id: " + ht2.recupererId(h));
     //  ch2.supprimer_tout(h1);
      // ht2.supprimerHotel(h1);
        //System.out.println(ch2.existe(c, h1));
       
    }

}
