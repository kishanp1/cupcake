/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kishan
 */
public class DBConnection {
    public static Connection cakeconnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
          String url = "jdbc:mysql://localhost:3306/cupcake1.0";
          con = DriverManager.getConnection(url, "root", "root");
          
        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
