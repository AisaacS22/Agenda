import Modelo.Contacto;
import Modelo.NodoContacto;

/**
 * La clase Agenda maneja una agenda de contactos usando un árbol binario de búsqueda.
 * Permite agregar, buscar, eliminar y mostrar contactos.
 */
public class Agenda {
    private NodoContacto raiz;

    public Agenda() {
        this.raiz = null;
    }

    /**
     * Agrega un nuevo contacto a la agenda.
     *
     * @param nombre   Nombre del contacto.
     * @param telefono Teléfono del contacto.
     */
    public void agregarContacto(String nombre, String telefono) {
        Contacto nuevoContacto = new Contacto(nombre, telefono);
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            insertar(this.raiz, nuevoContacto);
        }
    }

    /**
     * Inserta un contacto en el árbol binario de búsqueda.
     *
     * @param padre    Nodo actual en el árbol.
     * @param contacto Contacto a insertar.
     */
    private void insertar(NodoContacto padre, Contacto contacto) {
        if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) < 0) {
            if (padre.getIzdo() == null) {
                padre.setIzdo(new NodoContacto(contacto));
            } else {
                insertar(padre.getIzdo(), contacto);
            }
        } else if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) > 0) {
            if (padre.getDcho() == null) {
                padre.setDcho(new NodoContacto(contacto));
            } else {
                insertar(padre.getDcho(), contacto);
            }
        }
    }

    /**
     * Busca un contacto por nombre.
     *
     * @param nombre Nombre del contacto a buscar.
     * @return El contacto encontrado o null si no se encuentra.
     */
    public Contacto buscarContacto(String nombre) {
        return buscar(this.raiz, nombre);
    }

    /**
     * Busca un contacto en el árbol binario de búsqueda.
     *
     * @param nodo   Nodo actual en el árbol.
     * @param nombre Nombre del contacto a buscar.
     * @return El contacto encontrado o null si no se encuentra.
     */
    private Contacto buscar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.equals(nodo.getContacto().getNombre())) {
            return nodo.getContacto();
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            return buscar(nodo.getIzdo(), nombre);
        } else {
            return buscar(nodo.getDcho(), nombre);
        }
    }

    /**
     * Elimina un contacto por nombre.
     *
     * @param nombre Nombre del contacto a eliminar.
     */
    public void eliminarContacto(String nombre) {
        this.raiz = eliminar(this.raiz, nombre);
    }

    /**
     * Elimina un nodo del árbol binario de búsqueda.
     *
     * @param nodo   Nodo actual en el árbol.
     * @param nombre Nombre del contacto a eliminar.
     * @return Nodo resultante después de la eliminación.
     */
    private NodoContacto eliminar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            nodo.setIzdo(eliminar(nodo.getIzdo(), nombre));
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) > 0) {
            nodo.setDcho(eliminar(nodo.getDcho(), nombre));
        } else {
            // Nodo con solo un hijo o sin hijos
            if (nodo.getIzdo() == null) {
                return nodo.getDcho();
            } else if (nodo.getDcho() == null) {
                return nodo.getIzdo();
            }

            // Nodo con dos hijos: Obtener el sucesor en orden (el menor en el subárbol derecho)
            NodoContacto temp = minValorNodo(nodo.getDcho());
            nodo.getContacto().setTelefono(temp.getContacto().getTelefono());
            nodo.getContacto().setNombre(temp.getContacto().getNombre());
            nodo.setDcho(eliminar(nodo.getDcho(), temp.getContacto().getNombre()));
        }
        return nodo;
    }

    /**
     * Encuentra el nodo con el valor mínimo en un subárbol.
     *
     * @param nodo Nodo inicial del subárbol.
     * @return Nodo con el valor mínimo.
     */
    private NodoContacto minValorNodo(NodoContacto nodo) {
        NodoContacto actual = nodo;
        while (actual.getIzdo() != null) {
            actual = actual.getIzdo();
        }
        return actual;
    }

    /**
     * Muestra todos los contactos en la agenda en orden alfabético.
     */
    public void mostrarContactos() {
        inOrden(this.raiz);
    }

    /**
     * Realiza un recorrido en orden del árbol binario de búsqueda.
     *
     * @param nodo Nodo actual en el árbol.
     */
    private void inOrden(NodoContacto nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzdo());
            System.out.println("Nombre: " + nodo.getContacto().getNombre() + ", Teléfono: " + nodo.getContacto().getTelefono());
            inOrden(nodo.getDcho());
        }
    }
}
