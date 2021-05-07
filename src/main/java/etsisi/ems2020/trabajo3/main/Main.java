package etsisi.ems2020.trabajo3.main;


import java.io.FileWriter;
import java.io.PrintWriter;


import etsisi.ems2020.trabajo3.clasesauxiliares.Punto;
import etsisi.ems2020.trabajo3.lineadehorizonte.Ciudad;
import etsisi.ems2020.trabajo3.lineadehorizonte.LineaHorizonte;

public class Main {

	public static void main(String[] args) {
		LineaHorizonte l1=CalcularLineaHorizonteCiudad();
		if(resultadoCorrecto(l1))
			System.out.println("-- Proceso finalizado Correctamente --");
		else
			System.out.println("-- Proceso finalizado incorrectamente --");


		//eliminadas dos lineas de codigo useless
	}
	public static LineaHorizonte CalcularLineaHorizonteCiudad() {
		Ciudad c = new Ciudad();
		c.cargarEdificios("ciudad1.txt");
		LineaHorizonte linea = new LineaHorizonte();
		linea = c.getLineaHorizonte();
		linea.guardaLineaHorizonte("salida.txt");
		return linea;
	}
	public static boolean resultadoCorrecto(LineaHorizonte linea) {//Funciona mal y no se si hay que arreglarlo
		if(
				(linea.getPunto(0).getX()== 1  && linea.getPunto(0).getY()==4)||
				(linea.getPunto(1).getX()== 2  && linea.getPunto(1).getY()==7)||
				(linea.getPunto(2).getX()== 6  && linea.getPunto(2).getY()==9)||
				(linea.getPunto(3).getX()== 8  && linea.getPunto(3).getY()==7)||  
				(linea.getPunto(4).getX()== 9  && linea.getPunto(4).getY()==4)||
				(linea.getPunto(5).getX()== 11 && linea.getPunto(5).getY()==6)||
				(linea.getPunto(6).getX()== 13 && linea.getPunto(6).getY()==0)||
				(linea.getPunto(7).getX()== 14  && linea.getPunto(7).getY()==2)||
				(linea.getPunto(8).getX()== 15  && linea.getPunto(8).getY()==0))
			return true;
		else 
			return false;
	}
}
