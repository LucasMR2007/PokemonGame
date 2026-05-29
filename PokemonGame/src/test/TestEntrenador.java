package test;
 
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;
 
import pokemonGame.Entrenador;
import pokemonGame.Pokemon;
 
public class TestEntrenador {
 
    private Pokemon crearPokemon(String nombre) {
        return new Pokemon(nombre, "Normal", 100, 10, "Activo");
    }
 
    @Test
    void CE01_entrenadorValido_OK() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        assertEquals("Ash", e.getNombre());
        assertEquals('M', e.getRango());
        assertEquals(0, e.getEquipo().size());
        assertNull(e.getPokemonActivo());
    }
 
    @Test
    void CE02_anyadirPokemon_OK() {
        Entrenador e = new Entrenador("Ash", 'M');
        Pokemon p = crearPokemon("Pikachu");
 
        e.anyadirPokemon(p);
 
        assertEquals(1, e.getEquipo().size());
        assertEquals(p, e.getPokemonActivo());
    }
 
    @Test
    void CE03_equipoVacio_noTienePokemonDisponibles() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        assertFalse(e.tienePokemonDisponibles());
    }
 
    @Test
    void CE04_seisPokemon_noSeAnyadeSeptimo() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        for (int i = 1; i <= 7; i++) {
            e.anyadirPokemon(crearPokemon("Pokemon" + i));
        }
 
        assertEquals(6, e.getEquipo().size());
    }
 
    @Test
    void CE05_cambiarPokemonActivo_OK() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        Pokemon p1 = crearPokemon("Pikachu");
        Pokemon p2 = crearPokemon("Bulbasaur");
 
        e.anyadirPokemon(p1);
        e.anyadirPokemon(p2);
 
        e.cambiarPokemonActivo(p2);
 
        assertEquals(p2, e.getPokemonActivo());
    }
 
    @Test
    void CE06_noCambiarADebilitado() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        Pokemon p1 = crearPokemon("Pikachu");
        Pokemon p2 = crearPokemon("Bulbasaur");
 
        e.anyadirPokemon(p1);
        e.anyadirPokemon(p2);
 
        p2.recibirDanio(999);
 
        e.cambiarPokemonActivo(p2);
 
        assertEquals(p1, e.getPokemonActivo());
    }
 
    @Test
    void CE07_tienePokemonDisponibles_true() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        Pokemon p = crearPokemon("Pikachu");
 
        e.anyadirPokemon(p);
 
        assertTrue(e.tienePokemonDisponibles());
    }
 
    @Test
    void CE08_todosDebilitados_false() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        Pokemon p = crearPokemon("Pikachu");
 
        e.anyadirPokemon(p);
 
        p.recibirDanio(999);
 
        assertFalse(e.tienePokemonDisponibles());
    }
 
    @Test
    void CE09_numPokemonDebilitados_cero() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        e.anyadirPokemon(crearPokemon("P1"));
        e.anyadirPokemon(crearPokemon("P2"));
 
        assertEquals(0, e.getNumPokemonDebilitados());
    }
 
    @Test
    void CE10_numPokemonDebilitados_dos() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        Pokemon p1 = crearPokemon("P1");
        Pokemon p2 = crearPokemon("P2");
        Pokemon p3 = crearPokemon("P3");
 
        e.anyadirPokemon(p1);
        e.anyadirPokemon(p2);
        e.anyadirPokemon(p3);
 
        p1.recibirDanio(999);
        p3.recibirDanio(999);
 
        assertEquals(2, e.getNumPokemonDebilitados());
    }
 
    @Test
    void CE11_subirRango_ZaY() {
        Entrenador e = new Entrenador("Ash", 'Z');
 
        e.subirRango();
 
        assertEquals('Y', e.getRango());
    }
 
    @Test
    void CE12_subirRango_A_noCambia() {
        Entrenador e = new Entrenador("Ash", 'A');
 
        e.subirRango();
 
        assertEquals('A', e.getRango());
    }
 
    @Test
    void CE13_bajarRango_AaB() {
        Entrenador e = new Entrenador("Ash", 'A');
 
        e.bajarRango();
 
        assertEquals('B', e.getRango());
    }
 
    @Test
    void CE14_bajarRango_Z_noCambia() {
        Entrenador e = new Entrenador("Ash", 'Z');
 
        e.bajarRango();
 
        assertEquals('Z', e.getRango());
    }
}