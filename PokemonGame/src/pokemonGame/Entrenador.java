package pokemonGame;

import java.util.ArrayList;

public class Entrenador {

	private String nombre;
	private ArrayList<Pokemon> equipo;
	private Pokemon pokemonActivo;
	private char rango;

	public Entrenador(String nombre, char rango) {
		this.nombre = nombre;
		this.rango = rango;
		this.equipo = new ArrayList<Pokemon>();
		this.pokemonActivo = null;
	}

	public String getNombre() {
		return nombre;
	}

	public char getRango() {
		return rango;
	}

	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}

	public Pokemon getPokemonActivo() {
		return pokemonActivo;
	}

	public void anyadirPokemon(Pokemon pokemon) {
		if (equipo.size() < 6) {
			equipo.add(pokemon);
			if (pokemonActivo == null) {
				pokemonActivo = pokemon;
			}
		}
	}

	public void cambiarPokemonActivo(Pokemon pokemon) {
		if (equipo.contains(pokemon) && !pokemon.estaDebilitado()) {
			pokemonActivo = pokemon;
		}
	}

	public boolean tienePokemonDisponibles() {
		for (int i = 0; i < equipo.size(); i++) {
			if (!equipo.get(i).estaDebilitado()) {
				return true;
			}
		}
		return false;
	}

	public int getNumPokemonDebilitados() {
		int contador = 0;
		for (int i = 0; i < equipo.size(); i++) {
			if (equipo.get(i).estaDebilitado()) {
				contador++;
			}
		}
		return contador;
	}

	public void subirRango() {
		if (rango > 'A') {
			rango--;
		}
	}

	public void bajarRango() {
		if (rango < 'Z') {
			rango++;
		}
	}
}
