
package esprit.assets;

import esprit.entities.Client;
import esprit.entities.Hotel;
import java.util.ArrayList;


public interface IHotel {
    public void ajouterHotel(Hotel h);
    public void supprimerHotel(Hotel h);
    public void updateHotel(String nameHotel,int  prix_indiv,int prix_double ,int  idHotel);
    public ArrayList<Hotel> afficherHotel(Hotel h);
    public ArrayList<Hotel> rechercherHotel(Hotel h);

  
}
