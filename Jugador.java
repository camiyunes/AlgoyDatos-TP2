package ar.uba.fi.cb100_onto_TP2;

public class Jugador {
    private String nombre;
    private boolean turnoActivo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.turnoActivo = true; // Al comenzar, el turno está activo
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isTurnoActivo() {
        return turnoActivo;
    }

    public void perderTurno() {
        this.turnoActivo = false; // El jugador pierde su turno
    }

    public void reiniciarTurno() {
        this.turnoActivo = true; // Reiniciar el turno al comenzar de nuevo
    }
}