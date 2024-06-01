package Servicio;

import Modelo.Contacto;
import Modelo.NodoContacto;
import java.time.LocalDate;

public class Agenda {
    private NodoContacto raiz;

    public Agenda() {
        this.raiz = null;
    }

    /**
     * Agrega un nuevo contacto a la agenda.
     *
     * @param nombre Nombre del contacto.
     * @param telefono Teléfono del contacto.
     * @param correoElectronico Correo electrónico del contacto.
     * @param fechaNacimiento Fecha de nacimiento del contacto.
     */
    public void agregarContacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, correoElectronico, fechaNacimiento);
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            this.insertar(this.raiz, nuevoContacto);
        }
    }

    // Método para insertar un contacto en el árbol
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

    // Método auxiliar para buscar un contacto en el árbol
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

    // Método auxiliar para eliminar un contacto en el árbol
    private NodoContacto eliminar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            nodo.setIzdo(eliminar(nodo.getIzdo(), nombre));
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) > 0) {
            nodo.setDcho(eliminar(nodo.getDcho(), nombre));
        } else {
            if (nodo.getIzdo() == null) {
                return nodo.getDcho();
            } else if (nodo.getDcho() == null) {
                return nodo.getIzdo();
            }

            NodoContacto temp = minValorNodo(nodo.getDcho());
            nodo.getContacto().setTelefono(temp.getContacto().getTelefono());
            nodo.getContacto().setNombre(temp.getContacto().getNombre());
            nodo.getContacto().setCorreoElectronico(temp.getContacto().getCorreoElectronico());
            nodo.getContacto().setFechaNacimiento(temp.getContacto().getFechaNacimiento());
            nodo.setDcho(eliminar(nodo.getDcho(), temp.getContacto().getNombre()));
        }
        return nodo;
    }

    // Método auxiliar para encontrar el nodo con el valor mínimo en un subárbol
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

    // Método auxiliar para mostrar todos los contactos en la agenda
    private void inOrden(NodoContacto nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzdo());
            Contacto c = nodo.getContacto();
            System.out.println("Nombre: " + c.getNombre() + ", Teléfono: " + c.getTelefono() +
                    ", Correo: " + c.getCorreoElectronico() + ", Fecha de Nacimiento: " + c.getFechaNacimiento());
            inOrden(nodo.getDcho());
        }
    }
}
