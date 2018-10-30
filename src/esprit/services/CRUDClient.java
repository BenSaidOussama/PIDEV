/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.services;

import esprit.entities.SendHTMLEmail;
import esprit.assets.IClient;
import esprit.entities.*;
import esprit.pidev.entities.Confirmation;
import esprit.utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author STA
 */
public class CRUDClient implements IClient{
    
    Connection con = ConnectDB.getInstance().getConnection();
    
    public void sendEmail(Client c)
    {
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "yassine.sta@esprit.tn";
        String password = "azertysta";

        // outgoing message information
        String mailTo = c.getEmail();
        String subject = "Validating email registration";

        // message contains HTML markups
        String message = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">	\n" +
        "		<tr>\n" +
        "			<td style=\"padding: 10px 0 30px 0;\">\n" +
        "				<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\">\n" +
        "					<tr>\n" +
        "						<td align=\"center\" bgcolor=\"#5770c2\" style=\"padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\n" +
        "							<img src=\"https://image.ibb.co/iL1HQf/registration.png\" alt=\"registration\" width=\"400\" height=\"225\" style=\"display: block;\" />\n" +
        "						</td>\n" +
        "					</tr>\n" +
        "					<tr>\n" +
        "						<td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" +
        "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
        "								<tr>\n" +
        "									<td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n" +
        "										<b>Thank you for registration!</b>\n" +
        "									</td>\n" +
        "								</tr>\n" +
        "								<tr>\n" +
        "									<td style=\"padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
        "										You have been successfuly registred on our HolidaysNow application, just one more step and you will be able\n" +
        "                                        to have full control of your account. We have provided a PIN code bellow for validating your email.\n" +
        "									</td>\n" +
        "								</tr>\n" +
        "                                <tr>\n" +
        "                                    <td style=\"text-align: center; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
        "                                        PIN code: <br>\n" +
                                                        c.getToken() + "<br><br>\n" +
        "                                    </td>\n" +
        "                                </tr>\n" +
        "								<tr>\n" +
        "									<td>\n" +
        "										<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
        "											<tr>\n" +
        "												<td width=\"260\" valign=\"top\">\n" +
        "													<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
        "														<tr>\n" +
        "															<td>\n" +
        "																<img src=\"https://image.ibb.co/dtxDy0/left1.png\" alt=\"\" width=\"100%\" height=\"140\" style=\"display: block;\" />\n" +
        "															</td>\n" +
        "														</tr>\n" +
        "														<tr>\n" +
        "															<td style=\"padding: 25px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px; text-align: justify;\">\n" +
        "																You can already use our mobile app for better experience by downloading it from \n" +
        "                                                                AppStore for iOs devices or PlayStore for Android version.\n" +
        "															</td>\n" +
        "														</tr>\n" +
        "													</table>\n" +
        "												</td>\n" +
        "												<td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
        "													&nbsp;\n" +
        "												</td>\n" +
        "												<td width=\"260\" valign=\"top\">\n" +
        "													<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
        "														<tr>\n" +
        "															<td>\n" +
        "																<img src=\"https://image.ibb.co/eNXDy0/right1.png\" alt=\"\" width=\"100%\" height=\"140\" style=\"display: block;\" />\n" +
        "															</td>\n" +
        "														</tr>\n" +
        "														<tr>\n" +
        "															<td style=\"padding: 25px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px; text-align: justify;\">\n" +
        "																\n" +
        "															</td>\n" +
        "														</tr>\n" +
        "													</table>\n" +
        "												</td>\n" +
        "											</tr>\n" +
        "										</table>\n" +
        "									</td>\n" +
        "								</tr>\n" +
        "							</table>\n" +
        "						</td>\n" +
        "					</tr>\n" +
        "					<tr>\n" +
        "						<td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px 30px 30px;\">\n" +
        "							<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
        "								<tr>\n" +
        "									<td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\" width=\"75%\">\n" +
        "										&reg; GeekGods 2018<br/>\n" +
        "									</td>\n" +
        "									<td align=\"right\" width=\"25%\">\n" +
        "										<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
        "											<tr>\n" +
        "												<td style=\"font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;\">\n" +
        "													<a href=\"http://www.twitter.com/\" style=\"color: #ffffff;\">\n" +
        "														<img src=\"https://image.ibb.co/g4c2rL/tw.gif\" alt=\"Twitter\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n" +
        "													</a>\n" +
        "												</td>\n" +
        "												<td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\n" +
        "												<td style=\"font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;\">\n" +
        "													<a href=\"http://www.twitter.com/\" style=\"color: #ffffff;\">\n" +
        "														<img src=\"https://image.ibb.co/jwOmJ0/fb.gif\" alt=\"Facebook\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n" +
        "													</a>\n" +
        "												</td>\n" +
        "											</tr>\n" +
        "										</table>\n" +
        "									</td>\n" +
        "								</tr>\n" +
        "							</table>\n" +
        "						</td>\n" +
        "					</tr>\n" +
        "				</table>\n" +
        "			</td>\n" +
        "		</tr>\n" +
        "	</table>";

                SendHTMLEmail mailer = new SendHTMLEmail();

                try {
                    mailer.SendHTMLEmail(host, port, mailFrom, password, mailTo,subject, message);
                    System.out.println("Email sent.");
                } catch (Exception ex) {
                    System.out.println("Failed to sent email.");
                    ex.printStackTrace();
                }
    }
    
