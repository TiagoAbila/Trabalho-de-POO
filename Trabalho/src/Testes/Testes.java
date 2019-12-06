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
			System.out.println( "Souza:" + teca.getIndexLocalPublicacao("Souza Cruz") );
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getClass() + "\n" + sqle.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
