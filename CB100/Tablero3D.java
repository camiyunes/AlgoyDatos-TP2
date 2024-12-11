package ar.fiuba.CB100;

public class Tablero3D {
	private static int TAMAÑO_DEFAULT = 3;
	private Casillero<Ficha>[][][] tablero;
	private int tamaño = TAMAÑO_DEFAULT;

    // Constructor
	public Tablero3D(int tamaño) throws Exception {
		if (tamaño < 1) { throw new Exception("¿y dónde ponemos las fichas entonces?"); }
		this.tamaño = tamaño;
		this.tablero = new Casillero[tamaño][tamaño][tamaño]; // matriz 3D
		inicializarCeldas(this.tablero);
	}
    
    private void inicializarCeldas(Casillero<Ficha>[][][] tablero2) throws Exception {
		for (int i = 0; i < tablero2.length; i++) {
			for (int j = 0; j < tablero2.length; j++) {
				for (int k = 0; k < tablero2.length; k++) { tablero2[i][j][k] = new Casillero<Ficha>(i+1, j+1, k+1, new Ficha(i, j, k)); }
			}
		}
	}

	// Constructor promedio
	public Tablero3D() throws Exception {
		this.tablero = new Casillero[TAMAÑO_DEFAULT][TAMAÑO_DEFAULT][TAMAÑO_DEFAULT]; // matriz 3D
		inicializarCeldas(this.tablero);
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
   
    // Imprimir el tablero
    public void imprimirTablero() {
        for (int k = 0; k < tamaño; k++) {  // Recorrer cada piso
            System.out.println("Piso " + (k + 1) + ":");
            
            for (int j = 0; j < tamaño; j++) {  // Recorrer cada fila
                for (int i = 0; i < tamaño; i++) {  // Recorrer cada columna
                    // Mostrar la ficha de la posición (i, j, k)
                    System.out.print(tablero[i][j][k].getDato().getColor() + " "); 
                }
                System.out.println();  // Salto de línea después de cada fila
            }
            System.out.println();  // Salto de línea después de cada piso
        }
    }
    
	public boolean partidaTerminada() throws Exception {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				for (int k = 0; k < tamaño; k++) {
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
						int fichasSeguidasExtremo = contarFichasSeguidas(x, y, z, x+i, y+j, z+k);
						if (fichasSeguidasExtremo >= 3) { return true; }
					}
				}
			}
		} catch (Exception e) { return false; }
		return false;
	}
	
    public int contarFichasSeguidas(int x1, int y1, int z1, int x2, int y2, int z2) throws Exception {
        if (!posicionValida(x1, y1, z1) || !posicionValida(x2, y2, z2)) {
            return 0;
        }

        if (getValor(x1, y1, z1).isBloqueada() || getValor(x2, y2, z2).isBloqueada()
            || getValor(x1, y1, z1).getColor() != getValor(x2, y2, z2).getColor()) {
            return 1;
        }

        // Verifica si la siguiente posición está dentro de los límites.
        if (!posicionValida(x2 + (x2 - x1), y2 + (y2 - y1), z2 + (z2 - z1))) {
            return 1;
        }

        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        int deltaZ = z2 - z1;

        // Si no hay movimiento, terminar la recursión.
        if (deltaX == 0 && deltaY == 0 && deltaZ == 0) {
            return 1;
        }

        return 1 + contarFichasSeguidas(x2, y2, z2, x2 + deltaX, y2 + deltaY, z2 + deltaZ);
    }
	
    //Post: devuelve true si la posición indicada es válida, y sino, false
    public boolean posicionValida(int x, int y, int z) {
    	return x >= 0 && x < tamaño && y >= 0 && y < tamaño && z >= 0 && z < tamaño;
    }
    
    //Post: devuelve el valor correspondiente a una posicion
    public Ficha getValor(int x, int y, int z) throws Exception {
        validarPosicion(x, y, z);
        return tablero[x][y][z].getDato();
    }
    
    public Casillero<Ficha> getCasilla(int x, int y, int z) throws Exception {
    	validarPosicion(x, y, z);
    	return tablero[x][y][z];
    }
    
    // Post: setea un valor en una posición específica
    public void setValor(int x, int y, int z, Ficha valor) throws Exception {
        validarPosicion(x, y, z);
        tablero[x][y][z] = new Casillero<Ficha>(x, y, z);
        valor.mover(x, y, z);
    }
    
    public void validarPosicion(int x, int y, int z) throws Exception {
        if (!posicionValida(x, y, z)) {
            throw new Exception("Índice fuera de los límites: (" + x + ", " + y + ", " + z + ")");
        }
    }
    
    public void validarPositivo(int n) throws Exception {
    	if (n <= 0) { throw new Exception("Hay que ingresar un parámetro positivo, y " + n + " no lo es. "); }
    }

	public void bloquearFicha(int x, int y, int z) {
		this.tablero[x][y][z].getDato().bloquear();
	}

	public void cambiarColorFicha(String nombre, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	public void intercambiarFichas(Jugador jugador, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	public void anularCasillero(int x, int y, int z) {
		this.tablero[x][y][z].anular();
	}

	public void moverFicha(int x0, int y0, int z0, int x1, int y1, int z1) {
		Ficha ficha = this.tablero[x0][y0][z0].vaciar();
		this.tablero[x1][y1][z1].setDato(ficha);
	}

	public void colocarFicha(int x, int y, int z, Ficha ficha) {
		this.tablero[x][y][z].setDato(ficha);
	}
}


