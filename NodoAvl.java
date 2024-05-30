package Entity;

public class NodoAvl {
    Comparador dato;
    int fe; // factor de equilibrio
    NodoAvl izdo, dcho;

    public NodoAvl(Comparador valor) {
        dato = valor;
        fe = 0;
        izdo = dcho = null;
    }

    public Comparador valorNodo() {
        return dato;
    }

    public NodoAvl subarbolIzdo() {
        return izdo;
    }

    public NodoAvl subarbolDcho() {
        return dcho;
    }

    public void ramaIzdo(NodoAvl n) {
        izdo = n;
    }

    public void ramaDcho(NodoAvl n) {
        dcho = n;
    }

    public void setDato(Comparador dato) {
        this.dato = dato;
    }
}
