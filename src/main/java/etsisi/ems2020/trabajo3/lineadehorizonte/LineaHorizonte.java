package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import etsisi.ems2020.trabajo3.clasesauxiliares.Punto;


public class LineaHorizonte {

	private ArrayList <Punto> LineaHorizonte ;

    /*
     * Constructor sin par�metros
     */
    public LineaHorizonte()
    {
        LineaHorizonte = new ArrayList <Punto>();
    }

    /*
     * m�todo que devuelve un objeto de la clase Punto
     */
    public Punto getPunto(int i) {
        return (Punto)this.LineaHorizonte.get(i);
    }

    // A�ado un punto a la l�nea del horizonte
    public void addPunto(Punto p)
    {
        LineaHorizonte.add(p);
    }

    // m�todo que borra un punto de la l�nea del horizonte
    public void borrarPunto(int i)
    {
        LineaHorizonte.remove(i);
    }

    public int size()
    {
        return LineaHorizonte.size();
    }
    // m�todo que me dice si la l�nea del horizonte est� o no vac�a
    public boolean isEmpty()
    {
        return LineaHorizonte.isEmpty();
    }

    /*
      M�todo al que le pasamos una serie de par�metros para poder guardar
      la linea del horizonte resultante despu�s de haber resuelto el ejercicio
      mediante la t�cnica de divide y vencer�s.
     */

    public void guardaLineaHorizonte (String fichero)
    {
        try
        {
            Punto p = new Punto();
            FileWriter fileWriter = new FileWriter(fichero);
            PrintWriter out = new PrintWriter (fileWriter);

            for(int i=0; i<this.size(); i++)
            {
                p=(getPunto(i));
                out.print(p.getX());
                out.print(" ");
                out.print(p.getY());
                out.println();
            }
            out.close();
        }
        catch(Exception e){}
    }

    public void imprimir (){ //creo q tambien se puede cambiar el nombre
    	for(int i=0; i< LineaHorizonte.size(); i++ ){
    		System.out.println(LineaHorizonte.get(i).toString());
    	}
    }
    public void LineaHorizonteFussion(LineaHorizonte s1,LineaHorizonte s2){
	    int s1y=-1, s2y=-1, prev=-1; // en estas variables guardaremos las alturas de los puntos anteriores y en prev guardaremos la previa del segmento anterior introducido
	    while ((!s1.isEmpty()) && (!s2.isEmpty()))     //Mientras tengamos elementos en s1 y en s2
	    {
	      Punto p1 = s1.getPunto(0); // guardamos el primer elemento de s1
	      Punto p2 = s2.getPunto(0); // guardamos el primer elemento de s2

	      if (p1.getX() < p2.getX()) // si X del s1 es menor que la X del s2
	      {
	        prev=fusionarAltosDif(p1, prev, s2y);
	        s1y = p1.getY();   // actualizamos la altura s1y
	        s1.borrarPunto(0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es valido)
	      }
	      else if (p1.getX() > p2.getX()) // si X del s1 es mayor que la X del s2
	      {
	        prev=fusionarAltosDif(p2, prev, s1y);
	        s2y = p2.getY();   // actualizamos la altura s2y
	        s2.borrarPunto(0); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es valido)
	      }
	      else // si la X del s1 es igual a la X del s2
	      {
	        prev=fusionarAltosIguales(p1,p2,prev);
	        s1y = p1.getY();   // actualizamos la s1y e s2y
	        s2y = p2.getY();
	        s1.borrarPunto(0); // eliminamos el punto del s1 y del s2
	        s2.borrarPunto(0);
	      }
	    }
	    añadirRestoPuntos(s1, prev);
	    añadirRestoPuntos(s2, prev);
	  }


	  public int fusionarAltosDif(Punto p, int prev, int sy ) {
	    Punto paux=new Punto();
	    paux.setX(p.getX());                // guardamos en paux esa X
	    paux.setY(Math.max(p.getY(), sy)); // y hacemos que el maximo entre la Y del s1 y la altura previa del s2 sea la altura Y de paux

	    if (paux.getY()!=prev) // si este maximo no es igual al del segmento anterior
	    {
	      this.addPunto(paux); // añadimos el punto al LineaHorizonte de salida
	      prev = paux.getY();    // actualizamos prev
	    }
	    return prev;
	  }

	  public int fusionarAltosIguales(Punto p1, Punto p2, int prev) {
	    if ((p1.getY() > p2.getY()) && (p1.getY()!=prev)) // guardaremos aquel punto que tenga la altura mas alta
	    {
	      this.addPunto(p1);
	      prev = p1.getY();
	    }
	    if ((p1.getY() <= p2.getY()) && (p2.getY()!=prev))
	    {
	      this.addPunto(p2);
	      prev = p2.getY();
	    }
	    return prev;
	  }

	  public void añadirRestoPuntos(LineaHorizonte restante,int prev) {
	    while ((!restante.isEmpty())) //si aun nos quedan elementos en el s
	    {
	      Punto paux=new Punto();

	      paux=restante.getPunto(0); // guardamos en paux el primer punto

	      if (paux.getY()!=prev) // si paux no tiene la misma altura del segmento previo
	      {
	        this.addPunto(paux); // lo añadimos al LineaHorizonte de salida
	        prev = paux.getY();    // y actualizamos prev
	      }
	      restante.borrarPunto(0); // en cualquier caso eliminamos el punto de s (tanto si se añade como si no es valido)
	    }
	  }
}
