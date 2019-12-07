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
import javax.swing.JTextField;

public class Apresentacao extends JFrame {	
	private JPanel contentPane;
	private File arquivoTrabalhado;
	private Leitura leitor;
	private JTextField tfEntidadeNome;
	private JTextField tfEntidadeTipo;
	private JTextField tfMaterialTitulo;
	private JTextField tfMaterialAnoProd;
	private JTextField tfMaterialAnoPub;
	private JTextField tfMaterialUrl;
	private JTextField tfMaterialEdicao;
	private JTextField tfMaterialIsbn;
	private JTextField tfMaterialIssn;
	
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
		setBounds(100, 100, 749, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 713, 290);
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
		
		JButton btnAtualizarAutor = new JButton("Atualizar");
		btnAtualizarAutor.setBounds(279, 206, 89, 23);
		panelAutor.add(btnAtualizarAutor);
		
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
		
		JButton btnAtualizarLocalPublicacao = new JButton("Atualizar");
		btnAtualizarLocalPublicacao.setBounds(288, 209, 89, 23);
		panelLocal.add(btnAtualizarLocalPublicacao);
		
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
		
		JButton btnAtualizarEditora = new JButton("Atualizar");
		btnAtualizarEditora.setBounds(288, 209, 89, 23);
		panelEditora.add(btnAtualizarEditora);
		
		JPanel panelEntidade = new JPanel();
		tabbedPane.addTab("Entidade", null, panelEntidade, null);
		panelEntidade.setLayout(null);
		
		JComboBox cbEntidadeSource = new JComboBox();
		cbEntidadeSource.setBounds(152, 11, 312, 20);
		panelEntidade.add(cbEntidadeSource);
		
		JLabel lblEntidades = new JLabel("Entidades:");
		lblEntidades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEntidades.setBounds(41, 11, 113, 14);
		panelEntidade.add(lblEntidades);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(99, 60, 46, 14);
		panelEntidade.add(lblNome);
		
		tfEntidadeNome = new JTextField();
		tfEntidadeNome.setBounds(153, 57, 311, 20);
		panelEntidade.add(tfEntidadeNome);
		tfEntidadeNome.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(99, 103, 46, 14);
		panelEntidade.add(lblTipo);
		
		tfEntidadeTipo = new JTextField();
		tfEntidadeTipo.setBounds(152, 100, 312, 20);
		panelEntidade.add(tfEntidadeTipo);
		tfEntidadeTipo.setColumns(10);
		
