package ar.uba.fi.cb100_onto_TP2;

import java.util.Random;

public class Dado {
	private int cantidadDeCaras = 2;
	Random random = new Random(System.nanoTime());
	
	public Dado(int cantidadDeCaras) throws Exception {
		if (cantidadDeCaras <= 1) {
			throw new Exception("El dado no puede tener menos de 2 caras");
		}
		this.cantidadDeCaras = cantidadDeCaras;
	}
	
	public int lanzarDado() {
		 return random.nextInt(this.cantidadDeCaras);
	}
}
