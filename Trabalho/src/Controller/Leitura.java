package Controller;
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
	String ab = "";
	  StringBuilder builder = new StringBuilder();
	public Leitura(File arquivo) throws IOException {
	  FileReader leitor = new FileReader(arquivo);
	  BufferedReader ler = new BufferedReader(leitor); 
	  String l;
	  while((l = ler.readLine()) != null) {	
		  builder.append(l+"\n");
	  }
	  ab = builder.toString();
	}
	public String getAb() {
		return ab;
	}
	public void setAb(String ab) {
		this.ab = ab;
	}
	
	
}
