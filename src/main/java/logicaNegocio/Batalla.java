package logicaNegocio;

import java.util.LinkedList;
import java.util.List;

public class Batalla {
    // TODO: Aquí va tu código
    
    private List<Entrenador> entrenadoresListos;

    public Batalla(List<Entrenador> entrenadoresListos) {
        this.entrenadoresListos = new LinkedList(entrenadoresListos);
    }
    
    public List<Entrenador> getEntrenadoresListos() {
        return entrenadoresListos;
    }
    
    
    public String iniciar() {
        if (entrenadoresListos.size()<2) {
            return "Se necesitan al menos 2 Entrenadores";
        }
        
        for (Entrenador entrenador : entrenadoresListos) {
            if (!entrenador.listoParaBatalla()) {
                entrenadoresListos.remove(entrenador);
            }
        }
        
        if (entrenadoresListos.size()<2) {
            return "Se necesitan al menos 2 Entrenadores con Pokémones sanos";
        }
        
        return null;
    }
    
    
    public List<Pokemon> pokemonesListos(Entrenador entrenador) {
        entrenadoresListos.remove(entrenador);
        
        List<Pokemon> pokemones = entrenador.getPokemones();
        for (Pokemon pokemon : pokemones) {
            if (pokemon.getSalud()<=0) {
                pokemones.remove(pokemon);
            }
        }
        return pokemones;
    }
    
    
    public boolean enBatalla(Pokemon pokemon1, Pokemon pokemon2) {
        return pokemon1.getSalud()>0 && pokemon2.getSalud()>0;
    }
    
    
    public String mostrarGanador(Pokemon pokemon1, Pokemon pokemon2) {
        if (enBatalla(pokemon1, pokemon2)) {
            return "Ningún Pokémon aumenta su nivel";
        } else {
            Pokemon ganador = (pokemon1.getSalud()>0) ? pokemon1 : pokemon2;
            return ganador.getNombre() + " gana la batalla y aumenta su nivel. " + ganador.entrenar();
        }
    }
    
    
    public void automatizar(Pokemon pokemon1, Pokemon pokemon2) {
        while (enBatalla(pokemon1, pokemon2)) {
            pokemon1.atacar(pokemon2);

            if (pokemon2.getSalud()>0) {
                pokemon2.atacar(pokemon1);
            }
        }
    }
    
}
