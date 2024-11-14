package logicaNegocio;

import java.io.Serializable;

public abstract class Pokemon implements Serializable {
    private String nombre;
    private int saludBase;
    private int salud;
    private int puntosDeAtaque;
    private final TipoPokemon TIPO;
    private String estado;

    public Pokemon(String nombre, int saludBase, int puntosDeAtaque, TipoPokemon TIPO) {
        this.nombre = nombre;
        this.saludBase = saludBase;
        this.puntosDeAtaque = puntosDeAtaque;
        this.TIPO = TIPO;
        
        salud = saludBase;
        estado = "Normal";
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getPuntosdeAtaque() {
        return puntosDeAtaque;
    }

    public String getEstado() {
        return estado;
    }

    public TipoPokemon getTIPO() {
        return TIPO;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        if (salud>0) {
            return nombre + " HP: " + salud + " ATK: " + puntosDeAtaque;
        }
        
        return nombre + " HP: " + salud;
    }
    
    
    public String atacar(Pokemon oponente){
        double dañoTotal = puntosDeAtaque*TipoPokemon.obtenerMultiplicadorDeDaño(TIPO, oponente.getTIPO());
        oponente.recibirDaño(dañoTotal);
        return nombre + " ataca a " + oponente.getNombre() + " causando " + dañoTotal + " puntos de daño";
    }
    
    private void recibirDaño(double daño) {
        salud -= daño;
        if (salud<0) {
            salud = 0;
        }
    }
    
    public String entrenar() {
        int incremento = 1;
        saludBase += incremento;
        salud = saludBase;
        puntosDeAtaque += incremento;
        return "[HP: " + (salud-incremento) + "+" + incremento + " ATK: " + (puntosDeAtaque-incremento) + "+" + incremento + "]";
    }
}