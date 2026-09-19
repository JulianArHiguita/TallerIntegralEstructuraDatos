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
public class ColaCamiones {
    // CLASE que implementa FIFO
    
    // Definicion de nodos
    private NodoCamion frente; // primer camión en la fila (el próximo a atender)
    private NodoCamion fin;    // último camión que llegó
    
    // Variable para el conocer el tamaño de la lista
    private int tamano;
 
    // CONSTRUCTOR
    public ColaCamiones() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }
 
    // GETTERS
    public int getTamano() { 
        return tamano; 
    }
    
    // METODO PARA IDENTIFICAR SI LA LISTA ESTA VACIA
    public boolean isEmpty() { return tamano == 0; }
 
    // Metodo que inserta camion al final de la lista
    public void encolar(Camion camion) {
        NodoCamion nuevo = new NodoCamion(camion);
        if (isEmpty()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamano++;
    }
 
    // Metodo que atiende el camion que esta primero en la fila
    public Camion desencolar() {
        if (isEmpty()) {
            throw new OperacionInvalidaException("No hay camiones en la fila para atender.");
        }
        Camion atendido = frente.getCamion();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null; 
        }
        tamano--;
        return atendido;
    }
 
    // Metodo que identifica el camion que sigue en la lsita
    public Camion verFrente() {
        if (isEmpty()) {
            throw new OperacionInvalidaException("No hay camiones en la fila.");
        }
        return frente.getCamion();
    }
 
    // Metodo para buscar por placa recorrdiendo de inicio a final
    public Camion buscarPorPlaca(String placa) {
        NodoCamion actual = frente;
        while (actual != null) {
            if (actual.getCamion().getPlaca().equalsIgnoreCase(placa)) {
                return actual.getCamion();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
 
    // Metodo que recorre en orden de llegada
    public String recorrido() {
        String resultado = "";
        NodoCamion actual = frente;
        int i = 1;
        while (actual != null) {
            resultado = resultado + i + ". " + actual.getCamion() + "\n";
            i++;
            actual = actual.getSiguiente();
        }
        return resultado;
    }
}
