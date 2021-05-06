package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import etsisi.ems2020.trabajo3.clasesauxiliares.Edificio;
import etsisi.ems2020.trabajo3.clasesauxiliares.Punto;


/*
Clase fundamental.
Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
están situados los edificios en el fichero de entrada. xi, xd, h, siendo. Siendo
xi la coordenada en X origen del edificio iésimo, xd la coordenada final en X, y h la altura del edificio.

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
    // Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio.
    if(pi==pd)
    {
      Edificio edificio = new Edificio();
      edificio = this.getEdificio(pi);//Plantearse eliminar getEdificio y cambiar por ciudad.get(pi)

      linea.addPunto(new Punto(edificio.getXi(), edificio.getY()));
      linea.addPunto(new Punto(edificio.getXd(),0));
    }
    else
    {
      // Edificio mitad
      int medio=(pi+pd)/2;

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
      int xi, y, xd;
      Scanner sr = new Scanner(new File(fichero));

      while(sr.hasNext())
      {
        xi = sr.nextInt();
        xd = sr.nextInt();
        y = sr.nextInt();

        Edificio Salida = new Edificio(xi, y, xd);
        this.addEdificio(Salida);
      }
    }
    catch(Exception e){}

  }


  public void metodoRandom(int n)
  {
    int i=0;
    int xi,y,xd;
    for(i=0;i<n;i++)
    {
      xi=(int)(Math.random()*100);
      y=(int)(Math.random()*100);
      xd=(int)(xi+(Math.random()*100));
      this.addEdificio(new Edificio(xi,y,xd));
    }
  }
}
