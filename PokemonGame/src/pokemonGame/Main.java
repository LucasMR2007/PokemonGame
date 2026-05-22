package pokemonGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner teclado = new Scanner(System.in);

	
	static ArrayList<Habilidad> listaHabilidades = new ArrayList<>();
	static ArrayList<Pokemon> listaPokemons = new ArrayList<>();
	static ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();

	public static void main(String[] args) {
		
		cargarDatosPredeterminados();

		int opcion = 0;
		do {
			System.out.println("\n===========================================");
			System.out.println("          MENÚ PRINCIPAL POKÉMON           ");
			System.out.println("===========================================");
			System.out.println("1. Crear Habilidad");
			System.out.println("2. Crear Pokémon");
			System.out.println("3. Crear Entrenador");
			System.out.println("4. Ver Entrenadores e hilos de equipo");
			System.out.println("5. Iniciar Combate");
			System.out.println("6. Salir del Juego");
			System.out.println("===========================================");

			opcion = leerEntero(1, 6);

			switch (opcion) {
				case 1:
					menuCrearHabilidad();
					break;
				case 2:
					menuCrearPokemon();
					break;
				case 3:
					menuCrearEntrenador();
					break;
				case 4:
					menuVerEntrenadores();
					break;
				case 5:
					menuIniciarCombate();
					break;
				case 6:
					System.out.println("¡Gracias por jugar! Saliendo del sistema...");
					break;
			}
		} while (opcion != 6);
	}


	public static void menuCrearHabilidad() {
		System.out.println("\n--- NUEVA HABILIDAD ---");
		System.out.print("Introduce el nombre del ataque: ");
		String nombre = teclado.nextLine();
		System.out.print("Introduce el tipo (ej: Fuego, Agua, Eléctrico): ");
		String tipo = teclado.nextLine();
		System.out.print("Introduce su potencia: ");
		int potencia = leerEntero(0, 200);

		Habilidad nuevaHab = new Habilidad(nombre, tipo, potencia);
		listaHabilidades.add(nuevaHab);
		System.out.println(">> ¡Habilidad '" + nombre + "' guardada con éxito!");
	}

	public static void menuCrearPokemon() {
		System.out.println("\n--- NUEVO POKÉMON ---");
		System.out.print("Nombre del Pokémon: ");
		String nombre = teclado.nextLine();
		System.out.print("Tipo principal: ");
		String tipo = teclado.nextLine();
		System.out.print("Vida Máxima (PS): ");
		int vidaMax = leerEntero(10, 999);
		System.out.print("Defensa: ");
		int defensa = leerEntero(0, 300);

		
		Pokemon nuevoPok = new Pokemon(nombre, tipo, vidaMax, defensa, "Activo");

		if (listaHabilidades.isEmpty()) {
			System.out.println("Aviso: No hay habilidades registradas. El Pokémon no tendrá ataques.");
		} else {
			System.out.println("¿Cuántas habilidades quieres asignarle? (Máximo 4 asignaciones):");
			int totalHab = leerEntero(0, 4);

			for (int i = 0; i < totalHab; i++) {
				System.out.println("Selecciona la habilidad " + (i + 1) + ":");
				for (int j = 0; j < listaHabilidades.size(); j++) {
					System.out.println("  " + (j + 1) + ". " + listaHabilidades.get(j).getNombre() + " (" + listaHabilidades.get(j).getTipo() + ")");
				}
				int seleccion = leerEntero(1, listaHabilidades.size()) - 1;
				nuevoPok.annadirHabilidad(listaHabilidades.get(seleccion));
			}
		}

		listaPokemons.add(nuevoPok);
		System.out.println(">> ¡Pokémon '" + nombre + "' registrado con éxito!");
	}

	public static void menuCrearEntrenador() {
		System.out.println("\n--- NUEVO ENTRENADOR ---");
		System.out.print("Nombre del entrenador: ");
		String nombre = teclado.nextLine();

		
		Entrenador nuevoEntrenador = new Entrenador(nombre, 'Z');

		if (listaPokemons.isEmpty()) {
			System.out.println("Aviso: No hay Pokémon creados todavía. Ve al menú 2 primero.");
		} else {
			System.out.println("¿Cuántos Pokémon quieres añadir a su equipo? (Máximo 6):");
			int totalPok = leerEntero(0, 6);

			for (int i = 0; i < totalPok; i++) {
				System.out.println("Selecciona el Pokémon número " + (i + 1) + ":");
				for (int j = 0; j < listaPokemons.size(); j++) {
					System.out.println("  " + (j + 1) + ". " + listaPokemons.get(j).getNombre());
				}
				int seleccion = leerEntero(1, listaPokemons.size()) - 1;
				nuevoEntrenador.anyadirPokemon(listaPokemons.get(seleccion));
			}
		}

		listaEntrenadores.add(nuevoEntrenador);
		System.out.println(">> ¡Entrenador '" + nombre + "' registrado con éxito!");
	}

	public static void menuVerEntrenadores() {
		System.out.println("\n--- LISTA DE ENTRENADORES REGISTRADOS ---");
		if (listaEntrenadores.isEmpty()) {
			System.out.println("No hay entrenadores creados.");
			return;
		}

		for (int i = 0; i < listaEntrenadores.size(); i++) {
			mostrarEquipo(listaEntrenadores.get(i));
			System.out.println("-------------------------------------------");
		}
	}

	public static void menuIniciarCombate() {
		if (listaEntrenadores.size() < 2) {
			System.out.println("\n[Error] Necesitas registrar al menos 2 entrenadores para iniciar una batalla.");
			return;
		}

		System.out.println("\n--- SELECCIONAR COMBATIENTES ---");
		System.out.println("Elige al primer Entrenador:");
		for (int i = 0; i < listaEntrenadores.size(); i++) {
			System.out.println("  " + (i + 1) + ". " + listaEntrenadores.get(i).getNombre());
		}
		int e1Index = leerEntero(1, listaEntrenadores.size()) - 1;

		System.out.println("Elige al segundo Entrenador:");
		for (int i = 0; i < listaEntrenadores.size(); i++) {
			if (i != e1Index) {
				System.out.println("  " + (i + 1) + ". " + listaEntrenadores.get(i).getNombre());
			}
		}
		int e2Index = leerEntero(1, listaEntrenadores.size()) - 1;

		while (e2Index == e1Index) {
			System.out.println("No puede luchar contra sí mismo. Elige otro número de la lista:");
			e2Index = leerEntero(1, listaEntrenadores.size()) - 1;
		}

		Entrenador retador1 = listaEntrenadores.get(e1Index);
		Entrenador retador2 = listaEntrenadores.get(e2Index);

		if (!retador1.tienePokemonDisponibles() || !retador2.tienePokemonDisponibles()) {
			System.out.println("\n[Error] Uno de los entrenadores no tiene ningún Pokémon apto para la batalla.");
			return;
		}

		
		bucleCombate(retador1, retador2);
	}

	

	private static void bucleCombate(Entrenador ash, Entrenador gary) {
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

			mostrarEstadoPokemon("Tu Pokémon", entrenadorActual.getPokemonActivo());
			mostrarEstadoPokemon("Pokémon rival", oponente.getPokemonActivo());
			System.out.println();

			System.out.println("¿Qué ataque quieres usar?");
			ArrayList<Habilidad> habilidades = entrenadorActual.getPokemonActivo().getHabilidades();
			
			if (habilidades.isEmpty()) {
				System.out.println(">> ¡Este Pokémon no tiene movimientos configurados! Pasa el turno.");
				combate.realizarTurno(0); 
				continue;
			}

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

		restaurarSaludEquipo(ganador);
		restaurarSaludEquipo(perdedor);
	}

	public static void restaurarSaludEquipo(Entrenador e) {
		for (Pokemon p : e.getEquipo()) {
			p.reiniciarEstado();
		}
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

	private static void cargarDatosPredeterminados() {
		Habilidad rayo = new Habilidad("Rayo", "Eléctrico", 90);
		Habilidad placaje = new Habilidad("Placaje", "Normal", 40);
		Habilidad llamarada = new Habilidad("Llamarada", "Fuego", 110);
		Habilidad surf = new Habilidad("Surf", "Agua", 90);
		
		listaHabilidades.add(rayo);
		listaHabilidades.add(placaje);
		listaHabilidades.add(llamarada);
		listaHabilidades.add(surf);

		Pokemon pikachu = new Pokemon("Pikachu", "Eléctrico", 100, 20, "Activo");
		pikachu.annadirHabilidad(rayo);
		pikachu.annadirHabilidad(placaje);

		Pokemon charmander = new Pokemon("Charmander", "Fuego", 110, 15, "Activo");
		charmander.annadirHabilidad(llamarada);
		charmander.annadirHabilidad(placaje);

		Pokemon squirtle = new Pokemon("Squirtle", "Agua", 120, 25, "Activo");
		squirtle.annadirHabilidad(surf);
		squirtle.annadirHabilidad(placaje);

		listaPokemons.add(pikachu);
		listaPokemons.add(charmander);
		listaPokemons.add(squirtle);

		Entrenador ash = new Entrenador("Ash", 'Z');
		ash.anyadirPokemon(pikachu);
		ash.anyadirPokemon(charmander);

		Entrenador gary = new Entrenador("Gary", 'Z');
		gary.anyadirPokemon(squirtle);

		listaEntrenadores.add(ash);
		listaEntrenadores.add(gary);
	}
}