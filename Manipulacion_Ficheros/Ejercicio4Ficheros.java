package ficheros;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;



public class Ejercicio4Ficheros {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Alumno a = new Alumno("Ester",  9.5, 8,"123456h", 123456, 8, 6, 7);
		Alumno b = new Alumno("Yhael",  7.5,9,"1236456h", 112356, 6, 8, 5);
//		Alumno c = new Alumno("Sara", 8.5,8.6, "246543636y", 989667, 10, 8, 9);
//		Alumno d = new Alumno("Lola", 10,9, "123445A", 6738293, 6, 7, 9);
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		listaAlumnos.add(a);
		listaAlumnos.add(b);
//		listaAlumnos.add(c);
//		listaAlumnos.add(d);
		
		//Creo un objeto Scanner para pedir al usuario el nombre de su fichero	
				//Ejercicio n�3
		System.out.println("Introduce el nombre de tu fichero: ");
		try (Scanner entrada = new Scanner(System.in)) {
			String ficheroEntrada = entrada.next();
			
			crearFichero(ficheroEntrada, listaAlumnos);
			anhadirValoracion(ficheroEntrada, ficheroEntrada, listaAlumnos);
			
		}
		
		



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
	


	//Ejercicio 4 1.a
	static void anhadirValoracion(String fichero, String fichero2,ArrayList<Alumno>listaAlumnos) throws IOException {
		BufferedWriter bw = null; 
		FileWriter fw = null;
		//Creo mi string valoracion con el contenido que quiero a�adir
		String valoracion = "\nProfesor 1: Valoraci�n profesor 1#Profesor 2: Valoraci�n profesor 2#Profesor 3: Valoraci�n profesor 3";
		try {
			fw = new FileWriter(fichero, true); //A�ado el fichero a mi filewriter y especifico true para decirle que ya tiene contenido
			bw = new BufferedWriter(fw); //A�ado el fileWriter dentro de mi BufferedWriter
			bw.write(valoracion);  //A�ado el String creado anteriormente dentro del bufferedWriter para que lo a�ada al final de todo
			
			//Utilizo un for each para ir creando mi ficha alumno con cada objeto de mi clase Alumno que se va almacenando en mi arraylist
			 for(Alumno a : listaAlumnos) {
					FileWriter fw2 = new FileWriter("Ficha "+a.nombre+".txt");
					BufferedWriter bw2 = new BufferedWriter(fw2);
					 
					PrintWriter salida = new PrintWriter(bw2);
					 
					salida.printf("Nombre: "+a.nombre);
					salida.printf("\nAcceso a datos: " + a.nota);
					salida.printf("\nPSP: " + a.nota2);
					salida.printf("\nNota media: " + a.notaMedia());
					salida.printf("\nDni: " + a.dni);
					salida.printf("\nTel�fono: " + a.telefono);
					
					
					//Ejercicio 4.b
					StringTokenizer st = new StringTokenizer(valoracion, "#"); //Creo mi clase stringtokenizer
					//Especifico que quiero que vaya saltando de linea cada vez que se tope con un # dentro de mi string valoracion
					
					int cont=1; 
					
					while(st.hasMoreTokens()) {
					System.out.println("Introduce valoraci�n "+ (cont++) +" del alumno "+ a.nombre);	
					Scanner sc = new Scanner(System.in);
					// Creo un scanner para ir a�adiendo por pantalla cada valoracion de cada alumno
					double valoracionProfe = sc.nextDouble();
					salida.printf(st.nextToken()+" -> " +valoracionProfe+"\n");
					}
					
					salida.close();
					
					
				 }
			
		} finally {
			// TODO: handle finally clause
			if(bw!=null)
				bw.close();
		}
		
	}



}