    @Override
    public void ajouterClient(Client c) {
        
       // verify email before inserting and if it's unique
        //Confirmation verif = new Confirmation();

       // if(uniqueEmail(c.getEmail()) && verif.validateEmail(c.getEmail()))
        //{                
            String query = "INSERT INTO `users`(`name`, `lname`, `email`, `password`, `sexe` )"
                    + " VALUES (?,?,?,?,?)";
            //genrating random token
          //  String token = verif.genrateToken();
          //  c.setToken(token);
          //  byte enable = 0;
            try {
                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, c.getPrenom());
                pst.setString(2, c.getNom());
                pst.setString(3, c.getEmail());
                pst.setString(4, c.getPassword());
                pst.setString(5, c.getSexe());
              //  pst.setString(6, token);
              //  pst.setByte(7, enable); 
                
             //   this.sendEmail(c);
                pst.executeUpdate();
                
                System.out.println("Utilisateur ajouté avec succès");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
       // else
       // {
       //     System.out.println("Utilisateur déjà existant");
        //}
   
    

    @Override
    public void supprimerClient(Client c) {
        try {
            String query = "Delete FROM users where id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erreur lors desuppression d'utilisateur");
        }
    }

    @Override
    public void updateClient(Client c) {
        String query="update users set name=? , lname=? , password=? , sexe =?"
                + " , email =? where id= ?";
        Confirmation verif = new Confirmation();
        if(this.uniqueEmail(c.getEmail()) && verif.validateEmail(c.getEmail()))
        {
            PreparedStatement pst ;
            try{
                pst = con.prepareStatement(query);
                pst.setString(1, c.getPrenom());
                pst.setString(2, c.getNom());
                pst.setString(3, c.getPassword());
                pst.setString(4, c.getSexe());
                pst.setString(5, c.getEmail());
                pst.setInt(6, c.getId());
                pst.executeUpdate();
            }catch(SQLException ex)
            {
                System.out.println(ex.getMessage()+"erreur lors de la mise a jour des donnees");
            }
        }
    }

    @Override
    public ArrayList<Client> afficherClient() {
        String query = "Select * from users";
        ArrayList<Client> myList = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs= pst.executeQuery();
            while(rs.next())
            {
                Client c = new Client();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("lname"));
                c.setPrenom(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setUsername(rs.getString("username"));
                c.setSexe(rs.getString("sexe"));
                c.setPassword(rs.getString("password"));
                c.setToken(rs.getString("token"));
                myList.add(c);
            }
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    @Override
    public boolean uniqueEmail(String mail)
    {
        String query="Select email from users";
        ArrayList<String> email = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                email.add(rs.getString("email"));  
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        if(email.size()>0)
            return !email.contains(mail);
        
        return true;
    }
    
    
    public boolean activatingAccount(String email, String token)
    {
        Scanner in = new Scanner(System.in);
  
        String query="Select id , email , token , enabled from users where enabled =0" ;
        //String query="Select  email , token , enabled from users where enabled =0" ;
        ArrayList<Confirmation> emailList = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                Confirmation s = new Confirmation();
                s.setId(rs.getInt(1)); 
                s.setEmail(rs.getString(2));  
                s.setToken(rs.getString(3));
                emailList.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("processing. . .");
        for(int i=0; i<emailList.size(); i++)
        {
            System.out.println( "id: " +emailList.get(i).getId() 
                    +" email: " +emailList.get(i).getEmail() 
                    + " token: " + emailList.get(i).getToken());
        }
           
        /*
        System.out.print("Please enter your id: ");
        int id = in.nextInt();
        System.out.print("Please enter provided token in your email: ");
        String activation = in.next(); 
        
        */
        System.out.println("entring to update");
        System.out.println("token entred: "+ token);
        for(int i=0; i<emailList.size(); i++)
        {
            if(token.equalsIgnoreCase(emailList.get(i).getToken()))
            //if((activation.equals(emailList.get(i).getToken())) && (id == emailList.get(i).getId()))
            {
                int id =-1;
                id = emailList.get(i).getId();
                System.out.println("id: "+id);
                //String Query = "update users set enabled= 1 , token = '' where id ="+ id;
                String Query = "update users set enabled= 1 , token = '' where id="+ id;
                PreparedStatement pst;
                try {
                    pst = con.prepareStatement(Query);
                    pst.executeUpdate();
                    System.out.println("Compte utilisateur activé avec succès");
                    return true;
                } catch (SQLException ex) {
                    System.out.println("Erreur lors de la mise à jour du enabled" + ex.getMessage());
                }
                
            }
        }  
        return false;
    }
    
    public void resendToken(Client c)
    {
        Confirmation verif = new Confirmation();
        String token = verif.genrateToken();
        String Query = "update users set `enabled`=0,`token`="+ token +"where id= " + c.getId();
        
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(Query);
            pst.executeUpdate();
            System.out.println("Compte utilisateur activé avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour du enabled \n" +ex.getMessage());
        }
    }
    
