import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.mysql.jdbc.PreparedStatement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class claseC {
	private String sFichero;
	private String bd, login, pwd, url;
	private Connection conection;
	private boolean op;
	private Scanner sc;
	private ArrayList<String> arrayInFic;
	private ArrayList<String> arraySeTec;
	private ArrayList<String> arraySeFic;
	private FileReader fr;
	private BufferedReader br;
	private BufferedWriter bw;
	private FileWriter fw;

	public claseC() {
		sFichero = "C:/Users/alvaro.montes/eclipse/java-neon/eclipse/workspace/Tarea1AaD/fichero.txt";
		sc = new Scanner(System.in);
		try {
			HashMap<String, String> hmret = loadFichero("bbdd.ini");
			Class.forName("com.mysql.jdbc.Driver");
			conection = DriverManager.getConnection(hmret.get("url"), hmret.get("login"), hmret.get("pwd"));
			System.out.println("Conectado a bbdd");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no cargado");
		} catch (SQLException e) {
			System.out.println("Error de Conexión con MySQL");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> loadFichero(String nomFichero) throws IOException {
		HashMap<String, String> hmDatos = new HashMap<String, String>();
		FileReader fr = new FileReader(nomFichero);
		BufferedReader bf = new BufferedReader(fr);
		String lineaLeida = bf.readLine();
		while (lineaLeida != null) {
			String sar[] = lineaLeida.split("=");
			hmDatos.put(sar[0], sar[1]);
			lineaLeida = bf.readLine();
		}
		bf.close();
		fr.close();
		return hmDatos;
	}

	public void insertTec() {
		System.out.println("Introduzca los deportes a insertar en la base de datos");
		try {
			String texto = new String();
			System.out.println("-- Escriba 'FIN' si desea terminar la introducción de nombres de deportes --");
			do {
				texto = sc.next();
				if (!texto.equals("FIN")) {
					String sql = "Insert into bddtarea2.deportes(`nombre`) values (?);";
					PreparedStatement stmt = (PreparedStatement) conection.prepareStatement(sql);
					stmt.setString(1, texto);
					stmt.executeUpdate();
					stmt.close();
				}
			} while (!texto.equals("FIN"));

			op = true;
			System.out.println("TERMINA INSTEC");
		} catch (Exception e) {
			e.printStackTrace();
			op = false;
		}
	}

	public void insertFic() {
		try {
			fr = new FileReader(sFichero);
			br = new BufferedReader(fr);
			arrayInFic = new ArrayList<String>();

			String linea = new String();
			while ((linea = br.readLine()) != null) {
				arrayInFic.add(linea);
			}
			br.close();
			int key = 0;
			while (key != arrayInFic.size()) {
				if (!arrayInFic.get(key).equals("FIN")) {
					String sql = "Insert into bddtarea2.deportes(`nombre`) values (?);";
					PreparedStatement stmt = (PreparedStatement) conection.prepareStatement(sql);
					stmt.setString(1, arrayInFic.get(key));
					stmt.executeUpdate();
					stmt.close();
					key++;
				}
			}
			op = true;
			System.out.println("TERMINA INSTEC");
		} catch (Exception e) {
			e.printStackTrace();
			op = false;
		}
	}

	public void selectTec() {
		try {
			String query = "SELECT * FROM bddtarea2.deportes";
			Statement stmt = conection.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			arraySeTec = new ArrayList<String>();

			while (rset.next()) {
				arraySeTec.add(rset.getString(2));
			}
			System.out.println(arraySeTec.toString());
			op = true;
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			op = false;
		}
	}

	public void selectFic() {
		try {
			String query = "SELECT * FROM bddtarea2.deportes";
			Statement stmt = conection.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			arraySeFic = new ArrayList<String>();
			fw = new FileWriter(sFichero);
			bw = new BufferedWriter(fw);

			while (rset.next()) {
				arraySeFic.add(rset.getString(2));
			}

			for (int i = 0; i < arraySeFic.size(); i++) {
				bw.write(arraySeFic.get(i));
				bw.newLine();
			}
			bw.close();
			op = true;
			rset.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			op = false;
		}
	}

	public boolean hecho() {
		return op;
	}
}
