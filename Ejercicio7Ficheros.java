package ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Ejercicio7Ficheros {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String Carpeta = "Curso";
		File directorio = new File(Carpeta);
		directorio.mkdir();//Creo mi directorio principal
		
		String subCarpeta1 = "Curso/Asignatura1";//Añado mi directorio principal en la ruta para que al crear el directorio hijo...
		File subDirectorio = new File(subCarpeta1);//se cree dentro de este
		subDirectorio.mkdir();
		
		String subCarpeta2 = "Curso/Asignatura2";//Mismo método que con el subdirectorio 1
		File subDirectorio2 = new File(subCarpeta2);
		subDirectorio2.mkdir();
		
		
		String fichero1 = ("NotasEvaluacion1.txt"); //Creo mis ficheros
		String fichero2 = "NotasEvaluacion2.txt";
		
		String subCarpetas[]= new String[] {subCarpeta1, subCarpeta2}; //Array de subdirectorios
		
		try {
			int cont =1;
			for(String subCarpeta:subCarpetas ) { //Recorro el array de subdirectorios
				String direcciones[]= new String[]{subCarpeta+ "/"+fichero1, subCarpeta+"/"+fichero2}; //Creo un segundo array donde iré almacenando en cada subdirectorio ...
				//... la ruta de las carpetas
				for(String direccion:direcciones) { //Recorro el array de rutas  para ir añadiendo las direcciones
					PrintWriter pw = new PrintWriter(direccion);
					pw.write("Notas del alumno "+ (cont++));
					pw.close();
				}
			}
			

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
