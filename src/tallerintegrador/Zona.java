package tallerintegrador;

public class Zona {
    
    private String id;    
    private String nombre; 
    private String tipo; 
 
    // CONSTRUCTOR
    public Zona(String id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }
 
    // GETTERS
    public String getId() { 
        return id; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public String getTipo() { 
        return tipo; 
    }
    
 
    // METODO ToString
    @Override
    public String toString() {
        return id + " - " + nombre + " (" + tipo + ")";
    }
}
