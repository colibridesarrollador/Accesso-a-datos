package manejoFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ficheros {

	public static void main(String[] args) {
		Ficheros f = new Ficheros();
		f.apartadoUno(new File("files_destino/apartadoUno.txt"), "este es el texto inicial","este es el texto final");
		f.apartadoDos();
		f.apartadoTres(new File("files_origen/apartadoTres.txt"),new File("files_destino/apartadoTres.txt"));
	}
	/*
	 * Los ficheros de trabajo estarán dentro de una subcarpeta del propio proyecto
	 * de nombre "files_origen" y los resultados se almacenarán dentro de una
	 * carpeta llamada “files_destino”.
	 */

	/*
	 * Un método que reciba el nombre de un fichero, dos textos (un texto inicial y
	 * un texto final) y compruebe si dicho fichero existe. En el caso de que no
	 * exista, se escribirá el texto inicial. De lo contrario, si el fichero ya
	 * existe, escribimos el texto final al final del fichero, para respetar el
	 * contenido que ya tenía
	 */
	public void apartadoUno(File fichero, String textoIni, String textoFin) {

		FileWriter fw = null;

		if (fichero.exists()) {
			System.out.println("El fichero existe");
			try {
				fw = new FileWriter(fichero);
				fw.write(textoFin);
				fw.flush();
				System.out.println("Texto final escrito correctamente(Método uno)");

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("El archivo no existe.(Método uno)");
			try {
				fw = new FileWriter(fichero);
				fw.write(textoIni);
				fw.flush();
				System.out.println("Texto inicial escrito correctamente");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/*
	 * Un método que solicite por teclado el nombre del fichero que se quiere crear,
	 * compruebe si dicho fichero existe y vuelva a pedir por teclado el texto que
	 * se quiere escribir. El máximo de líneas que podrá escribir el usuario es de
	 * 10.
	 */
	public void apartadoDos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escribe la ruta del fichero: ");
		File fichero = new File(sc.nextLine());
		FileWriter fw = null;
		if (fichero.exists()) {
			try {
				fw = new FileWriter(fichero);
				System.out.println("Introduzca el texto para guardar en el fichero: ");
				System.out.println("Recuerda, solo puedes escribir 10 lineas:");
				for (int i = 0; i < 10; i++) {
					System.out.println("linea " + (i + 1) + " :");
					fw.write(sc.nextLine()+"\n");
				}
				fw.flush();
				System.out.println("Lineas guardadas correctamente en el fichero.(Método dos)");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fw != null)
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			sc.close();
		} else {
			System.out.println("El fichero no existe.(Método dos)");
		}
	}

	/*
	 * Un método que permita copiar un fichero a otro, facilitando como parámetro de
	 * dichas funciones la ruta al fichero o ficheros, sin utilizar el método copy()
	 * proporcionado por la clase File.
	 */
	public void apartadoTres(File origen, File destino) {

		BufferedReader br = null;
		FileWriter fw = null;
		String linea = "";
		try {
			br = new BufferedReader(new FileReader(origen));
			fw = new FileWriter(destino);
			while ((linea = br.readLine()) != null) {
				fw.write(linea + "\n");
			}
			fw.flush();
			System.out.println("Fichero copiado exitosamente (Método tres).");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				try {
					fw.close();
					if (br != null)
						br.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}
	}
}
