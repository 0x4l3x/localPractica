package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


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
	 * m�todo que devuelve un Punto de la LineaHorizonte
	 */
	public Punto getPunto(int i) {
		return this.LineaHorizonte.get(i);// eliminado el (Punto)
	}

	public void addPunto(Punto p)
	{
		LineaHorizonte.add(p);
	}

	public void borrarPunto(int i)
	{
		LineaHorizonte.remove(i);
	}

	public int size()
	{
		return LineaHorizonte.size();
	}

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
			FileWriter fileWriter = new FileWriter(fichero);
			PrintWriter out = new PrintWriter (fileWriter);

			for(int i=0; i<this.size(); i++)
			{
				out.print(getPunto(i).getX());
				out.print(" ");
				out.print(getPunto(i).getY());
				out.println();
			}
			out.close();
		}
		catch(Exception e){        
			System.out.println("Exception thrown  :" + e);
		}
	}

	@Override
	public String toString() {
		String linea=new String();
		for(int i=0; i< LineaHorizonte.size(); i++ ){
			linea+=LineaHorizonte.get(i).toString()+"\n";
		}
		return linea;
	}
	public void LineaHorizonteFussion(LineaHorizonte s1,LineaHorizonte s2){
		int yPrevPuntoS1=-1, yPrevPuntoS2=-1, yPrevSegIntrod=-1; 
		
		while ((!s1.isEmpty()) && (!s2.isEmpty())) 
		{
			Punto puntoS1 = s1.getPunto(0);
			Punto puntoS2 = s2.getPunto(0); 

			if (puntoS2.tieneXMayorQue(puntoS1)) 
			{
				yPrevSegIntrod=fusionarAltosDiferentes(puntoS1, yPrevSegIntrod, yPrevPuntoS2);
				yPrevPuntoS1 = puntoS1.getY(); 
				s1.borrarPunto(0);
			}
			else if (puntoS1.tieneXMayorQue(puntoS2))
			{
				yPrevSegIntrod=fusionarAltosDiferentes(puntoS2, yPrevSegIntrod, yPrevPuntoS1);
				yPrevPuntoS2 = puntoS2.getY();  
				s2.borrarPunto(0);
			}
			else if(puntoS1.tienenXiguales(puntoS2))
			{
				yPrevSegIntrod=fusionarAltosIguales(puntoS1,puntoS2,yPrevSegIntrod);
				yPrevPuntoS1 = puntoS1.getY(); 
				yPrevPuntoS2 = puntoS2.getY();
				s1.borrarPunto(0); 
				s2.borrarPunto(0);
			}
		}
		añadirRestoPuntos(s1, yPrevSegIntrod);
		añadirRestoPuntos(s2, yPrevSegIntrod);
	}


	public int fusionarAltosDiferentes(Punto actual, int yPrevSegIntrod, int yPuntoPrev ) {
		int yMax;
		yMax=(Math.max(actual.getY(), yPuntoPrev));

		if (yMax!=yPrevSegIntrod)
		{
			this.addPunto(new Punto(actual.getX(), yMax)); 
			yPrevSegIntrod =yMax;   
		}
		return yPrevSegIntrod;
	}
	
	public int fusionarAltosIguales(Punto puntoS1, Punto puntoS2, int yPrevSegIntrod) {
		if ((puntoS1.getY() > puntoS2.getY()) && (puntoS1.getY()!=yPrevSegIntrod)) 
		{
			this.addPunto(puntoS1);
			yPrevSegIntrod = puntoS1.getY();
		}
		if ((puntoS1.getY() <= puntoS2.getY()) && (puntoS2.getY()!=yPrevSegIntrod))
		{
			this.addPunto(puntoS2);
			yPrevSegIntrod = puntoS2.getY();
		}
		return yPrevSegIntrod;
	}

	public void añadirRestoPuntos(LineaHorizonte restante,int yPrevSegIntrod) {
		while ((!restante.isEmpty()))
		{
			Punto paux=restante.getPunto(0);

			if (paux.getY()!=yPrevSegIntrod)
			{
				this.addPunto(paux);
				yPrevSegIntrod = paux.getY();
			}
			restante.borrarPunto(0);
		}
	}
}
