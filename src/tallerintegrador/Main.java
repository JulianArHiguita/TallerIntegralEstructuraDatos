package tallerintegrador;

public class Main {

    public static void main(String[] args) {

        Lista lista = new Lista();
        Arbol indice = new Arbol();
        ColaCamiones colaCamiones = new ColaCamiones();
        PilaContenedores pilaGrua = new PilaContenedores();
        BitacoraMovimientos bitacora = new BitacoraMovimientos();
        GrafoPuerto puerto = new GrafoPuerto();
 
        // -----------------------------------------------------------
        // 1) INVENTARIO INICIAL: cada contenedor entra a la Lista y al índice BST
        // -----------------------------------------------------------
        System.out.println(">>> 1) INVENTARIO INICIAL (Lista + índice BST)\n");
 
        Contenedor[] iniciales = {
            new Contenedor("CT-1005", 27.900, "Seca",        "En patio", "A-03", "12/08/2026", 1),
            new Contenedor("CT-1002", 18.200, "Refrigerada", "En patio", "B-04", "10/08/2026", 1),
            new Contenedor("CT-1008", 16.600, "Refrigerada", "En patio", "B-05", "13/08/2026", 2),
            new Contenedor("CT-1001", 24.500, "Seca",        "En patio", "A-01", "10/08/2026", 2),
            new Contenedor("CT-1004", 20.100, "Seca",        "En patio", "D-07", "11/08/2026", 2),
            new Contenedor("CT-1007", 22.000, "Peligrosa",   "En patio", "C-02", "13/08/2026", 1),
            new Contenedor("CT-1010", 14.800, "Granel",      "En patio", "C-06", "14/08/2026", 3),
            new Contenedor("CT-1003", 12.750, "Granel",      "En patio", "A-02", "11/08/2026", 3),
            new Contenedor("CT-1006", 9.400,  "Granel",      "En patio", "C-05", "12/08/2026", 3),
            new Contenedor("CT-1009", 19.300, "Seca",        "En patio", "D-08", "14/08/2026", 2)
        };
        for (int i = 0; i < iniciales.length; i++) {
            lista.insertarAlFinal(iniciales[i]);
            indice.insertar(iniciales[i]);
        }
        bitacora.registrar("INGRESO", "Inventario inicial cargado: " + lista.getTamano() + " contenedores en la lista y " + indice.getTamano() + " en el índice BST.");
 
        System.out.println("Lista en orden de llegada (sin ordenar):");
        System.out.println(lista.recorrerAdelante());
 
        // -----------------------------------------------------------
        // 2) ÁRBOL BST: recorridos, búsqueda y altura
        // -----------------------------------------------------------
        System.out.println(">>> 2) ÁRBOL BINARIO DE BÚSQUEDA\n");
 
        System.out.println("Inorden (inventario ORDENADO por código):");
        System.out.println(indice.inorden());
        System.out.println("Preorden   : " + indice.preorden());
        System.out.println("Postorden  : " + indice.postorden());
        System.out.println("Por niveles: " + indice.porNiveles());
        System.out.println("Altura del árbol: " + indice.altura() + " niveles | Nodos: " + indice.getTamano());
        System.out.println();
 
        int codigoBuscado = 1007;
        Contenedor hallado = indice.buscar(codigoBuscado);
        System.out.println("Buscar código " + codigoBuscado + " -> " + (hallado != null ? hallado : "NO ENCONTRADO"));
        System.out.println("Buscar código 9999 -> " + (indice.buscar(9999) != null ? "ENCONTRADO" : "NO ENCONTRADO"));
        System.out.println("Insertar código repetido CT-1005 -> " + (indice.insertar(iniciales[0]) ? "insertado" : "RECHAZADO (ya existe)"));
        System.out.println("Menor código del índice -> " + indice.buscarMenor());
        System.out.println("Mayor código del índice -> " + indice.buscarMayor());
        System.out.println();
        System.out.println("Búsqueda por rango de códigos 1004 a 1006:");
        System.out.println(indice.buscarPorRango(1004, 1006));
 
        // -----------------------------------------------------------
        // 3) BAJA DE CONTENEDORES: los 3 casos de eliminación del BST
        // -----------------------------------------------------------
        System.out.println(">>> 3) ELIMINACIÓN EN EL BST (la baja se hace en la Lista y en el índice)\n");
 
        darDeBaja(lista, indice, bitacora, "CT-1003", "hoja (sin hijos)");
        System.out.println("Preorden: " + indice.preorden() + "\n");
 
        darDeBaja(lista, indice, bitacora, "CT-1010", "un solo hijo");
        System.out.println("Preorden: " + indice.preorden() + "\n");
 
        darDeBaja(lista, indice, bitacora, "CT-1008", "dos hijos (lo reemplaza su sucesor inorden)");
        System.out.println("Preorden: " + indice.preorden() + "\n");
 
        System.out.println("Eliminar código 9999 (no existe) -> " + indice.eliminar(9999));
        System.out.println();
 
        // -----------------------------------------------------------
        // 4) FILA DE CAMIONES (Cola FIFO)
        // -----------------------------------------------------------
        System.out.println(">>> 4) INGRESO DE CAMIONES AL PUERTO (Cola FIFO)\n");
 
        colaCamiones.encolar(new Camion("ABC123", "Carlos Ruiz",  "08:00", "CT-1005", "En espera"));
        colaCamiones.encolar(new Camion("DEF456", "Mónica Pérez", "08:15", "CT-1002", "En espera"));
        colaCamiones.encolar(new Camion("GHI789", "Luis Torres",  "08:30", "CT-1001", "En espera"));
        colaCamiones.encolar(new Camion("JKL321", "Andrea Gómez", "08:45", "CT-1006", "En espera"));
        for (int i = 0; i < 4; i++) {
            bitacora.registrar("INGRESO", "Camión encolado, orden de llegada #" + (i + 1) + ".");
        }
        System.out.println("Fila de camiones en orden de llegada:");
        System.out.println(colaCamiones.recorrido());
 
        // -----------------------------------------------------------
        // 5) CARGA EN GRÚA PÓRTICO (Pila LIFO): se busca en el índice y se mueve
        // -----------------------------------------------------------
        System.out.println(">>> 5) CARGA EN GRÚA PÓRTICO (Pila LIFO)\n");
        System.out.println("Se apilan, en este orden: CT-1005, CT-1002, CT-1001, CT-1006\n");
 
        String[] ordenDeApilado = {"CT-1005", "CT-1002", "CT-1001", "CT-1006"};
        for (int i = 0; i < ordenDeApilado.length; i++) {
            String id = ordenDeApilado[i];
            Contenedor encontrado = indice.buscar(codigoDe(id)); // búsqueda rápida por el índice
            if (encontrado == null) {
                System.out.println("El contenedor " + id + " no está en el índice, no se puede apilar.");
                continue;
            }
            Contenedor extraido = lista.extraerPorId(id);
            extraido.setEstado("En zona de apilamiento");
            pilaGrua.apilar(extraido);
            bitacora.registrar("CARGA", "Contenedor " + id + " movido del inventario a la pila de la grúa.");
        }
 
        System.out.println("Pila (de la cima hacia la base):");
        System.out.println(pilaGrua.recorrido());
        System.out.println("Inventario restante en la lista:");
        System.out.println(lista.recorrerAdelante());
 
        // -----------------------------------------------------------
        // 6) ATENCIÓN DE CAMIONES (dequeue + pop) y baja del índice
        // -----------------------------------------------------------
        System.out.println(">>> 6) ATENCIÓN DE CAMIONES (dequeue + pop)\n");
 
        for (int i = 0; i < 2; i++) {
            Camion atendido = colaCamiones.desencolar();
            atendido.setEstadoAtencion("En atención");
            Contenedor entregado = pilaGrua.desapilar();
            entregado.setEstado("Despachado");
            indice.eliminar(entregado.getCodigoNumerico()); // sale del puerto: sale del índice
 
            System.out.println("Se atiende al camión: " + atendido);
            System.out.println("Se le entrega el contenedor (cima de la pila): " + entregado);
            System.out.println();
 
            bitacora.registrar("DESCARGA", "Camión " + atendido.getPlaca() + " atendido; se le entregó el contenedor " + entregado.getId() + ".");
            bitacora.registrar("ÍNDICE", "Contenedor " + entregado.getId() + " retirado del índice BST por despacho.");
        }
 
        System.out.println("Fila de camiones restante:");
        System.out.println(colaCamiones.recorrido());
        System.out.println("Pila restante (de la cima hacia la base):");
        System.out.println(pilaGrua.recorrido());
 
        // -----------------------------------------------------------
        // 7) BÚSQUEDAS Y VALIDACIONES
        // -----------------------------------------------------------
        System.out.println(">>> 7) BÚSQUEDAS Y VALIDACIONES DE OPERACIÓN\n");
 
        Camion camion = colaCamiones.buscarPorPlaca("JKL321");
        System.out.println("Buscar camión JKL321 -> " + (camion != null ? camion : "NO ENCONTRADO"));
        Contenedor enPila = pilaGrua.buscarPorId("CT-1005");
        System.out.println("Buscar CT-1005 en la pila -> " + (enPila != null ? enPila : "NO ENCONTRADO"));
        System.out.println("Buscar código 1006 en el índice (ya despachado) -> " + (indice.buscar(1006) != null ? "ENCONTRADO" : "NO ENCONTRADO"));
 
        PilaContenedores pilaVacia = new PilaContenedores();
        ColaCamiones colaVacia = new ColaCamiones();
        try {
            pilaVacia.desapilar();
        } catch (OperacionInvalidaException e) {
            System.out.println("Validación al desapilar: " + e.getMessage());
            bitacora.registrar("VALIDACIÓN", "Intento de desapilar sobre pila vacía, bloqueado correctamente.");
        }
        try {
            colaVacia.desencolar();
        } catch (OperacionInvalidaException e) {
            System.out.println("Validación al desencolar: " + e.getMessage());
            bitacora.registrar("VALIDACIÓN", "Intento de desencolar sobre cola vacía, bloqueado correctamente.");
        }
        try {
            indice.insertar(null);
        } catch (NullPointerException e) {
            System.out.println("Validación al indexar: " + e.getMessage());
            bitacora.registrar("VALIDACIÓN", "Intento de indexar un contenedor nulo, bloqueado correctamente.");
        }
        System.out.println();
 
        // -----------------------------------------------------------
        // 8) GRAFO: MAPA DEL PUERTO
        // -----------------------------------------------------------
        System.out.println(">>> 8) GRAFO: MAPA DEL PUERTO (lista de adyacencia)\n");
 
        puerto.agregarZona(new Zona("Z01", "Portería",             "Acceso"));
        puerto.agregarZona(new Zona("Z02", "Bodega 1",             "Bodega"));
        puerto.agregarZona(new Zona("Z03", "Bodega 2",             "Bodega"));
        puerto.agregarZona(new Zona("Z04", "Patio A",              "Patio"));
        puerto.agregarZona(new Zona("Z05", "Patio B",              "Patio"));
        puerto.agregarZona(new Zona("Z06", "Zona de Inspección",   "Inspección"));
        puerto.agregarZona(new Zona("Z07", "Muelle de Despacho",   "Despacho"));
        puerto.agregarZona(new Zona("Z08", "Zona de Cuarentena",   "Inspección"));
 
        //                  origen  destino  metros  min  costo
        puerto.agregarRuta("Z01", "Z04", 300,  4,  30);
        puerto.agregarRuta("Z01", "Z02", 500,  8,  50);
        puerto.agregarRuta("Z02", "Z04", 200,  3,  20);
        puerto.agregarRuta("Z02", "Z03", 250,  4,  25);
        puerto.agregarRuta("Z03", "Z05", 350,  6,  35);
        puerto.agregarRuta("Z04", "Z05", 150,  2,  15);
        puerto.agregarRuta("Z04", "Z06", 400,  7,  50);
        puerto.agregarRuta("Z05", "Z06", 250,  3,  25);
        puerto.agregarRuta("Z05", "Z07", 450, 12, 100);
        puerto.agregarRuta("Z06", "Z07", 300,  5,  30);
 
        System.out.println("Zonas registradas: " + puerto.getCantidadZonas());
        System.out.println("Agregar zona repetida Z04 -> " + puerto.agregarZona(new Zona("Z04", "Otra", "Patio")));
        System.out.println("Agregar ruta a zona inexistente Z99 -> " + puerto.agregarRuta("Z01", "Z99", 10, 1, 1));
        System.out.println("Agregar ruta ya registrada Z01-Z04 -> " + puerto.agregarRuta("Z01", "Z04", 300, 4, 30));
        System.out.println();
        System.out.println(puerto.mostrarMapa());
 
        System.out.println(puerto.consultarConexiones("Z04"));
        System.out.println(puerto.consultarConexiones("Z08"));
 
        System.out.println("Recorrido en anchura (BFS) desde Portería:");
        System.out.println(puerto.recorridoAnchura("Z01") + "\n");
        System.out.println("Recorrido en profundidad (DFS) desde Portería:");
        System.out.println(puerto.recorridoProfundidad("Z01") + "\n");
 
        // -----------------------------------------------------------
        // 9) RUTA RECOMENDADA
        // -----------------------------------------------------------
        System.out.println(">>> 9) RUTA RECOMENDADA: Bodega 1 (Z02) -> Muelle de Despacho (Z07)\n");
        System.out.println("Comparación de criterios:");
        System.out.println(puerto.rutaOptima("Z02", "Z07", GrafoPuerto.CRITERIO_DISTANCIA));
        System.out.println(puerto.rutaOptima("Z02", "Z07", GrafoPuerto.CRITERIO_COSTO));
        System.out.println("RUTA RECOMENDADA (criterio institucional = menor tiempo):");
        String recomendada = puerto.rutaRecomendada("Z02", "Z07");
        System.out.println(recomendada);
        bitacora.registrar("RUTA", "Ruta recomendada Bodega 1 -> Muelle de Despacho calculada por menor tiempo.");
 
        System.out.println("Destino sin conexión (Z02 -> Z08 Cuarentena):");
        System.out.println(puerto.rutaRecomendada("Z02", "Z08"));
 
        // -----------------------------------------------------------
        // 10) REPORTES FINALES
        // -----------------------------------------------------------
        System.out.println(">>> 10) VERIFICACIÓN DE INTEGRIDAD Y REPORTES FINALES\n");
        System.out.println("Contenedores en el índice BST: " + indice.getTamano() +
                           " | En la lista: " + lista.getTamano() +
                           " | En la pila de la grúa: " + pilaGrua.getTamano());
        System.out.println("(el índice debe ser igual a lista + pila)\n");
 
        System.out.println("Índice BST en inorden (ordenado por código):");
        System.out.println(indice.inorden());
        System.out.println(lista.generarReporteOrdenado());
        System.out.println(bitacora.generarReporte());
    }
 
    /** Código numérico a partir de un ID, igual que Contenedor.getCodigoNumerico(). */
    private static int codigoDe(String id) {
        int codigo = 0;
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (c >= '0' && c <= '9') {
                codigo = codigo * 10 + (c - '0');
            }
        }
        return codigo;
    }
 
    /** Da de baja un contenedor en la Lista y en el índice BST a la vez. */
    private static void darDeBaja(Lista lista, Arbol indice, BitacoraMovimientos bitacora,
                                  String id, String caso) {
        boolean enIndice = indice.eliminar(codigoDe(id));
        boolean enLista = lista.eliminarPorId(id);
        System.out.println("Baja de " + id + " [caso: " + caso + "] -> índice: " + enIndice + ", lista: " + enLista);
        bitacora.registrar("BAJA", "Contenedor " + id + " dado de baja (" + caso + ").");
    }
}
