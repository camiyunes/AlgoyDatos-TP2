package ar.uba.fi.cb100_onto_TP2;

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
        Ficha ficha = new Ficha(jugador.getFicha(), casillero.getX(), casillero.getY(), casillero.getZ());
        tablero.setValor(casillero.getX(), casillero.getY(), casillero.getZ(), ficha);

        // Usar una carta si el jugador decide hacerlo
        if (carta != null) {
            switch (carta.getAccion()) {
                case "Perder el turno":
                case "Volver atrás jugada":
                    carta.jugarCarta(jugador);
                    break;
                case "Bloquear ficha":
                case "Anular casillero":
                    carta.jugarCarta(tablero, casillero.getX(), casillero.getY(), casillero.getZ());
                    break;
                case "Cambiar color ficha":
                case "Intercambiar fichas":
                    carta.jugarCarta(jugador, tablero, casillero.getX(), casillero.getY(), casillero.getZ());
                    break;
                default:
                    throw new Exception("Acción de carta desconocida.");
            }
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