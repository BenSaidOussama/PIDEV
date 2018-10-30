
package esprit.entities;


public class Reservation_hotel {
     private int idReservation;
      private int idClient;
      private int idHotel;
      private int nbre_indiv;
      private int nbre_double;

    public Reservation_hotel(int idClient, int idHotel, int nbre_indiv, int nbre_double) {
        this.idClient = idClient;
        this.idHotel = idHotel;
        this.nbre_indiv = nbre_indiv;
        this.nbre_double = nbre_double;
    }

    public int getNbre_indiv() {
        return nbre_indiv;
    }

    public int getNbre_double() {
        return nbre_double;
    }

    public void setNbre_indiv(int nbre_indiv) {
        this.nbre_indiv = nbre_indiv;
    }

    public void setNbre_double(int nbre_double) {
        this.nbre_double = nbre_double;
    }

    

    public Reservation_hotel() {
    }

    public int getIdReservation() {
        return idReservation;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    @Override
    public String toString() {
        return "Reservation_hotel{" + "idReservation=" + idReservation + ", idClient=" + idClient + ", idHotel=" + idHotel + '}';
    }

    public Reservation_hotel(int idClient, int idHotel) {
        this.idClient = idClient;
        this.idHotel = idHotel;
    }
    
   
}
