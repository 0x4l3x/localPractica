package etsisi.ems2020.trabajo3.clasesauxiliares;

/*
 * 
 * @author Juan Manuel
 * Clase para definir un punto sobre el eje
 * cartesiano de coordendas
 */
public class Punto {
	int x;
	int y;

	/*
	 * Constructor sin par�metros de un punto en concreto
	 */
	public Punto() {
		this.x = 0;
		this.y = 0;
	}

	/*
	 * Constructos con par�metros de un punto
	 * @param x
	 * @param y
	 */
	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/*
	 * 
	 * Get de la coordenada X
	 */
	public int getX() {
		return x;
	}

	/*
	 * 
	 * Set de la coordenada X
	 */
	public void setX(int x) {
		this.x = x;
	}
	/*
 	   Get de la coordenada Y
	 */
	public int getY() {
		return y;
	}
	/* 
	 * Set de la coordenada Y
	 */
	public void setY(int y) {
		this.y = y;
	}
/*Eliminado metodo distancia*/
	
	@Override
	public String toString() {
		String punto = "Punto [x=";
		punto = punto + x;
		punto = punto + ", y=";
		punto = punto + y;
		punto = punto +  "]";
		return punto;
	}

	public boolean tieneXMayorQue(Punto p2) {
		return this.getX()>p2.getX();
	}
	public boolean tienenXiguales(Punto p2) {
		return this.getX()==p2.getX();
	}

}