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
	
	public String getCarta() {
        return this.jugada;
    }

    public void jugarCarta() {
        switch (this.jugada) {
            case "Perder el turno":
                System.out.println("El jugador pierde su turno.");
                break;
            case "Bloquear ficha":
                System.out.println("Se bloquea la ficha de otro jugador.");
                // bloquear una ficha de otro jugador
                break;
            case "Anular casillero":
                System.out.println("Se anula un casillero del tablero.");
                // anular un casillero del tablero
                break;
            case "Volver atrás jugada":
                System.out.println("Se vuelve atrás una jugada del turno.");
                // volver atrás una jugada
                break;
            case "Cambiar color ficha":
                System.out.println("Se cambia el color de una ficha.");
                // cambiar el color de una ficha
                break;
            case "Intercambiar fichas":
                System.out.println("Se intercambian dos fichas.");
                // intercambiar dos fichas
                break;
            default:
                System.out.println("Jugada desconocida.");
        }
    }
	
}
