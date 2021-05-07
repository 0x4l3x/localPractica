package etsisi.ems2020.trabajo3.clasesauxiliares;

public class Edificio {
	private int xIzquierda;
	private int y;
	private int xDerecha;

	public Edificio()
	{
		this.xDerecha = 0;
		this.xIzquierda = 0;
		this.y = 0;
	}    
	public Edificio(int xIzquierda, int y, int xDerecha)
	{
		this.xDerecha = xDerecha;
		this.xIzquierda = xIzquierda;
		this.y = y;
	}    
	public int getXi() {
		return this.xIzquierda;
	}
	public void setXi(int xIzquierda) {
		this.xIzquierda = xIzquierda;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getxDerecha() {
		return this.xDerecha;
	}
	public void setxDerecha(int xDerecha) {
		this.xDerecha = xDerecha;
	}
}
