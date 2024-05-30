package Entity;

public class ArbolAvl {
    protected NodoAvl raiz;

    public ArbolAvl() {
        raiz = null;
    }

    public NodoAvl raizArbol() {
        return raiz;
    }

    public void insertar(Comparador valor) throws Exception {
        Logical h = new Logical(false);
        raiz = insertarAvl(raiz, valor, h);
    }

    public int altura(NodoAvl r) {
        if (r != null) {
            return Math.max(altura(r.subarbolIzdo()), altura(r.subarbolDcho())) + 1;
        } else {
            return 0;
        }
    }

    public void preorden() {
        preorden(raiz);
    }

    private void preorden(NodoAvl r) {
        if (r != null) {
            System.out.print(r.valorNodo() + " ");
            preorden(r.subarbolIzdo());
            preorden(r.subarbolDcho());
        }
    }

    public void inorden() {
        inorden(raiz);
    }

    private void inorden(NodoAvl r) {
        if (r != null) {
            inorden(r.subarbolIzdo());
            System.out.print(r.valorNodo() + " ");
            inorden(r.subarbolDcho());
        }
    }

    public void postorden() {
        postorden(raiz);
    }

    private void postorden(NodoAvl r) {
        if (r != null) {
            postorden(r.subarbolIzdo());
            postorden(r.subarbolDcho());
            System.out.print(r.valorNodo() + " ");
        }
    }

    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(NodoAvl r) {
        if (r != null) {
            return contarNodos(r.subarbolIzdo()) + contarNodos(r.subarbolDcho()) + 1;
        } else {
            return 0;
        }
    }

    private NodoAvl insertarAvl(NodoAvl raiz, Comparador dato, Logical h) throws Exception {
        NodoAvl n1;
        if (raiz == null) { //Si no tengo raiz, el valor ingresado será raiz
            raiz = new NodoAvl(dato);
            h.setLogical(true);
        } else if (dato.menorQue(raiz.valorNodo())) { //Si ya tengo raiz comparo el valor para saber si va a ser hijo izuqierda o derecha
            NodoAvl iz; //Para el caso que el nuevo valor sea menor (hijo izquierdo)
            iz = insertarAvl((NodoAvl) raiz.subarbolIzdo(), dato, h); //Le asigno como subArbol izquierdo
            raiz.ramaIzdo(iz);
            if (h.booleanValue()) { //Seteo el factor de equilibrio
                switch (raiz.fe) {
                    case 1:
                        raiz.fe = 0;
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.fe = -1;
                        break;
                    case -1://Si agrego un nuevo valor me va a desiquilibrar, por lo tanto, dependiendo el caso, hago la rotación izquierda-izquierda o izquierda-derecha
                        n1 = (NodoAvl) raiz.subarbolIzdo();
                        if (n1.fe == -1) {
                            raiz = rotacionII(raiz, n1);
                        } else {
                            raiz = rotacionID(raiz, n1);
                        }
                        h.setLogical(false);
                        break;
                }
            }
        } else if (dato.mayorQue(raiz.valorNodo())) { //Le asigno como subArbol derecho
            NodoAvl dr;
            dr = insertarAvl((NodoAvl) raiz.subarbolDcho(), dato, h);
            raiz.ramaDcho(dr);
            if (h.booleanValue()) { //Seteo el factor de equilibrio
                switch (raiz.fe) {
                    case -1:
                        raiz.fe = 0;
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.fe = 1;
                        break;
                    case 1: //Si agrego un nuevo valor me va a desiquilibrar, por lo tanto, dependiendo el caso, hago la rotación derecha-izquierda o derecha-derecha
                        n1 = (NodoAvl) raiz.subarbolDcho();
                        if (n1.fe == 1) {
                            raiz = rotacionDD(raiz, n1);
                        } else {
                            raiz = rotacionDI(raiz, n1);
                        }
                        h.setLogical(false);
                        break;
                }
            }
        } else {
            throw new Exception("Nodo duplicado");
        }
        return raiz;
    }

    //Rotación izquierda izquierda
    private NodoAvl rotacionII(NodoAvl n, NodoAvl n1) {
        n.ramaIzdo(n1.subarbolDcho());
        n1.ramaDcho(n);
        if (n1.fe == -1) {
            n.fe = 0;
            n1.fe = 0;
        } else {
            n.fe = -1;
            n1.fe = 1;
        }
        return n1;
    }

    //Rotación derecha derecha
    private NodoAvl rotacionDD(NodoAvl n, NodoAvl n1) {
        n.ramaDcho(n1.subarbolIzdo());
        n1.ramaIzdo(n);
        if (n1.fe == 1) {
            n.fe = 0;
            n1.fe = 0;
        } else {
            n.fe = 1;
            n1.fe = -1;
        }
        return n1;
    }

    //Rotación izquierda derecha
    private NodoAvl rotacionID(NodoAvl n, NodoAvl n1) {
        NodoAvl n2;
        n2 = (NodoAvl) n1.subarbolDcho();
        n.ramaIzdo(n2.subarbolDcho());
        n2.ramaDcho(n);
        n1.ramaDcho(n2.subarbolIzdo());
        n2.ramaIzdo(n1);
        switch (n2.fe) {
            case -1:
                n1.fe = 0;
                n.fe = 1;
                break;
            case 0:
                n1.fe = 0;
                n.fe = 0;
                break;
            case 1:
                n1.fe = -1;
                n.fe = 0;
                break;
        }
        n2.fe = 0;
        return n2;
    }

