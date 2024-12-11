package ar.fiuba.CB100;

public class Jugada {
	private Jugador jugador;
	private Casillero casillero;
	private Carta carta;
	private Tablero3D tablero;
	
	public Jugada(Jugador jugador, Casillero casillero, Carta carta, Tablero3D tablero) {
		this.jugador = jugador;
		this.casillero = casillero;
		this.carta = carta;
		this.tablero = tablero;
	}
    
	public void realizarJugada() throws Exception {
		// Colocar una ficha en el casillero
		Ficha ficha = new Ficha(jugador.getFicha().getColor(), casillero.getX(), casillero.getY(), casillero.getZ());
		tablero.setValor(casillero.getX(), casillero.getY(), casillero.getZ(), ficha);

		// Usar una carta si el jugador decide hacerlo
		if (carta == null) { throw new Exception("Pero si no est�s jugando ninguna carta, amigo"); }
		switch (carta.getAccion()) {
			case "Perder el turno":
				carta.jugarCarta(jugador);
			case "Volver atras jugada":
				carta.jugarCarta(jugador);
				break;
			case "Bloquear ficha":
				carta.jugarCarta(tablero, casillero.getX(), casillero.getY(), casillero.getZ());
			case "Anular casillero":
				carta.jugarCarta(tablero, casillero.getX(), casillero.getY(), casillero.getZ());
				break;
			case "Cambiar color ficha":
				carta.jugarCarta(jugador, tablero, casillero.getX(), casillero.getY(), casillero.getZ());
			case "Intercambiar fichas":
				carta.jugarCarta(jugador, tablero, casillero.getX(), casillero.getY(), casillero.getZ());
			case "Rehacer jugada":
				carta.jugarCarta(jugador);
			default:
				throw new Exception("Acci�n de carta desconocida.");
		}

		// Actualizar el estado del tablero
		tablero.imprimirTablero();
    }

	@Override
	public String toString() {
		return "Jugada{" +
			"jugador=" + jugador +
			", casillero=" + casillero +
			", carta=" + carta +
			", tablero=" + tablero +
			'}';
    }
}
