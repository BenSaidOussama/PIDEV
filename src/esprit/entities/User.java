/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;

import java.util.Date;

/**
 *
 * @author STA
 */
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Byte enabled; 
    private String token;
    private String sexe;

    public User() {
    }

    public User(int id, String nom, String prenom, String email, String password, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
    }

    public User(String nom, String prenom, String email, String password, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
    }

    public User(String email) {
        this.email = email;
    }
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public String getToken() {
        return token;
    }

    public String getSexe() {
        return sexe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password ;
    }
    
    
    
}
