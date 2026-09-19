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
public class NodoCamion {
    
    // Llamar clase Camion
    private Camion camion;
    
    // Definir nodo siguiente para lista
    private NodoCamion siguiente;
 
    // Constructor
    public NodoCamion(Camion camion) {
        this.camion = camion;
        this.siguiente = null;
    }
 
    // GETTERS
    public Camion getCamion() { 
        return camion; 
    }
    
    public NodoCamion getSiguiente() {
        return siguiente; 
    }
    
    // SETTERS
    public void setSiguiente(NodoCamion siguiente) { 
        this.siguiente = siguiente; 
    }
}
