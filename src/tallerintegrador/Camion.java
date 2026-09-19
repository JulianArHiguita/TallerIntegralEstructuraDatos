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
public class Camion {
    
    // DEFINICION DE ATRIBUTOS
    private String placa;
    private String conductor;
    private String horaEstimada;
    private String contenedorAsociado;
    private String estadoAtencion;
 
    
    // CONSTRUCTOR
    public Camion(String placa, String conductor, String horaEstimada, String contenedorAsociado, String estadoAtencion) {
        this.placa = placa;
        this.conductor = conductor;
        this.horaEstimada = horaEstimada;
        this.contenedorAsociado = contenedorAsociado;
        this.estadoAtencion = estadoAtencion;
    }
 
    // GETTERS
    public String getPlaca() {
        return placa; 
    }
    
    public String getConductor() { 
        return conductor; 
    }
    
    public String getHoraEstimada() {
        return horaEstimada; 
    }
    
    public String getContenedorAsociado() {
        return contenedorAsociado;
    }
    
    public String getEstadoAtencion() { 
        return estadoAtencion; 
    }
 
    // SETTERS
    public void setEstadoAtencion(String estadoAtencion) {
        this.estadoAtencion = estadoAtencion; 
    }
    
    public void setContenedorAsociado(String contenedorAsociado) { 
        this.contenedorAsociado = contenedorAsociado; 
    }
 
    
    // METODO ToString
    @Override
    public String toString() {
        return String.format(
            "Placa: " , placa," | Conductor: ", conductor, " | Hora estimada: ", horaEstimada, " | Contenedor: ",contenedorAsociado," | Estado: ", estadoAtencion
        );
    }
    
}
