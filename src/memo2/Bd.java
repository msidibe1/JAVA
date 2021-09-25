package memo2;
import java.sql.*;

public class Bd {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/commerce?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static Connection con;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connection faite");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Erreur getConnection", e);
        }
     return con;
    }
    public void fermerConnexion(){

        if (con != null){
            try{
               con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
     
}