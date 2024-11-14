package logicaNegocio;

public enum TipoPokemon {
    // TODO: Aquí va tu código
    FUEGO, AGUA, VENENO, ELECTRICO, TIERRA, NORMAL, LUCHA, BICHO;
    
    private static final double[][] multiplicadores = {
       //FUE, AGU, VEN, ELE, TIE, NOR, LUC, BIC;
        {  1, 0.5,   1,   1,   1,   1,   1,   2}, // FUEGO
        {  2,   1,   1,   1,   1,   1,   1,   1}, // AGUA
        {  1,   1,   1,   1,   1,   1,   1,   1}, // VENENO
        {  1,   2,   1,   1,   0,   1,   1,   1}, // ELECTRICO
        {  2,   1,   2,   0,   1,   1,   1,   1}, // TIERRA
        {  1,   1,   1,   1,   1,   1,   1,   1}, // NORMAL
        {  1,   1,   1,   1,   1,   2,   1,   1}, // LUCHA
        {  1,   1,   1,   1,   1,   1,   1,   1}, // BICHO
    };
    
    public static double obtenerMultiplicadorDeDaño(TipoPokemon atacante, TipoPokemon defensor) {
        return multiplicadores[atacante.ordinal()][defensor.ordinal()];
    }
}