    public void passwordForgot(String mail)
    {
        ArrayList<Client> clients = new ArrayList<>();
        CRUDClient c = new CRUDClient();
        clients = c.afficherClient();
        
        for(int i=0; i<clients.size(); i++)
        {
            System.out.println(clients.get(i).getEmail());
            if(clients.get(i).getEmail().equals(mail))
            {
                System.out.println(mail + " exite dans la base des données :)");
                System.out.println("We have provided for you pin code on your email adresse"
                         + "\nWould you please confirm: ");
                //clients.get(i).
                int id = clients.get(i).getId() ;
                System.out.println("id: "+id);
                Confirmation newValidation = new Confirmation();
                String newToken = newValidation.genrateToken();
                System.out.println("new token provided: "+newToken);
                String query = "update users set token =?,enabled =? where id = ?";
                clients.get(i).setEmail(mail);
                clients.get(i).setToken(newToken);
                this.sendEmail(clients.get(i));
                PreparedStatement pst;
                try {
                    pst = con.prepareStatement(query);
                    byte enabled =0;
                    pst.setString(1, newToken);
                    pst.setByte(2, enabled);
                    pst.setInt(3, id);
                    pst.executeUpdate();
                    //System.out.println("Compte utilisateur a effacé le mots de passe avec succès");
                } catch (SQLException ex) {
                    System.out.println("Erreur lors de la mise à jour du enabled \n" +ex.getMessage());
                }
                return;
                //ne pas oublier de sqlinjection!!!
            }  
        }
        System.out.println(mail + " n'existe pas dans la base des données");
    }
    
    public boolean updatePassword(String password, String email){
          
        CRUDClient test = new CRUDClient();
        ArrayList<Client> clients = new ArrayList<>();
        clients = test.afficherClient();
        
        for(int i=0; i<clients.size(); i++)
        {
            System.out.println("test1 client: "+clients.get(i).getToken());
            //System.out.println("token writen: "+token);
            if(clients.get(i).getEmail().equalsIgnoreCase(email))
            {
                //System.out.println("client: "+clients.get(i).getToken());
                String query2="update users set password=? where email=?";
                PreparedStatement pst;
                try{
                    pst = con.prepareStatement(query2);
                    pst.setString(1, password);
                    pst.setString(2, email);
                    pst.executeUpdate();
                    System.out.println("Your password has been set to "+ password);
                }catch(SQLException ex){
                     System.out.println("Erreur lors de la mise à jour du mots de passe \n" +ex.getMessage());
                }
                return true;
            }
        }
        System.out.println("Erreur lors de l'entrée des données"); 
        return false;
    }
   public int recupererId(Client c)
       {
           CRUDClient cc = new CRUDClient();
           ArrayList<Client> client = new ArrayList<>();
           client = cc.afficherClient();
           int id = -1;
           for(int i=0; i<client.size(); i++)
           {
               if(client.get(i).getEmail().equals(c.getEmail()) && client.get(i).getNom().equals(c.getNom()))
               {
                   id = client.get(i).getId();
                   break;
               }
           }
           return id;
       }
   public Client rechercher_client(String name, String lname, String mail) {
        String query = "SELECT * FROM users where name=? and lname=? and email=?";
                    Client c = new Client();

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, lname);
            pst.setString(3, mail);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                c.setEmail(mail);
                c.setId(rs.getInt("id"));
                c.setEmail(rs.getString("email"));
                c.setNom(rs.getString("lname"));
                c.setPrenom(rs.getString("name"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "erreur lors de la recherche");

        }
        return c;
    }
}
