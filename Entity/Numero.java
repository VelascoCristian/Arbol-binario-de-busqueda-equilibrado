package Entity;
/*
Se declara la clase Numero que implementa la interfaz Comparador y guarda un valor de tipo
int. La formación del árbol se hace con repetidas llamadas al método insertar(), que pasa
control a insertarAvl(). La condición para terminar la formación del árbol está expuesta en el
enunciado: altura del árbol igual a 5. El método altura() de la clase principal determina dicho
parámetro. También en la clase principal, se escribe el método visualizar(), basado en el reco-
rrido inorden, para mostrar las claves en orden creciente y, a la vez, contar los nodos visitados.
 */
public class Numero implements Comparador {
    int valor;

    public Numero(int n) {
        valor = n;
    }

    public String toString() {
        return " " + valor;
    }

    @Override
    public boolean menorQue(Object op2) {
        Numero p2 = (Numero) op2;
        return valor < p2.valor;
    }

    @Override
    public boolean menorIgualQue(Object op2) {
        Numero p2 = (Numero) op2;
        return valor <= p2.valor;
    }

    @Override
    public boolean mayorQue(Object op2) {
        Numero p2 = (Numero) op2;
        return valor > p2.valor;
    }

    @Override
    public boolean mayorIgualQue(Object op2) {
        Numero p2 = (Numero) op2;
        return valor >= p2.valor;
    }

    @Override
    public boolean igualQue(Object op2) {
        Numero p2 = (Numero) op2;
        return valor == p2.valor;
    }

    @Override
    public boolean diferenteQue(Object op2) {
        Numero p2 = (Numero) op2;
        return valor != p2.valor;
    }
}
