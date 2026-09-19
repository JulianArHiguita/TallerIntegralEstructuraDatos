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
public class BitacoraMovimientos {
    
    // Definir nodos
    private NodoMovimiento inicio;
    private NodoMovimiento ultimo;
    
    // Atributos
    private int contador; // usado para numerar la hora simulada de cada evento
    private int tamano;
 
    // CONSTRUCTOR
    public BitacoraMovimientos() {
        this.inicio = null;
        this.ultimo = null;
        this.contador = 0;
        this.tamano = 0;
    }
 
    // GETTER
    public int getTamano() { 
        return tamano; 
    }
 
    // Metodo que registra eventos al final de la bitacora
    public void registrar(String tipoOperacion, String descripcion) {
        contador++;
        String horaSimulada = String.format("Evento %02d", contador);
        Movimiento mov = new Movimiento(horaSimulada, tipoOperacion, descripcion);
        NodoMovimiento nuevo = new NodoMovimiento(mov);
 
        if (tamano == 0) {
            inicio = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        tamano++;
    }
 
    // Metodo que devuelve el reporte
    public String generarReporte() {
        String resultado = "";
        resultado = resultado + "================== BITÁCORA DE MOVIMIENTOS ==================\n";
        resultado = resultado + "Total de eventos registrados: " + tamano + "\n";
        resultado = resultado + "---------------------------------------------------------------\n";
 
        NodoMovimiento actual = inicio;
        while (actual != null) {
            resultado = resultado + actual.getMovimiento() + "\n";
            actual = actual.getSiguiente();
        }
        resultado = resultado + "=================================================================\n";
        return resultado;
    }
}
