package esprit.services;

import esprit.assets.IReservation_hotel;
import esprit.entities.Chambre;
import esprit.entities.Client;
import esprit.entities.Hotel;
import esprit.entities.Reservation_hotel;
import esprit.utils.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUDReservation implements IReservation_hotel {

    Connection cnx = ConnectDB.getInstance().getConnection();

    public void reserverHotel(Hotel h, Client c, int nbre_indiv, int nbre_double) {
        try {
            if(!this.existe(c, h)){
            CRUDHotel ch = new CRUDHotel();
            CRUDClient cc = new CRUDClient();
            String query = " INSERT INTO `reservation_hotel`(`idClient`, `idHotel`,`nbre_indiv`,`nbre_double`) " + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, cc.recupererId(c));
            pst.setInt(2, ch.recupererId(h));
            pst.setInt(3, nbre_indiv);
            pst.setInt(4, nbre_double);
            pst.executeUpdate();

            String query2 = "SELECT `nbre_de_chambre_despo` FROM `hotel` WHERE `idHotel`= ?";
            PreparedStatement pst2 = cnx.prepareStatement(query2);
            pst2.setInt(1, ch.recupererId(h));
            ResultSet rs = pst2.executeQuery();

            while (rs.next()) {

                h.setNbreChambre(rs.getInt("nbre_de_chambre_despo"));

                String query1 = "UPDATE `hotel` SET `nbre_de_chambre_despo`=? WHERE `idHotel`= ?";
                PreparedStatement pst1 = cnx.prepareStatement(query1);
                pst1.setInt(1, h.getNbre_de_chambre_despo() - nbre_double - nbre_indiv);
                pst1.setInt(2, ch.recupererId(h));
                pst1.executeUpdate();
            }}

           else{
                System.out.println("Vous avez deja une reservation !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void annulerReservation(Hotel h, Client c) {
        try {
          if(this.existe(c, h)){
            CRUDHotel ch = new CRUDHotel();
            CRUDClient cc = new CRUDClient();
            Reservation_hotel r = new Reservation_hotel();
            //recuperer nbre_indiv
            String query0 = "SELECT nbre_indiv FROM reservation_hotel WHERE idClient=? and idHotel=?";
            PreparedStatement pst0 = cnx.prepareStatement(query0);
            pst0.setInt(1, cc.recupererId(c));
            pst0.setInt(2, ch.recupererId(h));
            ResultSet rs0 = pst0.executeQuery();
            while (rs0.next()) {

                r.setNbre_indiv(rs0.getInt("nbre_indiv"));
            }
            System.out.println(r.getNbre_indiv());
            //recuperer nbre double
            String query1 = "SELECT nbre_double FROM reservation_hotel WHERE idClient=? and idHotel=?";
            PreparedStatement pst1 = cnx.prepareStatement(query1);
            pst1.setInt(1, cc.recupererId(c));
            pst1.setInt(2, ch.recupererId(h));
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {

                r.setNbre_double(rs1.getInt("nbre_double"));
            }
            System.out.println(r.getNbre_double());
            //faire la somme 
            int nbre_reserv = r.getNbre_double() + r.getNbre_indiv();
            System.out.println(nbre_reserv);
            //supprimer reserv de tab reserv  
            String query2 = " DELETE  FROM reservation_hotel WHERE idClient=? and idHotel=?";
            PreparedStatement pst2 = cnx.prepareStatement(query2);
            pst2.setInt(1, cc.recupererId(c));
            pst2.setInt(2, ch.recupererId(h));
            pst2.executeUpdate();

            //reccuperer nbre de chambre dispo
             String query3 = "SELECT `nbre_de_chambre_despo` FROM `hotel` WHERE `idHotel`= ?";
             PreparedStatement pst3 = cnx.prepareStatement(query3);
             pst3.setInt(1, ch.recupererId(h));
             ResultSet rs3 = pst3.executeQuery();
          
        while (rs3.next()) {
               
                h.setNbre_de_chambre_despo(rs3.getInt("nbre_de_chambre_despo"));
                System.out.println(h.getNbre_de_chambre_despo());
                
              //Modifier/augmenter nbre d chambre ispo
                String query4 = "UPDATE `hotel` SET `nbre_de_chambre_despo`=? WHERE `idHotel`= ?";
                PreparedStatement pst4 = cnx.prepareStatement(query4);
                pst4.setInt(1, h.getNbre_de_chambre_despo()+nbre_reserv);
                pst4.setInt(2, ch.recupererId(h));
                pst4.executeUpdate();
                        }
        }else{
              
                System.out.println("Vous n'avez pas de reservation !");
            } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int recupererId_reserv(Hotel h,Client c) {

        ArrayList<Reservation_hotel> reservation_hotel = new ArrayList<>();
       
        reservation_hotel = this.afficherReservation_hotel();
        int id = -1;
        for (int i = 0; i < reservation_hotel.size(); i++) {
            if (reservation_hotel.get(i).getIdClient() == (c.getId()) && reservation_hotel.get(i).getIdHotel() == (h.getIdHotel())) {
                id = reservation_hotel.get(i).getIdHotel();
                break;
            }
        }
        return id;
    }
    public int recupererId(Reservation_hotel r) {

        ArrayList<Reservation_hotel> reservation_hotel = new ArrayList<>();
        reservation_hotel = this.afficherReservation_hotel();
        int id = -1;
        for (int i = 0; i < reservation_hotel.size(); i++) {
            if (reservation_hotel.get(i).getIdClient() == (r.getIdClient()) && reservation_hotel.get(i).getIdHotel() == (r.getIdHotel())) {
                id = reservation_hotel.get(i).getIdHotel();
                break;
            }
        }
        return id;
    }

    public ArrayList<Reservation_hotel> afficherReservation_hotel() {
        ArrayList<Reservation_hotel> myList = new ArrayList<>();
        try {
            String query = "Select * from reservation_hotel";
            PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reservation_hotel r = new Reservation_hotel();
                r.setIdReservation(rs.getInt("idReservation"));
                r.setIdClient(rs.getInt("idClient"));
                r.setIdHotel(rs.getInt("idHotel"));

                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
     public void supprimer_tout(Hotel h) {
        CRUDHotel ch= new CRUDHotel();
        String query = "Delete FROM reservation_hotel where idHotel = ?";
        try {
            PreparedStatement pst;
            pst = cnx.prepareStatement(query);
            pst.setInt(1, ch.recupererId(h));
            pst.executeUpdate();
     
        }catch (SQLException ex) {
            Logger.getLogger(CRUDChambre.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
     public boolean existe(Client c, Hotel h) {
        CRUDHotel ch = new CRUDHotel();
         CRUDClient cc = new CRUDClient();
        String query1 = "SELECT count(*) from reservation_hotel where idClient=? and idHotel=?";
        try {
            PreparedStatement pst1 = cnx.prepareStatement(query1);
            int nb = 0;
            pst1.setInt(1, cc.recupererId(c));
            pst1.setInt(2, ch.recupererId(h));
            ResultSet rs = pst1.executeQuery();
            while (rs.next()) {
                  nb=(rs.getInt(1));
            }
            System.out.println(nb);
            if(nb==0){return false;}
        } catch (SQLException ex) {
            Logger.getLogger(CRUDChambre.class.getName()).log(Level.SEVERE, null, ex);
        }
return true;
    }
    
   
    

}
