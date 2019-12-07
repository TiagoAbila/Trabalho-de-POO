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
import Controller.Persistencia;
import Model.*;

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
	private Leitura leitor;
	
	public File getArquivoTrabalhado() {
		return arquivoTrabalhado;
	}

	public void setArquivoTrabalhado(File arquivoTrabalhado) {
		this.arquivoTrabalhado = arquivoTrabalhado;
	}
	
	public Apresentacao(Leitura leitura) {
		leitor = leitura;
	}

	public Apresentacao(File arq) throws IOException {
		this.setArquivoTrabalhado(arq);
		leitor = new Leitura(arq);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 764, 440);
		contentPane.add(tabbedPane);
		
		JPanel panelAutor = new JPanel();
		tabbedPane.addTab("Autor", null, panelAutor, null);
		panelAutor.setLayout(null);
		
		JComboBox cbAutorSource = new JComboBox();
		cbAutorSource.setBounds(133, 56, 431, 20);
		panelAutor.add(cbAutorSource);
		
		JLabel label_2 = new JLabel("Equivale a");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_2.setBounds(302, 114, 96, 38);
		panelAutor.add(label_2);
		
		JComboBox cbAutorTarget = new JComboBox();
		cbAutorTarget.setBounds(133, 195, 431, 20);
		panelAutor.add(cbAutorTarget);
		
		JPanel panelEditora = new JPanel();
		tabbedPane.addTab("Editora", null, panelEditora, null);
		panelEditora.setLayout(null);
		
		JComboBox cbEditoraSource = new JComboBox();
		cbEditoraSource.setBounds(129, 65, 431, 20);
		panelEditora.add(cbEditoraSource);
		
		JLabel label_4 = new JLabel("Equivale a");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_4.setBounds(298, 123, 96, 38);
		panelEditora.add(label_4);
		
		JComboBox cbEditoraTarget = new JComboBox();
		cbEditoraTarget.setBounds(129, 204, 431, 20);
		panelEditora.add(cbEditoraTarget);
		
		JPanel panelEntidade = new JPanel();
		tabbedPane.addTab("Entidade", null, panelEntidade, null);
		panelEntidade.setLayout(null);
		
		JComboBox cbEntidadeSource = new JComboBox();
		cbEntidadeSource.setBounds(190, 42, 312, 20);
		panelEntidade.add(cbEntidadeSource);
		
		JLabel label = new JLabel("Equivale a");
		label.setBounds(296, 101, 86, 23);
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panelEntidade.add(label);
		
		JComboBox cbEntidadeTarget = new JComboBox();
		cbEntidadeTarget.setBounds(190, 167, 312, 20);
		panelEntidade.add(cbEntidadeTarget);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(148, 217, 431, 20);
		panelEntidade.add(comboBox);
		
		JLabel label_5 = new JLabel("Equivale a");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_5.setBounds(317, 275, 96, 38);
		panelEntidade.add(label_5);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(148, 356, 431, 20);
		panelEntidade.add(comboBox_1);
		
		JLabel lblEntidade = new JLabel("Entidade");
		lblEntidade.setBounds(62, 109, 46, 14);
		panelEntidade.add(lblEntidade);
		
		JLabel lblIssn = new JLabel("ISSN");
		lblIssn.setBounds(62, 291, 46, 14);
		panelEntidade.add(lblIssn);
		
		JPanel panelLocal = new JPanel();
		tabbedPane.addTab("Local de Publica\u00E7\u00E3o", null, panelLocal, null);
		panelLocal.setLayout(null);
		
		JComboBox cbLocalSource = new JComboBox();
		cbLocalSource.setBounds(131, 43, 431, 20);
		panelLocal.add(cbLocalSource);
		
		JLabel label_3 = new JLabel("Equivale a");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_3.setBounds(300, 101, 96, 38);
		panelLocal.add(label_3);
		
		JComboBox cbLocalTarget = new JComboBox();
		cbLocalTarget.setBounds(131, 182, 431, 20);
		panelLocal.add(cbLocalTarget);
		
		
		JPanel panelPalChave = new JPanel();
		tabbedPane.addTab("Palavras Chave", null, panelPalChave, null);
		panelPalChave.setLayout(null);
		
		
		JComboBox cbPalChaveSource = new JComboBox();
		cbPalChaveSource.setBounds(140, 63, 431, 20);
		panelPalChave.add(cbPalChaveSource);
		
		JLabel label_6 = new JLabel("Equivale a");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_6.setBounds(309, 121, 96, 38);
		panelPalChave.add(label_6);
		
		JComboBox cbPalChaveTarget = new JComboBox();
		cbPalChaveTarget.setBounds(140, 202, 431, 20);
		panelPalChave.add(cbPalChaveTarget);
		
		JPanel panelMeioDiv = new JPanel();
		tabbedPane.addTab("Meio de Divulga\u00E7\u00E3o", null, panelMeioDiv, null);
		panelMeioDiv.setLayout(null);
		
		JComboBox cbMeioDivulgaçãoSource = new JComboBox();
		cbMeioDivulgaçãoSource.setBounds(212, 31, 431, 20);
		panelMeioDiv.add(cbMeioDivulgaçãoSource);
		for (TipoDivulgacao objeto: leitor.getTiposDeDivulgacao()) {
			cbMeioDivulgaçãoSource.addItem(objeto);			
		}
		
		JComboBox cbMeioDivulgaçãoTarget = new JComboBox();
		cbMeioDivulgaçãoTarget.setBounds(212, 163, 431, 20);
		panelMeioDiv.add(cbMeioDivulgaçãoTarget);
		for (TipoDivulgacao objeto: leitor.getTiposDeDivulgacao()) {
			cbMeioDivulgaçãoTarget.addItem(objeto);			
		}
		
		JLabel lblEquivaleA = new JLabel("Equivale a");
		lblEquivaleA.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEquivaleA.setBounds(293, 91, 96, 38);
		panelMeioDiv.add(lblEquivaleA);
		
		JLabel lblSource = new JLabel("Source");
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSource.setBounds(104, 31, 66, 14);
		panelMeioDiv.add(lblSource);
		
		JLabel lblTarget = new JLabel("Target");
		lblTarget.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTarget.setBounds(104, 163, 63, 20);
		panelMeioDiv.add(lblTarget);
		
		JPanel panelTipoMaterial = new JPanel();
		tabbedPane.addTab("Tipo de Material", null, panelTipoMaterial, null);
		panelTipoMaterial.setLayout(null);
		
		JLabel label_1 = new JLabel("Source");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(90, 30, 66, 14);
		panelTipoMaterial.add(label_1);
		
		JComboBox cbTipoMaterialSource = new JComboBox();
		cbTipoMaterialSource.setBounds(198, 30, 431, 20);
		panelTipoMaterial.add(cbTipoMaterialSource);
		
		JLabel label_7 = new JLabel("Target");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBounds(90, 162, 63, 20);
		panelTipoMaterial.add(label_7);
		
		JComboBox cbTipoMaterialTarget = new JComboBox();
		cbTipoMaterialTarget.setBounds(198, 162, 431, 20);
		panelTipoMaterial.add(cbTipoMaterialTarget);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Persistencia p = new Persistencia();
				p.GravarObjetos(leitor);
			}
		});
		btnSalvar.setBounds(672, 462, 89, 23);
		contentPane.add(btnSalvar);
		
		for (TipoMaterial objeto: leitor.getTiposDeMaterial()) {
			cbTipoMaterialTarget.addItem(objeto);			
		}
		
		for (TipoMaterial objeto: leitor.getTiposDeMaterial()) {
			cbTipoMaterialSource.addItem(objeto);			
		}
		
		for (Entidade entidade: leitor.getEntidades()) {
			cbEntidadeSource.addItem(entidade);			
		}
		for (Entidade entidade: leitor.getEntidades()) {
			cbEntidadeTarget.addItem(entidade);			
		}
		
		for (Autor objeto: leitor.getAutores()) {
			cbAutorSource.addItem(objeto);			
		}
		for (Autor objeto: leitor.getAutores()) {
			cbAutorTarget.addItem(objeto);			
		}
		for (LocalPublicacao objeto: leitor.getLocais()) {
			cbLocalSource.addItem(objeto);			
		}
		for (LocalPublicacao objeto: leitor.getLocais()) {
			cbLocalTarget.addItem(objeto);			
		}
		for (Editora objeto: leitor.getEditoras()) {
			cbEditoraSource.addItem(objeto);			
		}
		for (Editora objeto: leitor.getEditoras()) {
			cbEditoraTarget.addItem(objeto);			
		}
		
		for (PalavraChave objeto: leitor.getPalavras()) {
			cbPalChaveSource.addItem(objeto);			
		}
		for (PalavraChave objeto: leitor.getPalavras()) {
			cbPalChaveTarget.addItem(objeto);			
		}
	}
}
