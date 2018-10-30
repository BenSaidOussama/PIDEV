
package esprit.entities;

import java.util.ArrayList;


public class Hotel {
      private int idHotel;
      private String nameHotel;
      private String adresseHotel;
      private int nb_etoile;
      private ArrayList<Chambre> chambres;
     private int nbreChambre;
       private int nbre_de_chambre_despo;
       private int prix_double;
       private int prix_indiv;

   

    public void setPrix_double(int prix_double) {
        this.prix_double = prix_double;
    }

    public void setPrix_indiv(int prix_indiv) {
        this.prix_indiv = prix_indiv;
    }

    public int getPrix_double() {
        return prix_double;
    }

    public int getPrix_indiv() {
        return prix_indiv;
    }

    public Hotel(int prix_double, int prix_indiv,String nameHotel, String adresseHotel, int nb_etoile) {
        this.prix_double = prix_double;
        this.prix_indiv = prix_indiv;
           this.nameHotel = nameHotel;
        this.adresseHotel = adresseHotel;
        this.nb_etoile = nb_etoile;
    }
   

 
  
   
    public Hotel() {
          chambres = new ArrayList<Chambre>();
        
    }

    public Hotel(String nameHotel, String adresseHotel, int nb_etoile) {
        this.nameHotel = nameHotel;
        this.adresseHotel = adresseHotel;
        this.nb_etoile = nb_etoile;
    }

    public void setNbreChambre(int nbreChambre) {
        this.nbreChambre = nbreChambre;
    }

    public Hotel(String nameHotel, String adresseHotel, int nb_etoile, int nbreChambre) {
        this.nameHotel = nameHotel;
        this.adresseHotel = adresseHotel;
        this.nb_etoile = nb_etoile;
        this.nbreChambre = nbreChambre;
    }
    
    public Hotel(int idHotel,String nameHotel, String adresseHotel, int nb_etoile, int nbreChambre) {
        this.idHotel = idHotel;
        this.nameHotel = nameHotel;
        this.adresseHotel = adresseHotel;
        this.nb_etoile = nb_etoile;
        this.nbreChambre = nbreChambre;
    }

    public Hotel(String nameHotel, String adresseHotel, int nb_etoile, int nbreChambre, int nbre_de_chambre_despo) {
        this.nameHotel = nameHotel;
        this.adresseHotel = adresseHotel;
        this.nb_etoile = nb_etoile;
        this.nbreChambre = nbreChambre;
        this.nbre_de_chambre_despo = nbre_de_chambre_despo;
    }

    public Hotel(String nameHotel, int nb_etoile, int nbreChambre, int nbre_de_chambre_despo) {
        this.nameHotel = nameHotel;
        this.nb_etoile = nb_etoile;
        this.nbreChambre = nbreChambre;
        this.nbre_de_chambre_despo = nbre_de_chambre_despo;
    }
    

   

    public int getIdHotel() {
        return idHotel;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public String getAdresseHotel() {
        return adresseHotel;
    }

    public int getTypeHotel() {
        return nb_etoile;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public void setAdresseHotel(String adresseHotel) {
        this.adresseHotel = adresseHotel;
    }

    public void setTypeHotel(int nb_etoile) {
        this.nb_etoile = nb_etoile;
    }

   

    public int getNbreChambre() {
        return nbreChambre;
    }

    @Override
    public String toString() {
        return "Hotel{" + "idHotel=" + idHotel + ", nameHotel=" + nameHotel + ", adresseHotel=" + adresseHotel + ", nb_etoile=" + nb_etoile + ", chambres=" + chambres + ", nbreChambre=" + nbreChambre + ", nbre_de_chambre_despo=" + nbre_de_chambre_despo + ", prix_double=" + prix_double + ", prix_indiv=" + prix_indiv + '}';
    }

   
   

    public int getNbre_de_chambre_despo() {
        return nbre_de_chambre_despo;
    }

    public void setNbre_de_chambre_despo(int nbre_de_chambre_despo) {
        this.nbre_de_chambre_despo = nbre_de_chambre_despo;
    }
    
     
}

    