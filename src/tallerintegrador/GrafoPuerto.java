package tallerintegrador;

public class GrafoPuerto {
    
    public static final int CRITERIO_DISTANCIA = 1;
    public static final int CRITERIO_TIEMPO = 2;
    public static final int CRITERIO_COSTO = 3;
 
    private static final int INFINITO = Integer.MAX_VALUE;
 
    private NodoZona primeraZona;
    private int cantidadZonas;
 
    // CONSTRUCTOR
    public GrafoPuerto() {
        this.primeraZona = null;
        this.cantidadZonas = 0;
    }
 
    // GETTER
    public int getCantidadZonas() {
        return cantidadZonas;
    }
 
    // ---------------- AGREGAR ZONAS Y RUTAS ----------------
 
    // METODO QUE AGREGA UNA ZONA
    public boolean agregarZona(Zona zona) {
        if (buscarZona(zona.getId()) != null) {
            return false;
        }
        NodoZona nueva = new NodoZona(zona, cantidadZonas);
        if (primeraZona == null) {
            primeraZona = nueva;
        } else {
            NodoZona actual = primeraZona;
            while (actual.getSiguienteZona() != null) {
                actual = actual.getSiguienteZona();
            }
            actual.setSiguienteZona(nueva);
        }
        cantidadZonas++;
        return true;
    }
 
    // METODO QUE AGREGA UNA RUTA BIDIRECCIONAL ENTRE DOS ZONAS
    public boolean agregarRuta(String idOrigen, String idDestino, int distancia, int tiempo, int costo) {
        NodoZona origen = buscarZona(idOrigen);
        NodoZona destino = buscarZona(idDestino);
        if (origen == null || destino == null || origen == destino) {
            return false;
        }
        if (origen.buscarArcoHacia(destino) != null) {
            return false;
        }
        origen.agregarArco(new NodoArco(destino, distancia, tiempo, costo));
        destino.agregarArco(new NodoArco(origen, distancia, tiempo, costo));
        return true;
    }
 
    // ---------------- BÚSQUEDAS INTERNAS ----------------
 
    // METODO QUE BUSCA ZONA POR ID
    private NodoZona buscarZona(String id) {
        NodoZona actual = primeraZona;
        while (actual != null) {
            if (actual.getZona().getId().equalsIgnoreCase(id)) {
                return actual;
            }
            actual = actual.getSiguienteZona();
        }
        return null;
    }
 
    // METODO QUE BUSCA ZONA POR INDICE
    private NodoZona buscarPorIndice(int indice) {
        NodoZona actual = primeraZona;
        while (actual != null) {
            if (actual.getIndice() == indice) {
                return actual;
            }
            actual = actual.getSiguienteZona();
        }
        return null;
    }
 
    // ---------------- CONSULTAS ----------------
 
    // METODO QUE MUESTRA EL MAPA COMPLETO
    public String mostrarMapa() {
        String resultado = "";
        NodoZona zona = primeraZona;
        while (zona != null) {
            resultado = resultado + zona.getZona() + "\n";
            NodoArco arco = zona.getPrimerArco();
            if (arco == null) {
                resultado = resultado + "     (sin rutas)\n";
            }
            while (arco != null) {
                resultado = resultado + "     -> " + arco.getDestino().getZona().getNombre() +
                            " [" + arco.getDistancia() + " m, " + arco.getTiempo() + " min, costo " + arco.getCosto() + "]\n";
                arco = arco.getSiguiente();
            }
            zona = zona.getSiguienteZona();
        }
        return resultado;
    }
 
    // METODO QUE CONSULTA LAS CONEXIONES DISPONIBLES DESDE UNA ZONA
    public String consultarConexiones(String idZona) {
        NodoZona zona = buscarZona(idZona);
        if (zona == null) {
            return "La zona " + idZona + " no existe en el mapa.\n";
        }
        String resultado = "Conexiones disponibles desde " + zona.getZona() + ":\n";
        NodoArco arco = zona.getPrimerArco();
        if (arco == null) {
            return resultado + "     (esta zona no tiene rutas conectadas)\n";
        }
        while (arco != null) {
            resultado = resultado + "     -> " + arco.getDestino().getZona() +
                        " | " + arco.getDistancia() + " m | " + arco.getTiempo() + " min | costo " + arco.getCosto() + "\n";
            arco = arco.getSiguiente();
        }
        return resultado;
    }
 
    // ---------------- RECORRIDOS ----------------
 
