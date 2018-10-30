/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author STA
 */
public class VerificationToken {

    private String email;
    private String token;
    private int id;
    
    public VerificationToken() {
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }


    
    public static boolean validateEmail(String input)
    {
        String emailRegx = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(input);
        return matcher.find();
    }
    
    public String genrateToken()
    {
        String chaine ="AZERTYUIOPQSDFGHJKLMWXCVBN1236547890";
        String tokens="";
        
        final int length = 5;
        
        Random rand = new Random();
        char text[] = new char[length];
        
        for (int i=0; i<length; i++)
            text[i] = chaine.charAt(rand.nextInt(chaine.length()));
        
        for(int i=0; i<length; i++)
            tokens += text[i];
            
        return tokens;
    }
    
    
    
    
}
