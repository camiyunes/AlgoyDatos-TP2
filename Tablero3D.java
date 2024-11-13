package ar.uba.fi.cb100_onto_TP2;

public class Tablero3D<T> {
	private static int TAMAÑO_DEFAULT = 3;
    private int[][][] tablero;
    private int tamaño = TAMAÑO_DEFAULT;

    // Constructor
    public Tablero3D(int tamaño) throws Exception {
    	if (tamaño < 1) { throw new Exception("¿y dónde ponemos las fichas entonces?"); }
    	this.tamaño = tamaño;
        this.tablero = new int[tamaño][tamaño][tamaño]; // matriz 3D
    }
    
    // Constructor promedio
    public Tablero3D() {
    	this.tablero = new int[TAMAÑO_DEFAULT][TAMAÑO_DEFAULT][TAMAÑO_DEFAULT]; // matriz 3D
    }

    // Método para obtener un valor de una posición específica
    public int obtenerValor(int x, int y, int z) {
    	if (posicionValida(x, y, z)) { return tablero[x][y][z]; }
        return -1; // Valor de error
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
                for (int k = 0; k < tamaño; k++) {
                    System.out.print(tablero[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    //Post: devuelve true si la posición indicada es válida, y sino, false
    public boolean posicionValida(int x, int y, int z) {
    	return x >= 0 && x < tamaño && y >= 0 && y < tamaño && z >= 0 && z < tamaño;
    }
    
    // Post: setea un valor en una posición específica
    public void setValor(int x, int y, int z, int valor) throws Exception {
        if (!posicionValida(x, y, z)){
        	throw new Exception("Índice fuera de los límites.");
        }
        tablero[x][y][z] = valor;
    }
}