		JButton btnObterDados = new JButton("Obter Dados");
		btnObterDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Entidade entidade = (Entidade) cbEntidadeSource.getSelectedItem();
				tfEntidadeNome.setText(entidade.getEntidade());
				tfEntidadeTipo.setText(entidade.getTipo());
			}
		});
		btnObterDados.setBounds(496, 10, 104, 23);
		panelEntidade.add(btnObterDados);
		
		JButton btnAtualizarEntidade = new JButton("Atualizar");
		btnAtualizarEntidade.setBounds(275, 209, 89, 23);
		panelEntidade.add(btnAtualizarEntidade);
		
		
		JPanel panelPalChave = new JPanel();
		tabbedPane.addTab("Palavras Chave", null, panelPalChave, null);
		panelPalChave.setLayout(null);
		
		
		JComboBox cbPalChaveSource = new JComboBox();
		cbPalChaveSource.setBounds(140, 63, 431, 20);
		panelPalChave.add(cbPalChaveSource);
		
		JComboBox cbPalChaveTarget = new JComboBox();
		cbPalChaveTarget.setBounds(140, 128, 431, 20);
		panelPalChave.add(cbPalChaveTarget);
		
		JLabel label_8 = new JLabel("Source");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_8.setBounds(64, 63, 66, 14);
		panelPalChave.add(label_8);
		
		JLabel label_9 = new JLabel("Target");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_9.setBounds(64, 125, 63, 20);
		panelPalChave.add(label_9);
		
		JButton btnAtualizarPalavrasChave = new JButton("Atualizar");
		btnAtualizarPalavrasChave.setBounds(302, 197, 89, 23);
		panelPalChave.add(btnAtualizarPalavrasChave);
		
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
		
		JButton btnAtualizarMeioDivulgacao = new JButton("Atualizar");
		btnAtualizarMeioDivulgacao.setBounds(298, 205, 89, 23);
		panelMeioDiv.add(btnAtualizarMeioDivulgacao);
		
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
		
		JButton btnAtualizarTipoMaterial = new JButton("Atualizar");
		btnAtualizarTipoMaterial.setBounds(300, 212, 89, 23);
		panelTipoMaterial.add(btnAtualizarTipoMaterial);
		
		JPanel panelMaterial = new JPanel();
		tabbedPane.addTab("Material", null, panelMaterial, null);
		panelMaterial.setLayout(null);
		
		JLabel lblMateriais = new JLabel("Materiais:");
		lblMateriais.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMateriais.setBounds(37, 12, 113, 14);
		panelMaterial.add(lblMateriais);
		
		JComboBox cbMaterial = new JComboBox();
		cbMaterial.setBounds(148, 12, 312, 20);
		panelMaterial.add(cbMaterial);
		
		JButton button = new JButton("Obter Dados");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Material material = (Material) cbMaterial.getSelectedItem();
				tfMaterialAnoProd.setText(material.getAnoProducao());
				tfMaterialAnoPub.setText(material.getAnoPublicacao());
				tfMaterialEdicao.setText(material.getEdicao());
				tfMaterialIsbn.setText(material.getNrISBN());
				tfMaterialIssn.setText(material.getNrISSN());
				tfMaterialTitulo.setText(material.getTitulo());
				tfMaterialUrl.setText(material.getUrlDisponivel());
			}
		});
		button.setBounds(492, 11, 104, 23);
		panelMaterial.add(button);
		
		tfMaterialTitulo = new JTextField();
		tfMaterialTitulo.setBounds(123, 56, 165, 20);
		panelMaterial.add(tfMaterialTitulo);
		tfMaterialTitulo.setColumns(10);
		
		tfMaterialAnoProd = new JTextField();
		tfMaterialAnoProd.setColumns(10);
		tfMaterialAnoProd.setBounds(488, 56, 165, 20);
		panelMaterial.add(tfMaterialAnoProd);
		
		tfMaterialAnoPub = new JTextField();
		tfMaterialAnoPub.setColumns(10);
		tfMaterialAnoPub.setBounds(123, 98, 165, 20);
		panelMaterial.add(tfMaterialAnoPub);
		
		tfMaterialUrl = new JTextField();
		tfMaterialUrl.setColumns(10);
		tfMaterialUrl.setBounds(488, 98, 165, 20);
		panelMaterial.add(tfMaterialUrl);
		
		tfMaterialEdicao = new JTextField();
		tfMaterialEdicao.setColumns(10);
		tfMaterialEdicao.setBounds(123, 140, 165, 20);
		panelMaterial.add(tfMaterialEdicao);
		
		tfMaterialIsbn = new JTextField();
		tfMaterialIsbn.setColumns(10);
		tfMaterialIsbn.setBounds(488, 140, 165, 20);
		panelMaterial.add(tfMaterialIsbn);
		
		tfMaterialIssn = new JTextField();
		tfMaterialIssn.setColumns(10);
		tfMaterialIssn.setBounds(123, 183, 165, 20);
		panelMaterial.add(tfMaterialIssn);
		
		JLabel lblNewLabel = new JLabel("T\u00EDtulo");
		lblNewLabel.setBounds(37, 59, 76, 14);
		panelMaterial.add(lblNewLabel);
		
		JLabel lblAnoProduo = new JLabel("Ano de Produ\u00E7\u00E3o");
		lblAnoProduo.setBounds(344, 59, 134, 14);
		panelMaterial.add(lblAnoProduo);
		
		JLabel lblAnoDePublicao = new JLabel("Ano de Publica\u00E7\u00E3o");
		lblAnoDePublicao.setBounds(10, 101, 103, 14);
		panelMaterial.add(lblAnoDePublicao);
		
		JLabel lblEdio = new JLabel("Edi\u00E7\u00E3o");
		lblEdio.setBounds(37, 143, 76, 14);
		panelMaterial.add(lblEdio);
		
		JLabel lblIssn = new JLabel("ISRN");
		lblIssn.setBounds(37, 186, 76, 14);
		panelMaterial.add(lblIssn);
		
		JLabel lblUrlDisponvel = new JLabel("Url Dispon\u00EDvel");
		lblUrlDisponvel.setBounds(344, 101, 134, 14);
		panelMaterial.add(lblUrlDisponvel);
		
		JLabel lblIssbn = new JLabel("ISBN");
		lblIssbn.setBounds(344, 143, 134, 14);
		panelMaterial.add(lblIssbn);
		
		JButton btnAtualizarMaterial = new JButton("Atualizar");
		btnAtualizarMaterial.setBounds(285, 214, 89, 23);
		panelMaterial.add(btnAtualizarMaterial);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Persistencia p = new Persistencia();
				p.GravarObjetos(leitor);
			}
		});
		btnSalvar.setBounds(563, 318, 89, 23);
		contentPane.add(btnSalvar);
		
		for (TipoMaterial tipoMaterial: leitor.getTiposDeMaterial()) {
			cbTipoMaterialTarget.addItem(tipoMaterial);	
			cbTipoMaterialSource.addItem(tipoMaterial);
		}
		
		
		for (Entidade entidade: leitor.getEntidades()) {
			cbEntidadeSource.addItem(entidade);	
		}
		
		for (Autor autor: leitor.getAutores()) {
			cbAutorSource.addItem(autor);	
			cbAutorTarget.addItem(autor);
		}
		
		for (LocalPublicacao local: leitor.getLocais()) {
			cbLocalSource.addItem(local);	
			cbLocalTarget.addItem(local);	
		}
		
		for (Editora editora: leitor.getEditoras()) {
			cbEditoraSource.addItem(editora);
			cbEditoraTarget.addItem(editora);
		}
		
		for (PalavraChave palavraChave: leitor.getPalavras()) {
			cbPalChaveSource.addItem(palavraChave);
			cbPalChaveTarget.addItem(palavraChave);
		}
		
		for (TipoDivulgacao divulgacao: leitor.getTiposDeDivulgacao()) {
			cbMeioDivulgaçãoSource.addItem(divulgacao);
			cbMeioDivulgaçãoTarget.addItem(divulgacao);			
		}
		
		for (Material material: leitor.getMaterias()) {
			cbMaterial.addItem(material);
		}
	}
}
