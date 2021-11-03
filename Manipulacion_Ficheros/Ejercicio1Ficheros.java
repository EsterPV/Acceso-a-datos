package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1Ficheros {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				
				
				Alumno a = new Alumno("Ester",  9.5, 8,"123456h", 123456,7 ,8 ,9 );
				Alumno b = new Alumno("Yhael",  7.5,9,"1236456h", 112356,5 , 6 ,7 );
				Alumno c = new Alumno("Sara", 8.5,8.6, "246543636y", 989667, 6 ,7, 7);
				Alumno d = new Alumno("Lola", 10,9, "123445A", 6738293, 8, 6, 9);
				ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
				listaAlumnos.add(a);
				listaAlumnos.add(b);
				listaAlumnos.add(c);
				listaAlumnos.add(d);
				
		//Creo un objeto Scanner para pedir al usuario el nombre de su fichero	
				//Ejercicio nº3
		System.out.println("Introduce el nombre de tu fichero: ");
		try (Scanner entrada = new Scanner(System.in)) {
			String ficheroEntrada = entrada.next();
			
			crearFichero(ficheroEntrada, listaAlumnos);
			leerFichero(ficheroEntrada, listaAlumnos);
		}
		
		mostrarFichas("Ficha Ester.txt","Ficha Sara.txt");
		
		
		
	}
		//Creo mi fichero Alumnos con el contenido de todos los alumnos
		//Ejercicio 1.a
	  static void crearFichero(String fichero, ArrayList<Alumno>listaAlumnos) throws IOException {
		 PrintWriter out = null;
			try {
				out = new PrintWriter(new FileWriter(fichero));
				for(Alumno a : listaAlumnos) {
					out.printf(a.toString()+"\n");
					
					
				}
			} finally {
				if(out != null)
					out.close();
				// TODO: handle finally clause
			}
			
			
	 }
	  static void leerFichero(String fichero,ArrayList<Alumno>listaAlumnos ) {
		
		 try {
			
			 //Creo mi bufferedReader para leer y mostrar por consola el fichero Alumnos
			 //Ejercicio nº2
			 BufferedReader br;
			 FileReader fr = new FileReader(fichero);
			 br = new BufferedReader(fr);
			 
			 System.out.println("El texto que contiene "+fichero+" es: ");
			 String linea = br.readLine();
			
			 //Creo ficha por cada alumno 
			 //Ejercicio 1.b
			 for(Alumno a : listaAlumnos) {
				FileWriter fw = new FileWriter("Ficha "+a.nombre+".txt");
				BufferedWriter bw = new BufferedWriter(fw);
				 
				PrintWriter salida = new PrintWriter(bw);
				 
				salida.printf("Nombre: "+a.nombre);
				salida.printf("\nAcceso a datos: " + a.nota);
				salida.printf("\nPSP: " + a.nota2);
				salida.printf("\nNota media: " + a.notaMedia());
				salida.printf("\nDni: " + a.dni);
				salida.printf("\nTeléfono: " + a.telefono);
				salida.close();
			 }
			
			 
			 while(linea != null) {
				 System.out.println(linea);
				 
				 linea = br.readLine();
				 
			 }
			 
			 br.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al leer de teclado");
		
		  
	  }
	 
	  }
	 
	  //Ejercicio nº2
	  //Creo dos bufferedReader para leer y mostrar por consola las fichas de los alumnos Ester y Sara
	  static void mostrarFichas(String fichero, String fichero2) {
		  try {
			  	BufferedReader br;
				 FileReader fr = new FileReader(fichero);
				 br = new BufferedReader(fr);
				 System.out.println("\nEl texto que contiene Ficha Ester.txt es: ");
				 String linea = br.readLine();
				 
				 while(linea != null) {
					 System.out.println(linea);
					 
					 
					 linea = br.readLine();
					 
				 }
				 
				 BufferedReader br2;
				 FileReader fr2 = new FileReader(fichero2);
				 br2 = new BufferedReader(fr2);
				 System.out.println("\nEl texto que contiene Ficha Sara.txt es: ");
				 String linea2 = br2.readLine();
				 
				 while(linea2 != null) {
					
					 System.out.println(linea2);
					 
					
					 linea2 = br2.readLine();
				 }
				 
				 br.close();
				 br2.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	  }


}
