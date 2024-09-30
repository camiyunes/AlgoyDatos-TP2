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
	
	public void setProximo(Carta prox) {
		this.proximo = prox;
	}

	public String getAccion() {
		return this.accion;
	}
	
	// Ejecutar el efecto de la carta
	public void jugarCarta(Jugador jugador) throws Exception {  
		switch (this.getAccion()) {
			case "Perder el turno":
				perderTurno(jugador);
				break;
			case "Bloquear ficha":
				bloquearFicha();
				break;
			case "Anular casillero":
				anularCasillero();
				break;
			case "Volver atrás jugada":
				volverAtras();
				break;
			case "Cambiar color ficha":
				cambiarColor();
				break;
			case "Intercambiar fichas":
				intercambiarFichas();
				break;
			default:
				System.out.println("Tipo de carta desconocido.");
		}
	}

	// Métodos específicos para cada acción
	private void perderTurno(Jugador jugador) throws Exception {
		if (jugador != null) {
            jugador.perderTurno();
            System.out.println(jugador.getNombre() + " ha perdido el turno.");
        } else {
            throw new Exception("No se puede aplicar la acción, el jugador no es válido.");
        }
	}

	private void bloquearFicha() {
		System.out.println("Se bloquea la ficha de otro jugador.");
	}

	private void anularCasillero() {
		System.out.println("Se anula un casillero del tablero.");
	}

	private void volverAtras() {
		System.out.println("Se vuelve atrás una jugada del turno.");
	}

	private void cambiarColor() {
		System.out.println("Se cambia el color de una ficha.");
	}

	private void intercambiarFichas() {
		System.out.println("Se intercambian dos fichas.");
	}

}
