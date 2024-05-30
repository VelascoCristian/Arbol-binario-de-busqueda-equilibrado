package Entity;

public class ArbolAvlNumerico extends ArbolAvl {
    public void insertarNumero(int valor) throws Exception {
        insertar(new Numero(valor));
    }

    public void dibujarArbol() {
        //Dibujar con líneas
        System.out.println("Versión con guiones:");
        dibujarArbolHorizontal(raiz, 0);
        //Dibujar sin líneas
        System.out.println("\nVersión sin guiones:");
        dibujarArbolVertical(raiz, 0);
    }

    private void dibujarArbolHorizontal(NodoAvl nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        dibujarArbolHorizontal(nodo.subarbolDcho(), nivel + 1);
        if (nivel != 0) {
            for (int i = 0; i < nivel - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + nodo.valorNodo().toString());
        } else {
            System.out.println(nodo.valorNodo().toString());
        }
        dibujarArbolHorizontal(nodo.subarbolIzdo(), nivel + 1);
    }

    private void dibujarArbolVertical(NodoAvl nodo, int espacio) {
        int contador = 10;
        if (nodo == null) {
            return;
        }
        espacio += contador;
        dibujarArbolVertical(nodo.subarbolDcho(), espacio);
        System.out.println();
        for (int i = contador; i < espacio; i++) {
            System.out.print(" ");
        }
        System.out.println(nodo.valorNodo().toString());
        dibujarArbolVertical(nodo.subarbolIzdo(), espacio);
    }

    //Devuelve la altura como entero
    public int altura() {
        if (raiz == null) {
            return -1;
        } else {
            return 1 + Math.max(altura(raiz.subarbolIzdo()), altura(raiz.subarbolDcho()));
        }
    }

    public void eliminarNum(int valor) throws Exception {
        eliminar(new Numero(valor));
    }

    public NodoAvl buscarNum(int valor) {
        return buscar(raiz, new Numero(valor));
    }

}
