package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;



public class LineaHorizonte {

	private  ArrayList <Punto> lineaHorizonte ;

	/*
	 * Constructor sin par�metros
	 */
	public LineaHorizonte()
	{
		lineaHorizonte = new ArrayList <Punto>();
	}

	/*
	 * m�todo que devuelve un Punto de la LineaHorizonte
	 */
	public Punto getPunto(int i) {
		return this.lineaHorizonte.get(i);// eliminado el (Punto)
	}

	public void addPunto(Punto p)
	{
		lineaHorizonte.add(p);
	}

	public void borrarPunto(int i)
	{
		lineaHorizonte.remove(i);
	}

	public int size()
	{
		return lineaHorizonte.size();
	}

	public boolean isEmpty()
	{
		return lineaHorizonte.isEmpty();
	}
	
	@Override
	public String toString() {
		String linea="";
		for(Punto punto: lineaHorizonte) {
			linea+=punto.toString()+"\n";
		}
		return linea;
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
			Punto aux;//creo que se puede eliminar pero creo q no renta
			for(int i=0; i<this.size(); i++)
			{
				aux=getPunto(i);
				out.println(aux.getX()+" "+ aux.getY());
			}
			out.close();
		}
		catch(Exception e){        
			e.getStackTrace();
		}
	}

	public void lineaHorizonteFussion(LineaHorizonte s1,LineaHorizonte s2){//Cambiar nombre a fusionarLineas
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
		this.aniadirRestoPuntos(s1, yPrevSegIntrod);
		this.aniadirRestoPuntos(s2, yPrevSegIntrod);
	}


	public int fusionarAltosDiferentes(Punto actual, int yPrevSegIntrod, int yPuntoPrev ) //Cambiar nombre a fusionarYDiferentes
	{ 
		int yMax=Math.max(actual.getY(), yPuntoPrev);

		if (yMax!=yPrevSegIntrod)
		{
			this.addPunto(new Punto(actual.getX(), yMax)); 
			yPrevSegIntrod =yMax;   
		}
		return yPrevSegIntrod;
	}
	
	public int fusionarAltosIguales(Punto puntoS1, Punto puntoS2, int yPrevSegIntrod) {// cambiar a fusionarYiguales
		if ((puntoS1.getY() > puntoS2.getY()) && (puntoS1.getY()!=yPrevSegIntrod)) //sustituir por un metodo en Punto
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

	public void aniadirRestoPuntos(LineaHorizonte restante,int yPrevSegIntrod) {
		while (!restante.isEmpty())
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
