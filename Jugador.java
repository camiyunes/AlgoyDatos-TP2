package ar.uba.fi.cb100_onto_TP2;

public class Jugador {
    private String nombre;
    private boolean turnoActivo;
    private Carta[] mano = null;
    private int maximoDeCartas = 6;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.turnoActivo = true; // Al comenzar, el turno está activo
        this.mano = new Carta[this.maximoDeCartas];
        for (int i = 0; i < this.mano.length; i++) {
        	this.mano[i] = null;
        }
    }
    
    public Jugador(String nombre, int cartas) throws Exception {
    	if (cartas < 1) {
    		throw new Exception("El jugador debe poder agarrar cartas");
    	}
    	this.nombre = nombre;
    	this.turnoActivo = true;
    	this.maximoDeCartas = cartas;
    	this.mano = new Carta[cartas];
    	for (int i = 0; i < this.mano.length; i++) {
    		this.mano[i] = null;
    	}
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
    
    public int getCantidadActualDeCartas() {
    	int res = 0;
    	for (Carta carta: this.mano) {
    		if (carta != null) {
    			res += 1;
    		}
    	}
    	return res;
    }
    
    public int getMaximoDeCartas() {
    	return this.maximoDeCartas;
    }
    
    public int getCapacidadRestante() {
    	return this.getMaximoDeCartas() - this.getCantidadActualDeCartas();
    }
    
    public void robarDelMazo(Mazo mazo, int numero) throws Exception {
    	if (mazo == null) {
    		throw new Exception("El mazo no puede estar vacío");
    	}
    	int cont = 0;
    	int max = 1;
    	if (numero > this.getCapacidadRestante()) {
    		max = this.getCapacidadRestante();
    	} else {
    		max = numero;
    	}
    	if (max > mazo.getCantidad()) {
    		max = mazo.getCantidad();
    	}
    	while (cont < max) {
    		this.agregarCarta(mazo.sacarCarta());
    	}
    }
    
    private void agregarCarta(Carta carta) throws Exception {
    	for (int i = 0; i < this.mano.length; i++) {
    		if (this.mano[i] == null) {
    			this.mano[i] = carta;
    			return;
    		}
    	}
    	throw new Exception("No hubo espacio para la carta");
    }
    
    public void usarCarta(Carta carta, Jugador rival) throws Exception {
    	if ((carta == null) || (rival == null)) {
    		throw new Exception("Ni la carta ni el objetivo pueden ser nulos");
    	}
    	for (int i = 0; i < this.mano.length; i++) {
    		if ((this.mano[i] != null) && (this.mano[i] == carta)) {
    		    carta.jugarCarta(rival);
    		    this.mano[i] = null;
    		    return;
    		}
    	}
    	throw new Exception("No se ha encontrado la carta");
    }
}