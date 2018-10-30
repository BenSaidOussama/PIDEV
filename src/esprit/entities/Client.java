/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;


/**
 *
 * @author STA
 */
public class Client extends User{
    private String username;

    public Client() {
    }

    public Client(int id, String nom, String prenom, String email, String password, String sexe, String username) {
        super(id, nom, prenom, email, password, sexe);
        this.username = username;
    }

    public Client(String nom, String prenom, String email, String password, String sexe) {
        super(nom, prenom, email, password, sexe);
    }

    public Client(String email) {
        super(email);
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getToken() {
        return super.getToken();
    }

    public int getId(){
        return super.getId();
    }
    

    @Override
    public String toString() {
        return super.toString()+ " username: " + this.username;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Client)
        {
            Client c = (Client) obj;
            return this.getId() == c.getId()
                    && this.getEmail().equalsIgnoreCase(c.getEmail())
                    && this.getToken().equalsIgnoreCase(c.getToken());
        }
        
        return false;
    }

    
    
}
