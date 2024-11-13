package ar.uba.fi.cb100_onto_TP2;

public class Main {
    public static void main(String[] args) throws Exception {
        Tablero3D tablero = new Tablero3D(5); // Crea un tablero 3x3x3
        
        tablero.setValor(1, 2, 1, 5); // Coloca un valor en la posición (1, 2, 1)
        System.out.println(tablero.obtenerValor(1, 2, 1)); // Muestra el valor colocado
        
        tablero.imprimirTablero(); // Muestra todo el tablero
    }
}

