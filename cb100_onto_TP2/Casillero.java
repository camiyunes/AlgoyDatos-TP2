package ar.uba.fi.cb100_onto_TP2;

public class Casillero {
    private int x;
    private int y;
    private int z;
    private char ficha; // Representa la ficha en el casillero, si hay una

    public Casillero(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ficha = ' '; // Inicialmente, el casillero está vacío
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public char getFicha() {
        return ficha;
    }

    public void colocarFicha(char ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "Casillero{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", ficha=" + ficha +
                '}';
    }
}