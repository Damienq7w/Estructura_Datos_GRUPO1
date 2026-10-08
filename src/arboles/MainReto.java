package arboles;

import arboles.app.VistaArbol;
import arboles.modelo.Nodo;
import arboles.negocio.ArbolBinario;
import java.util.Scanner;

public class MainReto {

    private static ArbolBinario<String> arbolA;
    private static ArbolBinario<String> arbolB;
    private static Nodo<String> ltx;
    private static Nodo<String> snc;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("=== Reto: arbol sano o arbol en cadena? ===");
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            System.out.print("Opcion: ");
            if (!entrada.hasNextLine()) {
                break;
            }
            String opcion = entrada.nextLine().trim();
            System.out.println();
            switch (opcion) {
                case "1":
                    construirArbolA();
                    break;
                case "2":
                    consultarArbolA();
                    break;
                case "3":
                    construirArbolB();
                    break;
                case "4":
                    verificarEsCadena();
                    break;
                case "5":
                    casosLimite();
                    break;
                case "6":
                    caminoIDI();
                    break;
                case "7":
                    construirArbolA();
                    consultarArbolA();
                    construirArbolB();
                    verificarEsCadena();
                    casosLimite();
                    caminoIDI();
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        System.out.println("Hasta luego");
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("1) Parte 1: construir el arbol A");
        System.out.println("2) Parte 2: consultar el arbol A");
        System.out.println("3) Parte 3: construir el arbol B");
        System.out.println("4) Parte 3: verificar esCadena en A y B");
        System.out.println("5) Casos limite");
        System.out.println("6) Extra: camino I, D, I");
        System.out.println("7) Ejecutar todo en orden");
        System.out.println("0) Salir");
    }

    // ===== PARTE 1: construir el arbol A =====
    private static void construirArbolA() {
        arbolA = new ArbolBinario<>();
        ltx = arbolA.crearRaiz("LTX");
        Nodo<String> atf = arbolA.agregarIzquierdo(ltx, "ATF");
        Nodo<String> mch = arbolA.agregarDerecho(ltx, "MCH");
        arbolA.agregarIzquierdo(atf, "TUA");
        arbolA.agregarDerecho(atf, "IBB");
        snc = arbolA.agregarIzquierdo(mch, "SNC");
        arbolA.agregarDerecho(mch, "OCC");
        arbolA.agregarIzquierdo(snc, "LGQ");

        System.out.println("== Parte 1: arbol A ==");
        VistaArbol.mostrar(arbolA);
    }

    // ===== PARTE 2: consultar =====
    private static void consultarArbolA() {
        if (!arbolAConstruido()) {
            return;
        }
        System.out.println();
        System.out.println("== Parte 2: consultas del arbol A ==");
        System.out.println("Raiz              -> " + arbolA.getRaiz().getDato());
        System.out.println("Cantidad de nodos -> " + arbolA.contarNodos());
        System.out.println("Cantidad de hojas -> " + arbolA.contarHojas());
        System.out.println("Altura            -> " + arbolA.altura());
        System.out.println("Grado de LTX      -> " + ltx.grado());
        System.out.println("Grado de SNC      -> " + snc.grado());
    }

    // ===== PARTE 3: construir el arbol B =====
    private static void construirArbolB() {
        arbolB = new ArbolBinario<>();
        Nodo<String> gps = arbolB.crearRaiz("GPS");
        Nodo<String> scy = arbolB.agregarDerecho(gps, "SCY");
        Nodo<String> mrr = arbolB.agregarDerecho(scy, "MRR");
        Nodo<String> ptz = arbolB.agregarDerecho(mrr, "PTZ");
        arbolB.agregarDerecho(ptz, "TPN");

        System.out.println();
        System.out.println("== Parte 3: arbol B ==");
        VistaArbol.mostrar(arbolB);
    }

    // ===== PARTE 3: esCadena en A y B =====
    private static void verificarEsCadena() {
        if (!arbolAConstruido()) {
            return;
        }
        if (arbolB == null) {
            System.out.println("Primero construya el arbol B (opcion 3).");
            return;
        }
        System.out.println();
        System.out.println("== Parte 3: esCadena ==");
        System.out.println("esCadena(A) -> " + esCadena(arbolA)
                + "  (altura " + arbolA.altura() + ", nodos " + arbolA.contarNodos() + ")");
        System.out.println("esCadena(B) -> " + esCadena(arbolB)
                + "  (altura " + arbolB.altura() + ", nodos " + arbolB.contarNodos() + ")");
        System.out.println("Respuesta: B se parece a una lista. Para buscar TPN hay que pasar por");
        System.out.println("los 5 nodos (O(n)); en A, como esta repartido, basta bajar 3 niveles.");
    }

    // ===== Casos limite =====
    private static void casosLimite() {
        if (!arbolAConstruido()) {
            return;
        }
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
    }

    // ===== Extra: camino I, D, I desde la raiz de A =====
    private static void caminoIDI() {
        if (!arbolAConstruido()) {
            return;
        }
        System.out.println();
        System.out.println("== Extra: camino I, D, I ==");
        Nodo<String> actual = arbolA.getRaiz().getIzquierdo(); // I -> ATF
        actual = actual.getDerecho();                         // D -> IBB
        actual = actual.getIzquierdo();                       // I -> null (IBB es hoja)
        System.out.println(actual == null
                ? "No hay aeropuerto: IBB es hoja, su hijo izquierdo es null."
                : "Aeropuerto encontrado: " + actual.getDato());
    }

    private static boolean arbolAConstruido() {
        if (arbolA == null) {
            System.out.println("Primero construya el arbol A (opcion 1).");
            return false;
        }
        return true;
    }

    static boolean esCadena(ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            return false; // un arbol vacio no es cadena (la formula daria -1 == -1)
        }
        return arbol.altura() == arbol.contarNodos() - 1;
    }
}
