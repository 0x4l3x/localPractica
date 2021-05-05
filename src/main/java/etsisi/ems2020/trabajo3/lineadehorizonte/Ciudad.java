package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/*
Clase fundamental.
Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
están situados los edificios en el fichero de entrada. xi, xd, h, siendo. Siendo
xi la coordenada en X origen del edificio iésimo, xd la coordenada final en X, y h la altura del edificio.

*/
public class Ciudad {

  private ArrayList <Edificio> ciudad;

  //Generamos una ciudad con 5 edificios aleatorios para pruebas
  public Ciudad()
  {
    int n = 5;
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
    int pi = 0;
    int pd = ciudad.size()-1;
    return crearLineaHorizonte(pi, pd);
  }

  public LineaHorizonte crearLineaHorizonte(int pi, int pd)
  {
    LineaHorizonte linea = new LineaHorizonte();
    Edificio edificio = new Edificio();
    // Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio.
    if(pi==pd)
    {
      edificio = this.getEdificio(pi);

      linea.addPunto(new Punto(edificio.getXi(), edificio.getY()));
      linea.addPunto(new Punto(edificio.getXd(),0));
    }
    else
    {
      // Edificio mitad
      int medio=(pi+pd)/2;

      LineaHorizonte s1 = this.crearLineaHorizonte(pi,medio);
      LineaHorizonte s2 = this.crearLineaHorizonte(medio+1,pd);
      linea = LineaHorizonteFussion(s1,s2);
    }
    return linea;
  }

  /**
  * Función encargada de fusionar los dos LineaHorizonte obtenidos por la técnica divide y
  * vencerás. Es una función muy compleja ya que es la encargada de decidir si un
  * edificio solapa a otro, si hay edificios contiguos, etc. y solucionar dichos
  * problemas para que el LineaHorizonte calculado sea el correcto.
  */
  public LineaHorizonte LineaHorizonteFussion(LineaHorizonte s1,LineaHorizonte s2)// quitar los 3 ultimos parametros
  {
    int s1y=-1, s2y=-1, prev=-1; // en estas variables guardaremos las alturas de los puntos anteriores y en prev guardaremos la previa del segmento anterior introducido
    LineaHorizonte salida = new LineaHorizonte(); // LineaHorizonte de salida

    Punto p1 = new Punto();         // punto donde guardaremos el primer punto del LineaHorizonte s1
    Punto p2 = new Punto();         // punto donde guardaremos el primer punto del LineaHorizonte s2
    printLineasHorizonte(s1, s2);

    while ((!s1.isEmpty()) && (!s2.isEmpty()))     //Mientras tengamos elementos en s1 y en s2
    {
      p1 = s1.getPunto(0); // guardamos el primer elemento de s1
      p2 = s2.getPunto(0); // guardamos el primer elemento de s2

      if (p1.getX() < p2.getX()) // si X del s1 es menor que la X del s2
      {
        prev=fusionarAltosDif(p1, prev, s2y, salida);
        s1y = p1.getY();   // actualizamos la altura s1y
        s1.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es valido)
      }
      else if (p1.getX() > p2.getX()) // si X del s1 es mayor que la X del s2
      {
        prev=fusionarAltosDif(p2, prev, s1y, salida);
        s2y = p2.getY();   // actualizamos la altura s2y
        s2.borrarPunto(0); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es valido)
      }
      else // si la X del s1 es igual a la X del s2
      {
        prev=fusionarAltosIguales(p1,p2,prev,salida);
        s1y = p1.getY();   // actualizamos la s1y e s2y
        s2y = p2.getY();
        s1.borrarPunto(0); // eliminamos el punto del s1 y del s2
        s2.borrarPunto(0);
      }
    }
    añadirRestoPuntos(s1, salida, prev);
    añadirRestoPuntos(s2, salida, prev);
    return salida;
  }

  public void printLineasHorizonte(LineaHorizonte s1, LineaHorizonte s2) {
    System.out.println("==== S1 ====");
    s1.imprimir();
    System.out.println("==== S2 ====");
    s2.imprimir();
    System.out.println("\n");
  }

  public int fusionarAltosDif(Punto p, int prev, int sy ,LineaHorizonte salida) {
    Punto paux=new Punto();
    paux.setX(p.getX());                // guardamos en paux esa X
    paux.setY(Math.max(p.getY(), sy)); // y hacemos que el maximo entre la Y del s1 y la altura previa del s2 sea la altura Y de paux

    if (paux.getY()!=prev) // si este maximo no es igual al del segmento anterior
    {
      salida.addPunto(paux); // añadimos el punto al LineaHorizonte de salida
      prev = paux.getY();    // actualizamos prev
    }
    return prev;
  }

  public int fusionarAltosIguales(Punto p1, Punto p2, int prev, LineaHorizonte salida) {
    if ((p1.getY() > p2.getY()) && (p1.getY()!=prev)) // guardaremos aquel punto que tenga la altura mas alta
    {
      salida.addPunto(p1);
      prev = p1.getY();
    }
    if ((p1.getY() <= p2.getY()) && (p2.getY()!=prev))
    {
      salida.addPunto(p2);
      prev = p2.getY();
    }
    return prev;
  }

  public void añadirRestoPuntos(LineaHorizonte s,LineaHorizonte salida, int prev) {
    while ((!s.isEmpty())) //si aun nos quedan elementos en el s
    {
      Punto paux=new Punto();

      paux=s.getPunto(0); // guardamos en paux el primer punto

      if (paux.getY()!=prev) // si paux no tiene la misma altura del segmento previo
      {
        salida.addPunto(paux); // lo añadimos al LineaHorizonte de salida
        prev = paux.getY();    // y actualizamos prev
      }
      s.borrarPunto(0); // en cualquier caso eliminamos el punto de s (tanto si se añade como si no es valido)
    }
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
