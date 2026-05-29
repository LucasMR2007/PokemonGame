package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pokemonGame.Habilidad;

public class TestHabilidad {

	@Test
	void CH01_habilidadValida_OK() {
		Habilidad h = new Habilidad("Impactrueno", "Eléctrico", 40);

		assertEquals("Impactrueno", h.getNombre());
		assertEquals("Eléctrico", h.getTipo());
		assertEquals(40, h.getPotencia());
	}

	@Test
	void CH02_nombreVacio_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("", "Fuego", 60);
		});
	}

	@Test
	void CH03_nombreNull_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad(null, "Fuego", 60);
		});
	}

	@Test
	void CH04_tipoNull_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("Llama Fusión", null, 100);
		});
	}

	@Test
	void CH05_potenciaNegativa_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("Placaje", "Normal", -15);
		});
	}

	@Test
	void CH06_potenciaCero_lanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("Malicioso", "Normal", 0);
		});
	}

	@Test
	void CH07_potenciaUno_esValida() {
		Habilidad h = new Habilidad("Arañazo", "Normal", 1);

		assertEquals(1, h.getPotencia());
	}
}
