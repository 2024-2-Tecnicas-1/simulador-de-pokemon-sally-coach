package presentacion;

import logicaNegocio.*;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Consola {
    final String ARCHIVO_ENTRENADORES = "entrenadores.simulador";
    final String ARCHIVO_POKEMONES = "pokemones.simulador";
    private Scanner sc;
    private List<Entrenador> entrenadores;
    private List<Pokemon> pokemones;
    
    public void iniciar() {
        sc = new Scanner(System.in);
        entrenadores = (LinkedList<Entrenador>)ArchivosControlador.leer(ARCHIVO_ENTRENADORES);
        pokemones = (LinkedList<Pokemon>)ArchivosControlador.leer(ARCHIVO_POKEMONES);
        
        if (entrenadores==null) {
            entrenadores = new LinkedList();
        }
        if (pokemones==null) {
            pokemones = new LinkedList();
        }
        
        System.out.println("SIMULADOR DE BATALLAS POKÉMON");
        int opcion;
        do {
            System.out.println();
            System.out.println("Menú Principal");
            System.out.println("0. Salir");
            System.out.println("1. Gestionar Entrenadores");
            System.out.println("2. Gestionar Pokémones");
            System.out.println("3. Iniciar Batalla");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 0 :
                    ArchivosControlador.guardar(entrenadores, ARCHIVO_ENTRENADORES);
                    ArchivosControlador.guardar(pokemones, ARCHIVO_POKEMONES);
                    System.out.println("Saliendo");
                    break;
                case 1:
                    gestionarEntrenadores();
                    break;
                case 2:
                    gestionarPokemones();
                    break;
                case 3 :
                    iniciarBatalla();
                    break;
                default :
                    System.out.println("Selección no válida");
            }
            
        } while (opcion!=0);
    }
    
    
    private void gestionarEntrenadores() {
        int opcion;
        do {
            System.out.println();
            System.out.println("Gestionar Entrenadores");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("1. Registrar nuevo entrenador");
            System.out.println("2. Ver lista de entrenadores");
            System.out.println("3. Seleccionar un entrenador");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al Menú Principal");
                    break;
                case 1:
                    System.out.print("Ingrese el nombre: ");
                    String nombre = sc.nextLine();
                    entrenadores.add(new Entrenador(nombre));
                    
                    ArchivosControlador.guardar(entrenadores, ARCHIVO_ENTRENADORES);
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
                        Entrenador entrenador = (Entrenador)seleccionarDeLista(entrenadores);
                        if (entrenador==null) return;
                        
                        int opcionEntrenador;
                        do {
                            System.out.println();
                            System.out.println("Entrenador " + entrenador.getNOMBRE());
                            System.out.println("0. Volver a Gestionar Entrenadores");
                            System.out.println("1. Ver equipo de Pokémones");
                            System.out.println("2. Agregar Pokémon al equipo");
                            System.out.println("3. Entrenar Pokémon");
                            System.out.print("Seleccione una opción: ");
                            opcionEntrenador = sc.nextInt();
                            sc.nextLine();
                            System.out.println();

                            switch (opcionEntrenador) {
                                case 0:
                                    System.out.println("Volviendo a Gestionar Entrenadores");
                                    break;
                                case 1:
                                    if (entrenador.getPokemones().isEmpty()) {
                                        System.out.println(entrenador.getNOMBRE() + " no tiene pokémones. Agregue algún Pokémon");
                                    } else {
                                        System.out.println("Pokémones de " + entrenador.getNOMBRE() + ":");
                                        imprimirLista(entrenador.getPokemones());
                                    }
                                    break;
                                case 2:
                                    if (pokemones.isEmpty()) {
                                        System.out.println("Lista de Pokémones vacía. Registre algún Pokémon");
                                    } else {
                                        List<Pokemon> pokemonesDisponibles = new LinkedList(pokemones);
                                        for (Entrenador entrenadorAux : entrenadores) {
                                            pokemonesDisponibles.removeAll(entrenadorAux.getPokemones());
                                        }
                                        
                                        if (pokemonesDisponibles.isEmpty()) {
                                            System.out.println("No hay Pokémones libres. Registre algún Pokémon");
                                        }
                                        
                                        System.out.println("Lista de Pokémones disponibles:");
                                        Pokemon pokemon = (Pokemon)seleccionarDeLista(pokemonesDisponibles);
                                        if (pokemon==null) return;
                                        entrenador.agregarPokemon(pokemon);
                                        
                                        ArchivosControlador.guardar(entrenadores, ARCHIVO_ENTRENADORES);
                                        ArchivosControlador.guardar(pokemones, ARCHIVO_POKEMONES);
                                        System.out.println(pokemon.getNombre() + " ha sido agregado al equipo de " + entrenador.getNOMBRE());
                                    }
                                    break;
                                case 3:
                                    if (entrenador.getPokemones().isEmpty()) {
                                        System.out.println(entrenador.getNOMBRE() + " no tiene pokémones. Agregue algún Pokémon");
                                    } else {
                                        System.out.println("Pokémones de " + entrenador.getNOMBRE() + ":");
                                        Pokemon pokemon = (Pokemon)seleccionarDeLista(entrenador.getPokemones());
                                        if (pokemon==null) return;
                                        System.out.println(pokemon.getNombre() + " fue entrenado " + pokemon.entrenar());
                                        
                                        ArchivosControlador.guardar(entrenadores, ARCHIVO_ENTRENADORES);
                                        ArchivosControlador.guardar(pokemones, ARCHIVO_POKEMONES);
                                        
                                    }
                                    break;
                                default:
                                    System.out.println("Selección no válida");
                            }
                        } while (opcionEntrenador!=0);
                    }
                    break;
                default:
                    System.out.println("Selección no válida");
            }
        } while (opcion!=0);
    }
    
    
    private void gestionarPokemones() {
        int opcion;
        do {
            System.out.println();
            System.out.println("Gestionar Pokémones");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("1. Ver todos los Pokémones registrados");
            System.out.println("2. Registrar nuevo Pokémon");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al Menú principal");
                    break;
                case 1:
                    if (pokemones.isEmpty()) {
                        System.out.println("Lista de Pokémones vacía. Registre algún Pokémon");
                    } else {
                        System.out.println("Pokemones registrados:");
                        imprimirLista(pokemones);
                    }
                    break;
                case 2:
                    List<Pokemon> especiesPokemon = new LinkedList();
                    especiesPokemon.add(new Ponyta("Ponyta"));
                    especiesPokemon.add(new Goldeen("Goldeen"));
                    especiesPokemon.add(new Paras("Paras"));
                    especiesPokemon.add(new Electrode("Electrode"));
                    especiesPokemon.add(new Staryu("Staryu"));
                    especiesPokemon.add(new Cubone("Cubone"));
                    especiesPokemon.add(new Doduo("Doduo"));
                    especiesPokemon.add(new Lickitung("Lickitung"));
                    especiesPokemon.add(new Hitmonchan("Hitmonchan"));
                    especiesPokemon.add(new Ekans("Ekans"));
                    
                    System.out.println("Especies Pokémon");
                    Pokemon pokemon = (Pokemon)seleccionarDeLista(especiesPokemon);
                    if (pokemon==null) return;
                    
                    System.out.println();
                    System.out.print("Ingrese el nombre: ");
                    String nombre = sc.nextLine();
                    pokemon.setNombre(nombre);
                    pokemones.add(pokemon);
                    
                    ArchivosControlador.guardar(pokemones, ARCHIVO_POKEMONES);
                    System.out.println(nombre + " ha sido registrado");
                    break;
                default:
                    System.out.println("Selección no válida");
            }
        } while (opcion!=0);
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
            Entrenador entrenador1 = (Entrenador)seleccionarDeLista(batalla.getEntrenadoresListos());
            if (entrenador1==null) return;
            System.out.println();
            
            System.out.println("Pokémones sanos:");
            Pokemon pokemon1 = (Pokemon)seleccionarDeLista(batalla.pokemonesListos(entrenador1));
            if (pokemon1==null) return;
            
            System.out.println();
            System.out.println("Entrenadores con Pokémones sanos:");
            Entrenador entrenador2 = (Entrenador)seleccionarDeLista(batalla.getEntrenadoresListos());
            if (entrenador2==null) return;
            System.out.println();
            
            System.out.println("Pokémones sanos:");
            Pokemon pokemon2 = (Pokemon)seleccionarDeLista(batalla.pokemonesListos(entrenador2));
            if (pokemon2==null) return;
            
            System.out.println();
            System.out.println(pokemon1.getNombre() + " vs. " + pokemon2.getNombre());
            System.out.println(pokemon1);
            System.out.println(pokemon2);
            
            boolean enBatalla = true;
            int opcion;
            while (enBatalla) {
                System.out.println();
                System.out.println("0. Cancelar Batalla");
                System.out.println("1. " + pokemon1.getNombre() + ". Atacar");
                System.out.println("2. " + pokemon2.getNombre() + ". Atacar");
                System.out.println("3. Automatizar Batalla");
                System.out.print("Seleccione una opcion: ");
                opcion = sc.nextInt();
                sc.nextLine();
                
                switch (opcion) {
                    case 0:
                        System.out.println("Batalla cancelada");
                        enBatalla = false;
                        break;
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
                    default: System.out.println("Selección no válida");
                }
                
                System.out.println();
                System.out.println(pokemon1);
                System.out.println(pokemon2);
            }
            
            System.out.println();
            System.out.println(batalla.mostrarGanador(pokemon1, pokemon2));
            ArchivosControlador.guardar(entrenadores, ARCHIVO_ENTRENADORES);
            ArchivosControlador.guardar(pokemones, ARCHIVO_POKEMONES);
        }
    }
    
    
    private void imprimirLista(List lista) {
        for (int i=0; i<lista.size(); i++) {
            System.out.println(i+1 + ". " + lista.get(i));
        }
    }
    
    
    private Object seleccionarDeLista(List lista) {
        System.out.println("0. Cancelar");
        imprimirLista(lista);
        
        int seleccion;
        if (lista.size()==1) {
            seleccion = 1;
            System.out.println("Selección automática: 1");
            System.out.print("Enter para continuar. 0 para Cancelar: ");
            String linea = sc.nextLine();
            if (linea.equals("0")) {
                seleccion = 0;
            }
        } else {
            do {
                System.out.print("Selección: ");
                seleccion = sc.nextInt();
                sc.nextLine();
                
                if (seleccion<0 || seleccion>lista.size()) {
                    System.out.println("Selección no válida. Intente de nuevo");
                }
            } while (seleccion<0 || seleccion>lista.size());
        }

        if (seleccion==0) {
            return null;
        }
        
        return lista.get(seleccion-1);
    }
    
}