package ar.uba.fi.cb100_onto_TP2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Tablero3D tablero = null; // reserva espacio vacio para un tablero
        
        tablero.setValor(1, 2, 1, 5); // Coloca un valor en la posición (1, 2, 1)
        System.out.println(tablero.obtenerValor(1, 2, 1)); // Muestra el valor colocado
        
        tablero.imprimirTablero(); // Muestra todo el tablero
    }
}

