
package esprit.assets;

import esprit.entities.Chambre;
import esprit.entities.Client;
import esprit.entities.Hotel;
import esprit.entities.Reservation_hotel;
import java.util.ArrayList;


public interface IReservation_hotel {
    public void reserverHotel(Hotel h,Client c,int nbre_indiv,int nbre_double);
    public void annulerReservation(Hotel h,Client c);
    public ArrayList<Reservation_hotel> afficherReservation_hotel();
    public int recupererId(Reservation_hotel r);

}
