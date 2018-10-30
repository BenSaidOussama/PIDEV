/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.assets;

import esprit.entities.Chambre;
import esprit.entities.Hotel;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface IChambre {
     public void ajouterChambre(Chambre c,Hotel h);
    public void supprimerChambre(Chambre c,Hotel h);
    public void updateChambre(Chambre c,Hotel h);
    public ArrayList<Chambre> afficherChambre();
 

    
   
    
}
