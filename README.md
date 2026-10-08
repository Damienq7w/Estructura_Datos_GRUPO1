# Reto: arbol sano o arbol en cadena?

**Materia:** Estructura de Datos - Universidad Tecnica de Ambato

**Tema:** Arboles binarios

**Integrantes:**
- Damian Alexander Cunalata Mendoza
- Darwin Joel Tisalema Guashco

---

## Que se hizo

El reto consistia en usar las clases ya hechas del paquete `arboles` (`Nodo`, `ArbolBinario` y `VistaArbol`) sin modificarlas, y programar todo el trabajo en un solo archivo: `src/arboles/MainReto.java`.

El programa se divide en tres partes, mas los casos limite y un ejercicio extra:

1. **Parte 1 - Construir:** se armo el arbol A usando solo `crearRaiz`, `agregarIzquierdo` y `agregarDerecho`. Despues se imprime con `VistaArbol.mostrar` para comprobar que coincide con el dibujo del reto.

   ```
   LTX
   +-- I: ATF
   |   +-- I: TUA
   |   `-- D: IBB
   `-- D: MCH
       +-- I: SNC
       |   `-- I: LGQ
       `-- D: OCC
   ```

2. **Parte 2 - Consultar:** se imprimen los datos del arbol A usando las operaciones de `ArbolBinario` y `Nodo`.

   | Dato | Resultado |
   |---|---|
   | Raiz | LTX |
   | Cantidad de nodos | 8 |
   | Cantidad de hojas | 4 |
   | Altura | 3 |
   | Grado de LTX | 2 |
   | Grado de SNC | 1 |

3. **Parte 3 - Decidir:** se armo el arbol B, que tiene todos sus nodos colgando a la derecha:

   ```
   GPS
   `-- D: SCY
       `-- D: MRR
           `-- D: PTZ
               `-- D: TPN
   ```

   Tambien se escribio el metodo `esCadena`, que revisa si la altura es igual a la cantidad de nodos menos 1. Antes de aplicar esa formula revisa `esVacio()`, porque en un arbol vacio la altura es -1 y hay 0 nodos, y sin esa revision la formula diria que es cadena.

   ```java
   static boolean esCadena(ArbolBinario<String> arbol) {
       if (arbol.esVacio()) {
           return false;
       }
       return arbol.altura() == arbol.contarNodos() - 1;
   }
   ```

   | Arbol | Altura | Nodos | esCadena |
   |---|---|---|---|
   | A | 3 | 8 | false |
   | B | 4 | 5 | true |

   **Respuesta:** el arbol B se parece a una lista. Para buscar un dato, por ejemplo TPN, hay que recorrer los 5 nodos uno por uno, asi que la busqueda es O(n), igual que en una lista. En cambio, el arbol A esta repartido y basta con bajar 3 niveles.

4. **Casos limite:**

   | Caso | Resultado |
   |---|---|
   | Arbol vacio | `esCadena` devuelve false |
   | Arbol de un solo nodo | `esCadena` devuelve true |
   | Agregar un hijo donde ya hay uno | Se captura la excepcion con `try/catch` y se muestra el mensaje de error, sin que el programa se caiga |

5. **Extra (camino I, D, I):** desde la raiz LTX, el camino I lleva a ATF, el D lleva a IBB y el siguiente I devuelve `null`, porque IBB es hoja. Por eso no se encuentra ningun aeropuerto. El codigo revisa si el nodo es `null` antes de pedir el dato, asi se evita un `NullPointerException`.

---

## Reparticion del trabajo (MainReto)

| Integrante | Parte del MainReto | Funciones y operaciones usadas |
|---|---|---|
| **Damian Alexander Cunalata Mendoza** | Parte 1: construccion del arbol A | `crearRaiz`, `agregarIzquierdo`, `agregarDerecho`, `VistaArbol.mostrar` |
| **Damian Alexander Cunalata Mendoza** | Parte 2: consultas del arbol A | `getRaiz().getDato()`, `contarNodos()`, `contarHojas()`, `altura()`, `grado()` de LTX y SNC |
| **Damian Alexander Cunalata Mendoza** | Extra: camino I, D, I | `getIzquierdo()`, `getDerecho()`, validacion de `null` |
| **Darwin Joel Tisalema Guashco** | Parte 3: construccion del arbol B | `crearRaiz`, `agregarDerecho`, `VistaArbol.mostrar` |
| **Darwin Joel Tisalema Guashco** | Metodo `esCadena` y prueba con los arboles A y B | `esVacio()`, `altura()`, `contarNodos()` |
| **Darwin Joel Tisalema Guashco** | Casos limite y respuesta de la Parte 3 | Arbol vacio, arbol de un solo nodo, `try/catch` con `IllegalStateException` |

---

## Estructura del proyecto

```
src/
└── arboles/
    ├── MainReto.java          <- solucion del reto
    ├── app/                   (Main, MainInteractivo, ConsolaArbol, VistaArbol)
    ├── modelo/                (Nodo)
    └── negocio/               (ArbolBinario)
```

## Reglas cumplidas

- Solo se usan `Nodo` y `ArbolBinario`. `VistaArbol` se usa unicamente para dibujar el arbol.
- No se usa `TreeSet`, `TreeMap`, `PriorityQueue`, `NavigableSet` ni `NavigableMap`.
- Las clases `Nodo`, `ArbolBinario` y `VistaArbol` no se modificaron en su logica.

## Compilar y ejecutar

```
javac -encoding UTF-8 -d out src/arboles/*.java src/arboles/modelo/*.java src/arboles/negocio/*.java src/arboles/app/*.java
java -cp out arboles.MainReto
```
