package ar.uba.fi.cb100_onto_TP2;

public class EventosDelTiempo {
	private Pila<Jugada>[] jugadasPasadas;
	private Pila<Jugada>[] jugadasFuturas;
	
	public EventosDelTiempo(int n) throws Exception {
		if (n <= 0) { throw new Exception("con " + n + " no se puede guardar elementos. Lo siento"); }
		this.jugadasPasadas = new Pila[n];
		this.jugadasFuturas = new Pila[n];
	}
	
	public void apilarPasado(int n, Jugada jugada) throws Exception {
		validarRango(n, this.jugadasPasadas);
		this.jugadasPasadas[n].apilar(jugada);
	}
	
	public void apilarFuturo(int n, Jugada jugada) throws Exception {
		validarRango(n, this.jugadasFuturas);
		this.jugadasFuturas[n].apilar(jugada);
	}
	
	public Jugada desapilarPasado(int n) throws Exception {
		validarRango(n, this.jugadasPasadas);
		return this.jugadasPasadas[n].desapilar();
	}
	
	public Jugada desapilarFuturo(int n) throws Exception {
		validarRango(n, this.jugadasFuturas);
		return this.jugadasFuturas[n].desapilar();
	}
	
	public Jugada obtenerFuturo(int n) throws Exception {
		validarRango(n, this.jugadasFuturas);
		return this.jugadasFuturas[n].verTope();
	}
	
	public Jugada obtenerPasado(int n) throws Exception {
		validarRango(n, this.jugadasPasadas);
		return this.jugadasPasadas[n].verTope();
	}
	
	public void validarRango(int n, Pila<Jugada>[] jugadas) throws Exception {
		if (n < 0 || n >= jugadas.length) { throw new Exception(n + " no está en el rango entre 0 y " + this.jugadasPasadas.length); }
	}
}
