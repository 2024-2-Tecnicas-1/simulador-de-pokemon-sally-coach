package logicaNegocio;

import persistencia.*;

public class ArchivosControlador {
    public void guardar(Object objeto, String nombreArchivo){
        ArchivosConexion.guardar(objeto, nombreArchivo);
    }
    
    public Object leer(String nombreArchivo){
        return ArchivosConexion.leer(nombreArchivo);
    }
}
