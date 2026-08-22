package tallerintegrador;
public class Main {
    public static void main(String[] args) {
 
        Lista patio = new Lista();
 
        System.out.println(">>> 1) INSERCIÓN DE 10 CONTENEDORES (mezclando inicio y final)\n");
 
        patio.insertarAlFinal(new Contenedor("CT-1001", 24.500, "Seca",         "En patio",     "A-01", "10/08/2026", 2));
        patio.insertarAlFinal(new Contenedor("CT-1002", 18.200, "Refrigerada",  "En patio",     "B-04", "10/08/2026", 1));
        patio.insertarAlInicio(new Contenedor("CT-1000", 30.000, "Peligrosa",   "En revisión",  "C-01", "09/08/2026", 1));
        patio.insertarAlFinal(new Contenedor("CT-1003", 12.750, "Granel",       "En patio",     "A-02", "11/08/2026", 3));
        patio.insertarAlFinal(new Contenedor("CT-1004", 20.100, "Seca",         "En tránsito",  "D-07", "11/08/2026", 2));
        patio.insertarAlInicio(new Contenedor("CT-0999", 15.300, "Refrigerada", "En patio",     "B-01", "08/08/2026", 1));
        patio.insertarAlFinal(new Contenedor("CT-1005", 27.900, "Seca",        "Despachado",    "A-03", "12/08/2026", 3));
        patio.insertarAlFinal(new Contenedor("CT-1006", 9.400,  "Granel",      "En patio",      "C-05", "12/08/2026", 2));
        patio.insertarAlFinal(new Contenedor("CT-1007", 22.000, "Peligrosa",   "En revisión",   "C-02", "13/08/2026", 1));
        patio.insertarAlFinal(new Contenedor("CT-1008", 16.600, "Refrigerada", "En patio",      "B-05", "13/08/2026", 2));
 
        System.out.println("Total de contenedores insertados: " + patio.getTamano() + "\n");
 
        System.out.println(">>> 2) RECORRIDO HACIA ADELANTE (cabeza -> cola)\n");
        System.out.println(patio.recorrerAdelante());
 
        System.out.println(">>> 3) RECORRIDO HACIA ATRÁS (cola -> cabeza)\n");
        System.out.println(patio.recorrerAtras());
 
        System.out.println(">>> 4) BÚSQUEDA POR ID\n");
        String idBuscado = "CT-1004";
        Contenedor encontrado = patio.buscarPorId(idBuscado);
        System.out.println("Buscando " + idBuscado + " -> " +
                (encontrado != null ? encontrado : "NO ENCONTRADO"));
 
        String idInexistente = "CT-9999";
        Contenedor noEncontrado = patio.buscarPorId(idInexistente);
        System.out.println("Buscando " + idInexistente + " -> " +
                (noEncontrado != null ? noEncontrado : "NO ENCONTRADO"));
        System.out.println();
 
        System.out.println(">>> 5) MODIFICACIÓN DE DATOS (CT-1003 pasa a 'Despachado', prioridad 1)\n");
        boolean modOk = patio.modificarDatos("CT-1003", 12.750, "Granel", "Despachado", "A-02", "14/08/2026", 1);
        System.out.println("¿Modificación exitosa? " + modOk);
        System.out.println("Estado actual -> " + patio.buscarPorId("CT-1003"));
        System.out.println();
 
        System.out.println(">>> 6) ELIMINACIÓN DE CONTENEDORES\n");
        System.out.println("Eliminando CT-1000 (era la cabeza)... " + patio.eliminarPorId("CT-1000"));
        System.out.println("Eliminando CT-1005 (nodo intermedio)... " + patio.eliminarPorId("CT-1005"));
        System.out.println("Eliminando CT-9999 (no existe)... " + patio.eliminarPorId("CT-9999"));
        System.out.println("Total de contenedores tras eliminar: " + patio.getTamano() + "\n");
 
        System.out.println(">>> 7) REPORTE ORDENADO DEL INVENTARIO (por prioridad operativa)\n");
        System.out.println(patio.generarReporteOrdenado());
    }
}
