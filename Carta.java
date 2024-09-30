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

	// Ejecutar el efecto de la carta
	public void jugarCarta() {  
		switch (this.getCarta) {
			case "Perder el turno":
				ejecutarPerderTurno();
				break;
			case "Bloquear ficha":
				ejecutarBloquearFicha();
				break;
			case "Anular casillero":
				ejecutarAnularCasillero();
				break;
			case "Volver atrás jugada":
				ejecutarVolverAtras();
				break;
			case "Cambiar color ficha":
				ejecutarCambiarColor();
				break;
			case "Intercambiar fichas":
				ejecutarIntercambiarFichas();
				break;
			default:
				System.out.println("Tipo de carta desconocido.");
		}
	}

	// Métodos específicos para cada acción
	private void ejecutarPerderTurno(Jugador jugador) {
		if (jugador != null) {
            jugador.perderTurno();
            System.out.println(jugador.getNombre() + " ha perdido el turno.");
        } else {
            System.out.println("No se puede aplicar la acción, el jugador no es válido.");
        }
	}

	private void ejecutarBloquearFicha() {
		System.out.println("Se bloquea la ficha de otro jugador.");
	}

	private void ejecutarAnularCasillero() {
		System.out.println("Se anula un casillero del tablero.");
	}

	private void ejecutarVolverAtras() {
		System.out.println("Se vuelve atrás una jugada del turno.");
	}

	private void ejecutarCambiarColor() {
		System.out.println("Se cambia el color de una ficha.");
	}

	private void ejecutarIntercambiarFichas() {
		System.out.println("Se intercambian dos fichas.");
	}

}
