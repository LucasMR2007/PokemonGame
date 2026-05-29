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
	void CH02_potenciaUno_esValida() {
		Habilidad h = new Habilidad("Arañazo", "Normal", 1);

		assertEquals(1, h.getPotencia());
	}
}
