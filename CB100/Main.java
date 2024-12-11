package ar.fiuba.CB100;

import java.util.Scanner;
import java.util.Random;

public class Main {
	
	private EventosDelTiempo jugadasADeshacerYRehacer = null;
	
	public EventosDelTiempo getLineaDeTiempo() { 
		return this.jugadasADeshacerYRehacer; 
	}
	
	public static void main(String[] args) throws Exception {
		// Inicializo valores iniciales
		Scanner teclado = new Scanner(System.in);
		Random random = new Random(System.nanoTime());
		int largo = 0;
		int jugadores = 0;
		EventosDelTiempo jugadasADeshacerYRehacer = null;
		
		// Defino las acciones
		String[] acciones = new String[7];
		acciones[0] = "Perder el turno";
		acciones[1] = "Bloquear ficha";
		acciones[2] = "Anular casillero";
		acciones[3] = "Volver atrás jugada";
		acciones[4] = "Cambiar color ficha";
		acciones[5] = "Intercambiar fichas";
		acciones[6] = "Rehacer jugada";

		Tablero3D tablero = null; // Reserva espacio vacío para un tablero
		System.out.println("Bienvenido al ta-te-ti tridimensional!");
		
		// Solicitar al usuario el volumen del tablero
		while (true) {
		    System.out.println("Ingrese el volumen del tablero (solo números enteros mayores o iguales a 1):");
		    String opcion = teclado.nextLine().trim();
		    
		    if (opcion.isEmpty() || !esUnNumero(opcion)) {
		        System.out.println("El input ingresado es inválido. Vuelva a intentar.");
		        continue;
		    }

		    largo = Integer.parseInt(opcion);

		    if (largo < 1) {
		        System.out.println("El tamaño del tablero debe ser mayor o igual a 1. Intente nuevamente.");
		    } else {
		        break;
		    }
		}

		// Si el largo es válido, inicializa el tablero
		if (largo < 1) {
			System.out.println("Usted ha exitosamente elegido un tablero de 3 x 3 x 3");
			tablero = new Tablero3D();
		} else {
			System.out.println("Usted ha exitosamente elegido un tablero de " + largo + " x " + largo + " x " + largo);
			tablero = new Tablero3D(largo);	
		}
		tablero.imprimirTablero();
		System.out.println("Parece que el tablero está hecho");

		// Solicitar la cantidad de jugadores
		while (true) {
			System.out.println("Seleccione la cantidad de jugadores:");
			String input = teclado.nextLine();
			if (!(input.isEmpty() || input.isBlank()) && !esUnNumero(input)) {
				System.out.println("El input ingresado es inválido. Vuelva a intentar");
				continue;
			} else {
				jugadores = Integer.parseInt(input);
			}
			break;
		}
		if (jugadores <= 1) {
			System.out.println("¿Por qué tan solito? Mejor si te damos un rival y son 2");
			jugadores = 2;
		} else { 
			System.out.println("Usted ha elegido exitosamente jugar contra " + (jugadores - 1) + " jugadores"); 
		}
		System.out.println("Que empiece el juego!");
		int turnoActual = 0;
		Dado dado = new Dado(6);
		// Creo los mazos principal y de descarte
		Mazo mazoPrincipal = new Mazo();
		Mazo mazoDescartes = new Mazo();
		int contCartas = 0;
		while (contCartas < jugadores * largo) {
			mazoPrincipal.agregarCarta(new Carta(acciones[random.nextInt(0, 7)]));
			contCartas++;
		}

		// Crear las listas de jugadas pasadas y futuras
		jugadasADeshacerYRehacer = new EventosDelTiempo(jugadores);
		Ficha criterioDeFicha = new Ficha(0, 0, 0);
		
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
				// Inicio del turno
				int cartasALevantar = dado.lanzarDado();
				try {
					jugadorActual.robarDelMazo(mazoPrincipal, cartasALevantar);
				} catch (Exception e) { System.out.println("Parece que no se pudieron levantar " + cartasALevantar + " cartas"); }
				// Realizar jugada
				System.out.println("Ingrese las coordenadas del casillero (x y z):");
				while (true) {
					if (jugadorActual.tieneTodasLasFichasEnElTablero()) {
						System.out.println("¿Qué ficha desea mover? (x y z)");
					} else { System.out.println("¿En dónde vas a poner tu ficha (x y z)?"); }
					int x0 = teclado.nextInt();
					int y0 = teclado.nextInt();
					int z0 = teclado.nextInt();
					
					 // Verificación de coordenadas dentro de los límites
				    if (x0 < 0 || x0 >= largo || y0 < 0 || y0 >= largo || z0 < 0 || z0 >= largo) {
				        System.out.println("Las coordenadas están fuera de los límites del tablero.");
				        continue; // Vuelve a pedir las coordenadas si están fuera de rango
				    }
				    Casillero<Ficha> casillero = tablero.getCasilla(x0, y0, z0);
				    
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
						} else { jugadorActual.colocarFicha(tablero, x0, y0, z0); }
					}
					break;
				}
				teclado.nextLine(); // Consumir el salto de línea

				// Fin de turno
				boolean cartaValida = false;
				while (!cartaValida) {
					System.out.println("Seleccione la carta que desea jugar (del 0 al 6):");
					int indice = teclado.nextInt();
					
					// Verificar que el índice esté dentro del rango de cartas disponibles
					if (indice < 0 || indice >= jugadorActual.getMano().length) {
					    System.out.println("Índice inválido. Debe seleccionar un número entre 0 y " + (jugadorActual.getMano().length - 1));
					    continue; // Volver a pedir la selección de carta
					}
					
					Carta carta = jugadorActual.getMano()[indice];
					System.out.println("Ingrese las coordenadas de la jugada (x y z):");
					int x = teclado.nextInt();
					int y = teclado.nextInt();
					int z = teclado.nextInt();
					Casillero<Ficha> casillero = tablero.getCasilla(x, y, z);
					Jugada jugada = new Jugada(jugadorActual, casillero, carta, tablero);
					jugada.realizarJugada();
					jugadasADeshacerYRehacer.apilarPasado(indiceJugador, jugada);
					cartaValida = true; // La carta es válida, se sale del ciclo
				}
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

	public void desapilarJugada(Jugador jugador, String accion, int turno) throws Exception {
		Jugada desapilada = null;
		switch (accion) {
			case "Volver atrás jugada":
				this.getLineaDeTiempo().desapilarPasado(turno);
			case "Rehacer jugada":
				this.getLineaDeTiempo().desapilarFuturo(turno);
		}
	}

	public static void rehacerJugada(Jugador jugador, EventosDelTiempo jugadas) {
		// Implementación pendiente
	}

	public static void deshacerJugada(Jugador jugador, EventosDelTiempo jugadas) {
		// Implementación pendiente
	}
}
