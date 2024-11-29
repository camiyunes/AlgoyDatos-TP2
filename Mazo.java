package ar.uba.fi.cb100_onto_TP2;

public class Mazo {
	private Carta primero = null;
	private Carta ultimo = null;
	private int cantidad = 0;
	
	public Mazo() {
		this.primero = null;
		this.ultimo = null;
		this.cantidad = 0;
	}
	
	public void agregarCarta(Carta carta) {
		if (this.primero == this.ultimo) {
			if (this.primero == null) {
				this.ultimo = carta;
				this.primero = carta;
			} else {
				carta.setProximo(this.primero);
				this.primero = carta;
			}
		}
		carta.setProximo(this.primero);
		this.primero = carta;
		this.cantidad += 1;
	}
	
	public Carta sacarCarta() throws Exception {
		Carta res = null;
		if (this.primero == this.ultimo) {
			if (this.primero == null) {
				throw new Exception("El mazo está vacío");
			}
			res = this.primero;
			this.primero = null;
			this.ultimo = null;
		} else {
			res = this.primero;
			this.primero = res.getProximo();
		}
		this.cantidad -= 1;
		return res;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
}
