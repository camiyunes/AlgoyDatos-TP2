package ar.fiuba.CB100;

public class Casillero<T> {
	public static int CANTIDAD_DE_VECINOS = 3;
	
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private T dato = null;
	private Casillero<T>[][][] vecinos;
	private boolean anulado = false;
	
	public Casillero(int x, int y, int z) throws Exception {
		if (x < 1) { throw new Exception("X debe ser mayor que 0"); }
		if (y < 1) { throw new Exception("Y debe ser mayor que 0"); }
		if (z < 1) { throw new Exception("Z debe ser mayor que 0"); }
		this.x = x;
		this.y = y;
		this.z = z;
		this.vecinos= new Casillero[CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS];
		for (int i = 0; i < this.vecinos.length; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) { this.vecinos[i][j][k] = null; }
			}
		}
		this.vecinos[1][1][1] = this;
	}
	
	public Casillero(int x, int y, int z, T dato) throws Exception {
		if (x < 1) { throw new Exception("X debe ser mayor que 0"); }
		if (y < 1) { throw new Exception("Y debe ser mayor que 0"); }
		if (z < 1) { throw new Exception("Z debe ser mayor que 0"); }
		this.x = x;
		this.y = y;
		this.z = z;
		this.vecinos= new Casillero[CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS];
		for (int i = 0; i < this.vecinos.length; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) { this.vecinos[i][j][k] = null; }
			}
		}
		this.vecinos[1][1][1] = this;
		this.dato = dato;
	}
	
	public boolean existeElVecino(Direccion movimiento) {
		switch (movimiento) {
			case ABAJO:
				return this.vecinos[2][1][1] != null;
			case ARRIBA:
				return this.vecinos[0][1][1] != null;
			case DERECHA:
				return this.vecinos[1][2][1] != null;
			case IZQUIERDA:
				return this.vecinos[1][0][1] != null;
			case ATRAS:
				return this.vecinos[1][1][2] != null;
			case ADELANTE:
				return this.vecinos[1][1][0] != null;
			case ABAJO_DERECHA:
				return this.vecinos[2][2][1] != null;
			case ARRIBA_DERECHA:
				return this.vecinos[0][2][1] != null;
			case ABAJO_IZQUIERDA:
				return this.vecinos[2][0][1] != null;
			case ARRIBA_IZQUIERDA:
				return this.vecinos[0][0][1] != null;
			
		}
		return false;
	}
	
	public void anular() { this.anulado = true; }
	
	public void desanular() { this.anulado = false; }
	
	public boolean estaAnulado() { return this.anulado; }
	
	public String toString() { return "'" + this.dato + "'"; }
	
	public int getX() { return x; }
	
	public int getY() { return y; }
	
	public int getZ() { return z; }
	
	public T getDato() { return this.dato; }
	
	public void setDato(T dato) { this.dato = dato; }
	
	public T vaciar() {
		T dato = this.dato;
		this.dato = null;
		return dato;
	}
	
	public void setCasilleroVecino(Casillero<T> casillero, int x, int y, int z) throws Exception {
		if (casillero == null) { throw new Exception("El casillero no puede ser nulo"); }
		this.vecinos[x+1][y+1][z+1] = casillero;
	}
}
