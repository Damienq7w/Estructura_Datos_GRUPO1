package arboles;

import arboles.modelo.Nodo;
import arboles.negocio.ArbolBinario;
import arboles.app.VistaArbol;

public class MainReto {

    public static void main(String[] args) {
        // ===== PARTE 1: construir el arbol A =====
        ArbolBinario<String> arbolA = new ArbolBinario<>();
        Nodo<String> ltx = arbolA.crearRaiz("LTX");
        Nodo<String> atf = arbolA.agregarIzquierdo(ltx, "ATF");
        Nodo<String> mch = arbolA.agregarDerecho(ltx, "MCH");
        arbolA.agregarIzquierdo(atf, "TUA");
        arbolA.agregarDerecho(atf, "IBB");
        Nodo<String> snc = arbolA.agregarIzquierdo(mch, "SNC");
        arbolA.agregarDerecho(mch, "OCC");
        arbolA.agregarIzquierdo(snc, "LGQ");

        System.out.println("== Parte 1: arbol A ==");
        VistaArbol.mostrar(arbolA);

        // ===== PARTE 2: consultar =====
        System.out.println();
        System.out.println("== Parte 2: consultas del arbol A ==");
        System.out.println("Raiz              -> " + arbolA.getRaiz().getDato());
        System.out.println("Cantidad de nodos -> " + arbolA.contarNodos());
        System.out.println("Cantidad de hojas -> " + arbolA.contarHojas());
        System.out.println("Altura            -> " + arbolA.altura());
        System.out.println("Grado de LTX      -> " + ltx.grado());
        System.out.println("Grado de SNC      -> " + snc.grado());

        // ===== PARTE 3: arbol B y esCadena =====
        ArbolBinario<String> arbolB = new ArbolBinario<>();
        Nodo<String> gps = arbolB.crearRaiz("GPS");
        Nodo<String> scy = arbolB.agregarDerecho(gps, "SCY");
        Nodo<String> mrr = arbolB.agregarDerecho(scy, "MRR");
        Nodo<String> ptz = arbolB.agregarDerecho(mrr, "PTZ");
        arbolB.agregarDerecho(ptz, "TPN");

        System.out.println();
        System.out.println("== Parte 3: arbol B ==");
        VistaArbol.mostrar(arbolB);
        System.out.println("esCadena(A) -> " + esCadena(arbolA)
                + "  (altura " + arbolA.altura() + ", nodos " + arbolA.contarNodos() + ")");
        System.out.println("esCadena(B) -> " + esCadena(arbolB)
                + "  (altura " + arbolB.altura() + ", nodos " + arbolB.contarNodos() + ")");
        System.out.println("Respuesta: B se parece a una lista. Para buscar TPN hay que pasar por");
        System.out.println("los 5 nodos (O(n)); en A, como esta repartido, basta bajar 3 niveles.");

        // ===== Casos limite =====
        System.out.println();
        System.out.println("== Casos limite ==");
        ArbolBinario<String> vacio = new ArbolBinario<>();
        System.out.println("Arbol vacio       -> esCadena = " + esCadena(vacio));

        ArbolBinario<String> unSolo = new ArbolBinario<>();
        unSolo.crearRaiz("UIO");
        System.out.println("Un solo nodo      -> esCadena = " + esCadena(unSolo));

        try {
            arbolA.agregarIzquierdo(ltx, "XXX"); // LTX ya tiene hijo izquierdo (ATF)
        } catch (IllegalStateException e) {
            System.out.println("Hijo ya ocupado   -> error: " + e.getMessage());
        }
        System.out.println("El programa sigue funcionando.");

        // ===== Extra: camino I, D, I desde la raiz de A =====
        System.out.println();
        System.out.println("== Extra: camino I, D, I ==");
        Nodo<String> actual = arbolA.getRaiz().getIzquierdo(); // I -> ATF
        actual = actual.getDerecho();                         // D -> IBB
        actual = actual.getIzquierdo();                       // I -> null (IBB es hoja)
        System.out.println(actual == null
                ? "No hay aeropuerto: IBB es hoja, su hijo izquierdo es null."
                : "Aeropuerto encontrado: " + actual.getDato());
    }

    static boolean esCadena(ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            return false; // un arbol vacio no es cadena (la formula daria -1 == -1)
        }
        return arbol.altura() == arbol.contarNodos() - 1;
    }
}
