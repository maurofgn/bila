import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {

  public static void main(String[] args) {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    try{
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bila", "root", "fgnmra");
      stmt = con.createStatement();
      rs = stmt.executeQuery("select * from account_cee");
      while (rs.next()) {
        System.out.println(" id: " + rs.getLong("id") +
            " code: " + rs.getString("code") +
            " description: " + rs.getString("description"));
      }
    }catch(SQLException e){
      e.printStackTrace();
    }catch(ClassNotFoundException e){
      e.printStackTrace();
    }finally{
      try{rs.close();}catch(Exception e){}
      try{stmt.close();}catch(Exception e){}
      try{con.close();}catch(Exception e){}
   }
  }
}