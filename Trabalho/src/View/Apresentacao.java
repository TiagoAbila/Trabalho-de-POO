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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

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
		panelMeioDiv.setLayout(null);
		
		JComboBox cbMeioDivulgaçãoSource = new JComboBox();
		cbMeioDivulgaçãoSource.setBounds(113, 35, 431, 20);
		panelMeioDiv.add(cbMeioDivulgaçãoSource);
		/*
		for (int i = 0; i < array.length; i++) {
			cbMeioDivulgaçãoSource.addItem();			
		}*/
		
		JComboBox cbMeioDivulgaçãoTarget = new JComboBox();
		cbMeioDivulgaçãoTarget.setBounds(124, 172, 431, 20);
		panelMeioDiv.add(cbMeioDivulgaçãoTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbMeioDivulgaçãoTarget.addItem();			
		}*/
		
		JLabel lblEquivaleA = new JLabel("Equivale a");
		lblEquivaleA.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEquivaleA.setBounds(293, 91, 96, 38);
		panelMeioDiv.add(lblEquivaleA);
		
		JPanel panelEntidade = new JPanel();
		tabbedPane.addTab("Entidade", null, panelEntidade, null);
		panelEntidade.setLayout(null);
		
		JComboBox cbEntidadeSource = new JComboBox();
		cbEntidadeSource.setBounds(190, 42, 312, 20);
		panelEntidade.add(cbEntidadeSource);
		/*
		for (int i = 0; i < array.length; i++) {
			cbEntidadeSource.addItem();			
		}*/
		
		JLabel label = new JLabel("Equivale a");
		label.setBounds(296, 101, 86, 23);
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panelEntidade.add(label);
		
		JComboBox cbEntidadeTarget = new JComboBox();
		cbEntidadeTarget.setBounds(190, 167, 312, 20);
		panelEntidade.add(cbEntidadeTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbEntidadeTarget.addItem();			
		}*/
		
		JPanel panelOrganizacao = new JPanel();
		tabbedPane.addTab("Organiza\u00E7\u00E3o", null, panelOrganizacao, null);
		panelOrganizacao.setLayout(null);
		
		JComboBox cbOrganizacaoSource = new JComboBox();
		cbOrganizacaoSource.setBounds(138, 45, 431, 20);
		panelOrganizacao.add(cbOrganizacaoSource);
		/*
		for (int i = 0; i < array.length; i++) {
			cbOrganizacaoSource.addItem();			
		}*/
		
		JLabel label_1 = new JLabel("Equivale a");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_1.setBounds(307, 103, 96, 38);
		panelOrganizacao.add(label_1);
		
		JComboBox cbOrganizacaoTarget = new JComboBox();
		cbOrganizacaoTarget.setBounds(138, 184, 431, 20);
		panelOrganizacao.add(cbOrganizacaoTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbOrganizacaoTarget.addItem();			
		}*/
		
		JPanel panelAutor = new JPanel();
		tabbedPane.addTab("Autor", null, panelAutor, null);
		panelAutor.setLayout(null);
		
		JComboBox cbAutorSource = new JComboBox();
		cbAutorSource.setBounds(133, 56, 431, 20);
		panelAutor.add(cbAutorSource);
		/*
		for (int i = 0; i < array.length; i++) {
			cbAutorSource.addItem();			
		}*/
		
		JLabel label_2 = new JLabel("Equivale a");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_2.setBounds(302, 114, 96, 38);
		panelAutor.add(label_2);
		
		JComboBox cbAutorTarget = new JComboBox();
		cbAutorTarget.setBounds(133, 195, 431, 20);
		panelAutor.add(cbAutorTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbAutorTarget.addItem();			
		}*/
		
		JPanel panelLocal = new JPanel();
		tabbedPane.addTab("Local de Publica\u00E7\u00E3o", null, panelLocal, null);
		panelLocal.setLayout(null);
		
		JComboBox cbLocalSource = new JComboBox();
		cbLocalSource.setBounds(131, 43, 431, 20);
		panelLocal.add(cbLocalSource);
		/*
		for (int i = 0; i < array.length; i++) {
			cbLocalSource.addItem();			
		}*/
		
		JLabel label_3 = new JLabel("Equivale a");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_3.setBounds(300, 101, 96, 38);
		panelLocal.add(label_3);
		
		JComboBox cbLocalTarget = new JComboBox();
		cbLocalTarget.setBounds(131, 182, 431, 20);
		panelLocal.add(cbLocalTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbLocalTarget.addItem();			
		}*/
		
		JPanel panelEditora = new JPanel();
		tabbedPane.addTab("Editora", null, panelEditora, null);
		panelEditora.setLayout(null);
		
		JComboBox cbEditoraSource = new JComboBox();
		cbEditoraSource.setBounds(129, 65, 431, 20);
		panelEditora.add(cbEditoraSource);
		/*
		for (int i = 0; i < array.length; i++) {
			cbEditoraSource.addItem();			
		}*/
		
		JLabel label_4 = new JLabel("Equivale a");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_4.setBounds(298, 123, 96, 38);
		panelEditora.add(label_4);
		
		JComboBox cbEditoraTarget = new JComboBox();
		cbEditoraTarget.setBounds(129, 204, 431, 20);
		panelEditora.add(cbEditoraTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbEditoraTarget.addItem();			
		}*/
		
		JPanel cbIssnSource = new JPanel();
		tabbedPane.addTab("ISSN", null, cbIssnSource, null);
		cbIssnSource.setLayout(null);
		/*
		for (int i = 0; i < array.length; i++) {
			cbIssnSource.addItem();			
		}*/
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setBounds(129, 56, 431, 20);
		cbIssnSource.add(comboBox_10);
		
		
		JLabel label_5 = new JLabel("Equivale a");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_5.setBounds(298, 114, 96, 38);
		cbIssnSource.add(label_5);
		
		JComboBox cbIssnTarget = new JComboBox();
		cbIssnTarget.setBounds(129, 195, 431, 20);
		cbIssnSource.add(cbIssnTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbIssnTarget.addItem();			
		}*/
		
		
		JPanel panelPalChave = new JPanel();
		tabbedPane.addTab("Palavras Chave", null, panelPalChave, null);
		panelPalChave.setLayout(null);
		
		
		JComboBox cbPalChaveSource = new JComboBox();
		cbPalChaveSource.setBounds(140, 63, 431, 20);
		panelPalChave.add(cbPalChaveSource);
		/*
		for (int i = 0; i < array.length; i++) {
			cbPalChaveSource.addItem();			
		}*/
		
		JLabel label_6 = new JLabel("Equivale a");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_6.setBounds(309, 121, 96, 38);
		panelPalChave.add(label_6);
		
		JComboBox cbPalChaveTarget = new JComboBox();
		cbPalChaveTarget.setBounds(140, 202, 431, 20);
		panelPalChave.add(cbPalChaveTarget);
		/*
		for (int i = 0; i < array.length; i++) {
			cbPalChaveTarget.addItem();			
		}*/
	}
}
