package logicaNegocio;

import java.io.Serializable;

public abstract class Pokemon implements Serializable {
    private String tipoPokemon;
    private String nombre;
    private int salud;
    private String estado;
    private int puntosdeAtaque;

    public Pokemon(String tipoPokemon, String nombre, int salud, int puntosdeAtaqye) {
        this.tipoPokemon = tipoPokemon;
        this.nombre = nombre;
        this.salud = salud;
        this.puntosdeAtaque = puntosdeAtaque;
    }

    public String getTipoPokemon() {
        return tipoPokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public String getEstado() {
        return estado;
    }

    public int getPuntosdeAtaqye() {
        return puntosdeAtaque;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPuntosdeAtaqye(int puntosdeAtaqye) {
        this.puntosdeAtaque = puntosdeAtaque;
    }
    
    public int atacar(Pokemon oponente){
        int dañoTotal = puntosdeAtaque*obtenerMultiplicadorDeDaño();
        
    }
}