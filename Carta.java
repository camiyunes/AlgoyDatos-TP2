package ar.uba.fi.cb100_onto_TP2;

public class Carta {
	private Carta proximo = null;
	private String accion = null;
	
	public Carta(String accion) throws Exception {
		if (accion == "") {
			throw new Exception("La acción no puede ser nula");
		}
		this.accion = accion;
		this.proximo = null;
	}
	
	public Carta getProximo() {
		Carta prox = proximo;
		return prox;
	}
	
	
}
