package esprit.services;

import esprit.assets.IChambre;
import esprit.entities.Chambre;
import esprit.entities.Hotel;
import esprit.entities.Reservation_hotel;
import esprit.utils.ConnectDB;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUDChambre implements IChambre {

    Connection cnx = ConnectDB.getInstance().getConnection();

    public void ajouterChambre(Chambre c, Hotel h) {
        try {

            CRUDHotel ch = new CRUDHotel();

            {
                String query = " INSERT INTO `chambre`(`typeChambre`, `numChambre`, `dispo`, `idHotel`) " + "VALUES (?,?,?,?)";
                PreparedStatement pst = cnx.prepareStatement(query);

                pst.setString(1, c.getTypeChambre());
                pst.setInt(2, c.getNumChambre());
                pst.setBoolean(3, c.isDispo());
                pst.setInt(4, ch.recupererId(h));
                pst.executeUpdate();
                String query2 = "SELECT `nbre_chambre` FROM `hotel` WHERE `idHotel`= ?";
                PreparedStatement pst2 = cnx.prepareStatement(query2);
                pst2.setInt(1, ch.recupererId(h));
                ResultSet rs = pst2.executeQuery();

                while (rs.next()) {

                    h.setNbreChambre(rs.getInt("nbre_chambre"));

                    String query1 = "UPDATE `hotel` SET `nbre_chambre`=?  WHERE `idHotel`= ?";
                    PreparedStatement pst1 = cnx.prepareStatement(query1);
                    pst1.setInt(1, h.getNbreChambre() + 1);
                    pst1.setInt(2, ch.recupererId(h));
                    pst1.executeUpdate();

                }
                 String query3 = "SELECT `nbre_de_chambre_despo` FROM `hotel` WHERE `idHotel`= ?";
            PreparedStatement pst3 = cnx.prepareStatement(query3);
            pst3.setInt(1, ch.recupererId(h));
              //  System.out.println(ch.recupererId(h));
            ResultSet rs3 = pst3.executeQuery();
                //System.out.println(h.getNbre_de_chambre_despo());

            while (rs3.next()) {
                System.out.println(rs3.getInt("nbre_de_chambre_despo"));
                h.setNbre_de_chambre_despo(rs3.getInt("nbre_de_chambre_despo"));
                System.out.println(h.getNbre_de_chambre_despo());
                String query1 = "UPDATE `hotel` SET `nbre_de_chambre_despo`=? WHERE `idHotel`= ?";
                PreparedStatement pst1 = cnx.prepareStatement(query1);
                pst1.setInt(1, h.getNbre_de_chambre_despo() +1);
                pst1.setInt(2, ch.recupererId(h));
                pst1.executeUpdate();
            }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerChambre(Chambre c, Hotel h) {

        try {
            CRUDHotel ch = new CRUDHotel();
            {
                if (this.existe(c, h)) {

                    if (c.isDispo()) {
                        String query3 = "SELECT `nbre_de_chambre_despo`FROM `hotel` WHERE `idHotel`= ?";
                        PreparedStatement pst3 = cnx.prepareStatement(query3);
                        pst3.setInt(1, ch.recupererId(h));
                        ResultSet rs3 = pst3.executeQuery();
                        while (rs3.next()) {
                            h.setNbre_de_chambre_despo(rs3.getInt("nbre_de_chambre_despo"));

                        }
                        String query1 = "UPDATE `hotel` SET `nbre_de_chambre_despo`=? WHERE `idHotel`= ?";
                        PreparedStatement pst1 = cnx.prepareStatement(query1);
                        pst1.setInt(1, h.getNbreChambre() - 1);
                        pst1.setInt(2, ch.recupererId(h));
                        pst1.executeUpdate();
                    }

                    String query2 = "SELECT `nbre_chambre`FROM `hotel` WHERE `idHotel`= ?";
                    PreparedStatement pst2 = cnx.prepareStatement(query2);
                    pst2.setInt(1, ch.recupererId(h));
                    ResultSet rs = pst2.executeQuery();
                    while (rs.next()) {
                        h.setNbreChambre(rs.getInt("nbre_chambre"));
                        //System.out.println("erreur");
                        String query1 = "UPDATE `hotel` SET `nbre_chambre`=? WHERE `idHotel`= ?";
                        PreparedStatement pst1 = cnx.prepareStatement(query1);
                        pst1.setInt(1, h.getNbreChambre() - 1);
                        pst1.setInt(2, ch.recupererId(h));
                        pst1.executeUpdate();
                    }
                    String query = "Delete FROM `chambre` where `id` = ?";
                    PreparedStatement pst4 = cnx.prepareStatement(query);
                    System.out.println(this.recupererId(c));
                    pst4.setInt(1, this.recupererId(c));
                    pst4.executeUpdate();

                    System.out.println("supprimé");
                } else {
                    System.out.println("Chambre n'existe pas!!");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erreur lors desuppression ");
        }
    }

    public void updateChambre(Chambre c, Hotel h) {
        String query = "UPDATE `chambre` SET `typeChambre`=?,`numChambre`=?,`dispo`=? WHERE `id`= ? and idHotel=?";

        try {
            if (this.existe(c, h)) {
                CRUDHotel hotel = new CRUDHotel();
                PreparedStatement st = cnx.prepareStatement(query);
                st.setString(1, c.getTypeChambre());
                st.setInt(2, c.getNumChambre());
                st.setBoolean(3, c.isDispo());

                st.setInt(4, this.recupererId(c));
                st.setInt(5, hotel.recupererId(h));
                st.executeUpdate();
                System.out.println("chambre Modifié ");
            } else {
                System.out.println("chambre inexistante !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "erreur lors de la mise a jour des donnees");
        }
    }

    public ArrayList<Chambre> afficherChambre() {
        ArrayList<Chambre> myList = new ArrayList<>();
        try {
            String query = "Select * from chambre";
            PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Chambre h = new Chambre();
                h.setId(rs.getInt("id"));
                h.setDispo(rs.getBoolean("dispo"));
                h.setNumChambre(rs.getInt("numChambre"));
                h.setTypeChambre(rs.getString("typeChambre"));

                myList.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public int recupererId(Chambre c) {

        ArrayList<Chambre> chambres = new ArrayList<>();
        chambres = this.afficherChambre();
        int id = -1;
        for (int i = 0; i < chambres.size(); i++) {
            if (chambres.get(i).getNumChambre() == (c.getNumChambre()) && chambres.get(i).getIdHotel() == (c.getIdHotel())) {
                id = chambres.get(i).getId();
                break;
            }
        }
        return id;
    }

    public void supprimer_tout(Hotel h) {
        CRUDHotel ch = new CRUDHotel();
        String query = "Delete FROM chambre where idHotel = ?";
        try {
            if (ch.existe(h)) {
                PreparedStatement pst;

                pst = cnx.prepareStatement(query);
                pst.setInt(1, ch.recupererId(h));
                pst.executeUpdate();
                String query1 = "UPDATE `hotel` SET `nbre_chambre`=? WHERE `idHotel`= ?";
                PreparedStatement pst1 = cnx.prepareStatement(query1);
                pst1.setInt(1, 0);
                pst1.setInt(2, ch.recupererId(h));
                pst1.executeUpdate();
            } else {
                System.out.println("Hotel innexistant !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDChambre.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public boolean existe(Chambre ch, Hotel h) {
        CRUDHotel c = new CRUDHotel();
        String query1 = "SELECT count(*) from chambre where id=? and idHotel=?";
        try {
            PreparedStatement pst1 = cnx.prepareStatement(query1);
            int nb = 0;
            pst1.setInt(1, this.recupererId(ch));
            pst1.setInt(2, c.recupererId(h));
            ResultSet rs = pst1.executeQuery();
            while (rs.next()) {
                nb = (rs.getInt(1));
            }
            System.out.println(nb);
            if (nb == 0) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDChambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
