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
import java.util.ArrayList;

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
	private JTextField tfNrPaginas;
	private JTextField tfAutorSource;
	private JTextField tfEditarLocal;
	private JTextField tfEditarEditora;
	private JTextField tfEditarPalavraChave;
	private JTextField tfEditarMeioDivulgacao;
	private JTextField tfEditarTipoMaterial;

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
		setBounds(100, 100, 749, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 713, 315);
		contentPane.add(tabbedPane);

		JPanel panelLocal = new JPanel();
		tabbedPane.addTab("Local de Publica\u00E7\u00E3o", null, panelLocal, null);
		panelLocal.setLayout(null);

		JComboBox cbLocalSource = new JComboBox();
		cbLocalSource.setBounds(137, 57, 431, 20);
		panelLocal.add(cbLocalSource);

		JComboBox cbLocalTarget = new JComboBox();
		cbLocalTarget.setBounds(137, 140, 431, 20);
		panelLocal.add(cbLocalTarget);

		JLabel label_10 = new JLabel("Source");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_10.setBounds(49, 64, 66, 14);
		panelLocal.add(label_10);

		JLabel label_11 = new JLabel("Target");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_11.setBounds(49, 140, 63, 20);
		panelLocal.add(label_11);

		JButton btnAtualizarLocalPublicacao = new JButton("Substituir");
		btnAtualizarLocalPublicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					leitor.Substituir((LocalPublicacao) cbLocalSource.getSelectedItem(),
							(LocalPublicacao) cbLocalTarget.getSelectedItem());
					JOptionPane.showMessageDialog(contentPane, "Substituição feita com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao fazer a substuição: " + e.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnAtualizarLocalPublicacao.setBounds(183, 184, 89, 23);
		panelLocal.add(btnAtualizarLocalPublicacao);

		tfEditarLocal = new JTextField();
		tfEditarLocal.setColumns(10);
		tfEditarLocal.setBounds(137, 88, 431, 20);
		panelLocal.add(tfEditarLocal);

		JButton button_1 = new JButton("Editar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				try {
					((LocalPublicacao) cbLocalSource.getSelectedItem()).setLocalPublicacao(tfEditarLocal.getText());
					JOptionPane.showMessageDialog(contentPane,
					        "Edição feita com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao editar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_1.setBounds(421, 184, 89, 23);
		panelLocal.add(button_1);

		JPanel panelAutor = new JPanel();
		tabbedPane.addTab("Autor", null, panelAutor, null);
		panelAutor.setLayout(null);

		JComboBox cbAutorSource = new JComboBox();
		cbAutorSource.setBounds(133, 63, 431, 20);
		panelAutor.add(cbAutorSource);

		JComboBox cbAutorTarget = new JComboBox();
		cbAutorTarget.setBounds(130, 155, 431, 20);
		panelAutor.add(cbAutorTarget);

		JLabel label_16 = new JLabel("Source");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_16.setBounds(57, 63, 66, 14);
		panelAutor.add(label_16);

		JLabel label_17 = new JLabel("Target");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_17.setBounds(57, 152, 63, 20);
		panelAutor.add(label_17);

		JButton btnAtualizarAutor = new JButton("Substituir");

		btnAtualizarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					leitor.Substituir((Autor) cbAutorSource.getSelectedItem(), (Autor) cbAutorTarget.getSelectedItem());
					JOptionPane.showMessageDialog(contentPane, "Substituição feita com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao fazer a substuição: " + e.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAtualizarAutor.setBounds(173, 206, 89, 23);
		panelAutor.add(btnAtualizarAutor);

		tfAutorSource = new JTextField();
		tfAutorSource.setBounds(133, 90, 431, 20);
		panelAutor.add(tfAutorSource);
		tfAutorSource.setColumns(10);

		JButton btnEditarAutor = new JButton("Editar");
		btnEditarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					((Autor) cbAutorSource.getSelectedItem()).setAutor(tfAutorSource.getText());
					JOptionPane.showMessageDialog(contentPane,
					        "Edição feita com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao editar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditarAutor.setBounds(365, 206, 89, 23);
		panelAutor.add(btnEditarAutor);

		JPanel panelEditora = new JPanel();
		tabbedPane.addTab("Editora", null, panelEditora, null);
		panelEditora.setLayout(null);

		JComboBox cbEditoraSource = new JComboBox();
		cbEditoraSource.setBounds(129, 65, 431, 20);
		panelEditora.add(cbEditoraSource);

		JComboBox cbEditoraTarget = new JComboBox();
		cbEditoraTarget.setBounds(129, 161, 431, 20);
		panelEditora.add(cbEditoraTarget);

		JLabel label_14 = new JLabel("Source");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_14.setBounds(53, 72, 66, 14);
		panelEditora.add(label_14);

		JLabel label_15 = new JLabel("Target");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_15.setBounds(53, 161, 63, 20);
		panelEditora.add(label_15);

		JButton btnAtualizarEditora = new JButton("Substituir");
		btnAtualizarEditora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor.Substituir((Editora) cbEditoraSource.getSelectedItem(),
							(Editora) cbEditoraTarget.getSelectedItem());
					JOptionPane.showMessageDialog(contentPane, "Substituição feita com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao Fazer a substuição: " + erro.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAtualizarEditora.setBounds(165, 209, 89, 23);
		panelEditora.add(btnAtualizarEditora);

		tfEditarEditora = new JTextField();
		tfEditarEditora.setColumns(10);
		tfEditarEditora.setBounds(129, 93, 431, 20);
		panelEditora.add(tfEditarEditora);

		JButton btnEditarEditora = new JButton("Editar");
		btnEditarEditora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					((Editora) cbEditoraSource.getSelectedItem()).setEditora(tfEditarEditora.getText());
					JOptionPane.showMessageDialog(contentPane,
					        "Edição feita com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao fazer ao editar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditarEditora.setBounds(423, 209, 89, 23);
		panelEditora.add(btnEditarEditora);

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
		btnAtualizarEntidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					((Entidade) cbEntidadeSource.getSelectedItem()).setEntidade(tfEntidadeNome.getText());
					((Entidade) cbEntidadeSource.getSelectedItem()).setTipo(tfEntidadeTipo.getText());
					JOptionPane.showMessageDialog(contentPane,
					        "Atualização feita com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao Atualizar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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

		JButton btnAtualizarPalavrasChave = new JButton("Substituir");
		btnAtualizarPalavrasChave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor.Substituir((PalavraChave) cbPalChaveSource.getSelectedItem(),
							(PalavraChave) cbPalChaveTarget.getSelectedItem());
					JOptionPane.showMessageDialog(contentPane, "Substituição feita com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao fazer a substuição: " + erro.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAtualizarPalavrasChave.setBounds(186, 186, 89, 23);
		panelPalChave.add(btnAtualizarPalavrasChave);

		tfEditarPalavraChave = new JTextField();
		tfEditarPalavraChave.setColumns(10);
		tfEditarPalavraChave.setBounds(140, 94, 431, 20);
		panelPalChave.add(tfEditarPalavraChave);

		JButton btnEditarPalavraChave = new JButton("Editar");
		btnEditarPalavraChave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				try {
					((PalavraChave) cbPalChaveSource.getSelectedItem()).setPalavraChave(tfEditarPalavraChave.getText());
					JOptionPane.showMessageDialog(contentPane,
					        "Edição feita com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao editar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditarPalavraChave.setBounds(379, 186, 89, 23);
		panelPalChave.add(btnEditarPalavraChave);

		JPanel panelMeioDiv = new JPanel();
		tabbedPane.addTab("Meio de Divulga\u00E7\u00E3o", null, panelMeioDiv, null);
		panelMeioDiv.setLayout(null);

		JComboBox cbMeioDivulgacaoSource = new JComboBox();
		cbMeioDivulgacaoSource.setBounds(140, 59, 431, 20);
		panelMeioDiv.add(cbMeioDivulgacaoSource);

		JComboBox cbMeioDivulgacaoTarget = new JComboBox();
		cbMeioDivulgacaoTarget.setBounds(140, 147, 431, 20);
		panelMeioDiv.add(cbMeioDivulgacaoTarget);

		JLabel lblSource = new JLabel("Source");
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSource.setBounds(32, 59, 66, 14);
		panelMeioDiv.add(lblSource);

		JLabel lblTarget = new JLabel("Target");
		lblTarget.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTarget.setBounds(32, 147, 63, 20);
		panelMeioDiv.add(lblTarget);

		JButton btnAtualizarMeioDivulgacao = new JButton("Substituir");
		btnAtualizarMeioDivulgacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor.Substituir((TipoDivulgacao) cbMeioDivulgacaoSource.getSelectedItem(),
							(TipoDivulgacao) cbMeioDivulgacaoTarget.getSelectedItem());
					JOptionPane.showMessageDialog(contentPane, "Substituição feita com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao fazer a substuição: " + erro.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAtualizarMeioDivulgacao.setBounds(182, 194, 89, 23);
		panelMeioDiv.add(btnAtualizarMeioDivulgacao);

		tfEditarMeioDivulgacao = new JTextField();
		tfEditarMeioDivulgacao.setColumns(10);
		tfEditarMeioDivulgacao.setBounds(140, 90, 431, 20);
		panelMeioDiv.add(tfEditarMeioDivulgacao);

		JButton btnEditarMeioDivulgacao = new JButton("Editar");
		btnEditarMeioDivulgacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					((TipoDivulgacao) cbMeioDivulgacaoSource.getSelectedItem())
					.setTipoDivulgacao(tfEditarMeioDivulgacao.getText());
					JOptionPane.showMessageDialog(contentPane,
					        "Edição feita com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao editar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditarMeioDivulgacao.setBounds(427, 194, 89, 23);
		panelMeioDiv.add(btnEditarMeioDivulgacao);

		JPanel panelTipoMaterial = new JPanel();
		tabbedPane.addTab("Tipo de Material", null, panelTipoMaterial, null);
		panelTipoMaterial.setLayout(null);

		JLabel label_1 = new JLabel("Source");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(51, 64, 66, 14);
		panelTipoMaterial.add(label_1);

		JComboBox cbTipoMaterialSource = new JComboBox();
		cbTipoMaterialSource.setBounds(159, 64, 431, 20);
		panelTipoMaterial.add(cbTipoMaterialSource);

		JLabel label_7 = new JLabel("Target");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBounds(51, 148, 63, 20);
		panelTipoMaterial.add(label_7);

		JComboBox cbTipoMaterialTarget = new JComboBox();
		cbTipoMaterialTarget.setBounds(159, 148, 431, 20);
		panelTipoMaterial.add(cbTipoMaterialTarget);

		JButton btnAtualizarTipoMaterial = new JButton("Substituir");
		btnAtualizarTipoMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor.Substituir((TipoMaterial) cbTipoMaterialSource.getSelectedItem(),
							(TipoMaterial) cbTipoMaterialTarget.getSelectedItem());
					JOptionPane.showMessageDialog(contentPane, "Substituição feita com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao fazer a substuição: " + erro.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAtualizarTipoMaterial.setBounds(217, 200, 89, 23);
		panelTipoMaterial.add(btnAtualizarTipoMaterial);

		tfEditarTipoMaterial = new JTextField();
		tfEditarTipoMaterial.setColumns(10);
		tfEditarTipoMaterial.setBounds(159, 96, 431, 20);
		panelTipoMaterial.add(tfEditarTipoMaterial);

		JButton btnEditarTipoMaterial = new JButton("Editar");
		btnEditarTipoMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					((TipoMaterial) cbTipoMaterialSource.getSelectedItem()).setTipoMaterial(tfEditarTipoMaterial.getText());
					JOptionPane.showMessageDialog(contentPane,
					        "Edição feita com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao editar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditarTipoMaterial.setBounds(440, 200, 89, 23);
		panelTipoMaterial.add(btnEditarTipoMaterial);

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

		JLabel lblIssn = new JLabel("ISSN");
		lblIssn.setBounds(37, 186, 76, 14);
		panelMaterial.add(lblIssn);

		JLabel lblUrlDisponvel = new JLabel("Url Dispon\u00EDvel");
		lblUrlDisponvel.setBounds(344, 101, 134, 14);
		panelMaterial.add(lblUrlDisponvel);

		JLabel lblIssbn = new JLabel("ISBN");
		lblIssbn.setBounds(344, 143, 134, 14);
		panelMaterial.add(lblIssbn);

		JButton btnAtualizarMaterial = new JButton("Atualizar");
		btnAtualizarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor.Substituir((Material) cbMaterial.getSelectedItem(), tfMaterialTitulo.getText(),
							tfMaterialAnoProd.getText(), tfMaterialAnoPub.getText(), tfMaterialUrl.getText(),
							tfNrPaginas.getText(), tfMaterialEdicao.getText(), tfMaterialIsbn.getText(),
							tfMaterialIssn.getText());
					JOptionPane.showMessageDialog(contentPane, "Atualização feita com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao Atualizar: " + erro.getMessage(), "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAtualizarMaterial.setBounds(288, 231, 89, 23);
		panelMaterial.add(btnAtualizarMaterial);

		tfNrPaginas = new JTextField();
		tfNrPaginas.setColumns(10);
		tfNrPaginas.setBounds(492, 183, 165, 20);
		panelMaterial.add(tfNrPaginas);

		JLabel lblnrPaginas = new JLabel("Nr de Paginas");
		lblnrPaginas.setBounds(344, 186, 76, 14);
		panelMaterial.add(lblnrPaginas);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				try {
					Persistencia p = new Persistencia();
					p.GravarObjetos(leitor);
					JOptionPane.showMessageDialog(contentPane,
					        "Alterações salvas com sucesso",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane,
					        "Erro ao salvar: "+erro.getMessage(),
					        "",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(535, 337, 89, 23);
		contentPane.add(btnSalvar);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnviarParaBD();
			}
		});
		btnEnviar.setBounds(634, 337, 89, 23);
		contentPane.add(btnEnviar);

		for (TipoMaterial tipoMaterial : leitor.getTiposDeMaterial()) {
			cbTipoMaterialTarget.addItem(tipoMaterial);
			cbTipoMaterialSource.addItem(tipoMaterial);
		}

		for (Entidade entidade : leitor.getEntidades()) {
			cbEntidadeSource.addItem(entidade);
		}

		for (Autor autor : leitor.getAutores()) {
			cbAutorSource.addItem(autor);
			cbAutorTarget.addItem(autor);
		}

		for (LocalPublicacao local : leitor.getLocais()) {
			cbLocalSource.addItem(local);
			cbLocalTarget.addItem(local);
		}

		for (Editora editora : leitor.getEditoras()) {
			cbEditoraSource.addItem(editora);
			cbEditoraTarget.addItem(editora);
		}

		for (PalavraChave palavraChave : leitor.getPalavras()) {
			cbPalChaveSource.addItem(palavraChave);
			cbPalChaveTarget.addItem(palavraChave);
		}

		for (TipoDivulgacao divulgacao : leitor.getTiposDeDivulgacao()) {
			cbMeioDivulgacaoSource.addItem(divulgacao);
			cbMeioDivulgacaoTarget.addItem(divulgacao);
		}

		for (Material material : leitor.getMaterias()) {
			cbMaterial.addItem(material);
		}
	}

	public void EnviarParaBD() {
		Conexao conector = new Conexao();
		String ds_material;
		String ds_divulgacao;
		String nm_entidade;
		String tp_entidade;
		ArrayList<String> nm_autor;
		ArrayList<String> ds_palavra_chave;
		String nm_local_publicacao;
		String nm_editora;
		String nm_titulo;
		String ds_ano_producao;
		String ds_ano_publicacao;
		String ds_edicao;
		String nr_paginas;
		String ds_url_disponivel;
		String nr_isbn;
		String nr_issn;
		try {
			conector.setConnection("Trabalho-POO", "postgres", "aluno");
			for (Linha linha : leitor.getLinhas()) {
				ds_material = linha.getTipoMateLinha().getTipoMaterial();
				ds_divulgacao = linha.getTipoDivulLinha().getTipoDivulgacao();
				nm_entidade = linha.getEntidadeLinha().getEntidade();
				tp_entidade = linha.getEntidadeLinha().getTipo();
				nm_autor = linha.nomesAutoresArray();
				ds_palavra_chave = linha.palavrasChaveArray();
				nm_local_publicacao = linha.getLocalPubliLinha().getLocalPublicacao();
				nm_editora = linha.getEditoraLinha().getEditora();
				nm_titulo = linha.getMaterialLinha().getTitulo();
				ds_ano_producao = linha.getMaterialLinha().getAnoProducao();
				ds_ano_publicacao = linha.getMaterialLinha().getAnoPublicacao();
				ds_edicao = linha.getMaterialLinha().getEdicao();
				nr_paginas = linha.getMaterialLinha().getNrPaginas();
				ds_url_disponivel = linha.getMaterialLinha().getUrlDisponivel();
				nr_isbn = linha.getMaterialLinha().getNrISBN();
				nr_issn = linha.getMaterialLinha().getNrISSN();
				conector.insertIntoDB(ds_material, ds_divulgacao, nm_entidade, tp_entidade, nm_autor, ds_palavra_chave,
						nm_local_publicacao, nm_editora, nm_titulo, ds_ano_producao, ds_ano_publicacao, ds_edicao,
						nr_paginas, ds_url_disponivel, nr_isbn, nr_issn);			
			}
			JOptionPane.showMessageDialog(contentPane,
			        "Enviado",
			        "",
			        JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null,"Erro no envio: " +sqle.getClass() + "\n" + sqle.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
