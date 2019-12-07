package Testes;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;
import Controller.Conexao;

class Testes {
	

	@Test
	void test() {
		Conexao teca = new Conexao();
		try {
			teca.setConnection("teca", "postgres", "102030");
			String[] keyword = {"Desenho", "Protagonista feminino", "Fantasia"};
			String[] autores = {"Mickey", "Pateta", "Mestre Spliter"};
			teca.insertIntoDB("Filme",
					"Cinema",
					"GNC",
					"Não Governamental",
					autores,
					keyword,
					"Disney",
					"JBC",
					"Mulan",
					"2017",
					"2018",
					"1a edição",
					"",
					"",
					"",
					""
			);
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getClass() + "\n" + sqle.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