    //Rotación derecha izquierda
    private NodoAvl rotacionDI(NodoAvl n, NodoAvl n1) {
        NodoAvl n2;
        n2 = (NodoAvl) n1.subarbolIzdo();
        n.ramaDcho(n2.subarbolIzdo());
        n2.ramaIzdo(n);
        n1.ramaIzdo(n2.subarbolDcho());
        n2.ramaDcho(n1);
        switch (n2.fe) {
            case 1:
                n1.fe = 0;
                n.fe = -1;
                break;
            case 0:
                n1.fe = 0;
                n.fe = 0;
                break;
            case -1:
                n1.fe = 1;
                n.fe = 0;
                break;
        }
        n2.fe = 0;
        return n2;
    }

    /*
    *
    *
    *
     */
    public void eliminar(Comparador valor) throws Exception {
        Logical flag = new Logical(false);
        raiz = borrarAvl(raiz, valor, flag);
    }

    private NodoAvl borrarAvl(NodoAvl r, Comparador clave, Logical cambiaAltura) throws Exception {
        if (r == null) {
            throw new Exception("No existe el dato");
        } else if (clave.menorQue(r.valorNodo())) {
            NodoAvl iz;
            iz = borrarAvl((NodoAvl) r.subarbolIzdo(), clave, cambiaAltura);
            r.ramaIzdo(iz);
            if (cambiaAltura.booleanValue()) {
                r = equilibrar1(r, cambiaAltura);
            }
        } else if (clave.mayorQue(r.valorNodo())) {
            NodoAvl dr;
            dr = borrarAvl((NodoAvl) r.subarbolDcho(), clave, cambiaAltura);
            r.ramaDcho(dr);
            if (cambiaAltura.booleanValue()) {
                r = equilibrar2(r, cambiaAltura);
            }
        } else {
            NodoAvl q;
            q = r;
            if (q.subarbolIzdo() == null) {
                r = (NodoAvl) q.subarbolDcho();
                cambiaAltura.setLogical(true);
            } else if (q.subarbolDcho() == null) {
                r = (NodoAvl) q.subarbolIzdo();
                cambiaAltura.setLogical(true);
            } else {
                NodoAvl iz;
                iz = reemplazar(r, (NodoAvl) r.subarbolIzdo(), cambiaAltura);
                r.ramaIzdo(iz);
                if (cambiaAltura.booleanValue()) {
                    r = equilibrar1(r, cambiaAltura);
                }
            }
            q = null;
        }
        return r;
    }

    private NodoAvl reemplazar(NodoAvl n, NodoAvl act, Logical cambiaAltura) {
        if (act.subarbolDcho() != null) {
            NodoAvl d;
            d = reemplazar(n, (NodoAvl) act.subarbolDcho(), cambiaAltura);
            act.ramaDcho(d);
            if (cambiaAltura.booleanValue()) {
                act = equilibrar2(act, cambiaAltura);
            }
        } else {
            n.setDato(act.valorNodo());
            n = act;
            act = act.subarbolIzdo();
            n = null;
            cambiaAltura.setLogical(true);
        }
        return act;
    }

    private NodoAvl equilibrar1(NodoAvl n, Logical cambiaAltura) {
        NodoAvl n1;
        switch (n.fe) {
            case -1:
                n.fe = 0;
                break;
            case 0:
                n.fe = 1;
                cambiaAltura.setLogical(false);
                break;
            case 1:
                n1 = (NodoAvl) n.subarbolDcho();
                switch (n1.fe) {
                    case 1:
                        n = rotacionDD(n, n1);
                        break;
                    case 0:
                        n = rotacionDD(n, n1);
                        cambiaAltura.setLogical(false);
                        break;
                    case -1:
                        n = rotacionDI(n, n1);
                        break;
                }
                break;
        }
        return n;
    }

    private NodoAvl equilibrar2(NodoAvl n, Logical cambiaAltura) {
        NodoAvl n1;
        switch (n.fe) {
            case -1:
                n1 = (NodoAvl) n.subarbolIzdo();
                switch (n1.fe) {
                    case -1:
                        n = rotacionII(n, n1);
                        break;
                    case 0:
                        n = rotacionII(n, n1);
                        cambiaAltura.setLogical(false);
                        break;
                    case 1:
                        n = rotacionID(n, n1);
                        break;
                }
                break;
            case 0:
                n.fe = -1;
                cambiaAltura.setLogical(false);
                break;
            case 1:
                n.fe = 0;
                break;
        }
        return n;
    }

    public static NodoAvl buscar(NodoAvl raiz, Comparador valor) {
        if (raiz == null) {
            return null;
        } else if (valor.igualQue(raiz.valorNodo())) {
            return raiz;
        } else if (valor.menorQue(raiz.valorNodo())) {
            return buscar(raiz.subarbolIzdo(), valor);
        } else {
            return buscar(raiz.subarbolDcho(), valor);
        }
    }

}
