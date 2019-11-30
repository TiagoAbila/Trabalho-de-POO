package Controller;
import Model.*;

//
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Leitura {
	ArrayList<Autor> autores = new ArrayList();
	ArrayList<PalavraChave> palavrasChaves = new ArrayList();
	ArrayList<Material> materiais = new ArrayList();
	private int linhaCorrente = 0;
	
	public Leitura(File arquivo) throw s IOException {
	  FileReader leitor = new FileReader(arquivo);
	  BufferedReader ler = new BufferedReader(leitor); 
	  String l;
	  String [] newRow;

	  while((l = ler.readLine()) != null) {	
		 linhaCorrente++;
		 newRow = l.split(";");
		 Material matCorrente = new Material(linhaCorrente);
		 Autor autCorrente = new Autor (linhaCorrente);
		 PalavraChave palChaCorrente = new PalavraChave(linhaCorrente);
		 
	  }
	}	
}
