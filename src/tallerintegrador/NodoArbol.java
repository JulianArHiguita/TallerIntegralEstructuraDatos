package tallerintegrador;

public class NodoArbol {
    
    private int codigo;
    private Contenedor contenedor;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
 
    // CONSTRUCTOR
    public NodoArbol(int codigo, Contenedor contenedor) {
        this.codigo = codigo;
        this.contenedor = contenedor;
        this.izquierdo = null;
        this.derecho = null;
    }
 
    // GETTERS
    public int getCodigo() { 
        return codigo; 
    }
    
    public Contenedor getContenedor() { 
        return contenedor; 
    }
    
    public NodoArbol getIzquierdo() {
        return izquierdo; 
    }
    
    public NodoArbol getDerecho() {
        return derecho; 
    }
 
    // SETTERS
    public void setCodigo(int codigo) { 
        this.codigo = codigo; 
    }
    
    public void setContenedor(Contenedor contenedor) { 
        this.contenedor = contenedor; 
    }
    
    public void setIzquierdo(NodoArbol izquierdo) { 
        this.izquierdo = izquierdo; 
    }
    
    public void setDerecho(NodoArbol derecho) { 
        this.derecho = derecho; 
    }
    
}
