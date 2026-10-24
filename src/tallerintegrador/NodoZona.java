
package tallerintegrador;


public class NodoZona {
    
    private Zona zona;
    private int indice;
    private NodoArco primerArco;
    private NodoZona siguienteZona;
 
    // CONSTRUCTOR
    public NodoZona(Zona zona, int indice) {
        this.zona = zona;
        this.indice = indice;
        this.primerArco = null;
        this.siguienteZona = null;
    }
 
    // GETTERS
    public Zona getZona() {
        return zona;
    }

    public int getIndice() {
        return indice;
    }

    public NodoArco getPrimerArco() {
        return primerArco;
    }

    public NodoZona getSiguienteZona() {
        return siguienteZona;
    }
 
    // SETTERS
    public void setPrimerArco(NodoArco primerArco) {
        this.primerArco = primerArco;
    }

    public void setSiguienteZona(NodoZona siguienteZona) {
        this.siguienteZona = siguienteZona;
    }
 
    // METODO QUE AGREGA UN ARCO AL FINALD E LA LISTA DE ADYACENCIA
    public void agregarArco(NodoArco nuevo) {
        if (primerArco == null) {
            primerArco = nuevo;
        } else {
            NodoArco actual = primerArco;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }
 
    // METODO QUE DEVUELVE EL ARCO QUE VA A DESTINO O NULL SI NO HAY RUTA DIRECTA
    public NodoArco buscarArcoHacia(NodoZona destino) {
        NodoArco actual = primerArco;
        while (actual != null) {
            if (actual.getDestino() == destino) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
