import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class claseB {
	private ArrayList<String> arrayl;
	private Scanner sc;

	public boolean muestraPantalla(Object ed) {
		if (ed.equals(null)) {
			return false;
		} else {
			System.out.println(ed.toString());
			return true;
		}
	}

	public Object leerTeclado() throws IOException {
		try {
			arrayl = new ArrayList<String>();
			sc = new Scanner(System.in);
			String texto = new String();
			System.out.println("-- Escriba 'FIN' si desea terminar la introducción de nombres de deportes --");
			do {
				texto = sc.next();
				if (!texto.equals("FIN")) {
					arrayl.add(texto);
				}
			} while (!texto.equals("FIN"));
			System.out.println("TERMINA");
			return arrayl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
