package Kalkulator_Serwer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Serwer_Connect {
    private static final String URL = "jdbc:postgresql://localhost/Baza_danych_1";
   private static final String USER = "postgres";
   private static final String PASSWD = "admin";
   
   public static Connection connect()
       {
           Connection connection=null;
       try {
           Class.forName("org.postgresql.Driver.class");
       } catch (ClassNotFoundException ex) {
           ex.getMessage();
       }
           try
           {
              
              connection = DriverManager.getConnection(URL,USER,PASSWD);
              System.out.println("yes");
           }
           catch(SQLException e)
           {
              e.printStackTrace();
           }
           return connection;
       }
}