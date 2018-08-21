package com.andresfernandeznadalesgmail.calculadorapolacainversa;

public class CPI {
	private double x,y,z,t;
	
	public CPI() {
		x = y = z = t = 0;
	}
	
	public void entra(double v) {
		t = z;
		z = y;
		y = x;
		x = v;
	}
	
	
	public void intercambia() {
		double v = x;
		x = y;
		y = v;
	}
	
	public void suma() {
		double res = y + x;
		x = res;
		y = z;
		z = t;
	}
	
	public void resta() {
		double res = y - x;
		x = res;
		y = z;
		z = t;
	}
	
	public void multiplica() {
		double res = y * x;
		x = res;
		y = z;
		z = t;
	}

	public void divide() {
		double res = y / x;
		x = res;
		y = z;
		z = t;
	}

	public double getResultado() {
		return x;
	}
	
	public String toString() {
		return "CPI(x = "+ x + " y = " + y + " z = " + z + " t = " + t+ ")";
	}
	
}
