package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pokemonGame.Combate;
import pokemonGame.Entrenador;
import pokemonGame.Habilidad;
import pokemonGame.Pokemon;

public class TestCombate {
	 private Entrenador ash, gary;
	    private Pokemon pikachu, bulbasaur;
	 @BeforeEach
	    void setUp() {
	        pikachu   = new Pokemon("Pikachu",   "Eléctrico", 100, 10, "Activo");
	        bulbasaur = new Pokemon("Bulbasaur", "Planta",    100, 80, "Activo");
	        pikachu.annadirHabilidad(new Habilidad("Rayo",    "Eléctrico", 90));
	        bulbasaur.annadirHabilidad(new Habilidad("Placaje", "Normal",   10));
	 
	        ash  = new Entrenador("Ash",  'Z');
	        gary = new Entrenador("Gary", 'Z');
	        ash.anyadirPokemon(pikachu);
	        gary.anyadirPokemon(bulbasaur);
	    }
	 
	   
	    @Test
	    void CC01_combateCreadoCorrectamente() {
	        Combate c = new Combate(ash, gary);
	 
	        assertEquals(ash,  c.getEntrenador1());
	        assertEquals(gary, c.getEntrenador2());
	        assertEquals(ash,  c.getTurnoActual());
	        assertNull(c.getGanador());
	        assertTrue(c.isActivo());
	    }
	 

	    @Test
	    void CC02_calcularDanio_normal() {
	        Combate c = new Combate(ash, gary);
	       
	        int danio = c.calcularDanio(pikachu, bulbasaur, 0);
	 
	        assertEquals(10, danio);
	    }
	 
	 
	    @Test
	    void CC03_calcularDanio_minimoUno() {
	        Combate c = new Combate(ash, gary);
	       
	        int danio = c.calcularDanio(bulbasaur, pikachu, 0);
	 
	        assertEquals(1, danio);
	    }
	 
	 
	    @Test
	    void CC04_realizarTurno_aplicaDanio() {
	        Combate c = new Combate(ash, gary);
	        c.realizarTurno(0);
	 
	        assertEquals(90, bulbasaur.getVidaActual()); // 100 - 10 = 90
	    }
	 
	    
	    @Test
	    void CC05_realizarTurno_alternaTurno() {
	        Combate c = new Combate(ash, gary);
	        c.realizarTurno(0);
	 
	        assertEquals(gary, c.getTurnoActual());
	    }
	 
	   
	    @Test
	    void CC06_todosCaen_combateTermina() {
	        Pokemon fuerte = new Pokemon("Fuerte", "Normal", 100, 1, "Activo");
	        Pokemon debil  = new Pokemon("Debil",  "Normal",   1, 1, "Activo");
	        fuerte.annadirHabilidad(new Habilidad("Golpe", "Normal", 999));
	        debil.annadirHabilidad(new Habilidad("G", "Normal", 1));
	 
	        Entrenador e1 = new Entrenador("E1", 'A');
	        Entrenador e2 = new Entrenador("E2", 'Z');
	        e1.anyadirPokemon(fuerte);
	        e2.anyadirPokemon(debil);
	 
	        Combate c = new Combate(e1, e2);
	        c.realizarTurno(0);
	 
	        assertFalse(c.isActivo());
	        assertEquals(e1, c.getGanador());
	    }
	 
	   
	    @Test
	    void CC07_combateTerminado_realizarTurnoNoHaceNada() {
	        Pokemon fuerte = new Pokemon("Fuerte", "Normal", 100, 1, "Activo");
	        Pokemon debil  = new Pokemon("Debil",  "Normal",   1, 1, "Activo");
	        fuerte.annadirHabilidad(new Habilidad("Golpe", "Normal", 999));
	        debil.annadirHabilidad(new Habilidad("G", "Normal", 1));
	 
	        Entrenador e1 = new Entrenador("E1", 'A');
	        Entrenador e2 = new Entrenador("E2", 'Z');
	        e1.anyadirPokemon(fuerte);
	        e2.anyadirPokemon(debil);
	 
	        Combate c = new Combate(e1, e2);
	        c.realizarTurno(0);
	        c.realizarTurno(0);
	 
	        assertFalse(c.isActivo());
	        assertEquals(e1, c.getGanador());
	    }
	 
	   
	    @Test
	    void CC08_getOponente_devuelveElOtro() {
	        Combate c = new Combate(ash, gary);
	 
	        assertEquals(gary, c.getOponente(ash));
	        assertEquals(ash,  c.getOponente(gary));
	    }
	
	 






}
