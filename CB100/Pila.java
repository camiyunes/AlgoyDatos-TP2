package ar.fiuba.CB100;

public class Pila<T> {
	private Nodo<T> prim = null;
	
	public Pila() {}
	
	public Pila(T dato) {
		this.prim = new Nodo<T>(dato);
	}
	
	public void apilar(T dato) {
		Nodo<T> nuevo = new Nodo<T>(dato);
		nuevo.setProx(this.prim);
		this.prim = nuevo;
	}
	
	public T desapilar() {
		T res = this.verTope();
		this.prim = this.prim.getProx();
		return res;
	}
	
	public T verTope() {
		return this.prim.getDato();
	}
}
