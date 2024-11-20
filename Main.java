package ar.uba.fi.cb100_onto_TP2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner teclado = new Scanner(System.in);
		int largo = 0;
		int jugadores = 0;

		Tablero3D tablero = null; // reserva espacio vacio para un tablero
		System.out.println("Bienvenido al ta-te-ti tridimensional!");
		while (true) {
			System.out.println("Ingrese el volumen del tablero (no se valen palabras, signos, tildes, + ni -; solo enteros)");
			String opcion = teclado.nextLine();
			opcion = opcion.toLowerCase();
			if (!(opcion.isEmpty() || opcion.isBlank()) && !esUnNumero(opcion)) {
				System.out.println("El input ingresado es inválido. Vuelva a intentar");
				continue;
			} else { largo = Integer.parseInt(opcion); }
			break;
		}
		if (largo < 1) {
			System.out.println("Usted ha exitosamente elegido un tablero de 3 x 3 x 3");
			tablero = new Tablero3D();
		} else {
			System.out.println("Usted ha exitosamente elegido un tablero de " + largo + " x " + largo + " x " + largo);
			tablero = new Tablero3D(largo);	
		}
		tablero.imprimirTablero();
		System.out.println("Parece que el tablero está hecho");
		while (true) {
			System.out.println("Seleccione la cantidad de jugadores");
			String input = teclado.nextLine();
			if (!(input.isEmpty() || input.isBlank()) && !esUnNumero(input)) {
				System.out.println("El input ingresado es inválido. Vuelva a intentar");
				continue;
			} else { jugadores = Integer.parseInt(input); }
			break;
		}
		if (jugadores <= 1) {
			System.out.println("¿Por qué tan solito? Mejor si te damos un rival y son 2");
			jugadores = 2;
		} else {
			System.out.println("Usted ha elegido exitosamente jugar contra " + (jugadores - 1) + " jugadores");
		}
		System.out.println("Que empieze el juego!");
		int turnoActual = 0;
		Mazo mazo = new Mazo(jugadores * 24);
		
    }

	public static boolean esUnNumero(char charAt) {
		String numeros = "0123456789";
		for (int i = 0; i < numeros.length(); i++) {
			if (numeros.charAt(i) == charAt) { return true; }
		}
		return false;
	}
	
	public static boolean esUnNumero(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			if (!esUnNumero(cadena.charAt(i))) { return false; }
		}
		return true;
	}
	
	public void cicloDelTurno(int turnoActual, Tablero3D tablero, int jugadores, Mazo mazo) {
		turnoActual++;
		int counter = deQuienEsElTurno(turnoActual, jugadores);
		System.out.println("Turno de: " + counter);
		
	}
	
	public int deQuienEsElTurno(int turnoActual, int jugadores) {
		if (turnoActual <= jugadores) { return turnoActual; }
		int multiplicador = turnoActual / jugadores;
		return turnoActual - (jugadores * multiplicador);
	}
}

