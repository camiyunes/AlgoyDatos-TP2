package ar.uba.fi.cb100_onto_TP2;

import ar.uba.fi.cb100_onto_estructuras.estructuras.Lista;

public class TableroV2<T> {
	private Lista<Lista<T>>[] tablero;
	
	public TableroV2() {}

	public void imprimirTablero() {
		for (int x = 0; x < this.tablero.length; x++) {
			Lista<Lista<T>> Y = tablero[x];
			Y.iniciarCursor();
			while (Y.avanzarCursor()) {
				Lista<T> Z = Y.obtenerCursor();
				Z.iniciarCursor();
				while(Z.avanzarCursor()) {
					System.out.println("");
				}
			}
		}
	}
}
