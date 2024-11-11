package Tablero;

public class Main {
    public static void main(String[] args) {
        Tablero3D tablero = new Tablero3D(5); // Crea un tablero 3x3x3
        
        tablero.colocarValor(1, 2, 1, 5); // Coloca un valor en la posición (1, 2, 1)
        System.out.println(tablero.obtenerValor(1, 2, 1)); // Muestra el valor colocado
        
        tablero.imprimirTablero(); // Muestra todo el tablero
    }
}

