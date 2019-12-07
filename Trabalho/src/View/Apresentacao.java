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
		MontaPagina();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public Apresentacao(File arq) throws IOException {
		this.setArquivoTrabalhado(arq);
		leitor = new Leitura(arq);
		MontaPagina();
	}
	
	public void MontaPagina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 642, 290);
		contentPane.add(tabbedPane);
		
		JPanel panelAutor = new JPanel();
		tabbedPane.addTab("Autor", null, panelAutor, null);
		panelAutor.setLayout(null);
		
		JComboBox cbAutorSource = new JComboBox();
		cbAutorSource.setBounds(133, 63, 431, 20);
		panelAutor.add(cbAutorSource);
		
		JComboBox cbAutorTarget = new JComboBox();
		cbAutorTarget.setBounds(130, 136, 431, 20);
		panelAutor.add(cbAutorTarget);
		
		JLabel label_16 = new JLabel("Source");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_16.setBounds(57, 63, 66, 14);
		panelAutor.add(label_16);
		
		JLabel label_17 = new JLabel("Target");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_17.setBounds(57, 133, 63, 20);
		panelAutor.add(label_17);
		
		JPanel panelLocal = new JPanel();
		tabbedPane.addTab("Local de Publica\u00E7\u00E3o", null, panelLocal, null);
		panelLocal.setLayout(null);
		
		JComboBox cbLocalSource = new JComboBox();
		cbLocalSource.setBounds(137, 81, 431, 20);
		panelLocal.add(cbLocalSource);
		
		JComboBox cbLocalTarget = new JComboBox();
		cbLocalTarget.setBounds(137, 140, 431, 20);
		panelLocal.add(cbLocalTarget);
		
		JLabel label_10 = new JLabel("Source");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_10.setBounds(49, 88, 66, 14);
		panelLocal.add(label_10);
		
		JLabel label_11 = new JLabel("Target");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_11.setBounds(49, 140, 63, 20);
		panelLocal.add(label_11);
		
		JPanel panelEditora = new JPanel();
		tabbedPane.addTab("Editora", null, panelEditora, null);
		panelEditora.setLayout(null);
		
		JComboBox cbEditoraSource = new JComboBox();
		cbEditoraSource.setBounds(129, 65, 431, 20);
		panelEditora.add(cbEditoraSource);
		
		JComboBox cbEditoraTarget = new JComboBox();
		cbEditoraTarget.setBounds(129, 135, 431, 20);
		panelEditora.add(cbEditoraTarget);
		
		JLabel label_14 = new JLabel("Source");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_14.setBounds(53, 72, 66, 14);
		panelEditora.add(label_14);
		
		JLabel label_15 = new JLabel("Target");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_15.setBounds(53, 135, 63, 20);
		panelEditora.add(label_15);
		
		JPanel panelEntidade = new JPanel();
		tabbedPane.addTab("Entidade", null, panelEntidade, null);
		panelEntidade.setLayout(null);
		
		JComboBox cbEntidadeSource = new JComboBox();
		cbEntidadeSource.setBounds(181, 66, 312, 20);
		panelEntidade.add(cbEntidadeSource);
		
		JComboBox cbEntidadeTarget = new JComboBox();
		cbEntidadeTarget.setBounds(181, 122, 312, 20);
		panelEntidade.add(cbEntidadeTarget);
		
		JLabel label_12 = new JLabel("Source");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_12.setBounds(98, 66, 66, 14);
		panelEntidade.add(label_12);
		
		JLabel label_13 = new JLabel("Target");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_13.setBounds(98, 122, 63, 20);
		panelEntidade.add(label_13);
		
		
		JPanel panelPalChave = new JPanel();
		tabbedPane.addTab("Palavras Chave", null, panelPalChave, null);
		panelPalChave.setLayout(null);
		
		
		JComboBox cbPalChaveSource = new JComboBox();
		cbPalChaveSource.setBounds(140, 63, 431, 20);
		panelPalChave.add(cbPalChaveSource);
		
		JComboBox cbPalChaveTarget = new JComboBox();
		cbPalChaveTarget.setBounds(140, 156, 431, 20);
		panelPalChave.add(cbPalChaveTarget);
		
		JLabel label_8 = new JLabel("Source");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_8.setBounds(64, 63, 66, 14);
		panelPalChave.add(label_8);
		
		JLabel label_9 = new JLabel("Target");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_9.setBounds(64, 153, 63, 20);
		panelPalChave.add(label_9);
		
		JPanel panelMeioDiv = new JPanel();
		tabbedPane.addTab("Meio de Divulga\u00E7\u00E3o", null, panelMeioDiv, null);
		panelMeioDiv.setLayout(null);
		
		JComboBox cbMeioDivulgaçãoSource = new JComboBox();
		cbMeioDivulgaçãoSource.setBounds(140, 76, 431, 20);
		panelMeioDiv.add(cbMeioDivulgaçãoSource);
		
		JComboBox cbMeioDivulgaçãoTarget = new JComboBox();
		cbMeioDivulgaçãoTarget.setBounds(140, 128, 431, 20);
		panelMeioDiv.add(cbMeioDivulgaçãoTarget);
		
		JLabel lblSource = new JLabel("Source");
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSource.setBounds(32, 76, 66, 14);
		panelMeioDiv.add(lblSource);
		
		JLabel lblTarget = new JLabel("Target");
		lblTarget.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTarget.setBounds(32, 128, 63, 20);
		panelMeioDiv.add(lblTarget);
		
		JPanel panelTipoMaterial = new JPanel();
		tabbedPane.addTab("Tipo de Material", null, panelTipoMaterial, null);
		panelTipoMaterial.setLayout(null);
		
		JLabel label_1 = new JLabel("Source");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(51, 75, 66, 14);
		panelTipoMaterial.add(label_1);
		
		JComboBox cbTipoMaterialSource = new JComboBox();
		cbTipoMaterialSource.setBounds(159, 75, 431, 20);
		panelTipoMaterial.add(cbTipoMaterialSource);
		
		JLabel label_7 = new JLabel("Target");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBounds(51, 128, 63, 20);
		panelTipoMaterial.add(label_7);
		
		JComboBox cbTipoMaterialTarget = new JComboBox();
		cbTipoMaterialTarget.setBounds(159, 128, 431, 20);
		panelTipoMaterial.add(cbTipoMaterialTarget);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Persistencia p = new Persistencia();
				p.GravarObjetos(leitor);
			}
		});
		btnSalvar.setBounds(563, 318, 89, 23);
		contentPane.add(btnSalvar);
		
		for (TipoMaterial objeto: leitor.getTiposDeMaterial()) {
			cbTipoMaterialTarget.addItem(objeto);	
			cbTipoMaterialSource.addItem(objeto);
		}
		
		
		for (Entidade entidade: leitor.getEntidades()) {
			cbEntidadeSource.addItem(entidade);	
			cbEntidadeTarget.addItem(entidade);
		}
		
		for (Autor objeto: leitor.getAutores()) {
			cbAutorSource.addItem(objeto);	
			cbAutorTarget.addItem(objeto);
		}
		
		for (LocalPublicacao objeto: leitor.getLocais()) {
			cbLocalSource.addItem(objeto);	
			cbLocalTarget.addItem(objeto);	
		}
		
		for (Editora objeto: leitor.getEditoras()) {
			cbEditoraSource.addItem(objeto);
			cbEditoraTarget.addItem(objeto);
		}
		
		for (PalavraChave objeto: leitor.getPalavras()) {
			cbPalChaveSource.addItem(objeto);
			cbPalChaveTarget.addItem(objeto);
		}
		
		for (TipoDivulgacao objeto: leitor.getTiposDeDivulgacao()) {
			cbMeioDivulgaçãoSource.addItem(objeto);
			cbMeioDivulgaçãoTarget.addItem(objeto);			
		}
	}
}
