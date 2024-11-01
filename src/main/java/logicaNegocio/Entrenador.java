package logicaNegocio;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Entrenador implements Serializable {
    // TODO: Aquí va tu código
    
    private String nombre;
    private List<Pokemon> pokemones;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.pokemones = new LinkedList();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public List<Pokemon> getPokemones() {
        return pokemones;
    }
    
    
    @Override
    public String toString() {
        return nombre + ". Pokémones: " + pokemones;
    }

    
    public void agregarPokemon(Pokemon pokemon) {
        pokemones.add(pokemon);
    }
    
    
    public String entrenarPokemon(Pokemon pokemon) {
        return pokemon.entrenar();
    }
    
    
    public boolean listoParaBatalla() {
        boolean pokemonListo = false;
        int i = 0;
        while (i<pokemones.size() && !pokemonListo) {
            if (pokemones.get(i).getSalud()>0) {
                pokemonListo = true;
            }
            i++;
        }
        return pokemonListo;
    }
    
}
