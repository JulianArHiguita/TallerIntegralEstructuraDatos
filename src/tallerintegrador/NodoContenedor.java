package tallerintegrador;

public class NodoContenedor {
    // Clase que crea y enlaza los nodos con el anterior y el siguiente
    
    // Llama los datos que se alamcenaran en el nodo
    private Contenedor contenedor;
    
    // Referencia de nodos
    private NodoContenedor anterior;
    private NodoContenedor siguiente;
    
    // CONSTRUCTOR
    public NodoContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
        this.anterior = null;
        this.siguiente = null;
    }
    
    // GETTERS
    public Contenedor getContenedor() {
        return contenedor;
    }

    public NodoContenedor getAnterior() {
        return anterior;
    }

    public NodoContenedor getSiguiente() {
        return siguiente;
    }
    
    // SETTERS
    
    // Este setter permite reemplazar el dato almacenado en el nodo
    public void setContenedorInterno(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public void setAnterior(NodoContenedor anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(NodoContenedor siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
