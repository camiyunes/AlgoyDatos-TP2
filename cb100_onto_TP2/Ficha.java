package ar.uba.fi.cb100_onto_TP2;

/**
 * La clase Ficha representa una ficha en el juego de Tateti.
 * Cada ficha tiene un color, una posición en el tablero (x, y, z) y un estado de bloqueo.
 */
public class Ficha {
    // ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    public static final char COLOR_DEFAULT = 'B'; // Color por defecto de la ficha
    
    // ATRIBUTOS -----------------------------------------------------------------------------------------------
    private char color; // Color de la ficha
    private int x; // Posición en el eje X
    private int y; // Posición en el eje Y
    private int z; // Posición en el eje Z
    private boolean bloqueada; // Estado de bloqueo de la ficha
    
    // CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * Crea una ficha con el color y posición indicados.
     * @param color, caracter que representa el color de la ficha.
     * @param x, entero que representa la posición en el eje X.
     * @param y, entero que representa la posición en el eje Y.
     * @param z, entero que representa la posición en el eje Z.
     */
    public Ficha(char color, int x, int y, int z) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
        this.bloqueada = false;
    }
    
    /**
     * Crea una ficha con el color por defecto y posición indicada.
     * @param x, entero que representa la posición en el eje X.
     * @param y, entero que representa la posición en el eje Y.
     * @param z, entero que representa la posición en el eje Z.
     */
    public Ficha(int x, int y, int z) {
        this(COLOR_DEFAULT, x, y, z);
    }
    
    // MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    /**
     * Devuelve una representación en cadena de la ficha.
     * @return una cadena que representa la ficha, incluyendo su color, posición y estado de bloqueo.
     */
    @Override
    public String toString() {
        return "Ficha [color=" + color + ", x=" + x + ", y=" + y + ", z=" + z + ", bloqueada=" + bloqueada + "]";
    }

    /**
     * Compara esta ficha con otro objeto para determinar si son iguales.
     * @param obj el objeto a comparar con esta ficha.
     * @return true si el objeto es una ficha con el mismo color y posición; false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ficha other = (Ficha) obj;
        return x == other.x && y == other.y && z == other.z && color == other.color;
    }
    
    // MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * Actualiza la posición de la ficha.
     * @param nuevoX, entero que representa la nueva posición en el eje X.
     * @param nuevoY, entero que representa la nueva posición en el eje Y.
     * @param nuevoZ, entero que representa la nueva posición en el eje Z.
     */
    public void mover(int nuevoX, int nuevoY, int nuevoZ) {
        if (!bloqueada) {
            this.x = nuevoX;
            this.y = nuevoY;
            this.z = nuevoZ;
        } else {
            System.out.println("La ficha está bloqueada y no se puede mover.");
        }
    }
    
    /**
     * Bloquea la ficha para que no pueda moverse.
     */
    public void bloquear() {
        this.bloqueada = true;
    }
    
    /**
     * Desbloquea la ficha para que pueda moverse.
     */
    public void desbloquear() {
        this.bloqueada = false;
    }
    
    // GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public char getColor() {
        return color;
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

    public boolean isBloqueada() {
        return bloqueada;
    }
    
    // SETTERS SIMPLES -----------------------------------------------------------------------------------------
    public void setColor(char color) {
        this.color = color;
    }
}