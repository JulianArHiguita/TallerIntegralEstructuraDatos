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
public class PilaContenedores {
    // CLASE CON ESTRUCTURA LIFO
    
    // Definimos el contenedor que queda encima de todos
    private NodoPila cima;
    
    // Variable para conocer el tamaño de la lista
    private int tamano;
 
    // CONSTRUCTOR
    public PilaContenedores() {
        this.cima = null;
        this.tamano = 0;
    }
 
    // GETTERS
    public int getTamano() { 
        return tamano; 
    }
    
    // Metodo que valida si está vacía
    public boolean isEmpty() { 
        return tamano == 0; 
    }
 
    // Metodo Push
    public void apilar(Contenedor contenedor) {
        NodoPila nuevo = new NodoPila(contenedor);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamano++;
    }
 
    // Metodo Pop
    public Contenedor desapilar() {
        if (isEmpty()) {
            throw new OperacionInvalidaException("La pila de contenedores está vacía, no hay nada para retirar.");
        }
        Contenedor retirado = cima.getContenedor();
        cima = cima.getSiguiente();
        tamano--;
        return retirado;
    }
 
    // Metodo que consulta el contenedor que está encima
    public Contenedor verCima() {
        if (isEmpty()) {
            throw new OperacionInvalidaException("La pila de contenedores está vacía.");
        }
        return cima.getContenedor();
    }
 
    // Mwtodo que busca por ID iniciando por la cima hasta la base
    public Contenedor buscarPorId(String id) {
        NodoPila actual = cima;
        while (actual != null) {
            if (actual.getContenedor().getId().equalsIgnoreCase(id)) {
                return actual.getContenedor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
 
    // Metodo que recorre desde la cima hasta la base para identificar el orden en el que se irá retirando
    public String recorrido() {
        String resultado = "";
        NodoPila actual = cima;
        int i = 1;
        while (actual != null) {
            resultado = resultado + i + ". " + actual.getContenedor() + "\n";
            i++;
            actual = actual.getSiguiente();
        }
        return resultado;
    }
}
