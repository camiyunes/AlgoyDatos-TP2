package Tablero;

public class Tablero3D {
    private int[][][] tablero;
    private int tamaño;

    // Constructor
    public Tablero3D(int tamaño) {
        this.tamaño = tamaño;
        this.tablero = new int[tamaño][tamaño][tamaño]; // matriz 3D
    }

    // Método para colocar un valor en una posición específica
    public void colocarValor(int x, int y, int z, int valor) {
        if (x >= 0 && x < tamaño && y >= 0 && y < tamaño && z >= 0 && z < tamaño){
            tablero[x][y][z] = valor;
        } 
        else {
            System.out.println("Índice fuera de los límites.");
        }
    }

    // Método para obtener un valor de una posición específica
    public int obtenerValor(int x, int y, int z) {
        if (x >= 0 && x < tamaño && y >= 0 && y < tamaño && z >= 0 && z < tamaño) {
            return tablero[x][y][z];
        }
        return -1; // Valor de error
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
}


