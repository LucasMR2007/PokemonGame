package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import pokemonGame.Entrenador;
import pokemonGame.Pokemon;

public class TestEntrenador {
	@Test
    void CE01_entrenadorValido_OK() {
        Entrenador e = new Entrenador("Ash", 'M');
        e.anyadirPokemon(crearPokemon("Pikachu"));
        e.anyadirPokemon(crearPokemon("Charmander"));
        e.anyadirPokemon(crearPokemon("Squirtle"));
 
        assertEquals("Ash", e.getNombre());
        assertEquals('M',   e.getRango());
        assertEquals(3,     e.getEquipo().size());
    }
 
    private Pokemon crearPokemon(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    void CE02_nombreVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Entrenador("", 'M');
        });
    }
 
   
    @Test
    void CE03_nombreNull_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Entrenador(null, 'M');
        });
    }
 
 
    @Test
    void CE04_equipoVacio_noTienePokemonDisponibles() {
        Entrenador e = new Entrenador("Ash", 'M');
 
        assertFalse(e.tienePokemonDisponibles());
    }
 
 
    @Test
    void CE05_sietePokemon_noSeAnyadeElSeptimo() {
        Entrenador e = new Entrenador("Giovanni", 'S');
        for (int i = 1; i <= 7; i++) {
            e.anyadirPokemon(crearPokemon("Pokemon" + i));
        }
 
        assertEquals(6, e.getEquipo().size());
    }
 
    
    @Test
    void CE06_cambiarADebilitado_noLoCambia() {
        Entrenador e = new Entrenador("James", 'E');
        Pokemon pkm1 = crearPokemon("Pikachu");
        Pokemon pkm2 = crearPokemon("Bulbasaur");
        e.anyadirPokemon(pkm1);
        e.anyadirPokemon(pkm2);
 
        pkm2.recibirDanio(999);
        e.cambiarPokemonActivo(pkm2);
 
        assertEquals(pkm1, e.getPokemonActivo()); 
    }
 
   
    @Test
    void CE07_rangoNumero_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Entrenador("Ash", '5');
        });
    }
 
    
    @Test
    void CE08_rangoSimbolo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Entrenador("Ash", '#');
        });
    }
 
    
    @Test
    void CE09_rangoMinuscula_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Entrenador("Ash", 'a');
        });
    }
 
    
    @Test
    void CE10_rangoNulo_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Entrenador("Ash", '\0');
        });
    }
 
    
    @Test
    void CE11_todosDebilitados_devuelveFalse() {
        Entrenador e = new Entrenador("Ash", 'B');
        Pokemon p = crearPokemon("Pikachu");
        e.anyadirPokemon(p);
        p.recibirDanio(999);
 
        assertFalse(e.tienePokemonDisponibles());
    }
 

    @Test
    void CE12_unoActivo_devuelveTrue() {
        Entrenador e = new Entrenador("Ash", 'B');
        Pokemon p1 = crearPokemon("Pikachu");
        Pokemon p2 = crearPokemon("Charmander");
        e.anyadirPokemon(p1);
        e.anyadirPokemon(p2);
        p1.recibirDanio(999);
 
        assertTrue(e.tienePokemonDisponibles());
    }
 
    
    @Test
    void CE13_dosDebilitados_devuelveDos() {
        Entrenador e = new Entrenador("Ash", 'B');
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
    void CE14_subirRango_deZaY() {
        Entrenador e = new Entrenador("Ash", 'Z');
        e.subirRango();
 
        assertEquals('Y', e.getRango());
    }
 
    
    @Test
    void CE15_subirRango_enA_noSube() {
        Entrenador e = new Entrenador("Ash", 'A');
        e.subirRango();
 
        assertEquals('A', e.getRango());
    }
 
    
    @Test
    void CE16_bajarRango_deAaB() {
        Entrenador e = new Entrenador("Ash", 'A');
        e.bajarRango();
 
        assertEquals('B', e.getRango());
    }
 
    
    @Test
    void CE17_bajarRango_enZ_noBaja() {
        Entrenador e = new Entrenador("Ash", 'Z');
        e.bajarRango();
 
        assertEquals('Z', e.getRango());
    }
}
