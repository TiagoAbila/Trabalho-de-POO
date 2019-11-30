package Controller;
//
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	// Atributos
	private Connection conexao;
	private Statement query;

	// Metodos
	public void setConnection(String db, String usr, String pass) throws SQLException {
		conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + db, usr, pass);
	}

	/* Teste Insert */
	public void insertQuery(int pk, String nome) throws SQLException {
		query = conexao.createStatement();
		query.execute("INSERT INTO autor (cd_autor, nm_autor) values (" + pk + ", '" + nome + "')");
		query.close();
	}

	/* Teste Select */
	public void selectQuery() throws SQLException {
		query = conexao.createStatement();
		ResultSet result;
		result = query.executeQuery("Select * from autor");
		while (result.next()) {
			int id = result.getInt(1);
			String nome = result.getString(2);
			System.out.println("ID = " + id);
			System.out.println("Nome = " + nome);
		}
		result.close();
		query.close();
	}
}
