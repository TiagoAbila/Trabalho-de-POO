package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import org.postgresql.*;

import Controller.Conexao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Apresentacao extends JFrame {	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apresentacao frame = new Apresentacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// Conexão com o BD
		Conexao teca = new Conexao();
		try {
			teca.setConnection("TECA", "postgres", "102030");
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getClass() + "\n" + sqle.getMessage(),null,JOptionPane.ERROR_MESSAGE);
		}
		// Testes
		try {
			teca.insertQuery(1, "teste");
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getClass() + "\n" + sqle.getMessage(),null,JOptionPane.ERROR_MESSAGE);
		}
		try {
			teca.selectQuery();
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, sqle.getClass() + "\n" + sqle.getMessage(),null,JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Create the frame.
	 */
	public Apresentacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	

}
