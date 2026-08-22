package tallerintegrador;

public class Contenedor {

    // Definir atributos
    private String id;
    private double peso;
    private String tipoCarga;
    private String estado;
    private String ubicacionPatio;
    private String fechaIngreso;
    private int prioridadOperativa;

    // CONSTRUCTOR
    public Contenedor(String id, double peso, String tipoCarga, String estado, String ubicacionPatio, String fechaIngreso, int prioridadOperativa) {
        this.id = id;
        this.peso = peso;
        this.tipoCarga = tipoCarga;
        this.estado = estado;
        this.ubicacionPatio = ubicacionPatio;
        this.fechaIngreso = fechaIngreso;
        this.prioridadOperativa = prioridadOperativa;
    }

    // Getters
    public String getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public String getEstado() {
        return estado;
    }

    public String getUbicacionPatio() {
        return ubicacionPatio;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public int getPrioridadOperativa() {
        return prioridadOperativa;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setUbicacionPatio(String ubicacionPatio) {
        this.ubicacionPatio = ubicacionPatio;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setPrioridadOperativa(int prioridadOperativa) {
        this.prioridadOperativa = prioridadOperativa;
    }

    // Metodo que permite actualizar datos sin cambiar el ID
    public void actualizarDatos(double peso, String tipoCarga, String estado, String ubicacionPatio, String fechaIngreso, int prioridadOperativa) {
        this.peso = peso;
        this.tipoCarga = tipoCarga;
        this.estado = estado;
        this.ubicacionPatio = ubicacionPatio;
        this.fechaIngreso = fechaIngreso;
        this.prioridadOperativa = prioridadOperativa;
    }
    
    // Metodo que define la prioridad de la operacion
    private String textoPrioridad() {
        switch (prioridadOperativa) {
            case 1: return "ALTA";
            case 2: return "MEDIA";
            default: return "BAJA";
        }
    }
    
    
    // toString para darle formato a la ejecucion
    @Override
    public String toString() {
        return " | ID: " + id + " | PESO: " + peso + " | TIPO DE CARGA: " + tipoCarga + " | ESTADO: " + estado + " | UBICACION EN PATIO: " + ubicacionPatio + " |  FECHA INGRESO: " + fechaIngreso + " | PRIORIDAD OPERATIVA: " + prioridadOperativa + " | ";
    }
    
}
