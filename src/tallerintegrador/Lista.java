
package tallerintegrador;

public class Lista {
    
    // Definimos cabeza, cola y tamaño
    private NodoContenedor cabeza;
    private NodoContenedor cola;
    private int tamano;
    
    // Constructor
    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.tamano = tamano;
    }

    // Obtiene el tamaño de la lista
    public int getTamano() { 
        return tamano;
    }
    
    // Devuelve 0 en caso de que la lista esté vacía
    public boolean estaVacia() {
        return tamano == 0; 
    }
    
    // ===== METODO PARA INSERTAR AL INICIO DE LA LISTA =====
    public void insertarAlInicio(Contenedor c) {
        NodoContenedor nuevo = new NodoContenedor(c);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamano++;
    }
    
    // ===== METODO PARA INSERTAR AL FINAL DE LA LISTA =====
    public void insertarAlFinal(Contenedor c) {
        NodoContenedor nuevo = new NodoContenedor(c);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
        tamano++;
    }
    
    // ===== METODO PARA BUSCAR NODO POR ID =====
    private NodoContenedor buscarNodoPorId(String id) {
        NodoContenedor actual = cabeza;
        while (actual != null) {
            if (actual.getContenedor().getId().equalsIgnoreCase(id)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
     // ===== METODO PARA DEVOLVER CONTENEDOR SEGUN ID =====
    public Contenedor buscarPorId(String id) {
        NodoContenedor nodo = buscarNodoPorId(id);
        return (nodo != null) ? nodo.getContenedor() : null;
    }
    
    // ===== METODO PARA RECORRER DE INICIO A FIN =====
    public String recorrerAdelante() {
        String resultado = "";
        NodoContenedor actual = cabeza;
        int i = 1;
        while (actual != null) {
            resultado = resultado + i + ". " + actual.getContenedor() + "\n";
            i++;
            actual = actual.getSiguiente();
        }
        return resultado;
    }
    
    // ===== METODO PARA RECORRER DE FIN A INICIO =====
    public String recorrerAtras() {
        String resultado = "";
        NodoContenedor actual = cola;
        int i = 1;
        while (actual != null) {
            resultado = resultado + i + ". " + actual.getContenedor() + "\n";
            i++;
            actual = actual.getAnterior();
        }
        return resultado;
    }
    
    // ===== METODO PARA ACTUALIZAR SEGUN ID =====
    public boolean modificarDatos(String id, double nuevoPeso, String nuevoTipoCarga, String nuevoEstado, String nuevaUbicacion, String nuevaFecha, int nuevaPrioridad) {
        NodoContenedor nodo = buscarNodoPorId(id);
        if (nodo == null) {
            return false;
        }
        nodo.getContenedor().actualizarDatos(nuevoPeso, nuevoTipoCarga, nuevoEstado, nuevaUbicacion, nuevaFecha, nuevaPrioridad);
        return true;
    }
    
    // ===== METODO PARA ELIMINAR SEGUN ID =====
    public boolean eliminarPorId(String id) {
        NodoContenedor nodo = buscarNodoPorId(id);
        if (nodo == null) {
            return false;
        }
 
        NodoContenedor anterior = nodo.getAnterior();
        NodoContenedor siguiente = nodo.getSiguiente();
 
        if (anterior != null) {
            anterior.setSiguiente(siguiente);
        } else {
            // El nodo eliminado era la cabeza
            cabeza = siguiente;
        }
 
        if (siguiente != null) {
            siguiente.setAnterior(anterior);
        } else {
            // El nodo eliminado era la cola
            cola = anterior;
        }

        tamano--;
        return true;
    }
    
    // ===== METODO PARA GENERAR REPORTE ORDENADO SEGUN PRIORIDAD =====
    
    public String generarReporteOrdenado() {
        ordenarPorPrioridad();
 
        String resultado = "";
        resultado = resultado + "========== REPORTE DE INVENTARIO - PATIO DE CARGA ==========\n";
        resultado = resultado + "Total de contenedores: " + tamano + "\n";
        resultado = resultado + "Orden: Prioridad operativa (Alta -> Baja), luego por ID\n";
        resultado = resultado + "--------------------------------------------------------------\n";
 
        NodoContenedor actual = cabeza;
        int i = 1;
        while (actual != null) {
            resultado = resultado + i + ". " + actual.getContenedor() + "\n";
            i++;
            actual = actual.getSiguiente();
        }
        resultado = resultado + "================================================================\n";
        return resultado;
    }
 
    private void ordenarPorPrioridad() {
        if (tamano < 2) return;
 
        NodoContenedor actual = cabeza.getSiguiente();
        while (actual != null) {
            Contenedor claveActual = actual.getContenedor();
            NodoContenedor comparar = actual.getAnterior();
 
            while (comparar != null && esMayor(comparar.getContenedor(), claveActual)) {
                // Desplaza el dato hacia adelante
                comparar.getSiguiente().setContenedorInterno(comparar.getContenedor());
                comparar = comparar.getAnterior();
            }
 
            NodoContenedor destino = (comparar == null) ? cabeza : comparar.getSiguiente();
            destino.setContenedorInterno(claveActual);
 
            actual = actual.getSiguiente();
        }
    }
 
    // ===== METODO QUE DEFINE LA PRIORIDAD =====
    private boolean esMayor(Contenedor a, Contenedor b) {
        if (a.getPrioridadOperativa() != b.getPrioridadOperativa()) {
            return a.getPrioridadOperativa() > b.getPrioridadOperativa();
        }
        return a.getId().compareToIgnoreCase(b.getId()) > 0;
    }
}
