package domXML;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Ejercicio02DOM {

	Document doc;
	
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
	public void recorrerDOMyMostrar() {
		try {
			doc.getDocumentElement().normalize();
			NodeList lista = doc.getElementsByTagName("zoo");
			for(int i =0; i < lista.getLength(); i++) {
				Node nodo = lista.item(i);
				
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;
					System.out.println("--------MAMÍFEROS------");
					System.out.println("animal: " + elemento.getElementsByTagName("animal").item(0).getTextContent());
					System.out.println("animal: " + elemento.getElementsByTagName("animal").item(1).getTextContent());
					System.out.println("--------REPTILES------");
					System.out.println("animal: " + elemento.getElementsByTagName("animal").item(2).getTextContent());
					System.out.println("animal: " + elemento.getElementsByTagName("animal").item(3).getTextContent());
					System.out.println("--------AVES------");
					System.out.println("animal: " + elemento.getElementsByTagName("animal").item(4).getTextContent());
					System.out.println("animal: " + elemento.getElementsByTagName("animal").item(5).getTextContent());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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
	
	public int borrarYModificarElemento() {
		try {
			//Creo una lista de nodos libro
			NodeList listaAnimales = doc.getElementsByTagName("animal");
			
			
			for(int i=0; i<listaAnimales.getLength(); i++) {
				Element animal = (Element) listaAnimales.item(i);//Recorro la lista de nodos animal
					
					if(animal.getTextContent().equalsIgnoreCase("Gato")) {
					//Si el contenido del elemento coincide le digo que añada nuevos atributo
						animal.setAttribute("ID", "1");
						animal.setAttribute("Año_nacimiento", "2009");
					}
					
					if(animal.getTextContent().equalsIgnoreCase("Perro")) {
						//Si el contenido del texto coincide le digo que añada nuevos atributo
							animal.setAttribute("ID", "2");
							animal.setAttribute("Año_nacimiento", "2014" );
						}
					if(animal.getTextContent().equalsIgnoreCase("Boa")) {
						animal.getParentNode().removeChild(animal);
					}
				}
			
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	
	public Node annadirElemento(String nombre,String nombre2, String id, String id2) {
		
	
		Element nAnimal = doc.createElement("Animal"); //Creo los elementos que van a estar dentro del nodo anfibios
		nAnimal.setTextContent(nombre);
		nAnimal.setAttribute("ID", id);
		
		Element nAnimal2 = doc.createElement("Animal");
		nAnimal2.setTextContent(nombre2);
		nAnimal2.setAttribute("ID", id2);//Y le añado a cada uno un atributo ID
		
		Node nTipo = doc.createElement("Anfibios"); //Creo el nodo anfibios
		
		nTipo.appendChild(doc.createTextNode("\n"));//marco saltos de línea
		nTipo.appendChild(nAnimal);
		nTipo.appendChild(doc.createTextNode("\n"));
		nTipo.appendChild(nAnimal2);
		nTipo.appendChild(doc.createTextNode("\n"));

		
		//Introduzco los nodos hijo dentro del nodo raiz
		Node raiz = doc.getFirstChild();
		raiz.appendChild(nTipo);
		raiz.appendChild(doc.createTextNode("\n"));
		
		return nTipo;
	 
	
	
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Ejercicio02DOM e = new Ejercicio02DOM();
		e.abrirXMLDOM("zoo.xml");
		e.guardarDOMcomoFile("zoo.txt");
		e.recorrerDOMyMostrar();
		e.borrarYModificarElemento();
		e.annadirElemento("rana","Lagarto", "9", "10");
		e.guardarDOMcomoFile("zooSalida.txt");

		
	}

}
