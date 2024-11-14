package logicaNegocio;

import persistencia.*;

public class ArchivosControlador {
    public static void guardar(Object objeto, String nombreArchivo){
        ArchivosConexion.guardar(objeto, nombreArchivo);
    }
    
    public static Object leer(String nombreArchivo){
        return ArchivosConexion.leer(nombreArchivo);
    }
}
