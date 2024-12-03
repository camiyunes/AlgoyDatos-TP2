package ar.uba.fi.cb100_onto_TP2;

import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws Exception {
		//inicializo valores iniciales
		Scanner teclado = new Scanner(System.in);
		Random random = new Random(System.nanoTime());
		int largo = 0;
		int jugadores = 0;
		EventosDelTiempo jugadasADeshacerYRehacer = null;
		
		//defino las acciones
		String[] acciones = new String[7];
		acciones[0] = "Perder el turno";
		acciones[1] = "Bloquear ficha";
		acciones[2] = "Anular casillero";
		acciones[3] = "Volver atrás jugada";
		acciones[4] = "Cambiar color ficha";
		acciones[5] = "Intercambiar fichas";
		acciones[6] = "Rehacer jugada";

		Tablero3D tablero = null; // reserva espacio vacio para un tablero
		System.out.println("Bienvenido al ta-te-ti tridimensional!");
		while (true) {
			System.out.println("Ingrese el volumen del tablero (no se valen palabras, signos, tildes, + ni -; solo enteros)");
			String opcion = teclado.nextLine();
			opcion = opcion.toLowerCase();
			if (!(opcion.isEmpty() || opcion.isBlank()) && !esUnNumero(opcion)) {
				System.out.println("El input ingresado es inválido. Vuelva a intentar");
				continue;
			} else if (opcion.isEmpty() || opcion.isBlank()){
				System.out.println("Dado que usted ingresó la nada misma, en su lugar será elegido el valor default: 3");
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
		} else { System.out.println("Usted ha elegido exitosamente jugar contra " + (jugadores - 1) + " jugadores"); }
		System.out.println("Que empieze el juego!");
		int turnoActual = 0;
		Dado dado = new Dado(6);
		//creo los mazos principal y de descarte
		Mazo mazoPrincipal = new Mazo();
		Mazo mazoDescartes = new Mazo();
		int contCartas = 0;
		while (contCartas < jugadores * largo) {
			mazoPrincipal.agregarCarta(new Carta(acciones[random.nextInt()]));
			contCartas++;
		}
		//creo las listas de jugadas pasadas y futuras
		jugadasADeshacerYRehacer = new EventosDelTiempo(jugadores);
		
		// Crear jugadores
		Jugador[] listaJugadores = new Jugador[jugadores];
		for (int i = 0; i < jugadores; i++) {
			System.out.println("Ingrese el nombre del jugador " + (i + 1) + ":");
			String nombre = teclado.nextLine();
			char ficha = (char) ('A' + i); // Asignar una ficha diferente a cada jugador
			listaJugadores[i] = new Jugador(nombre, new Ficha(ficha));
		}

        // Ciclo del juego
		while (!tablero.partidaTerminada()) {
			turnoActual++;
			int indiceJugador = deQuienEsElTurno(turnoActual, jugadores);
			Jugador jugadorActual = listaJugadores[indiceJugador - 1];
			System.out.println("Turno de: " + jugadorActual.getNombre());
			if (jugadorActual.isTurnoActivo()) {
				//Inicio del turno
				int cartasALevantar = dado.lanzarDado();
				try {
					jugadorActual.robarDelMazo(mazoPrincipal, cartasALevantar);
				} catch (Exception e) {
					System.out.println("Parece que no se pudieron levantar " + cartasALevantar + " cartas");
				}
				// Realizar jugada
				System.out.println("Ingrese las coordenadas del casillero (x y z):");
				while (true) {
					if (jugadorActual.tieneTodasLasFichasEnElTablero()) {
						System.out.println("¿Qué ficha desea mover? (x y z)");
					} else {
						System.out.println("¿En dónde vas a poner tu ficha (x y z)?");
					}
					int x0 = teclado.nextInt();
					int y0 = teclado.nextInt();
					int z0 = teclado.nextInt();
					if (jugadorActual.tieneTodasLasFichasEnElTablero()) {
						if (tablero.getValor(x0, y0, z0).getColor() == jugadorActual.getFicha().getColor()) {
							System.out.println("¿A dónde desea mover la ficha? (x y z):");
							int x1 = teclado.nextInt();
							int y1 = teclado.nextInt();
							int z1 = teclado.nextInt();
							tablero.moverFicha(x0, y0, z0, x1, y1, z1);
						} else {
							System.out.println("La posición ingresada no es válida porque no hay ficha que mover");
							continue;
						}
					} else {
						if (tablero.getValor(x0, y0, z0).getColor() == jugadorActual.getFicha().getColor()) {
							System.out.println("La posición ingresada no es válida porque ya hay una ficha ahí");
							continue;
						} else {
							jugadorActual.colocarFicha(tablero, x0, y0, z0);
						}
					}
					break;
				}
				teclado.nextLine(); // Consumir el salto de línea

				int jugadorProximo = deQuienEsElTurno(turnoActual+1, jugadores);
				Jugador jugadorOponente = listaJugadores[indiceJugador - 1];
				//fin de turno
				System.out.println("Seleccione la carta que desea jugar (del " + 0 + " al" + 6 + ")");
				int indice = teclado.nextInt();
				Carta carta = jugadorActual.getMano()[indice];
				System.out.println("Ingrese las coordenadas de la jugada (x y z):");
				int x = teclado.nextInt();
				int y = teclado.nextInt();
				int z = teclado.nextInt();
				Casillero<Ficha> casillero = tablero.getCasilla(x, y, z);
				Jugada jugada = new Jugada(jugadorActual, casillero, carta, tablero);
				jugada.realizarJugada();
				jugadasADeshacerYRehacer.apilarPasado(indiceJugador, jugada);
			}
			jugadorActual.reiniciarTurno();
		}

		System.out.println("¡Juego terminado!");
		teclado.close();
    }
	
	public static boolean esUnNumero(char charAt) {
		String numeros = "0123456789";
		for (int i = 0; i < numeros.length(); i++) {
			if (numeros.charAt(i) == charAt) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean esUnNumero(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			if (!esUnNumero(cadena.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static int deQuienEsElTurno(int turnoActual, int jugadores) {
        if (turnoActual <= jugadores) {
            return turnoActual;
        }
        int multiplicador = turnoActual / jugadores;
        return turnoActual - (jugadores * multiplicador);
    }

	public static void desapilarJugada(Jugador jugador, String accion, EventosDelTiempo jugadas) {
		
	}

	public static void rehacerJugada(Jugador jugador, EventosDelTiempo jugadas) {
		
	}

	public static void deshacerJugada(Jugador jugador, EventosDelTiempo jugadas) {
		// TODO Auto-generated method stub
		
	}
}
