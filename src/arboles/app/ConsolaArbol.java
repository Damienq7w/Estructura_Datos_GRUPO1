package arboles.app;

import arboles.modelo.Nodo;
import arboles.negocio.ArbolBinario;
import java.util.Scanner;

public class ConsolaArbol {
     public static ArbolBinario<String> arbolDemo() {
        ArbolBinario<String> arbol = new ArbolBinario<>();
        Nodo<String> uio = arbol.crearRaiz("UIO");
        Nodo<String> gye = arbol.agregarIzquierdo(uio, "GYE");
        Nodo<String> mec = arbol.agregarDerecho(uio, "MEC");
        arbol.agregarIzquierdo(gye, "CUE");
        arbol.agregarDerecho(gye, "LOH");
        arbol.agregarDerecho(mec, "ESM");
        return arbol;
    }

    public static String leer(Scanner entrada, String pregunta) {
        System.out.print(pregunta);
        if (!entrada.hasNextLine()) {
            return null;
        }
        return entrada.nextLine().trim();
    }

    public static Nodo<String> buscar(Nodo<String> nodo, String dato) {
        if (nodo == null) {
            return null;
        }
        if (String.valueOf(nodo.getDato()).equals(dato)) {
            return nodo;
        }
        Nodo<String> enIzquierdo = buscar(nodo.getIzquierdo(), dato);
        if (enIzquierdo != null) {
            return enIzquierdo;
        }
        return buscar(nodo.getDerecho(), dato);
    }

    public static void mostrarEstado(ArbolBinario<String> arbol) {
        System.out.println();
        VistaArbol.mostrarGrafico(arbol);
        System.out.println("[nodos " + arbol.contarNodos() + " | hojas " + arbol.contarHojas()
                + " | altura " + arbol.altura() + "]");
    }

    public static void crearRaiz(Scanner entrada, ArbolBinario<String> arbol) {
        String dato = leer(entrada, "Dato de la raiz: ");
        if (dato == null || dato.isEmpty()) {
            System.out.println("Error: el dato no puede estar vacio");
            return;
        }
        try {
            arbol.crearRaiz(dato);
            mostrarEstado(arbol);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void agregar(Scanner entrada, ArbolBinario<String> arbol, boolean izquierdo) {
        if (arbol.esVacio()) {
            System.out.println("Error: primero hay que crear la raiz");
            return;
        }
        String textoPadre = leer(entrada, "Padre: ");
        if (textoPadre == null) {
            return;
        }
        Nodo<String> padre = buscar(arbol.getRaiz(), textoPadre);
        if (padre == null) {
            System.out.println("Error: no existe un nodo con el dato '" + textoPadre + "'");
            return;
        }
        String lado = izquierdo ? "izquierdo" : "derecho";
        String dato = leer(entrada, "Dato del hijo " + lado + ": ");
        if (dato == null || dato.isEmpty()) {
            System.out.println("Error: el dato no puede estar vacio");
            return;
        }
        try {
            if (izquierdo) {
                arbol.agregarIzquierdo(padre, dato);
            } else {
                arbol.agregarDerecho(padre, dato);
            }
            mostrarEstado(arbol);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void consultarNodo(Scanner entrada, ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            System.out.println("Error: el arbol esta vacio");
            return;
        }
        String texto = leer(entrada, "Nodo: ");
        if (texto == null) {
            return;
        }
        Nodo<String> nodo = buscar(arbol.getRaiz(), texto);
        if (nodo == null) {
            System.out.println("Error: no existe un nodo con el dato '" + texto + "'");
            return;
        }
        System.out.println(nodo.getDato() + " -> esHoja: " + nodo.esHoja() + ", grado: " + nodo.grado());
        System.out.println("izquierdo: " + describir(nodo.getIzquierdo()) + ", derecho: " + describir(nodo.getDerecho()));
    }

    private static String describir(Nodo<String> nodo) {
        return nodo == null ? "null" : String.valueOf(nodo.getDato());
    }

    public static void operaciones(ArbolBinario<String> arbol) {
        System.out.println("esVacio()    -> " + arbol.esVacio());
        System.out.println("raiz         -> " + (arbol.esVacio() ? "null" : arbol.getRaiz().getDato()));
        System.out.println("contarNodos  -> " + arbol.contarNodos());
        System.out.println("contarHojas  -> " + arbol.contarHojas());
        System.out.println("altura       -> " + arbol.altura());
    }
}
