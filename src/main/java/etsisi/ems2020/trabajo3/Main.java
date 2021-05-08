package etsisi.ems2020.trabajo3;


import java.io.FileWriter;
import java.io.PrintWriter;

import etsisi.ems2020.trabajo3.lineadehorizonte.Ciudad;
import etsisi.ems2020.trabajo3.lineadehorizonte.LineaHorizonte;


public class Main {

	public static void main(String[] args) {
		LineaHorizonte l1=CalcularLineaHorizonteCiudad();
			System.out.println("-- Proceso finalizado Correctamente --");



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
}
