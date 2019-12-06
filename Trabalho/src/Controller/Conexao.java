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
	
	public void insertIntoDB(
			String ds_material,
			String ds_divulgacao,
			String nm_entidade,
			String tp_entidade, // Governamental ou ñ
			String[] nm_autor, // Por hora não vincula na tabela n para n
			String[] ds_palavra_chave, // Por hora não vincula na tabela n para n
			String nm_local_publicacao,
			String nm_editora,
			String nm_titulo,
			String ds_ano_producao,
			String ds_ano_publicacao,
			String ds_edicao,
			String nr_paginas,
			String ds_url_disponivel,
			String nr_isbn,
			String nr_issn
			) throws SQLException {
		String cd_editora = null;
		String cd_entidade = null;
		String cd_local_publicacao = null;
		String tp_material = null;
		String tp_divulgacao = null;
		
		query = conexao.createStatement();
		query.execute( 
				"insert into material (\r\n" + 
				"	cd_editora,\r\n" + 
				"	cd_entidade,\r\n" + 
				"	cd_local_publicacao,\r\n" + 
				"	tp_material,\r\n" + 
				"	tp_divulgacao,\r\n" + 
				"	nm_titulo,\r\n" + 
				"	ds_ano_producao,\r\n" + 
				"	ds_ano_publicacao,\r\n" + 
				"	ds_edicao,\r\n" + 
				"	nr_paginas,\r\n" + 
				"	ds_url_disponivel,\r\n" + 
				"	nr_isbn,\r\n" + 
				"	nr_issn\r\n" + 
				") values (\r\n" + 
				"	"+getIndexEditora(nm_editora)+",\r\n" +
				"	"+getIndexEntidade(nm_entidade, tp_entidade)+",\r\n" +
				"	"+getIndexLocalPublicacao(nm_local_publicacao)+",\r\n" +
				"	"+getIndexMaterial(ds_material)+",\r\n" +
				"	"+getIndexDivulgacao(ds_divulgacao)+",\r\n" +
				"	'"+nm_titulo+"',\r\n" + 
				"	'"+ds_ano_producao+"',\r\n" + 
				"	'"+ds_ano_publicacao+"',\r\n" + 
				"	'"+ds_edicao+"',\r\n" + 
				"	'"+nr_paginas+"',\r\n" + 
				"	'"+ds_url_disponivel+"',\r\n" + 
				"	"+nr_isbn+",\r\n" + 
				"	"+nr_issn+"\r\n" + 
				");"
				);
		for (int i = 0; i < nm_autor.length; i++) {
			query.execute("insert into autor (nm_autor) values ('"+nm_autor[i]+"');");
		}
		for (int i = 0; i < ds_palavra_chave.length; i++) {
			query.execute("insert into palavra_chave (ds_palavra_chave) values ('"+ds_palavra_chave[i]+"');");
		}
	}
	
	public int getIndexMaterial( String ds_material ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("Select tp_material from tipo_material where ds_material like '"+ds_material+"';");
		System.out.print(result.toString());
		if (  !result.first() ) {
			query.execute("Insert into tipo_material (ds_material) values ('"+ds_material+"');");
			result = query.executeQuery("Select tp_material from tipo_material where ds_material like '"+ds_material+"';");
			result.first();
			return result.getInt(1);
 		} else {
 			return result.getInt(1);
 		}
	}
	
	public int getIndexDivulgacao( String ds_divulgacao ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("Select tp_divulgacao from tipo_divulgacao where ds_divulgacao like '"+ds_divulgacao+"';");
		System.out.print(result.toString());
		if (  !result.first() ) {
			query.execute("Insert into tipo_divulgacao (ds_divulgacao) values ('"+ds_divulgacao+"');");
			result = query.executeQuery("Select tp_divulgacao from tipo_divulgacao where ds_divulgacao like '"+ds_divulgacao+"';");
			result.first();
			return result.getInt(1);
 		} else {
 			return result.getInt(1);
 		}
	}
	
	public int getIndexEntidade( String nm_entidade, String tp_entidade ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("Select cd_entidade from entidade where nm_entidade like '"+nm_entidade+"' and tp_entidade like '"+tp_entidade+"';");
		System.out.print(result.toString());
		if (  !result.first() ) {
			query.execute("Insert into entidade (nm_entidade, tp_entidade) values ('"+nm_entidade+"', '"+tp_entidade+"');");
			result = query.executeQuery("Select cd_entidade from entidade where nm_entidade like '"+nm_entidade+"' and tp_entidade like '"+tp_entidade+"';");
			result.first();
			return result.getInt(1);
 		} else {
 			return result.getInt(1);
 		}
	}
	
	public int getIndexLocalPublicacao( String nm_local_publicacao ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("Select cd_local_publicacao from local_publicacao where nm_local_publicacao like '"+nm_local_publicacao+"';");
		System.out.print(result.toString());
		if (  !result.first() ) {
			query.execute("Insert into local_publicacao (nm_local_publicacao) values ('"+nm_local_publicacao+"');");
			result = query.executeQuery("Select cd_local_publicacao from local_publicacao where nm_local_publicacao like '"+nm_local_publicacao+"';");
			result.first();
			return result.getInt(1);
 		} else {
 			return result.getInt(1);
 		}
	}
	
	public int getIndexEditora( String nm_editora ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("Select cd_editora from editora where nm_editora like '"+nm_editora+"';");
		System.out.print(result.toString());
		if (  !result.first() ) {
			query.execute("Insert into editora (nm_editora) values ('"+nm_editora+"');");
			result = query.executeQuery("Select cd_editora from editora where nm_editora like '"+nm_editora+"';");
			result.first();
			return result.getInt(1);
 		} else {
 			return result.getInt(1);
 		}
	}
	
}
