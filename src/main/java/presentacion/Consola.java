package presentacion;

import logicaNegocio.*;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Consola {
    final String NOMBRE_ARCHIVO = "simulador.pokemon";
    private Scanner sc;
    ArchivosControlador controlador;
    private List<Entrenador> entrenadores;
    private List<Pokemon> pokemones;
    
    public void iniciar() {
        sc = new Scanner(System.in);
        controlador = new ArchivosControlador();
        entrenadores = (LinkedList<Entrenador>)controlador.leer(NOMBRE_ARCHIVO);
        
        if (entrenadores==null) {
            entrenadores = new LinkedList<>();
        }
        if (pokemones==null) {
            pokemones = new LinkedList<>();
        }
        
        System.out.println("SIMULADOR DE BATALLAS POKÉMON");
        int opcion;
        do {
            System.out.println();
            System.out.println("Menú Principal");
            System.out.println("1. Gestionar Entrenadores");
            System.out.println("2. Gestionar Pokémones");
            System.out.println("3. Iniciar Batalla");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    gestionarEntrenadores();
                    break;
                case 2:// TODO:
                    //gestionarPokemones(); pueden incluir sus opciones en un método como ese para poder regresar con un return
                    //si no están las condiciones para una opción, por ejemplo.
                    
                    //Hay un método imprimirLista([nombre de lista]) y seleccionarDeLista([nombre de lista]) por si lo necesitan.
                    //El de seleccionar devuelve un objeto que hay que hacerle cast de la clase que se requiera al llamarlo,
                    //como en el case 3 del primer switch de gestionarEntrenaadores().
                    break;
                case 3 :
                    iniciarBatalla();
                    break;
                case 4 :
                    break;
                default :
                    System.out.println("Selección no válida");
            }
            
        } while (opcion!=4);
    }
    
    private void gestionarEntrenadores() {
        int opcion;
        do {
            System.out.println();
            System.out.println("Gestionar Entrenadores");
            System.out.println("1. Registrar nuevo entrenador");
            System.out.println("2. Ver lista de entrenadores");
            System.out.println("3. Seleccionar un entrenador");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            System.out.println();
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre: ");
                    String nombre = sc.next();
                    entrenadores.add(new Entrenador(nombre));
                    controlador.guardar(entrenadores, NOMBRE_ARCHIVO);
                    System.out.println("Entrenador " + nombre + " ha sido registrado");
                    break;
                case 2:
                    if (entrenadores.isEmpty()) {
                        System.out.println("Lista de Entrenadores vacía. Registre algún Entrenador");
                    } else {
                        System.out.println("Lista de entrenadores:");
                        imprimirLista(entrenadores);
                    }
                    break;
                case 3:
                    if (entrenadores.isEmpty()) {
                        System.out.println("Lista de entrenadores vacía. Registre algún entrenador");
                    } else {
                        System.out.println("Lista de entrenadores:");
                        System.out.println("0. Cancelar");
                        imprimirLista(entrenadores);
                        Entrenador entrenador = (Entrenador)seleccionarDeLista(entrenadores); //Este es el ejemplo
                        //con cast entre paréntesis de la clase requerida, en este caso, Entrenador
                        if (entrenador == null) return;
                        int opcionEntrenador;
                        do {
                            System.out.println();
                            System.out.println("Entrenador " + entrenador.getNombre());
                            System.out.println("1. Ver equipo de Pokémones");
                            System.out.println("2. Agregar Pokémon al equipo");
                            System.out.println("3. Entrenar Pokémon");
                            System.out.println("4. Volver a Gestionar Entrenadores");
                            System.out.print("Seleccione una opción: ");
                            opcionEntrenador = sc.nextInt();
                            System.out.println();

                            switch (opcionEntrenador) {
                                case 1:
                                    if (entrenador.getPokemones().isEmpty()) {
                                        System.out.println(entrenador.getNombre() + " no tiene pokémones. Agregue algún Pokémon");
                                    } else {
                                        System.out.println("Pokémones de " + entrenador.getNombre() + ":");
                                        imprimirLista(entrenador.getPokemones());
                                    }
                                    break;
                                case 2:
                                    if (pokemones.isEmpty()) {
                                        System.out.println("Lista de Pokémones vacía. Registre algún Pokémon");
                                    } else {
                                        System.out.println("Lista de Pokémones:");
                                        System.out.println("0. Cancelar");
                                        imprimirLista(pokemones);
                                        Pokemon pokemon = (Pokemon)seleccionarDeLista(pokemones);
                                        if (pokemon == null) return;
                                        entrenador.agregarPokemon(pokemon);
                                        controlador.guardar(entrenadores, NOMBRE_ARCHIVO);
                                        System.out.println(pokemon.getNombre() + " ha sido agregado al equipo de " + entrenador.getNombre());
                                    }
                                    break;
                                case 3:
                                    if (entrenador.getPokemones().isEmpty()) {
                                        System.out.println(entrenador.getNombre() + " no tiene pokémones. Agregue algún Pokémon");
                                    } else {
                                        System.out.println("Pokémones de " + entrenador.getNombre() + ":");
                                        System.out.println("0. Cancelar");
                                        imprimirLista(entrenador.getPokemones());
                                        Pokemon pokemon = (Pokemon)seleccionarDeLista(entrenador.getPokemones());
                                        if (pokemon == null) return;
                                        System.out.println(entrenador.entrenarPokemon(pokemon));
                                        controlador.guardar(entrenadores, NOMBRE_ARCHIVO);
                                        System.out.println(pokemon.getNombre() + " ha sido entrenado.");
                                    }
                                    break;
                                case 4:
                                    System.out.println("Volviendo a Gestionar Entrenadores");
                                    break;
                                default:
                                    System.out.println("Selección no válida");
                            }
                        } while (opcionEntrenador!=4);
                    }
                    break;
                case 4:
                    System.out.println("Volviendo a Menú Principal");
                    break;
                default:
                    System.out.println("Selección no válida");
            }
        } while (opcion!=4);
    }
    
    
    public void iniciarBatalla() {
        System.out.println();
        Batalla batalla = new Batalla(entrenadores);
        String iniciar = batalla.iniciar();
        
        if (iniciar!=null) {
            System.out.println(iniciar);
            if (entrenadores.size()>=2) {
                System.out.println("Lista de Entrenadores:");
                imprimirLista(entrenadores);
                System.out.println("Entrenar un Pokémon aumenta su nivel y lo cura");
            }
        } else {
            System.out.println("Entrenadores con Pokémones sanos:");
            System.out.println("0. Cancelar");
            imprimirLista(batalla.getEntrenadoresListos());
            System.out.print("Entrenador 1. ");
            Entrenador entrenador1 = (Entrenador)seleccionarDeLista(batalla.getEntrenadoresListos());
            if (entrenador1 == null) return;
            System.out.println();
            
            System.out.println("Pokémones sanos:");
            System.out.println("0. Cancelar");
            imprimirLista(batalla.pokemonesListos(entrenador1));
            System.out.print("Pokemon 1. ");
            Pokemon pokemon1 = (Pokemon)seleccionarDeLista(batalla.pokemonesListos(entrenador1));
            if (pokemon1 == null) return;
            
            System.out.println();
            System.out.println("Entrenadores con Pokémones sanos:");
            System.out.println("0. Cancelar");
            imprimirLista(batalla.getEntrenadoresListos());
            System.out.print("Entrenador 2. ");
            Entrenador entrenador2 = (Entrenador)seleccionarDeLista(batalla.getEntrenadoresListos());
            if (entrenador2 == null) return;
            System.out.println();
            
            System.out.println("Pokémones sanos:");
            System.out.println("0. Cancelar");
            imprimirLista(batalla.pokemonesListos(entrenador2));
            System.out.print("Pokemon 2. ");
            Pokemon pokemon2 = (Pokemon)seleccionarDeLista(batalla.pokemonesListos(entrenador2));
            if (pokemon2 == null) return;
            
            System.out.println();
            System.out.println(pokemon1.getNombre() + " vs. " + pokemon2.getNombre());
            System.out.println(pokemon1.toString());
            System.out.println(pokemon2.toString());
            
            boolean enBatalla = true;
            int opcion;
            while (enBatalla) {
                System.out.println();
                System.out.println("1. " + pokemon1.getNombre() + ". Atacar");
                System.out.println("2. " + pokemon2.getNombre() + ". Atacar");
                System.out.println("3. Automatizar Batalla");
                System.out.println("4. Finalizar Batalla");
                System.out.print("Seleccione una opcion: ");
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println(pokemon1.atacar(pokemon2));
                        enBatalla = batalla.enBatalla(pokemon1, pokemon2);
                        break;
                    case 2:
                        System.out.println(pokemon2.atacar(pokemon1));
                        enBatalla = batalla.enBatalla(pokemon1, pokemon2);
                        break;
                    case 3:
                        System.out.println("Batalla automatizada");
                        batalla.automatizar(pokemon1, pokemon2);
                        enBatalla = batalla.enBatalla(pokemon1, pokemon2);
                        break;
                    case 4:
                        System.out.println("Batalla finalizada");
                        enBatalla = false;
                        break;
                    default: System.out.println("Selección no válida");
                }
                
                System.out.println();
                System.out.println(pokemon1.toString());
                System.out.println(pokemon2.toString());
            }
            
            System.out.println();
            System.out.println(batalla.mostrarGanador(pokemon1, pokemon2));
            controlador.guardar(entrenadores, NOMBRE_ARCHIVO);
        }
    }
    
    
    private void imprimirLista(List lista) {
        for (int i=0; i<lista.size(); i++) {
            System.out.println(i+1 + ". " + lista.get(i).toString());
        }
    }
    
    
    private Object seleccionarDeLista(List lista) {
        int seleccion;
        if (lista.size()==1) {
            seleccion = 1;
            System.out.print("Selección automática: 1");
            System.out.println();
        } else {
            do {
                System.out.print("Selección: ");
                seleccion = sc.nextInt();
                if (seleccion==0) {
                    return null;
                }
                if (seleccion<0 || seleccion>lista.size()) {
                    System.out.println("Selección no válida. Intente de nuevo");
                }
            } while (seleccion<0 || seleccion>lista.size());
        }
        
        return lista.get(seleccion-1);
    }
    
}