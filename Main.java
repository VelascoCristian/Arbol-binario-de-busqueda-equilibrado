import Entity.ArbolAvl;
import Entity.ArbolAvlNumerico;
import Entity.Comparador;
import Entity.NodoAvl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Ej14.2
        ArbolAvlNumerico arbolAvlNumerico = new ArbolAvlNumerico();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1. Insertar número");
            System.out.println("2. Visualizar árbol");
            System.out.println("3. Altura del árbol");
            System.out.println("4. Recorrido preorden");
            System.out.println("5. Recorrido inorden");
            System.out.println("6. Recorrido postorden");
            System.out.println("7. Contar nodos");
            System.out.println("8. Buscar");
            System.out.println("9. Eliminar");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número a insertar: ");
                    int num = scanner.nextInt();
                    try {
                        arbolAvlNumerico.insertarNumero(num);
                        System.out.println("Número insertado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al insertar el número: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Árbol AVL:");
                    arbolAvlNumerico.dibujarArbol();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Altura del árbol: " + arbolAvlNumerico.altura());
                    break;
                case 4:
                    System.out.println("Recorrido preorden:");
                    arbolAvlNumerico.preorden();
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Recorrido inorden:");
                    arbolAvlNumerico.inorden();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Recorrido postorden:");
                    arbolAvlNumerico.postorden();
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Número de nodos: " + arbolAvlNumerico.contarNodos());
                    break;
                case 8:
                    System.out.print("Ingrese valor a buscar: ");
                    int valorBuscar = scanner.nextInt();
                    NodoAvl resultado = arbolAvlNumerico.buscarNum(valorBuscar);
                    if (resultado != null) {
                        Comparador valor = resultado.valorNodo();
                        System.out.println("El valor" + valor + " se encuentra en el árbol");
                    } else {
                        System.out.println("Valor no encontrado.");
                    }
                    break;
                case 9:
                    System.out.print("Ingrese valor a eliminar: ");
                    int valorAEliminar = scanner.nextInt();
                    try {
                        arbolAvlNumerico.eliminarNum(valorAEliminar);
                        System.out.println("Valor eliminado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al eliminar: " + e.getMessage());
                    }
                    break;
                case 10:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 10);
    }
}