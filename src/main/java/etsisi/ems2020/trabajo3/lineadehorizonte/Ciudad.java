package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import etsisi.ems2020.trabajo3.clasesauxIzquierdaliares.Edificio;
import etsisi.ems2020.trabajo3.clasesauxIzquierdaliares.Punto;


/*
Clase fundamental.
Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
están situados los edificios en el fichero de entrada. xIzquierda, xDerecha, h, siendo. Siendo
xIzquierda la coordenada en X origen del edificio iésimo, xDerecha la coordenada final en X, y h la altura del edificio.

*/
public class Ciudad {

  private ArrayList <Edificio> ciudad;

  private LineaHorizonte lineadehorizonte;

  //Generamos una ciudad con 5 edificios aleatorios para pruebas
  public Ciudad()
  {
	 ciudad = new ArrayList <Edificio>();
	 lineadehorizonte= new LineaHorizonte();

  }
  public Ciudad(int n) {//creo que hay que renombrar metodoRandom y quitar esto
	    metodoRandom(n);
  }

  public Edificio getEdificio(int i) {
    return (Edificio)this.ciudad.get(i);
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

  public LineaHorizonte getLineaHorizonte()
  {
      return  lineadehorizonte=crearLineaHorizonte(0, ciudad.size()-1);
  }

  public LineaHorizonte crearLineaHorizonte(int pi, int pd)
  {
    LineaHorizonte linea = new LineaHorizonte();
    if(pi==pd) // Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio.
    {
      Edificio edificio = this.getEdificio(pi);//Plantearse eliminar getEdificio y cambiar por ciudad.get(pi)
      linea.addPunto(new Punto(edificio.getXi(), edificio.getY()));
      linea.addPunto(new Punto(edificio.getxDerecha(),0));
    }
    else
    {
      int medio=(pi+pd)/2; // Edificio mitad

      LineaHorizonte s1 = this.crearLineaHorizonte(pi,medio);
      LineaHorizonte s2 = this.crearLineaHorizonte(medio+1,pd);
	  printLineasHorizonte(s1, s2);  
      linea.LineaHorizonteFussion(s1,s2);
    }
    return linea;
  }
  
  public void printLineasHorizonte(LineaHorizonte s1, LineaHorizonte s2) {
	    System.out.println("==== S1 ====");
	    s1.imprimir();
	    System.out.println("==== S2 ====");
	    s2.imprimir();
	    System.out.println("\n");
	  }


  /**
  * Función encargada de fusionar los dos LineaHorizonte obtenidos por la técnica divide y
  * vencerás. Es una función muy compleja ya que es la encargada de decidir si un
  * edificio solapa a otro, si hay edificios contiguos, etc. y solucionar dichos
  * problemas para que el LineaHorizonte calculado sea el correcto.
  */

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

        Edificio Salida = new Edificio(xIzquierda, y, xDerecha);
        this.addEdificio(Salida);
      }
    }
    catch (Exception e) {			
        System.out.println("Exception thrown  :" + e);
	}

  }


  public void metodoRandom(int n)
  {
    int i=0;
    int xIzquierda,y,xDerecha;
    for(i=0;i<n;i++)
    {
      xIzquierda=(int)(Math.random()*100);
      y=(int)(Math.random()*100);
      xDerecha=(int)(xIzquierda+(Math.random()*100));
      this.addEdificio(new Edificio(xIzquierda,y,xDerecha));
    }
  }
}
