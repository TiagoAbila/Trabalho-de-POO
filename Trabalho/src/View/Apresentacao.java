package View;
//
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import org.postgresql.*;

import Controller.Conexao;
import Controller.Leitura;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Apresentacao extends JFrame {	
	private JPanel contentPane;
	private File arquivoTrabalhado;
	
	
	public File getArquivoTrabalhado() {
		return arquivoTrabalhado;
	}

	public void setArquivoTrabalhado(File arquivoTrabalhado) {
		this.arquivoTrabalhado = arquivoTrabalhado;
	}

	public Apresentacao(File arq) throws IOException {
		this.setArquivoTrabalhado(arq);
		Leitura contrador = new Leitura(arq);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 764, 440);
		contentPane.add(tabbedPane);
		
		JPanel panelTipo = new JPanel();
		tabbedPane.addTab("Tipo de Material", null, panelTipo, null);
		panelTipo.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 739, 351);
		panelTipo.add(textPane);		
		
		JPanel panelMeioDiv = new JPanel();
		tabbedPane.addTab("Meio de Divulga\u00E7\u00E3o", null, panelMeioDiv, null);
		
		JPanel panelEntidade = new JPanel();
		tabbedPane.addTab("Entidade", null, panelEntidade, null);
		
		JPanel panelOrganizacao = new JPanel();
		tabbedPane.addTab("Organiza\u00E7\u00E3o", null, panelOrganizacao, null);
		
		JPanel panelAutor = new JPanel();
		tabbedPane.addTab("Autor", null, panelAutor, null);
		
		JPanel panelLocal = new JPanel();
		tabbedPane.addTab("Local de Publica\u00E7\u00E3o", null, panelLocal, null);
		
		JPanel panelEditora = new JPanel();
		tabbedPane.addTab("Editora", null, panelEditora, null);
		
		JPanel panelISSN = new JPanel();
		tabbedPane.addTab("ISSN", null, panelISSN, null);
		
		JPanel panelPalChave = new JPanel();
		tabbedPane.addTab("Palavras Chave", null, panelPalChave, null);
	}
}
