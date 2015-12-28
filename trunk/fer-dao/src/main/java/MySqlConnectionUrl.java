import java.sql.DriverManager;

public class MySqlConnectionUrl {
  public static void main(String [] args) {
    java.sql.Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver") ;
      System.out.println("MySQL JDBC driver loaded ok.");
 
      con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/fer_scoysbjr6", "srfe","adminadmin");
      System.out.println("Connected with host:port/database.");
      con.close();

      
    } catch (Exception e) {
      System.err.println("Exception: "+e.getMessage());
    }
  }
}