package pokemonGame;

import java.util.ArrayList;

public class Pokemon {

	private String nombre;
	private String tipo;
	private int vidaMaxima;
	private int vidaActual;
	private int defensa;
	private String estado;
	private ArrayList<Habilidad> habilidades;

	public Pokemon(String nombre, String tipo, int vidaMaxima, int defensa, String estado) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaMaxima;
		this.defensa = defensa;
		this.estado = estado;
		this.habilidades = new ArrayList<Habilidad>();
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	public int getDefensa() {
		return defensa;
	}

	public String getEstado() {
		return estado;
	}

	public ArrayList<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void annadirHabilidad(Habilidad habilidad) {
		if (habilidades.size() < 4) {
			habilidades.add(habilidad);
		}
	}

	public void recibirDanio(int danio) {
		if (danio < 1) {
			danio = 1;
		}
		vidaActual = vidaActual - danio;
		if (vidaActual < 0) {
			vidaActual = 0;
		}
		if (vidaActual == 0) {
			estado = "Debilitado";
		}
	}

	public void curarse(int cantidad) {
		vidaActual = vidaActual + cantidad;
		if (vidaActual > vidaMaxima) {
			vidaActual = vidaMaxima;
		}
	}

	public boolean estaDebilitado() {
		if (estado.equals("Debilitado")) {
			return true;
		}
		return false;
	}

	public void reiniciarEstado() {
		estado = "Activo";
		vidaActual = vidaMaxima;
	}

}
