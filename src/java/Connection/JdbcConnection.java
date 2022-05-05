package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class JdbcConnection {
 public static Connection getConnection()
 {
     Connection con = null;
     String url = "jdbc:derby://localhost:1527/FriendsDB"; 
     String username = "app"; 
     String password = "app"; 
     System.out.println("In DBConnection.java class ");
      
     try
     {
         try
         {
            Class.forName("org.apache.derby.jdbc.ClientDriver"); 
         } 
         catch (ClassNotFoundException e)
         {
            e.printStackTrace();
         }       
         con = DriverManager.getConnection(url, username, password);
         System.out.println("Printing connection object "+con);
     } 
     catch (Exception e) 
     {
        e.printStackTrace();
     }   
     return con; 
 }
}