package ar.uba.fi.cb100_onto_TP2;

public class Tablero3D {
	private static int TAMAÑO_DEFAULT = 3;
	private int[][][] tablero;
	private int tamaño = TAMAÑO_DEFAULT;

    // Constructor
	public Tablero3D(int tamaño) throws Exception {
		if (tamaño < 1) { throw new Exception("¿y dónde ponemos las fichas entonces?"); }
		this.tamaño = tamaño;
		this.tablero = new int[tamaño][tamaño][tamaño]; // matriz 3D
		inicializarCeldas(this.tablero);
	}
    
    private void inicializarCeldas(int[][][] tablero2) {
		for (int i = 0; i < tablero2.length; i++) {
			for (int j = 0; j < tablero2.length; j++) {
				for (int k = 0; k < tablero2.length; k++) { tablero2[i][j][k] = 0; }
			}
		}
	}

	// Constructor promedio
	public Tablero3D() {
		this.tablero = new int[TAMAÑO_DEFAULT][TAMAÑO_DEFAULT][TAMAÑO_DEFAULT]; // matriz 3D
		inicializarCeldas(this.tablero);
	}

    // Método para obtener un valor de una posición específica
    public int obtenerValor(int x, int y, int z) throws Exception {
		validarPosicion(x, y, z);
		return tablero[x][y][z];
	}

    //Post: devuelve la forma String del tablero
    public String toString() {
    	String res = "";
    	int i = 0;
    	int j = 0;
    	for (int k = 0; k < tamaño; k++) {
    		while (j < tamaño) {
    			while (i < tamaño) {
    	    		if (i != 0) { res += ", "; }
    				res += ", " + this.tablero[i][j][k];
    				i++;
    			}
    			j++;
    		}
    	}
    	return res;
    }
    
    // Método para imprimir el tablero
	public void imprimirTablero() {
		for (int i = 0; i < tamaño; i++) {
			System.out.println("Piso " + i + ":");
			for (int j = 0; j < tamaño; j++) {
				for (int k = 0; k < tamaño; k++) { System.out.print(tablero[i][j][k] + " "); }
				System.out.println();
			}
			System.out.println();
		}
	}
    
	public boolean partidaTerminada() throws Exception {
		for (int i = -1; i < 1; i++) {
			for (int j = -1; j < 1; j++) {
				for (int k = -1; k < 1; k++) {
					if (tresEnLinea(i, j, k)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean tresEnLinea(int x, int y, int z) throws Exception {
		try {
			for (int i = -1; i < 1; i++) {
				for (int j = -1; j < 1; j++) {
					for (int k = -1; k < 1; k++) {
						int fichasSeguidasExtremo = contarFichasSeguidas(x, y, z, i, j, k);
						if (fichasSeguidasExtremo == 3) { return true; }
					}
				}
			}
		} catch (Exception e) { return false; }
		return false;
	}
	
	public int contarFichasSeguidas(int x1, int y1, int z1, int x2, int y2, int z2) throws Exception {
		if (!posicionValida(x1, y2, z2)) { return 0; }
		if (!posicionValida(x2, y2, z2)) { return 0; }
		if (getValor(x1, y1, z1)!= getValor(x2, y2, z2)) { return 0; }
		int deltaX = x2-x1;
		int deltaY = y2-y1;
		int deltaZ = z2-z1;
		return 1 + contarFichasSeguidas(x2, y2, z2, x2+deltaX, y2+deltaY, z2+deltaZ);
	}
	
    //Post: devuelve true si la posición indicada es válida, y sino, false
    public boolean posicionValida(int x, int y, int z) {
    	return x >= 0 && x < tamaño && y >= 0 && y < tamaño && z >= 0 && z < tamaño;
    }
    
    //Post: devuelve el valor correspondiente a una posicion
    public int getValor(int x, int y, int z) throws Exception {
        validarPosicion(x, y, z);
        return tablero[x][y][z];
    }
    
    // Post: setea un valor en una posición específica
    public void setValor(int x, int y, int z, int valor) throws Exception {
        validarPosicion(x, y, z);
        tablero[x][y][z] = valor;
    }
    
    public void validarPosicion(int x, int y, int z) throws Exception {
    	if (!posicionValida(x, y, z) ) { throw new Exception("Índice fuera de los límites."); }
    }
    
    public void validarPositivo(int n) throws Exception {
    	if (n <= 0) { throw new Exception("Hay que ingresar un parámetro positivo, y " + n + " no lo es. "); }
    }
}