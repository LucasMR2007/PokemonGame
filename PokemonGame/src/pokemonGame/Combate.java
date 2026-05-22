package pokemonGame;

public class Combate {
	 
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Entrenador turnoActual;
    private Entrenador ganador;
    private boolean activo;
 
    public Combate(Entrenador entrenador1, Entrenador entrenador2) {
        this.entrenador1 = entrenador1;
        this.entrenador2 = entrenador2;
        this.turnoActual = entrenador1;
        this.ganador     = null;
        this.activo      = true;
    }
 

 
    public Entrenador getEntrenador1() {
        return entrenador1;
    }
 
    public Entrenador getEntrenador2() {
        return entrenador2;
    }
 
    public Entrenador getTurnoActual() {
        return turnoActual;
    }
 
    public Entrenador getGanador() {
        return ganador;
    }
 
    public boolean isActivo() {
        return activo;
    }

    public int calcularDanio(Pokemon atacante, Pokemon defensor, int indiceHabilidad) {
        int potencia = atacante.getHabilidades().get(indiceHabilidad).getPotencia();
        int danio    = potencia - defensor.getDefensa();
        if (danio < 1) {
            danio = 1;
        }
        return danio;
    }
 
 
    public void realizarTurno(int indiceHabilidad) {
        if (!activo) {
            return;
        }
 
 
        Entrenador atacante;
        Entrenador defensor;
 
        if (turnoActual == entrenador1) {
            atacante = entrenador1;
            defensor = entrenador2;
        } else {
            atacante = entrenador2;
            defensor = entrenador1;
        }
 
        Pokemon pokemonAtacante = atacante.getPokemonActivo();
        Pokemon pokemonDefensor = defensor.getPokemonActivo();

        int danio = calcularDanio(pokemonAtacante, pokemonDefensor, indiceHabilidad);
        pokemonDefensor.recibirDanio(danio);
 
  
        if (pokemonDefensor.estaDebilitado()) {
            if (!defensor.tienePokemonDisponibles()) {
                activo  = false;
                ganador = atacante;
                return;
            }
        }
 
  
        if (turnoActual == entrenador1) {
            turnoActual = entrenador2;
        } else {
            turnoActual = entrenador1;
        }
    }
 

    public Entrenador getOponente(Entrenador entrenador) {
        if (entrenador == entrenador1) {
            return entrenador2;
        }
        return entrenador1;
    }
}
