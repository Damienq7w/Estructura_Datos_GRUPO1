package arboles.app;

import arboles.modelo.Nodo;
import arboles.negocio.ArbolBinario;

public class Main {

    public static void main(String[] args) {
        ArbolBinario<String> arbol = new ArbolBinario<>();

        titulo("Arbol recien creado");
        System.out.println("esVacio() -> " + arbol.esVacio());
        VistaArbol.mostrar(arbol);

        titulo("crearRaiz(\"UIO\")");
        Nodo<String> uio = arbol.crearRaiz("UIO");
        VistaArbol.mostrar(arbol);

        titulo("agregarIzquierdo(uio, \"GYE\")");
        Nodo<String> gye = arbol.agregarIzquierdo(uio, "GYE");
        VistaArbol.mostrar(arbol);

        titulo("agregarDerecho(uio, \"MEC\")");
        Nodo<String> mec = arbol.agregarDerecho(uio, "MEC");
        VistaArbol.mostrar(arbol);

        titulo("Se agregan CUE y LOH bajo GYE, y ESM bajo MEC");
        arbol.agregarIzquierdo(gye, "CUE");
        arbol.agregarDerecho(gye, "LOH");
        arbol.agregarDerecho(mec, "ESM");
        VistaArbol.mostrar(arbol);

        titulo("El mismo arbol en forma grafica");
        VistaArbol.mostrarGrafico(arbol);

        titulo("Operaciones del arbol");
        System.out.println("getRaiz().getDato() -> " + arbol.getRaiz().getDato());
        System.out.println("contarNodos()       -> " + arbol.contarNodos());
        System.out.println("contarHojas()       -> " + arbol.contarHojas());
        System.out.println("altura()            -> " + arbol.altura());

        titulo("Operaciones de un nodo");
        System.out.println("gye.esHoja() -> " + gye.esHoja());
        System.out.println("gye.grado()  -> " + gye.grado());
        System.out.println("mec.grado()  -> " + mec.grado());

        titulo("Intento de agregar donde ya hay un hijo");
        try {
            arbol.agregarIzquierdo(uio, "XXX");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("== " + texto + " ==");
    }
}