    // METODO QUE RECORRE EN ANCHURA
    public String recorridoAnchura(String idInicio) {
        NodoZona inicio = buscarZona(idInicio);
        if (inicio == null) {
            return "La zona " + idInicio + " no existe en el mapa.";
        }
        boolean[] visitado = new boolean[cantidadZonas];
        int[] cola = new int[cantidadZonas];
        int frente = 0;
        int fin = 0;
 
        cola[fin] = inicio.getIndice();
        fin++;
        visitado[inicio.getIndice()] = true;
 
        String resultado = "";
        while (frente < fin) {
            NodoZona actual = buscarPorIndice(cola[frente]);
            frente++;
            if (resultado.equals("")) {
                resultado = actual.getZona().getNombre();
            } else {
                resultado = resultado + " -> " + actual.getZona().getNombre();
            }
            NodoArco arco = actual.getPrimerArco();
            while (arco != null) {
                int idx = arco.getDestino().getIndice();
                if (!visitado[idx]) {
                    visitado[idx] = true;
                    cola[fin] = idx;
                    fin++;
                }
                arco = arco.getSiguiente();
            }
        }
        return resultado;
    }
 
    // METODO QUE RECORRE EN PROFUNDIDAD
    public String recorridoProfundidad(String idInicio) {
        NodoZona inicio = buscarZona(idInicio);
        if (inicio == null) {
            return "La zona " + idInicio + " no existe en el mapa.";
        }
        boolean[] visitado = new boolean[cantidadZonas];
        return profundidadRec(inicio, visitado);
    }
 
    private String profundidadRec(NodoZona actual, boolean[] visitado) {
        visitado[actual.getIndice()] = true;
        String resultado = actual.getZona().getNombre();
        NodoArco arco = actual.getPrimerArco();
        while (arco != null) {
            if (!visitado[arco.getDestino().getIndice()]) {
                resultado = resultado + " -> " + profundidadRec(arco.getDestino(), visitado);
            }
            arco = arco.getSiguiente();
        }
        return resultado;
    }
 
    // ---------------- RUTA RECOMENDADA  ----------------
 
    //  Ruta RECOMENDADA del puerto: la de MENOR TIEMPO de traslado
    public String rutaRecomendada(String idOrigen, String idDestino) {
        return rutaOptima(idOrigen, idDestino, CRITERIO_TIEMPO);
    }
 
    /**
     * Algoritmo de Dijkstra (arcos con pesos no negativos) usando solo arreglos:
     * en cada paso se fija la zona no visitada con menor peso acumulado y se
     * intenta mejorar el peso de sus vecinas.
     */
    public String rutaOptima(String idOrigen, String idDestino, int criterio) {
        NodoZona origen = buscarZona(idOrigen);
        NodoZona destino = buscarZona(idDestino);
        if (origen == null || destino == null) {
            return "Origen o destino no existen en el mapa.\n";
        }
 
        int n = cantidadZonas;
        int[] peso = new int[n];
        int[] previo = new int[n];
        boolean[] fijado = new boolean[n];
        for (int i = 0; i < n; i++) {
            peso[i] = INFINITO;
            previo[i] = -1;
        }
        peso[origen.getIndice()] = 0;
 
        for (int paso = 0; paso < n; paso++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!fijado[i] && peso[i] != INFINITO && (u == -1 || peso[i] < peso[u])) {
                    u = i;
                }
            }
            if (u == -1) {
                break; // las zonas restantes son inalcanzables
            }
            fijado[u] = true;
 
            NodoArco arco = buscarPorIndice(u).getPrimerArco();
            while (arco != null) {
                int v = arco.getDestino().getIndice();
                int candidato = peso[u] + arco.getPeso(criterio);
                if (!fijado[v] && candidato < peso[v]) {
                    peso[v] = candidato;
                    previo[v] = u;
                }
                arco = arco.getSiguiente();
            }
        }
 
        if (peso[destino.getIndice()] == INFINITO) {
            return "No existe ruta disponible entre " + origen.getZona().getNombre() +
                   " y " + destino.getZona().getNombre() + ".\n";
        }
 
        // Reconstrucción del camino de atrás hacia adelante y suma de los tres pesos
        String camino = "";
        int totalDistancia = 0;
        int totalTiempo = 0;
        int totalCosto = 0;
        int actual = destino.getIndice();
        while (actual != -1) {
            NodoZona nodo = buscarPorIndice(actual);
            if (camino.equals("")) {
                camino = nodo.getZona().getNombre();
            } else {
                camino = nodo.getZona().getNombre() + " -> " + camino;
            }
            if (previo[actual] != -1) {
                NodoArco tramo = buscarPorIndice(previo[actual]).buscarArcoHacia(nodo);
                totalDistancia = totalDistancia + tramo.getDistancia();
                totalTiempo = totalTiempo + tramo.getTiempo();
                totalCosto = totalCosto + tramo.getCosto();
            }
            actual = previo[actual];
        }
 
        return "Criterio: " + nombreCriterio(criterio) + "\n" +
               "Ruta: " + camino + "\n" +
               "Totales -> " + totalDistancia + " m | " + totalTiempo + " min | costo " + totalCosto + "\n";
    }
 
    private String nombreCriterio(int criterio) {
        if (criterio == CRITERIO_DISTANCIA) return "menor DISTANCIA";
        if (criterio == CRITERIO_COSTO) return "menor COSTO";
        return "menor TIEMPO";
    }
}
