package domXML;

import java.io.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;




public class Ejercicio01DOM {

	Document doc; //Creo mi documento que almacenará el DOM del XML como un atributo de mi clase Ejercicio01DOM
	
	//creo el DOM
	public int abrirXMLDOM(String fichero)  {
		
		doc=null; //La declaro null 
		
		try {//Me creo un objeto DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true); //Le digo que ignore los comentarios
			factory.setIgnoringElementContentWhitespace(true); //que ignore los espacios en blanco
			DocumentBuilder builder = factory.newDocumentBuilder(); //instancia el analizador
			
			doc = builder.parse(fichero); //Parsea el xml y genera mi DOM equivalente
			
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	//Función para recorrer y mostrar el nodo 
	public String recorrerDOMyMostrar() {
		String datos_nodo[] = null;
		String salida = "";
		Node node;
		//Obtengo el primer nodo del Dom
		Node raiz = doc.getFirstChild();
		NodeList nodelist = raiz.getChildNodes();
		//Obtengo una lista de nodos con todos los nodos hijo
		for(int i=0; i<nodelist.getLength(); i++) {
			node = nodelist.item(i);//proceso los nodos hijo
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {//Muestro los elementos por consola
				datos_nodo = procesarLibro(node);
				salida = salida + "\n" + "El título es: " + datos_nodo[1];
				salida = salida + "\n" + "Publicado en: " + datos_nodo[0];
				salida = salida + "\n" + "El autor es: " + datos_nodo[2];
				salida = salida + "\n -------------------------";
			}
		}
		return salida;
		
	}
	//creo un array de nodos libro para procesarlos uno a uno y en
	//mi función recorrerDomymostrar poder leer por consola cada nodo libro
	protected String[] procesarLibro(Node n) {
		
		String datos[] = new String[3];
		Node ntemp = null;
		int contador = 1;
		
		datos[0]= n.getAttributes().item(0).getNodeValue();
		
		NodeList nodos = n.getChildNodes();
		
		for(int i =0; i<nodos.getLength(); i++) {
			ntemp = nodos.item(i);
			
			if(ntemp.getNodeType()==Node.ELEMENT_NODE) {
				datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
				contador++;
			}
			
		}
		return datos;
		
	}
	
	public int guardarDOMcomoFile(String fichero) {
		//Creo mi objeto transformer factory
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			//Creo un StringWriter con el que a posteriori escribiré mi xml en un txt
			StringWriter sw = new StringWriter();
			//Creo un objeto Transformer 
			Transformer t = tf.newTransformer();
			//Creo un objeto DOMSource con el que recorro mi documento 
			DOMSource source = new DOMSource(doc);
			
			t.transform(source, new StreamResult(sw));
			//Una vez transformado mi xml lo escribo en un txt
			FileWriter fw = new FileWriter(fichero);
			fw.write(sw.toString());
			fw.close();
			 
			
			
			return 0;
		} catch (TransformerConfigurationException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -3;
		}catch (TransformerException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -2;
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public Node annadirElemento(String titulo,String autor, String anno) {
		
			//creo un nodo nuevo al que le añado un elemnto titulo
			Node nTitulo = doc.createElement("Titulo");
			Node nTitulo_text=doc.createTextNode(titulo);//añado el contenido del nodo
			nTitulo.appendChild(nTitulo_text);//le digo que lo coloque colgando del nodo hijo
			//Repito lo mismo con cada nodo
			Node nAutor = doc.createElement("Autor");
			Node nAutor_text = doc.createTextNode(autor);
			nAutor.appendChild(nAutor_text);
			
			Node nLibro = doc.createElement("Libro");
			((Element)nLibro).setAttribute("Publicado_ano", anno);
			
			nLibro.appendChild(doc.createTextNode("\n"));//marco saltos de línea
			nLibro.appendChild(nTitulo);
			nLibro.appendChild(doc.createTextNode("\n"));
			nLibro.appendChild(nAutor);
			nLibro.appendChild(doc.createTextNode("\n"));
			//Introduzco los nodos hijo dentro del nodo raiz
			Node raiz = doc.getChildNodes().item(0);
			raiz.appendChild(nLibro);
			raiz.appendChild(doc.createTextNode("\n"));
			
			return nLibro;
		 
		
		
	}
	
	public int borrarYModificarElemento() {
		try {
			//Creo una lista de nodos libro
			NodeList listaLibro = doc.getElementsByTagName("Libro");
			
			
			for(int i=0; i<listaLibro.getLength(); i++) {
				Element libro = (Element) listaLibro.item(i);//Recorro la lista
				//Si el atributo coincide le digo que elimine ese nodo
				if(libro.getAttribute("publicado_en").equalsIgnoreCase("2008")) {
					libro.getParentNode().removeChild(libro);

				}
					//Si el atributo coincide le digo que añada nuevos atributos
				if(libro.getAttribute("publicado_en").equalsIgnoreCase("1840")) {
					libro.setAttribute("Fecha_publicacion", "1845");
					libro.setAttribute("Número_páginas", "546");

				}
			}
			
			
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ejercicio01DOM e = new Ejercicio01DOM();
		String fichero = "librosXML.xml";
		
		e.abrirXMLDOM(fichero);
		
		System.out.println(e.recorrerDOMyMostrar());
		
		e.guardarDOMcomoFile("libros.txt");
		
		e.annadirElemento("Dune", "Frank Herbert", "1965");
		
		e.borrarYModificarElemento();
		
		e.guardarDOMcomoFile("librosSalida.txt");
		
	}

}
