package tallerintegrador;

// IMPORTAR LIBRERIAS
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Arbol {
    private NodoArbol raiz;
    private int tamano;
 
    public Arbol() {
        this.raiz = null;
        this.tamano = 0;
    }
 
    // GETTER
    public int getTamano() { 
        return tamano; 
    }
    
    // METODO QUE VALIDA SI ESTA VACIO
    public boolean estaVacio() { 
        return tamano == 0; 
    }
 
    
    // ---------------- INSERTAR ----------------
 
    // INSERTA UN CONTENEDOR EN EL INDICE
    public boolean insertar(Contenedor c) {
        Objects.requireNonNull(c, "No se puede indexar un contenedor nulo.");
        int codigo = c.getCodigoNumerico();
        if (Objects.nonNull(buscar(codigo))) {
            return false;
        }
        raiz = insertarRec(raiz, codigo, c);
        tamano++;
        return true;
    }
 
    private NodoArbol insertarRec(NodoArbol nodo, int codigo, Contenedor c) {
        if (Objects.isNull(nodo)) {
            return new NodoArbol(codigo, c);
        }
        if (codigo < nodo.getCodigo()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), codigo, c));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), codigo, c));
        }
        return nodo;
    }
 
    // ---------------- BÚSQUEDA ----------------
 
    // BUSCA UN CONTENEDIR POR CODIGO BAJANDO POR LA DERECHA O LA IZQUIERDA SEGUN CORRESPONDA
    public Contenedor buscar(int codigo) {
        NodoArbol actual = raiz;
        while (Objects.nonNull(actual)) {
            if (codigo == actual.getCodigo()) {
                return actual.getContenedor();
            } else if (codigo < actual.getCodigo()) {
                actual = actual.getIzquierdo();
            } else {
                actual = actual.getDerecho();
            }
        }
        return null;
    }
 
    // CONTENEDOR CON MENOR CODIGO
    public Contenedor buscarMenor() {
        if (Objects.isNull(raiz)) {
            return null;
        }
        return minimo(raiz).getContenedor();
    }
 
    // CONTENEDOR CON MAYOR CODIGO
    public Contenedor buscarMayor() {
        if (Objects.isNull(raiz)) {
            return null;
        }
        NodoArbol actual = raiz;
        while (Objects.nonNull(actual.getDerecho())) {
            actual = actual.getDerecho();
        }
        return actual.getContenedor();
    }
 
    // BUSQUEDA CONTENEDORES EN RANGO
    public String buscarPorRango(int desde, int hasta) {
        if (desde > hasta) {
            return "";
        }
        return rangoRec(raiz, desde, hasta);
    }
 
    // INORDEN
    private String rangoRec(NodoArbol nodo, int desde, int hasta) {
        if (Objects.isNull(nodo)) {
            return "";
        }
        String resultado = "";
        if (desde < nodo.getCodigo()) {
            resultado = rangoRec(nodo.getIzquierdo(), desde, hasta);
        }
        if (nodo.getCodigo() >= desde && nodo.getCodigo() <= hasta) {
            resultado = resultado + nodo.getCodigo() + " -> " + nodo.getContenedor() + "\n";
        }
        if (hasta > nodo.getCodigo()) {
            resultado = resultado + rangoRec(nodo.getDerecho(), desde, hasta);
        }
        return resultado;
    }
 
    // ---------------- ELIMINACIÓN ----------------
 
    // ELIMINA EL CONTENDOR POR CODIGO
    public boolean eliminar(int codigo) {
        if (Objects.isNull(buscar(codigo))) {
            return false;
        }
        raiz = eliminarRec(raiz, codigo);
        tamano--;
        return true;
    }
 
    private NodoArbol eliminarRec(NodoArbol nodo, int codigo) {
        if (Objects.isNull(nodo)) {
            return null;
        }
        if (codigo < nodo.getCodigo()) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), codigo));
        } else if (codigo > nodo.getCodigo()) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), codigo));
        } else {
            // Caso 1: hoja o Caso 2: un solo hijo -> el hijo (o null) ocupa su lugar
            if (Objects.isNull(nodo.getIzquierdo())) {
                return nodo.getDerecho();
            }
            if (Objects.isNull(nodo.getDerecho())) {
                return nodo.getIzquierdo();
            }
            // Caso 3: dos hijos -> lo reemplaza su sucesor inorden
            // (el menor código del subárbol derecho) y se elimina ese sucesor.
            NodoArbol sucesor = minimo(nodo.getDerecho());
            int codigoSucesor = sucesor.getCodigo();
            Contenedor contenedorSucesor = sucesor.getContenedor();
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), codigoSucesor));
            nodo.setCodigo(codigoSucesor);
            nodo.setContenedor(contenedorSucesor);
        }
        return nodo;
    }
 
    private NodoArbol minimo(NodoArbol nodo) {
        while (Objects.nonNull(nodo.getIzquierdo())) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }
 
    // ---------------- RECORRIDOS (resultado en LinkedList) ----------------
 
    // PREORDEN
    public LinkedList<Contenedor> listaPreorden() {
        LinkedList<Contenedor> resultado = new LinkedList<>();
        preordenRec(raiz, resultado);
        return resultado;
    }
 
    private void preordenRec(NodoArbol nodo, LinkedList<Contenedor> resultado) {
        if (Objects.isNull(nodo)) return;
        resultado.addLast(nodo.getContenedor());
        preordenRec(nodo.getIzquierdo(), resultado);
        preordenRec(nodo.getDerecho(), resultado);
    }
 
    // INORDEN
    public LinkedList<Contenedor> listaInorden() {
        LinkedList<Contenedor> resultado = new LinkedList<>();
        inordenRec(raiz, resultado);
        return resultado;
    }
 
    private void inordenRec(NodoArbol nodo, LinkedList<Contenedor> resultado) {
        if (Objects.isNull(nodo)) return;
        inordenRec(nodo.getIzquierdo(), resultado);
        resultado.addLast(nodo.getContenedor());
        inordenRec(nodo.getDerecho(), resultado);
    }
 
    // POSTORDEN
    public LinkedList<Contenedor> listaPostorden() {
        LinkedList<Contenedor> resultado = new LinkedList<>();
        postordenRec(raiz, resultado);
        return resultado;
    }
 
    private void postordenRec(NodoArbol nodo, LinkedList<Contenedor> resultado) {
        if (Objects.isNull(nodo)) return;
        postordenRec(nodo.getIzquierdo(), resultado);
        postordenRec(nodo.getDerecho(), resultado);
        resultado.addLast(nodo.getContenedor());
    }
 
    // RECORRIDO POR NIVELES QUEUE
    public LinkedList<Contenedor> listaPorNiveles() {
        LinkedList<Contenedor> resultado = new LinkedList<>();
        if (Objects.isNull(raiz)) {
            return resultado;
        }
        Queue<NodoArbol> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            NodoArbol actual = cola.poll();
            resultado.addLast(actual.getContenedor());
            if (Objects.nonNull(actual.getIzquierdo())) {
                cola.add(actual.getIzquierdo());
            }
            if (Objects.nonNull(actual.getDerecho())) {
                cola.add(actual.getDerecho());
            }
        }
        return resultado;
    }
 
    // ---------------- RECORRIDOS COMO TEXTO ----------------
 
    // PREORDEN 
    public String preorden() {
        return codigosEnLinea(listaPreorden());
    }
 
    // POSTORDEN
    public String postorden() {
        return codigosEnLinea(listaPostorden());
    }
 
    // POR NIVELES
    public String porNiveles() {
        return codigosEnLinea(listaPorNiveles());
    }
 
    // ORDEN ASCENDENTE
    public String codigosOrdenados() {
        return codigosEnLinea(listaInorden());
    }
 
    // INORDEN
    public String inorden() {
        String resultado = "";
        for (Contenedor c : listaInorden()) {
            resultado = resultado + c.getCodigoNumerico() + " -> " + c + "\n";
        }
        return resultado;
    }
 
    private String codigosEnLinea(LinkedList<Contenedor> contenedores) {
        String resultado = "";
        for (Contenedor c : contenedores) {
            resultado = resultado + c.getCodigoNumerico() + " ";
        }
        return resultado.trim();
    }
 
    // ---------------- ALTURA ----------------
 
    // ALTURA DEL ARBOL (CANTIDAD DE NIVELES)
    public int altura() {
        return alturaRec(raiz);
    }
 
    private int alturaRec(NodoArbol nodo) {
        if (Objects.isNull(nodo)) return 0;
        return 1 + Math.max(alturaRec(nodo.getIzquierdo()), alturaRec(nodo.getDerecho()));
    }
}
