package tallerintegrador;

public class Main {

    public static void main(String[] args) {

        Lista patio = new Lista();
        ColaCamiones colaCamiones = new ColaCamiones();
        PilaContenedores pilaGrua = new PilaContenedores();
        BitacoraMovimientos bitacora = new BitacoraMovimientos();

        // =====================================================================================
        // -----------------------------------------------------------
        // 1) INVENTARIO INICIAL (Lista doblemente enlazada)
        // -----------------------------------------------------------
        System.out.println(">>> 1) INVENTARIO INICIAL EN PATIO\n");

        patio.insertarAlFinal(new Contenedor("CT-1001", 24.500, "Seca", "En patio", "A-01", "10/08/2026", 2));
        patio.insertarAlFinal(new Contenedor("CT-1002", 18.200, "Refrigerada", "En patio", "B-04", "10/08/2026", 1));
        patio.insertarAlFinal(new Contenedor("CT-1003", 12.750, "Granel", "En patio", "A-02", "11/08/2026", 3));
        patio.insertarAlFinal(new Contenedor("CT-1004", 20.100, "Seca", "En patio", "D-07", "11/08/2026", 2));
        patio.insertarAlFinal(new Contenedor("CT-1005", 27.900, "Seca", "En patio", "A-03", "12/08/2026", 1));
        patio.insertarAlFinal(new Contenedor("CT-1006", 9.400, "Granel", "En patio", "C-05", "12/08/2026", 3));

        System.out.println(patio.recorrerAdelante());
        bitacora.registrar("INGRESO", "Se cargó el inventario inicial con " + patio.getTamano() + " contenedores.");

        // -----------------------------------------------------------
        // 2) FILA DE CAMIONES (Cola FIFO)
        // -----------------------------------------------------------
        System.out.println(">>> 2) INGRESO DE CAMIONES AL PUERTO (Cola FIFO)\n");

        colaCamiones.encolar(new Camion("ABC123", "Carlos Ruiz", "08:00", "CT-1005", "En espera"));
        colaCamiones.encolar(new Camion("DEF456", "Mónica Pérez", "08:15", "CT-1002", "En espera"));
        colaCamiones.encolar(new Camion("GHI789", "Luis Torres", "08:30", "CT-1001", "En espera"));
        colaCamiones.encolar(new Camion("JKL321", "Andrea Gómez", "08:45", "CT-1006", "En espera"));

        for (int i = 0; i < 4; i++) {
            bitacora.registrar("INGRESO", "Camión encolado, orden de llegada #" + (i + 1) + ".");
        }

        System.out.println("Fila de camiones en orden de llegada:");
        System.out.println(colaCamiones.recorrido());

        // -----------------------------------------------------------
        // 3) MOVER CONTENEDORES DEL INVENTARIO A LA PILA DE LA GRÚA
        // -----------------------------------------------------------
        System.out.println(">>> 3) CARGA EN GRÚA PÓRTICO (Pila LIFO)\n");
        System.out.println("Se apilan, en este orden: CT-1005, CT-1002, CT-1001, CT-1006\n");

        String[] ordenDeApilado = {"CT-1005", "CT-1002", "CT-1001", "CT-1006"};
        for (String id : ordenDeApilado) {
            Contenedor extraido = patio.buscarPorId(id);
            if (extraido != null) {
                extraido.setEstado("En zona de apilamiento");
                pilaGrua.apilar(extraido);
                bitacora.registrar("CARGA", "Contenedor " + id + " movido del inventario a la pila de la grúa.");
            }
        }

        System.out.println("Estado de la pila (de la cima hacia la base):");
        System.out.println(pilaGrua.recorrido());

        System.out.println("Inventario restante en el patio:");
        System.out.println(patio.recorrerAdelante());

        // -----------------------------------------------------------
        // 4) ATENCIÓN DE CAMIONES: se desencola el camión y se desapila
        //    el contenedor que le corresponde entregar (LIFO)
        // -----------------------------------------------------------
        System.out.println(">>> 4) ATENCIÓN DE CAMIONES (dequeue + pop)\n");

        for (int i = 0; i < 2; i++) {
            Camion atendido = colaCamiones.desencolar();
            atendido.setEstadoAtencion("En atención");
            Contenedor entregado = pilaGrua.desapilar();
            entregado.setEstado("Despachado");

            System.out.println("Se atiende al camión: " + atendido);
            System.out.println("Se le entrega el contenedor (tope de la pila): " + entregado);
            System.out.println();

            bitacora.registrar("DESCARGA",
                    "Camión " + atendido.getPlaca() + " atendido; se le entregó el contenedor " + entregado.getId() + ".");
        }

        System.out.println("Fila de camiones restante:");
        System.out.println(colaCamiones.recorrido());

        System.out.println("Pila de la grúa restante (de la cima hacia la base):");
        System.out.println(pilaGrua.recorrido());

        // -----------------------------------------------------------
        // 5) BÚSQUEDAS
        // -----------------------------------------------------------
        System.out.println(">>> 5) BÚSQUEDAS\n");

        String placaBuscada = "JKL321";
        Camion camionEncontrado = colaCamiones.buscarPorPlaca(placaBuscada);
        System.out.println("Buscando camión con placa " + placaBuscada + " -> "
                + (camionEncontrado != null ? camionEncontrado : "NO ENCONTRADO"));

        String idBuscado = "CT-1006";
        Contenedor contenedorEnPila = pilaGrua.buscarPorId(idBuscado);
        System.out.println("Buscando " + idBuscado + " en la pila -> "
                + (contenedorEnPila != null ? contenedorEnPila : "NO ENCONTRADO"));
        System.out.println();

        // -----------------------------------------------------------
        // 6) VALIDACIONES DE OPERACIÓN (estructuras vacías)
        // -----------------------------------------------------------
        System.out.println(">>> 6) VALIDACIONES DE OPERACIÓN\n");

        try {
            // Se vacía intencionalmente la pila para forzar el error
            pilaGrua.desapilar();
            pilaGrua.desapilar();
            pilaGrua.desapilar(); // aquí ya debería estar vacía
        } catch (OperacionInvalidaException e) {
            System.out.println("Validación capturada al desapilar: " + e.getMessage());
            bitacora.registrar("VALIDACIÓN", "Intento de desapilar sobre pila vacía, bloqueado correctamente.");
        }

        try {
            colaCamiones.desencolar();
            colaCamiones.desencolar();
            colaCamiones.desencolar();
            colaCamiones.desencolar(); // ya no deberían quedar camiones
        } catch (OperacionInvalidaException e) {
            System.out.println("Validación capturada al desencolar: " + e.getMessage());
            bitacora.registrar("VALIDACIÓN", "Intento de desencolar sobre cola vacía, bloqueado correctamente.");
        }
        System.out.println();

        // -----------------------------------------------------------
        // 7) REPORTE FINAL DEL INVENTARIO Y BITÁCORA COMPLETA
        // -----------------------------------------------------------
        System.out.println(">>> 7) REPORTE FINAL DE INVENTARIO\n");
        System.out.println(patio.generarReporteOrdenado());

        System.out.println(">>> 8) BITÁCORA COMPLETA DE MOVIMIENTOS\n");
        System.out.println(bitacora.generarReporte());
    }
}
