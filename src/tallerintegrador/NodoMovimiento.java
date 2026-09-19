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
public class NodoMovimiento {
    // Llamar clase movimiento
    private Movimiento movimiento;
    
    // Definir nodo siguiente
    private NodoMovimiento siguiente;
 
    // CONSTRUCTOR
    public NodoMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
        this.siguiente = null;
    }
 
    // GETTERS
    public Movimiento getMovimiento() { 
        return movimiento; 
    }
    
    public NodoMovimiento getSiguiente() { 
        return siguiente; 
    }
    
    // SETTERS
    public void setSiguiente(NodoMovimiento siguiente) { 
        this.siguiente = siguiente; 
    }
}
