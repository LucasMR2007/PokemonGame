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
	void CP2_cincoHabilidades_noSeAnyadeQuinta() {
		Pokemon p = new Pokemon("Mewtwo", "Psíquico", 100, 10, "Activo");
		p.annadirHabilidad(new Habilidad("h1", "Normal", 10));
		p.annadirHabilidad(new Habilidad("h2", "Normal", 10));
		p.annadirHabilidad(new Habilidad("h3", "Normal", 10));
		p.annadirHabilidad(new Habilidad("h4", "Normal", 10));
		p.annadirHabilidad(new Habilidad("h5", "Normal", 10)); // no se debe añadir

		assertEquals(4, p.getHabilidades().size());
	}

	@Test
	void CP3_recibirDanio_reduceVidaActual() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		p.recibirDanio(30);

		assertEquals(70, p.getVidaActual());
	}

	@Test
	void CP4_recibirDanio_vidaCero_cambiaADebilitado() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		p.recibirDanio(100);

		assertEquals(0, p.getVidaActual());
		assertEquals("Debilitado", p.getEstado());
		assertTrue(p.estaDebilitado());
	}

	@Test
	void CP5_recibirDanio_noNegativa() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		p.recibirDanio(999);

		assertEquals(0, p.getVidaActual());
	}

	@Test
	void CP6_recibirDanio_menorQue1_seAplicaUnoDeDanio() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		p.recibirDanio(0);

		assertEquals(99, p.getVidaActual());
	}

	@Test
	void CP7_curarse_aumentaVida() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		p.recibirDanio(50);
		p.curarse(20);

		assertEquals(70, p.getVidaActual());
	}

	@Test
	void CP8_curarse_noSuperaVidaMaxima() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		p.recibirDanio(10);
		p.curarse(999);

		assertEquals(100, p.getVidaActual());
	}

	@Test
	void CP9_estaDebilitado_conEstadoActivo_devuelveFalse() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");

		assertFalse(p.estaDebilitado());
	}

	@Test
	void CP10_estaDebilitado_conEstadoDebilitado_devuelveTrue() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Debilitado");

		assertTrue(p.estaDebilitado());
	}

	@Test
	void CP11_reiniciarEstado_vuelveAActivoConVidaMaxima() {
		Pokemon p = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		p.recibirDanio(100);
		p.reiniciarEstado();

		assertEquals("Activo", p.getEstado());
		assertEquals(100, p.getVidaActual());
		assertFalse(p.estaDebilitado());
	}

}
