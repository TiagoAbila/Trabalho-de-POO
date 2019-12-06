package Testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import Controller.Conexao;

class Testes {
	

	@Test
	void test() {
		Conexao teca = new Conexao();
		try {
			teca.setConnection("teca", "postgres", "102030");
			teca.getIndexMaterial("sua mae");
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getClass() + "\n" + sqle.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
