package ficheros;

public class Alumno {
	public String nombre;
	public double nota;
	public double nota2;
	public String dni;
	public int telefono;
	public int valoracion1;
	public int valoracion2;
	public int valoracion3;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public double getNota2() {
		return nota2;
	}
	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}
	
	public double notaMedia() {
		
		double resultado;
		
		resultado=(this.nota+this.nota2)/2;
		return resultado;
		
	}
	
	
	
	public int getValoracion1() {
		return valoracion1;
	}
	public void setValoracion1(int valoracion1) {
		this.valoracion1 = valoracion1;
	}
	public int getValoracion2() {
		return valoracion2;
	}
	public void setValoracion2(int valoracion2) {
		this.valoracion2 = valoracion2;
	}
	public int getValoracion3() {
		return valoracion3;
	}
	public void setValoracion3(int valoracion3) {
		this.valoracion3 = valoracion3;
	}
	public Alumno(String nombre, double nota, double nota2, String dni, int telefono, int valoracion1, int valoracion2,
			int valoracion3) {
		super();
		this.nombre = nombre;
		this.nota = nota;
		this.nota2 = nota2;
		this.dni = dni;
		this.telefono = telefono;
		this.valoracion1 = valoracion1;
		this.valoracion2 = valoracion2;
		this.valoracion3 = valoracion3;
	}
	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Acceso a Datos: " + nota + ", PSP: " + nota2 + ", Dni:" + dni + ", Teléfono: "
				+ telefono;
	}
	
	
	
	
	
	
}
