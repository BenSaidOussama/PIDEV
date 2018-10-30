package esprit.services;

import esprit.assets.IHotel;
import esprit.entities.Chambre;
import esprit.entities.Client;
import esprit.entities.Hotel;
import esprit.entities.Reservation_hotel;
import esprit.entities.VerificationToken;
import esprit.utils.ConnectDB;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUDHotel implements IHotel {

    Connection cnx = ConnectDB.getInstance().getConnection();

    public CRUDHotel() {
    }

    public void ajouterHotel(Hotel h) {
        try {
            if (!this.existe(h)) {
                String query = "INSERT INTO `hotel`(`nameHotel`, `adresseHotel`, `typeHotel`,`prix_indiv`,`prix_double`)"
                        + " VALUES (?,?,?,?,?)";

                PreparedStatement pst = cnx.prepareStatement(query);
                pst.setString(1, h.getNameHotel());
                pst.setString(2, h.getAdresseHotel());
                pst.setInt(3, h.getTypeHotel());
                pst.setInt(4, h.getPrix_indiv());
                pst.setInt(5, h.getPrix_double());

                pst.executeUpdate();

                System.out.println("Hotel ajouté avec succès");

            } else {
                System.out.println("l'hotel existe déjà");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerHotel(Hotel h) {
        String query = "Delete FROM hotel where idHotel = ?";
        try {
            if (this.existe(h)) {
                CRUDChambre c = new CRUDChambre();
                CRUDReservation r = new CRUDReservation();
                r.supprimer_tout(h);
                c.supprimer_tout(h);
                PreparedStatement pst = cnx.prepareStatement(query);
                pst.setInt(1, this.recupererId(h));
                pst.executeUpdate();

                // c.supprimerChambre(c, h);
                System.out.println("Hotel supprimé avec succès");

            } else {
                System.out.println("l'hotel innexistant");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erreur lors desuppression d'Hotel");
        }
    }

    public void updateHotel(String nameHotel, int prix_indiv, int prix_double, int idHotel) {

        String query = "UPDATE `hotel` SET `nameHotel`=?,`prix_indiv`=?,`prix_double`=? WHERE `idHotel`= ?";

        try {

            PreparedStatement st = cnx.prepareStatement(query);

            st.setString(1, nameHotel);
            st.setInt(2, prix_indiv);
            st.setInt(3, prix_double);
            st.setInt(4,idHotel);
            st.executeUpdate();
            System.out.println("hotel Modifié ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "erreur lors de la mise a jour des donnees");
        }
    }

    public ArrayList<Hotel> afficherHotel(Hotel h) {
        ArrayList<Hotel> myList = new ArrayList<>();

        try {
            int nb = 0;
            String query1 = "Select * from chambre where dispo=1 and idHotel=?";
            PreparedStatement pst1 = cnx.prepareStatement(query1);
            pst1.setInt(1, this.recupererId(h));
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                nb++;
            }
            String query2 = "UPDATE `hotel` SET `nbre_de_chambre_despo`= ? where idHotel=?";
            PreparedStatement pst2 = cnx.prepareStatement(query2);
            pst2.setInt(1, nb);
            pst2.setInt(2, this.recupererId(h));
            pst2.executeUpdate();

            String query = "Select * from hotel where idHotel=? ";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, this.recupererId(h));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                h.setIdHotel(rs.getInt("idHotel"));
                h.setAdresseHotel(rs.getString("adresseHotel"));
                h.setNameHotel(rs.getString("nameHotel"));
                h.setTypeHotel(rs.getInt("typeHotel"));
                h.setNbre_de_chambre_despo(rs.getInt("nbre_de_chambre_despo"));

                myList.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override

    public ArrayList<Hotel> rechercherHotel(Hotel h) {

        ArrayList<Hotel> myList = new ArrayList<>();
        String query = "SELECT * FROM hotel WHERE `nameHotel`=? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, h.getNameHotel());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                h.setIdHotel(rs.getInt("idHotel"));
                h.setAdresseHotel(rs.getString("adresseHotel"));
                h.setNameHotel(rs.getString("nameHotel"));
                h.setTypeHotel(rs.getInt("typeHotel"));
                // h.setNbre_de_chambre_despo (rs.getInt("Nbre_de_chambre_despo "));

                myList.add(h);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "erreur lors de la recherche");
        }
        if (!myList.isEmpty()) {
            return myList;
        } else {
            return null;
        }
    }

    public ArrayList<Hotel> listHotel(String adress) {
        String query = "Select * from hotel where adresseHotel=?";
        ArrayList<Hotel> hotels = new ArrayList<>();

        try {
            PreparedStatement st = cnx.prepareStatement(query);
            st.setString(1, adress);
            st.execute();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                //  System.out.println("erreur");
                Hotel h = new Hotel();
                h.setIdHotel(rs.getInt("idHotel"));
                //System.out.println(rs.getInt("idHotel"));
                h.setNameHotel(rs.getString("nameHotel"));
                h.setAdresseHotel(rs.getString("adresseHotel"));
                h.setTypeHotel(rs.getInt("typeHotel"));
                h.setNbreChambre(rs.getInt("nbre_chambre"));
                h.setNbre_de_chambre_despo(rs.getInt("nbre_de_chambre_despo"));
                h.setPrix_double(rs.getInt("prix_double"));
                h.setPrix_indiv(rs.getInt("prix_indiv"));
                System.out.println(h);

                hotels.add(h);
                //  System.out.println(rs.getInt("nbre_chambre"));
                //System.out.println(h);
            }
        } catch (SQLException ex) {
            System.out.println("erreur while loading data from DB " + ex.getMessage());
        }
        return hotels;
    }

    public int recupererId(Hotel h) {
        CRUDHotel ch = new CRUDHotel();
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels = ch.listHotel(h.getAdresseHotel());
        //  System.out.println(hotels);
        int id = -1;
        // System.out.println(hotels.size());
        for (int i = 0; i < hotels.size(); i++) {
            //System.out.println("erreur");
            if (hotels.get(i).getNameHotel().equals(h.getNameHotel()) && hotels.get(i).getAdresseHotel().equals(h.getAdresseHotel())) {
                id = hotels.get(i).getIdHotel();
                //   System.out.println(hotels.get(i));
                //  System.out.println(id);
                break;
            }
        }
        return id;
    }

    public boolean existe(Hotel h) {
        int id = this.recupererId(h);
        if (id != -1) {
            return true;
        }
        return false;
    }

    public Hotel return_hotel(String adresse, String nom) {
        String query = "SELECT * FROM hotel where nameHotel=? and adresseHotel=?";
        Hotel h = new Hotel();

        try {
            PreparedStatement st = cnx.prepareStatement(query);
            st.setString(1, nom);
            st.setString(2, adresse);
            st.execute();
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                //  System.out.println("erreur");

                h.setIdHotel(rs.getInt("idHotel"));
                h.setNameHotel(rs.getString("nameHotel"));
                h.setAdresseHotel(rs.getString("adresseHotel"));
                h.setTypeHotel(rs.getInt("typeHotel"));
                h.setNbreChambre(rs.getInt("nbre_chambre"));
                h.setNbre_de_chambre_despo(rs.getInt("nbre_de_chambre_despo"));

            }
        } catch (SQLException ex) {
            System.out.println("erreur while loading data from DB " + ex.getMessage());
        }
        return h;
    }

}
