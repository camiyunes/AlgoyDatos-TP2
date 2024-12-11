package ar.fiuba.CB100;

public class Nodo<T> {
	private T dato = null;
	private Nodo<T> prox = null;
	
	public Nodo() {}
	
	public Nodo(T dato) {
		this.dato = dato;
	}
	
	public T getDato() {
		return this.dato;
	}
	
	public Nodo<T> getProx() {
		return this.prox;
	}
	
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	public void setProx(Nodo<T> nodo) {
		this.prox = nodo;
	}
}
