package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Persistencia {
	private static void GravarObjetos(ArrayList<Object> lista) {
		File arq = new File("ArquivoGeral.dat");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq));
			oos.writeObject(lista);
			oos.close();
		} catch (IOException ex) {
			System.out.printf("Erro: %s", ex.getMessage());
		}
	}

	public static ArrayList<Object> BuscarObjetos() {
		ArrayList<Object> lista = new ArrayList();
		try {
			File arq = new File("ArquivoGeral.dat");
			if (arq.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq));
				lista = (ArrayList<Object>) ois.readObject();
				ois.close();
			}
		} catch (IOException ex) {
			System.out.printf("Erro: %s", ex.getMessage());
		} catch (ClassNotFoundException exe) {
			System.out.printf("Erro: %s", exe.getMessage());
		}
		return(lista);
	}
}
