package Controller;
//
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	// Atributos
	private Connection conexao;

	// Metodos
	public void setConnection(String db, String usr, String pass) throws SQLException {
		conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + db, usr, pass);
	}
	
	public void insertIntoDB(
			String ds_material,
			String ds_divulgacao,
			String nm_entidade,
			String tp_entidade, // Governamental ou ñ
			ArrayList<String> nm_autor,
			ArrayList<String> ds_palavra_chave, 
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
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery( "select cd_material from material " +
				"where "+
				"cd_editora = (Select cd_editora from editora where nm_editora like '"+nm_editora+"') and " + 
				"cd_entidade = (Select cd_entidade from entidade where nm_entidade like '"+nm_entidade+"' and tp_entidade like '"+tp_entidade+"') and " + 
				"cd_local_publicacao = (Select cd_local_publicacao from local_publicacao where nm_local_publicacao like '"+nm_local_publicacao+"') and " + 
				"tp_material = (Select tp_material from tipo_material where ds_material like '"+ds_material+"') and " + 
				"tp_divulgacao = (Select tp_divulgacao from tipo_divulgacao where ds_divulgacao like '"+ds_divulgacao+"') and " + 
				"nm_titulo like '"+nm_titulo+"' and " + 
				"ds_ano_producao like '"+ds_ano_producao+"' and " + 
				"ds_ano_publicacao like '"+ds_ano_publicacao+"' and " + 
				"ds_edicao like '"+ds_edicao+"' and " + 
				"nr_paginas like '"+nr_paginas+"' and " + 
				"ds_url_disponivel like '"+ds_url_disponivel+"' and " + 
				"nr_isbn like '"+nr_isbn+"' and " + 
				"nr_issn like '"+nr_issn+"';"
		);
		if ( !result.first() ) {
			query.execute( 
					"insert into material ( " + 
					"cd_editora, " + 
					"cd_entidade, " + 
					"cd_local_publicacao, " + 
					"tp_material, " + 
					"tp_divulgacao, " + 
					"nm_titulo, " + 
					"ds_ano_producao, " + 
					"ds_ano_publicacao, " + 
					"ds_edicao, " + 
					"nr_paginas, " + 
					"ds_url_disponivel, " + 
					"nr_isbn, " + 
					"nr_issn " + 
					") values (" + 
					getIndexEditora(nm_editora) + ", " +
					getIndexEntidade(nm_entidade, tp_entidade) + ", " +
					getIndexLocalPublicacao(nm_local_publicacao) + ", " +
					getIndexMaterial(ds_material) + ", " +
					getIndexDivulgacao(ds_divulgacao) + ", '" +
					nm_titulo + "', '" + 
					ds_ano_producao + "', '" + 
					ds_ano_publicacao + "', '" + 
					ds_edicao + "', '" + 
					nr_paginas + "', '" + 
					ds_url_disponivel + "', '" + 
					nr_isbn + "', '" + 
					nr_issn + 
					"' );"
			);
			bindAutores( getIndexAutores( nm_autor ) );
			bindPalavraChave( getIndexPalavrasChave( ds_palavra_chave ) );
			query.close();
			result.close();
		} else {
			JOptionPane.showMessageDialog(null, "Registro idêntico já cadastrado, inserção cancelada.");
		}
	}
	
	private void bindAutores( ArrayList<Integer> cd_autor ) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("select max(cd_material) from material;");
		result.first();
		int cd_material =  result.getInt(1);
		for (int i = 0; i < cd_autor.size(); i++) {
			result = query.executeQuery(
					"Select cd_autor, cd_material from material_autor where cd_material = '"+cd_material+"' and cd_autor = '"+cd_autor.get(i)+"';");
			if ( !result.first() ) {
				query.execute("Insert into material_autor (cd_material, cd_autor) values ('"+cd_material+"', '"+cd_autor.get(i)+"');");
			} 		
		}
		query.close();
		result.close();
	}
	
	private void bindPalavraChave( ArrayList<Integer> cd_palavra_chave ) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result;
		result = query.executeQuery("select max(cd_material) from material;");
		result.first();
		int cd_material =  result.getInt(1);
		for (int i = 0; i < cd_palavra_chave.size(); i++) {
			result = query.executeQuery(
					"Select cd_palavra_chave, cd_material from material_palavra_chave where cd_material = '"+cd_material+"' and cd_palavra_chave = '"+cd_palavra_chave.get(i)+"';");
			if ( !result.first() ) {
				query.execute("Insert into material_palavra_chave (cd_material, cd_palavra_chave) values ('"+cd_material+"', '"+cd_palavra_chave.get(i)+"');");
			} 		
		}
		query.close();
		result.close();
	}
	
	private ArrayList<Integer> getIndexAutores( ArrayList<String> nm_autor) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = null;
		ArrayList<Integer> saida = new ArrayList<Integer>();
		for (int i = 0; i < nm_autor.size(); i++) {
			result = query.executeQuery(
					"Select cd_autor from autor where nm_autor like '" + nm_autor.get(i) + "';");
			
			if (!result.first()) {
				query.execute("Insert into autor (nm_autor) values ('" + nm_autor.get(i) + "');");
				result = query.executeQuery(
						"Select cd_autor from autor where nm_autor like '" + nm_autor.get(i) + "';");
				result.first();
				saida.add(result.getInt(1));
			} else {
				saida.add(result.getInt(1));
			}
		}
		query.close();
		result.close();
		return saida ;
	}
	
	private ArrayList<Integer> getIndexPalavrasChave( ArrayList<String> ds_palavra_chave) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = null;
		ArrayList<Integer> saida = new ArrayList<Integer>();
		for (int i = 0; i < ds_palavra_chave.size(); i++) {
			result = query.executeQuery(
					"Select cd_palavra_chave from palavra_chave where ds_palavra_chave like '" + ds_palavra_chave.get(i) + "';");
			
			if (!result.first()) {
				query.execute("Insert into palavra_chave (ds_palavra_chave) values ('" + ds_palavra_chave.get(i) + "');");
				result = query.executeQuery(
						"Select cd_palavra_chave from palavra_chave where ds_palavra_chave like '" + ds_palavra_chave.get(i) + "';");
				result.first();
				saida.add(result.getInt(1));
			} else {
				saida.add(result.getInt(1));
			}
		}
		query.close();
		result.close();
		return saida ;
	}
	
	private int getIndexMaterial( String ds_material ) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	
	private int getIndexDivulgacao( String ds_divulgacao ) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	
	private int getIndexEntidade( String nm_entidade, String tp_entidade ) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	
	private int getIndexLocalPublicacao( String nm_local_publicacao ) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	
	private int getIndexEditora( String nm_editora ) throws SQLException {
		Statement query  = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
