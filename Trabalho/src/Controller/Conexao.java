package Controller;
//
import java.sql.Statement;
import java.util.ArrayList;
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
			String[] nm_autor,
			String[] ds_palavra_chave, 
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
				bindAutores( getIndexAutores( nm_autor ) );
				bindPalavraChave( getIndexPalavrasChave( ds_palavra_chave ) );
		query.close();
				
	}
	
	public void bindAutores( ArrayList<Integer> cd_autor ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("select max(cd_material) from material;");
		result.first();
		int cd_material =  result.getInt(1);
		for (int i = 0; i < cd_autor.size(); i++) {
			result = query.executeQuery(
					"Select cd_autor, cd_material from material_autor where cd_material like '"+cd_material+"' and cd_autor like '"+cd_autor.get(i)+"';");
			if ( !result.first() ) {
				query.execute("Insert into material_autor (cd_material, cd_autor) values ('"+cd_material+"', '"+cd_autor.get(i)+"');");
			} 		
		}
	}
	
	public void bindPalavraChave( ArrayList<Integer> cd_palavra_chave ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("select max(cd_material) from material;");
		result.first();
		int cd_material =  result.getInt(1);
		for (int i = 0; i < cd_palavra_chave.size(); i++) {
			result = query.executeQuery(
					"Select cd_palavra_chave, cd_material from material_palavra_chave where cd_material like '"+cd_material+"' and cd_palavra_chave like '"+cd_palavra_chave.get(i)+"';");
			if ( !result.first() ) {
				query.execute("Insert into material_palavra_chave (cd_material, cd_palavra_chave) values ('"+cd_material+"', '"+cd_palavra_chave.get(i)+"');");
			} 		
		}
	}
	
	public ArrayList<Integer> getIndexAutores( String[] nm_autor) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		ArrayList<Integer> saida = new ArrayList<Integer>();
		for (int i = 0; i < nm_autor.length; i++) {
			result = query.executeQuery(
					"Select cd_autor from autor where nm_autor like '" + nm_autor[i] + "';");
			
			if (!result.first()) {
				query.execute("Insert into autor (nm_autor) values ('" + nm_autor[i] + "');");
				result = query.executeQuery(
						"Select cd_autor from autor where nm_autor like '" + nm_autor[i] + "';");
				result.first();
				saida.add(result.getInt(1));
			} else {
				saida.add(result.getInt(1));
			}
		}
		return saida ;
	}
	
	public ArrayList<Integer> getIndexPalavrasChave( String[] ds_palavra_chave) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		ArrayList<Integer> saida = new ArrayList<Integer>();
		for (int i = 0; i < ds_palavra_chave.length; i++) {
			result = query.executeQuery(
					"Select cd_palavra_chave from palavra_chave where ds_palavra_chave like '" + ds_palavra_chave[i] + "';");
			
			if (!result.first()) {
				query.execute("Insert into palavra_chave (ds_palavra_chave) values ('" + ds_palavra_chave[i] + "');");
				result = query.executeQuery(
						"Select cd_palavra_chave from palavra_chave where ds_palavra_chave like '" + ds_palavra_chave[i] + "';");
				result.first();
				saida.add(result.getInt(1));
			} else {
				saida.add(result.getInt(1));
			}
		}
		return saida ;
	}
	
	public int getIndexMaterial( String ds_material ) throws SQLException {
		query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("Select tp_material from tipo_material where ds_material like '"+ds_material+"';");
		
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
