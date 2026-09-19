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
public class NodoPila {
    
    // Clase que implementa los nodos para las pilas
       
    // Llamar a clase contenedor
    private Contenedor contenedor;
    
    // Definimos nodo
    private NodoPila siguiente;
 
    // CONSTRUCTOR
    public NodoPila(Contenedor contenedor) {
        this.contenedor = contenedor;
        this.siguiente = null;
    }
 
    // GETTERS
    public Contenedor getContenedor() {
        return contenedor; 
    }
    
    public NodoPila getSiguiente() { 
        return siguiente; 
    }
    
    // SETTERS
    public void setSiguiente(NodoPila siguiente) { 
        this.siguiente = siguiente; 
    }
}
