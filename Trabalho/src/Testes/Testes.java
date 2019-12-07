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
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getClass() + "\n" + sqle.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
