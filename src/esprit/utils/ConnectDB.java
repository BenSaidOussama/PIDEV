/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.utils;
import java.sql.*;

/**
 *
 * @author STA
 */
public class ConnectDB {
    
    private static ConnectDB instance;
    private static final String url = "jdbc:mysql://localhost:3306/pidev";
    private static final String username = "root";
    private static final String password = "";
    Connection connect;
    
    private ConnectDB(){
        try{
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
    public Connection getConnection()
    {
        return connect;
    }
    
    public static ConnectDB getInstance(){
        if(instance ==null)
            instance = new ConnectDB();
        
        return instance;
    }
}
