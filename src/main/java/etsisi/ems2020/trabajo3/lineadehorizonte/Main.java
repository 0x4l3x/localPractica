package etsisi.ems2020.trabajo3.lineadehorizonte;

public class Main {

	public static void main(String[] args) {
		LineaHorizonte l1=calcularLineaHorizonteCiudad();
			System.out.println("-- Proceso finalizado Correctamente --");



		//eliminadas dos lineas de codigo useless
	}
	public static LineaHorizonte calcularLineaHorizonteCiudad() {
		Ciudad c = new Ciudad();
		c.cargarEdificios("ciudad1.txt");
		LineaHorizonte linea = new LineaHorizonte();
		linea = c.getLineaHorizonte();
		linea.guardaLineaHorizonte("salida.txt");
		return linea;
	}
}
