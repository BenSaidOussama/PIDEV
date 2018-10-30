
package esprit.entities;


public class Chambre {
   private boolean dispo;
   private String typeChambre;
   private int id;
   private int numChambre;

   private int idHotel;

    public Chambre(boolean dispo, String typeChambre, int numChambre) {
        this.dispo = dispo;
        this.typeChambre = typeChambre;
        this.numChambre = numChambre;
    }

    public Chambre(String typeChambre, int numChambre) {
        this.typeChambre = typeChambre;
        this.numChambre = numChambre;
       
    }


    public Chambre() {
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public Chambre(boolean dispo, String typeChambre, int numChambre, int idHotel) {
        this.dispo = dispo;
        this.typeChambre = typeChambre;
        this.numChambre = numChambre;
        this.idHotel = idHotel;
    }

    

    public Chambre(boolean dispo, String typeChambre) {
        this.dispo = dispo;
        this.typeChambre= typeChambre;
    }

    

    public boolean isDispo() {
        return dispo;
    }

    public String getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(String typeChambre) {
        this.typeChambre = typeChambre;
    }

   

    public int getId() {
        return id;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Chambre{" + "dispo=" + dispo + ", typeChambre=" + typeChambre+ ", id=" + id + '}';
    }

    public int getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(int numChambre) {
        this.numChambre = numChambre;
    }

   

   
    public Chambre(boolean dispo, String typeChambre, int id, int numChambre, int idHotel) {
        this.dispo = dispo;
        this.typeChambre = typeChambre;
        this.id = id;
        this.numChambre = numChambre;
       
        this.idHotel = idHotel;
    }

 
    
   
}
