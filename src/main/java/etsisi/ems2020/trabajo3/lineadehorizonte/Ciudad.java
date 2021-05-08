package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import etsisi.ems2020.trabajo3.clasesauxiliares.Edificio;
import etsisi.ems2020.trabajo3.clasesauxiliares.Punto;



/*
Clase fundamental.
Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
están situados los edificios en el fichero de entrada. xIzquierda, xDerecha, h, siendo. Siendo
xIzquierda la coordenada en X origen del edificio iésimo, xDerecha la coordenada final en X, y h la altura del edificio.

 */
public class Ciudad {

	private ArrayList <Edificio> ciudad;//rename 

	private LineaHorizonte lineadehorizonte;

	public Ciudad()
	{
		ciudad = new ArrayList <Edificio>();
		lineadehorizonte= new LineaHorizonte();

	}

	public Edificio getEdificio(int i) {
		return this.ciudad.get(i);
	}
	public void addEdificio (Edificio e)
	{
		ciudad.add(e);
	}
	public void removeEdificio(int i)
	{
		ciudad.remove(i);
	}

	public int size()
	{
		return ciudad.size();
	}
	
	public void setLineaHorizonte() {
		lineadehorizonte=crearLineaHorizonte(0, ciudad.size()-1);
	}
	
	public LineaHorizonte getLineaHorizonte()
	{
		if(lineadehorizonte.isEmpty()) {
			setLineaHorizonte();
		}
		return  lineadehorizonte;
	}

	/**
	 * Función encargada de fusionar los dos LineaHorizonte obtenidos por la técnica divide y
	 * vencerás. Es una función muy compleja ya que es la encargada de decidir si un
	 * edificio solapa a otro, si hay edificios contiguos, etc. y solucionar dichos
	 * problemas para que el LineaHorizonte calculado sea el correcto.
	 */

	public LineaHorizonte crearLineaHorizonte(int xEdificioInicial, int xEdificioFinal)
	{
		LineaHorizonte linea = new LineaHorizonte();
		if(xEdificioInicial==xEdificioFinal) 
		{
			casoBase(linea,xEdificioInicial);
		}
		else
		{
			divideConRecursividad(linea,new int [] {xEdificioInicial, xEdificioFinal});
		}
		return linea;
	}

	public void casoBase(LineaHorizonte linea, int edificioUnico) {
		Edificio edificio = this.getEdificio(edificioUnico);//Plantearse eliminar getEdificio y cambiar por ciudad.get(pi) creo q es absutdo

		linea.addPunto(new Punto(edificio.getXi(), edificio.getY()));
		linea.addPunto(new Punto(edificio.getxDerecha(),0));
	}
	
	public void divideConRecursividad(LineaHorizonte linea, int [] x) { //reemplazar por casoGeneral
		int edificioMitad=(x[0]+x[1])/2;

		LineaHorizonte s1 = this.crearLineaHorizonte(x[0],edificioMitad);
		LineaHorizonte s2 = this.crearLineaHorizonte(edificioMitad+1,x[1]);
		printLineasHorizonte(s1, s2);  
		linea.lineaHorizonteFussion(s1,s2);
	}

	public void printLineasHorizonte(LineaHorizonte s1, LineaHorizonte s2) {//cambiar por simplemente imprimirLineas
		System.out.println("==== Linea del horizonte de la izquierda a fusionar ====");
		
		System.out.print(s1.toString());
		
		System.out.println("==== Linea del horizonte de la derecha a fusionar ====");
		
		System.out.print(s2.toString());
		
		System.out.println("\n");
	}

	/*
  Método que carga los edificios que me pasan en el
  archivo cuyo nombre se encuentra en "fichero".
  El formato del fichero nos lo ha dado el profesor en la clase del 9/3/2020,
  pocos días antes del estado de alarma.
	 */

	public void cargarEdificios (String fichero)
	{
		try
		{
			int xIzquierda, y, xDerecha;
			Scanner sr = new Scanner(new File(fichero));

			while(sr.hasNext())
			{
				xIzquierda = sr.nextInt();
				xDerecha = sr.nextInt();
				y = sr.nextInt();
				this.addEdificio(new Edificio(xIzquierda, y, xDerecha));
			}
			sr.close();
		}
		catch (Exception e) {			
			e.getStackTrace();		
			}

	}
//eliminado metodo random
}
