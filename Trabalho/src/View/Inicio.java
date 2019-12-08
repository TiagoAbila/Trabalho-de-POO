package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Conexao;
import Controller.Leitura;
import Controller.Persistencia;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
//
public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 128);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file;
				JFileChooser jfc = new JFileChooser("");
				jfc.setFileFilter(new FileNameExtensionFilter( "CSV", "csv"));
				int retorno = jfc.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					file = jfc.getSelectedFile();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Apresentacao frame = new Apresentacao(file);
								frame.setArquivoTrabalhado(file);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		btnNovo.setBounds(10, 58, 114, 23);
		contentPane.add(btnNovo);

		JButton btnExiste = new JButton("Continuar");
		btnExiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persistencia p = new Persistencia();
				Leitura l = p.BuscarObjetos();
				if (l != null) {
					Apresentacao a = new Apresentacao(l);
					a.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane,
					        "Não há nada a ser continuado, favor começar um novo",
					        "",
					        JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnExiste.setBounds(342, 58, 114, 23);
		contentPane.add(btnExiste);
		
		JLabel lblDesejaContinuarA = new JLabel("Deseja continuar a edi\u00E7\u00E3o e envio de um arquivo, ou enviar e editar um novo?");
		lblDesejaContinuarA.setBounds(40, 11, 385, 36);
		contentPane.add(lblDesejaContinuarA);
	}
}
