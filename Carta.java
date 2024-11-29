package ar.uba.fi.cb100_onto_TP2;

public class Carta {
	private Carta proximo = null;
	private String accion = null;
	
	private static Long IDactual = 1L;
	private Long id = null;
	private String titulo = null;
	
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

	public String getAccion() {
		return this.accion;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setProximo(Carta prox) {
		this.proximo = prox;
	}
	
	// Ejecutar el efecto de la carta
	public void jugarCarta(Jugador jugador) throws Exception {  
		switch (this.getAccion()) {
			case "Perder el turno":
				perderTurno(jugador);
				break;
			case "Volver atrás jugada":
				volverAtras(jugador);
				break;
			default:
				System.out.println("Tipo de carta desconocido.");
		}
	}
	
	public void jugarCarta(Tablero3D tablero, int x, int y, int z) throws Exception {  
		switch (this.getAccion()) {
			case "Bloquear ficha":
				bloquearFicha(tablero, x, y, z);
				break;
			case "Anular casillero":
				anularCasillero(tablero, x, y , z);
				break;
			default:
				System.out.println("Tipo de carta desconocido.");
		}
	}
	
	public void jugarCarta(Jugador jugador, Tablero3D tablero, int x, int y, int z) throws Exception {  
		switch (this.getAccion()) {
			case "Cambiar color ficha":
				cambiarColor(jugador, tablero, x, y, z);
				break;
			case "Intercambiar fichas":
				intercambiarFichas(jugador, tablero, x, y, z);
				break;
			default:
				System.out.println("Tipo de carta desconocido.");
		}
	}

	// Métodos específicos para cada acción
	private void perderTurno(Jugador jugador) throws Exception {
		validarJugador(jugador);
		jugador.perderTurno();
		System.out.println(jugador.getNombre() + " ha perdido el turno.");

	}

	private void bloquearFicha(Tablero3D tablero, int x, int y , int z) throws Exception {
		validarTablero(tablero);
		tablero.validarPosicion(x, y, z);
		tablero.bloquearFicha(x, y, z);
		System.out.println("Se bloquea la ficha de otro jugador.");
	}

	private void anularCasillero(Tablero3D tablero, int x, int y , int z) throws Exception {
		if (tablero != null) {
			tablero.anularCasillero(x, y, z);
			System.out.println("Se anula un casillero del tablero.");
		} else {
			throw new Exception("El casillero es inválido.");
		}
	}

	private void volverAtras(Jugador jugador) throws Exception {
		validarJugador(jugador);
		jugador.deshacerUltimaJugada();
		System.out.println("Se vuelve atrás la última jugada de " + jugador.getNombre() + ".");
	}

	private void cambiarColor(Jugador jugador, Tablero3D tablero, int x, int y, int z) throws Exception {
		validarJugador(jugador);
		validarTablero(tablero);
		tablero.validarPosicion(x, y, z);
		tablero.cambiarColorFicha(jugador.getNombre(), x, y, z);
		System.out.println("Se cambia el color de una ficha.");
	}

	private void intercambiarFichas(Jugador jugador, Tablero3D tablero, int x, int y, int z) throws Exception {
		validarJugador(jugador);
		validarTablero(tablero);
		tablero.validarPosicion(x, y, z);
		tablero.intercambiarFichas(jugador, x, y, z);
		System.out.println("Se intercambian dos fichas.");
	}
	
	public void validarJugador(Jugador jugador) throws Exception {
		if (jugador == null) { throw new Exception("Se te olvidó referenciar el jugador"); }
	}
	
	public void validarTablero(Tablero3D tablero) throws Exception {
		if (tablero == null) { throw new Exception("Perdón, pero la nada no es un tablero"); }
	}
}
