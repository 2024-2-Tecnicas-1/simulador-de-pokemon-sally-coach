package logicaNegocio;

import persistencia.ArchivosConexion;

public class ArchivosControlador {
    public void guardar(Object objeto, String nombreArchivo){
        ArchivosConexion.guardar(objeto, nombreArchivo);
    }
    
    public Object leer(String nombreArchivo){
        return ArchivosConexion.leer(nombreArchivo);
    }
}
