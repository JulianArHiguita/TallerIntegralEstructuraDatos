/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerintegrador;

/**
 *
 * @author jhigu
 */
public class Movimiento {
    
    // Definir atributos
    private String hora;
    private String tipoOperacion; // INGRESO", "CARGA", "DESCARGA", "ATENCIÓN"
    private String descripcion;
 
    // CONSTRUCTOR
    public Movimiento(String hora, String tipoOperacion, String descripcion) {
        this.hora = hora;
        this.tipoOperacion = tipoOperacion;
        this.descripcion = descripcion;
    }
 
    // GETTERS
    public String getHora() { 
        return hora; 
    }
    
    public String getTipoOperacion() { 
        return tipoOperacion; 
    }
    
    public String getDescripcion() {
        return descripcion;
    }
 
    // Metodo toString
    @Override
    public String toString() {
        return "[" + hora + "] " + tipoOperacion + " -> " + descripcion;
    }
}
