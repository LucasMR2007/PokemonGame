package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pokemonGame.Habilidad;
import pokemonGame.Pokemon;

class TestPokemon {

	@Test
	void CP01_pokemonValido_OK() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");

		assertEquals("Pikachu", p.getNombre());
		assertEquals("Eléctrico", p.getTipo());
		assertEquals(100, p.getVidaMaxima());
		assertEquals(100, p.getVidaActual());
		assertEquals(20, p.getDefensa());
		assertEquals("Activo", p.getEstado());
	}

	@Test
	void CP02_nombreVacio_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("", "Fuego", 1, 1, "Activo");
		});
	}

	@Test
	void CP03_nombreNull_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon(null, "Fuego", 1, 1, "Activo");
		});
	}

	@Test
	void CP04_tipoNull_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", null, 1, 1, "Activo");
		});
	}

	@Test
	void CP05_tipoInvalido_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", "Nuclear", 1, 1, "Activo");
		});
	}

	@Test
	void CP06_vidaMaximaCero_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", "Fuego", 0, 1, "Activo");
		});
	}

	@Test
	void CP07_vidaMaximaNegativa_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", "Fuego", -5, 1, "Activo");
		});
	}

	@Test
	void CP08_defensaCero_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", "Fuego", 1, 0, "Activo");
		});
	}

	@Test
	void CP09_defensaNegativa_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", "Fuego", 1, -5, "Activo");
		});
	}

	@Test
	void CP10_estadoInvalido_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", "Fuego", 1, 1, "Dormido");
		});
	}

	@Test
	void CP11_estadoNull_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("a", "Fuego", 1, 1, null);
		});
	}
	 @Test
	    void CP12_cincoHabilidades_noSeAnyadeQuinta() {
	        Pokemon p = new Pokemon("Mewtwo", "Psíquico", 100, 10, "Activo");
	        p.annadirHabilidad(new Habilidad("h1", "Normal", 10));
	        p.annadirHabilidad(new Habilidad("h2", "Normal", 10));
	        p.annadirHabilidad(new Habilidad("h3", "Normal", 10));
	        p.annadirHabilidad(new Habilidad("h4", "Normal", 10));
	        p.annadirHabilidad(new Habilidad("h5", "Normal", 10)); // no se debe añadir
	 
	        assertEquals(4, p.getHabilidades().size());
	    }
	 @Test
	    void CP13_recibirDanio_reduceVidaActual() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	        p.recibirDanio(30);
	 
	        assertEquals(70, p.getVidaActual());
	    }
	 
	   
	    @Test
	    void CP14_recibirDanio_vidaCero_cambiaADebilitado() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	        p.recibirDanio(100);
	 
	        assertEquals(0,            p.getVidaActual());
	        assertEquals("Debilitado", p.getEstado());
	        assertTrue(p.estaDebilitado());
	    }
	 
	
	    @Test
	    void CP15_recibirDanio_noNegativa() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	        p.recibirDanio(999);
	 
	        assertEquals(0, p.getVidaActual());
	    }
	 
	 
	    @Test
	    void CP16_recibirDanio_menorQue1_seAplicaUnoDeDanio() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	        p.recibirDanio(0);
	 
	        assertEquals(99, p.getVidaActual());
	    }
	 
	  
	    @Test
	    void CP17_curarse_aumentaVida() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	        p.recibirDanio(50);
	        p.curarse(20);
	 
	        assertEquals(70, p.getVidaActual());
	    }
	 
	   
	    @Test
	    void CP18_curarse_noSuperaVidaMaxima() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	        p.recibirDanio(10);
	        p.curarse(999);
	 
	        assertEquals(100, p.getVidaActual());
	    }
	 
	    
	    @Test
	    void CP19_estaDebilitado_conEstadoActivo_devuelveFalse() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	 
	        assertFalse(p.estaDebilitado());
	    }
	 
	  
	    @Test
	    void CP20_estaDebilitado_conEstadoDebilitado_devuelveTrue() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Debilitado");
	 
	        assertTrue(p.estaDebilitado());
	    }
	 
	    
	    @Test
	    void CP21_reiniciarEstado_vuelveAActivoConVidaMaxima() {
	        Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
	        p.recibirDanio(100);
	        p.reiniciarEstado();
	 
	        assertEquals("Activo", p.getEstado());
	        assertEquals(100,      p.getVidaActual());
	        assertFalse(p.estaDebilitado());
	    }

}
