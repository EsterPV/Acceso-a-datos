package ficheros;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;



public class Ejercicio5y6Ficheros {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		
		System.out.println("Escoge uno de las opciones");
		System.out.println("1. Ejercicio 5");
		System.out.println("2. Ejercicio 6");
		Scanner sc = new Scanner(System.in);
		
		int opciones = sc.nextInt();
		switch (opciones) {
		case 1: {
			Scanner leer = new Scanner(System.in);
			
			ejercicio5(leer, "FicheroNumeros1.txt", "FicheroNumeros2.txt");
			System.out.println("Ficheros generados correctamente");
			break;
		}
		case 2:{
			Scanner leer = new Scanner(System.in);
			ejercicio6(leer, "FicheroPalabras.txt");
			System.out.println("Fichero generado correctamente");
			break;
		}
		default:
			System.out.println("El número introducido es erróneo");
		
		}
		
		
	}

	public static void ejercicio5(Scanner leerEnteros, String fichero, String fichero2) throws FileNotFoundException {
			
			try {
				LinkedList<Integer> enteros = new LinkedList<Integer>();
				
				FileWriter fw = new FileWriter(fichero);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter salida = new PrintWriter(bw);
				
				for(int i = 0; i<5; i++) {
					System.out.println("Introduce entero nº " + (i));
					enteros.add(leerEnteros.nextInt());//Pido por pantalla la lista de enteros
					//Introduzo los enteros en mi lista
				}
				
				
			
				 
				//Utilizo stringtokenizer para eliminar las comas de mi linkedlist 
				//y así separar los números con espacios en blanco
				//Elimino los [] que crea el toString de mi lista con replace
				StringTokenizer st = new StringTokenizer(enteros.toString().replace("[", " ").replace("]", " "), ",");
				while(st.hasMoreTokens()) {
					
					
					salida.printf(st.nextToken());
					}
				salida.close();
				
			
				
				try {
					//Creo otro fichero para la segunda parte del ejercicio
					FileWriter fw2 = new FileWriter(fichero2);
					BufferedWriter bw2 = new BufferedWriter(fw2);
					PrintWriter salida2 = new PrintWriter(bw2);
					
					//Eliminino las comas con String tokenizer 
					//Elimino los [] que crea el toString de mi lista con replace
					StringTokenizer st2 = new StringTokenizer(enteros.toString().replace("[", " ").replace("]", " "), ",");
					while(st2.hasMoreTokens()) {
						
						//Añado un salto de línea dentro del bucle para que escriba cada número 
						//en una línea diferente
						salida2.printf(st2.nextToken()+"\n"); 
						}
					salida2.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
	}
	
	public static void ejercicio6(Scanner leerPalabras,String fichero) {
		ArrayList<String> palabras = new ArrayList<String>();//Creo mi lista 
		
		try {
			for(int i =0; i<5; i++) {
				System.out.println("Introduce palabra nº " + (i));
				palabras.add(leerPalabras.next());//Leo por teclado las palabras y las meto en la lista
				
			}
			
			//Utilizo la clase collections para ordenar las palabras alfabéticamente
			Collections.sort(palabras);
			
			FileWriter fw = new FileWriter(fichero);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			//Eliminino las comas con String tokenizer 
			//Elimino los [] que crea el toString de mi lista con replace
			StringTokenizer st = new StringTokenizer(palabras.toString().replace("[", " ").replace("]", " "), ",");
			int cont=1; //Creo un contador para numerar cada palabra
			while(st.hasMoreTokens()) {
				pw.printf((cont++)+"."+st.nextToken()+"\n");//Escribo en mi fichero con printwriter
				//añadiendo el contador y el salto de línea
			}
			pw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
}
