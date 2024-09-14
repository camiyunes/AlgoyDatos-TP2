package ar.uba.fi.cb100_onto_TP2;

public class Mazo {
	private Carta primero = null;
	private Carta ultimo = null;
	private int cantidad = 0;
	
	public Mazo(int cantidadDeCartas) throws Exception {
		if (cantidadDeCartas <= 0) {
			throw new Exception("El mazo debe tener más de 0 cartas");
		}
		
	}
	
	public void agregarCarta(Carta carta) {
		if (this.primero == this.ultimo) {
			if (this.primero == null) {
				this.ultimo = carta;
				this.primero = carta;
			} else {
				carta.getProximo() = this.primero;
				this.primero = carta;
			}
		}
		carta.getProximo() = this.primero;
		this.primero = carta;
	}
}
