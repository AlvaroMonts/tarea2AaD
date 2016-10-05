import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class claseA {
	private String sFichero = "C:/Users/alvaro.montes/eclipse/java-neon/eclipse/workspace/fichero.txt";
	private File archivo = new File(sFichero);
	private FileReader fr;
	private BufferedReader br;
	private ArrayList<String> arrayl;
	private BufferedWriter bw;
	private FileWriter fw;

	public Object leerFichero() throws IOException {
		try {
			fr = new FileReader(sFichero);
			br = new BufferedReader(fr);
			arrayl = new ArrayList<String>();

			String linea = new String();
			while ((linea = br.readLine()) != null) {
				arrayl.add(linea);
			}

			br.close();
			return arrayl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean guardarDatos(Object ed) throws IOException {
		if (ed.equals(null)) {
			return false;
		} else {
			fw = new FileWriter(sFichero);
			bw = new BufferedWriter(fw);

			ArrayList<String> al = (ArrayList<String>) ed;

			for (int i = 0; i < al.size(); i++) {
				bw.write(al.get(i));
				bw.newLine();
			}

			bw.close();
			return true;
		}
	}
}
