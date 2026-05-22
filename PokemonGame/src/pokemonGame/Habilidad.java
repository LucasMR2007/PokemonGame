package pokemonGame;

public class Habilidad {
	 
    private String nombre;
    private String tipo;
    private int potencia;
 
    public Habilidad(String nombre, String tipo, int potencia) {
        this.nombre   = nombre;
        this.tipo     = tipo;
        this.potencia = potencia;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public String getTipo() {
        return tipo;
    }
 
    public int getPotencia() {
        return potencia;
    }
}
