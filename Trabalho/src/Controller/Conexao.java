package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import org.postgresql.*;

//Atributos



//Construtores

	
//Metodos
public class Conexao  {
	public static void main(String args[]) {
	      Connection conexao = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         conexao = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/teca",
	            "postgres", "102030");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
}
