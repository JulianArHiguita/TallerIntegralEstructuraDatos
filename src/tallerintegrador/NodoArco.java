package tallerintegrador;

public class NodoArco {
    
    private NodoZona destino;
    private int distancia; 
    private int tiempo;    
    private int costo;
    private NodoArco siguiente;
 
    // CONSTRUCTOR
    public NodoArco(NodoZona destino, int distancia, int tiempo, int costo) {
        this.destino = destino;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.costo = costo;
        this.siguiente = null;
    }
 
    // GETTERS
    public NodoZona getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public int getCosto() {
        return costo;
    }

    public NodoArco getSiguiente() {
        return siguiente;
    }
    
    // SETTER
    public void setSiguiente(NodoArco siguiente) {
        this.siguiente = siguiente;
    }
 
    // GETTER QUE OBTIENE EL PESO DEL ARCO SEGUN CRITERIO
    public int getPeso(int criterio) {
        if (criterio == GrafoPuerto.CRITERIO_DISTANCIA) return distancia;
        if (criterio == GrafoPuerto.CRITERIO_COSTO) return costo;
        return tiempo;
    }
}
