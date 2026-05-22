package pokemonGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("===========================================");
		System.out.println("        BIENVENIDO AL POKEMON GAME      ");
		System.out.println("===========================================");

		Habilidad rayo = new Habilidad("Rayo", "Eléctrico", 90);
		Habilidad placaje = new Habilidad("Placaje", "Normal", 40);
		Habilidad llamarada = new Habilidad("Llamarada", "Fuego", 110);
		Habilidad pistolAgua = new Habilidad("Pistola Agua", "Agua", 40);
		Habilidad hojaCortante = new Habilidad("Hoja Cortante", "Planta", 55);
		Habilidad impactrueno = new Habilidad("Impactrueno", "Eléctrico", 40);
		Habilidad ascuas = new Habilidad("Ascuas", "Fuego", 40);
		Habilidad surf = new Habilidad("Surf", "Agua", 90);

		Pokemon pikachu = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		pikachu.annadirHabilidad(rayo);
		pikachu.annadirHabilidad(impactrueno);
		pikachu.annadirHabilidad(placaje);

		Pokemon charmander = new Pokemon("Charmander", "Fuego", 110, 15, "Activo");
		charmander.annadirHabilidad(llamarada);
		charmander.annadirHabilidad(ascuas);
		charmander.annadirHabilidad(placaje);

		Pokemon squirtle = new Pokemon("Squirtle", "Agua", 120, 25, "Activo");
		squirtle.annadirHabilidad(pistolAgua);
		squirtle.annadirHabilidad(surf);
		squirtle.annadirHabilidad(placaje);

		Pokemon bulbasaur = new Pokemon("Bulbasaur", "Planta", 115, 22, "Activo");
		bulbasaur.annadirHabilidad(hojaCortante);
		bulbasaur.annadirHabilidad(placaje);

		Pokemon geodude = new Pokemon("Geodude", "Roca", 90, 50, "Activo");
		geodude.annadirHabilidad(placaje);

		Pokemon gastly = new Pokemon("Gastly", "Fantasma", 80, 10, "Activo");
		gastly.annadirHabilidad(placaje);

		Entrenador ash = new Entrenador("Ash", 'Z');
		ash.anyadirPokemon(pikachu);
		ash.anyadirPokemon(charmander);
		ash.anyadirPokemon(squirtle);

		Entrenador gary = new Entrenador("Gary", 'Z');
		gary.anyadirPokemon(bulbasaur);
		gary.anyadirPokemon(geodude);
		gary.anyadirPokemon(gastly);

		System.out.println();
		mostrarEquipo(ash);
		System.out.println();
		mostrarEquipo(gary);
		System.out.println();

		System.out.println("Pulsa ENTER para iniciar el combate...");
		teclado.nextLine();

		Combate combate = new Combate(ash, gary);

		while (combate.isActivo()) {

			Entrenador entrenadorActual = combate.getTurnoActual();
			Entrenador oponente = combate.getOponente(entrenadorActual);

			System.out.println("-------------------------------------------");
			System.out.println("  Turno de: " + entrenadorActual.getNombre());
			System.out.println("-------------------------------------------");

			mostrarEstadoPokemon("Tu Pokemon", entrenadorActual.getPokemonActivo());
			mostrarEstadoPokemon("Pokemon rival", oponente.getPokemonActivo());
			System.out.println();

			System.out.println("¿Qué ataque quieres usar?");
			ArrayList<Habilidad> habilidades = entrenadorActual.getPokemonActivo().getHabilidades();
			for (int i = 0; i < habilidades.size(); i++) {
				Habilidad h = habilidades.get(i);
				System.out.println("  " + (i + 1) + ". " + h.getNombre() + " (Tipo: " + h.getTipo() + ", Potencia: "
						+ h.getPotencia() + ")");
			}

			int eleccion = leerEntero(1, habilidades.size());
			int indice = eleccion - 1;

			int danio = combate.calcularDanio(entrenadorActual.getPokemonActivo(), oponente.getPokemonActivo(), indice);

			String nombreAtaque = habilidades.get(indice).getNombre();
			String nombreAtacante = entrenadorActual.getPokemonActivo().getNombre();
			String nombreDefensor = oponente.getPokemonActivo().getNombre();

			System.out.println();
			System.out.println(">> " + nombreAtacante + " usó " + nombreAtaque + " e hizo " + danio + " de daño a "
					+ nombreDefensor + ".");

			combate.realizarTurno(indice);

			if (oponente.getPokemonActivo().estaDebilitado()) {
				System.out.println(">> ¡" + nombreDefensor + " se ha debilitado!");

				if (oponente.tienePokemonDisponibles()) {
					System.out.println();
					System.out.println(oponente.getNombre() + ", elige tu siguiente Pokémon:");
					elegirSiguientePokemon(oponente);
				}
			}

			System.out.println();
		}

		Entrenador ganador = combate.getGanador();
		Entrenador perdedor = combate.getOponente(ganador);

		System.out.println("===========================================");
		System.out.println("            FIN DEL COMBATE");
		System.out.println("===========================================");
		System.out.println("  ¡" + ganador.getNombre() + " ha ganado el combate!");
		System.out.println();

		ganador.subirRango();
		perdedor.bajarRango();

		System.out.println("  Nuevo rango de " + ganador.getNombre() + ": " + ganador.getRango());
		System.out.println("  Nuevo rango de " + perdedor.getNombre() + ": " + perdedor.getRango());
		System.out.println("===========================================");
	}

	public static void mostrarEquipo(Entrenador entrenador) {
		System.out.println("Entrenador: " + entrenador.getNombre() + "  |  Rango: " + entrenador.getRango());
		System.out.println("Equipo:");
		ArrayList<Pokemon> equipo = entrenador.getEquipo();
		for (int i = 0; i < equipo.size(); i++) {
			Pokemon p = equipo.get(i);
			System.out.println("  " + (i + 1) + ". " + p.getNombre() + " (" + p.getTipo() + ")" + "  Vida: "
					+ p.getVidaActual() + "/" + p.getVidaMaxima() + "  Defensa: " + p.getDefensa());
		}
	}

	public static void mostrarEstadoPokemon(String etiqueta, Pokemon pokemon) {
		System.out.println("  " + etiqueta + ": " + pokemon.getNombre() + "  [Vida: " + pokemon.getVidaActual() + "/"
				+ pokemon.getVidaMaxima() + "]");
	}

	public static void elegirSiguientePokemon(Entrenador entrenador) {
		ArrayList<Pokemon> equipo = entrenador.getEquipo();

		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < equipo.size(); i++) {
			if (!equipo.get(i).estaDebilitado()) {
				indices.add(i);
				System.out.println("  " + indices.size() + ". " + equipo.get(i).getNombre() + "  [Vida: "
						+ equipo.get(i).getVidaActual() + "/" + equipo.get(i).getVidaMaxima() + "]");
			}
		}

		int eleccion = leerEntero(1, indices.size());
		int indiceReal = indices.get(eleccion - 1);
		entrenador.cambiarPokemonActivo(equipo.get(indiceReal));

		System.out.println(
				"--> " + entrenador.getNombre() + " envía a " + entrenador.getPokemonActivo().getNombre() + ".");
	}

	public static int leerEntero(int min, int max) {
		int valor = -1;
		boolean valido = false;
		while (!valido) {
			System.out.print("Elige una opción (" + min + "-" + max + "): ");
			if (teclado.hasNextInt()) {
				valor = teclado.nextInt();
				teclado.nextLine();
				if (valor >= min && valor <= max) {
					valido = true;
				} else {
					System.out.println("  Número fuera de rango. Inténtalo de nuevo.");
				}
			} else {
				System.out.println("  Eso no es un número. Inténtalo de nuevo.");
				teclado.nextLine();
			}
		}
		return valor;
	}
}
