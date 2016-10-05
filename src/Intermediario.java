import java.io.IOException;
import java.util.Scanner;

public class Intermediario {

	claseA encargadoFicheros;
	claseB engargadoTecladoPantalla;
	claseC encargadoBDD;
	Scanner teclado;

	public Intermediario() {
		teclado = new Scanner(System.in); // Para leer las opciones de teclado
		encargadoBDD = new claseC();
		encargadoFicheros = new claseA();
		engargadoTecladoPantalla = new claseB();	
	}

	public void ejecucion() {
		int op = 0; // Opcion
		boolean salir = false;

		while (!salir) { // Estructura que repite el algoritmo del menu
							// principal hasta que se la condicion sea falsa
			// Se muestra el menu principal
			System.out.println(".......................... \n" + ".  1 Leer de teclado y escribir en fichero  \n"
					+ ".  2 Leer de fichero y escribir en pantalla \n" + ".  3 Trasvases en la base de datos \n"
					+ ".  4 Salir \n" + "..........................");
			try {
				op = teclado.nextInt(); // Se le da a la variable op el valor
										// del teclado
				System.out.println("OPCION SELECCIONADA:" + op);
				switch (op) {
				case 1:// Insertar Moneda
					tecladoFichero();
					break;
				case 2:// Retornar Monedas
					ficheroPantalla();
					break;
				case 3: // trasvases
					bdd();
					break;
				case 4:// Salir
					salir = true;
					break;
				default:// No valido
					System.out.println("Opcion invalida: marque un numero de 1 a 4");
					break;
				}
			} catch (Exception e) {
				System.out.println("Excepcion por opcion invalida: marque un numero de 1 a 4");
				// flushing scanner
				// e.printStackTrace();
				teclado.next();
			}
		}

		// teclado.close();

	}

	private void ficheroPantalla() throws IOException {
		System.out.println("Pasamos datos de fichero a pantalla");
		Object ed = encargadoFicheros.leerFichero();
		boolean ok = engargadoTecladoPantalla.muestraPantalla(ed);
		if (ok) {
			System.out.println("Se han mostrado correctamente los datos del fichero por pantalla");
		} else {
			System.out.println("Ha ocurrido un error");
		}
	}

	private void tecladoFichero() throws IOException {
		System.out.println("Pasamos datos de teclado a fichero");
		Object ed = engargadoTecladoPantalla.leerTeclado();
		boolean ok = encargadoFicheros.guardarDatos(ed);
		if (ok) {
			System.out.println("Se han guardado correctamente los datos en el fichero");
		} else {
			System.out.println("Ha ocurrido un error");
		}
	}

	private void bdd() throws IOException {
		try {
			System.out.println("¿Qué desea realizar sobre la base de datos?");
			System.out.println(".......................... \n" + ".  1 Realizar una inserción de datos por teclado \n"
					+ ".  2 Realizar una inserción de datos por ficheros \n" + ".  3 Consultar datos por teclado \n" + ".  4 Consultar datos por ficheros \n" + ".  5 Atrás \n"
					+ "..........................");
			int op = teclado.nextInt();
			bddOpciones(op);
			boolean ok = encargadoBDD.hecho();
			if (ok) {
				System.out.println("Se ha ejecutado correctamente el trasvase sobre la base de datos");
			} else {
				System.out.println("Ha ocurrido un error");
			}
		} catch (Exception e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 1 a 5");
			// flushing scanner
			// e.printStackTrace();
			teclado.next();
		}
	}

	private void bddOpciones(int op) throws IOException {
		try {
			switch (op) {
			case 1: // insert
				encargadoBDD.insertTec();
				break;
			case 2: // delete
				encargadoBDD.insertFic();
				break;
			case 3: // update
				encargadoBDD.selectTec();
				break;
			case 4: // select
				encargadoBDD.selectFic();
				break;
			case 5: // atras
				ejecucion();
				break;
			default:
				System.out.println("Opcion invalida: marque un numero de 1 a 5");
				break;
			}
		} catch (Exception e) {
			System.out.println("Excepcion por opcion invalida: marque un numero de 1 a 5");
			// flushing scanner
			// e.printStackTrace();
			teclado.next();
		}
	}
}