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
		if (jugador != null) {
            jugador.perderTurno();
            System.out.println(jugador.getNombre() + " ha perdido el turno.");
        } else {
            throw new Exception("No se puede aplicar la acción, el jugador no es válido.");
        }
	}

	private void bloquearFicha(Tablero3D tablero, int x, int y , int z) {
		if (tablero != null) {
			//tablero.bloquearFicha(x, y, z);
			System.out.println("Se bloquea la ficha de otro jugador.");
		} else {
			throw new Exception("La ficha es inválida.");
		}
	}

	private void anularCasillero(Tablero3D tablero, int x, int y , int z) {
		if (tablero != null) {
			//tablero.anularCasillero(x, y, z);
			System.out.println("Se anula un casillero del tablero.");
		} else {
			throw new Exception("El casillero es inválido.");
		}
	}

	private void volverAtras(Jugador jugador) {
		if (jugador != null) {
			//jugador.deshacerUltimaJugada();
			System.out.println("Se vuelve atrás la última jugada de " + jugador.getNombre() + ".");
		} else {
			throw new Exception("El jugador no es válido.");
		}
	}

	private void cambiarColor(Jugador jugador, Tablero3D tablero, int x, int y, int z) {
		if (jugador != null & tablero != null) {
			//tablero.cambiarColorFicha(jugador.getNombre(), int x, int y, int z);
			System.out.println("Se cambia el color de una ficha.");
		} else {
			throw new Exception("El jugador y/o el tablero no es/son válido/s.");
		}
	}

	private void intercambiarFichas(Jugador jugador, Tablero3D tablero, int x, int y, int z) {
		if (jugador != null & tablero != null) {
			//tablero.intercambiarFichas(jugador.getNombre(), int x, int y, int z);
			System.out.println("Se intercambian dos fichas.");
		} else {
			throw new Exception("El jugador y/o el tablero no es/son válido/s.");
		}
	}

}
