package Vista;

import Modelo.Contacto;
import Servicio.Agenda;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        // Agregar contactos con nombres de actores
        agenda.agregarContacto("Robert Downey Jr.", 13105510984567L, "robert@downeyjr.com", LocalDate.of(1965, Month.APRIL, 4)); // Actor
        agenda.agregarContacto("Scarlett Johansson", 1646559876543L, "scarlett@johansson.com", LocalDate.of(1984, Month.NOVEMBER, 22)); // Actriz
        agenda.agregarContacto("Chris Hemsworth", 611483456789L, "chris@hemsworth.com", LocalDate.of(1983, Month.AUGUST, 11)); // Actor
        agenda.agregarContacto("Gal Gadot", 9729834567890L, "gal@gadot.com", LocalDate.of(1985, Month.APRIL, 30)); // Actriz
        agenda.agregarContacto("Leonardo DiCaprio", 1310457776789L, "leo@dicaprio.com", LocalDate.of(1974, Month.NOVEMBER, 11)); // Actor
        agenda.agregarContacto("Emma Stone", 13101926567890L, "emma@stone.com", LocalDate.of(1988, Month.NOVEMBER, 6)); // Actriz
        agenda.agregarContacto("Ryan Gosling", 1610596733542L, "ryan@gosling.com", LocalDate.of(1980, Month.NOVEMBER, 12)); // Actor
        agenda.agregarContacto("Tom Hardy", 441522453675L, "tom@hardy.com", LocalDate.of(1977, Month.SEPTEMBER, 15)); // Actor
        agenda.agregarContacto("Margot Robbie", 6147113535234L, "margot@robbie.com", LocalDate.of(1990, Month.JULY, 2)); // Actriz
        agenda.agregarContacto("Dwayne Johnson", 13108519012345L, "dwayne@johnson.com", LocalDate.of(1972, Month.MAY, 2)); // Actor
        agenda.agregarContacto("Jennifer Lawrence", 16130123456L, "jennifer@lawrence.com", LocalDate.of(1990, Month.AUGUST, 15)); // Actriz
        agenda.agregarContacto("Johnny Depp", 16131123456L, "johnny@depp.com", LocalDate.of(1963, Month.JUNE, 9)); // Actor
        agenda.agregarContacto("Natalie Portman", 97452123456L, "natalie@portman.com", LocalDate.of(1981, Month.JUNE, 9)); // Actriz
        agenda.agregarContacto("Samuel L. Jackson", 12483123456L, "samuel@jackson.com", LocalDate.of(1948, Month.DECEMBER, 21)); // Actor
        agenda.agregarContacto("Christian Bale", 491234123456L, "christian@bale.com", LocalDate.of(1974, Month.JANUARY, 30)); // Actor

        // Mostrar todos los contactos
        System.out.println("Contactos en la agenda:");
        agenda.mostrarContactos();

        // Buscar un contacto específico
        System.out.println("\nBuscando el contacto de Scarlett Johansson:");
        Contacto contacto = agenda.buscarContacto("Scarlett Johansson");
        if (contacto != null) {
            System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono() + ", Correo: " + contacto.getCorreoElectronico() + ", Fecha de Nacimiento: " + contacto.getFechaNacimiento());
        } else {
            System.out.println("Contacto no encontrado.");
        }

        // Eliminar contactos específicos
        System.out.println("\nEliminando el contacto de Chris Hemsworth.");
        agenda.eliminarContacto("Chris Hemsworth");
        System.out.println("Eliminando el contacto de Robert Downey Jr.");
        agenda.eliminarContacto("Robert Downey Jr.");

        // Mostrar contactos después de la eliminación
        System.out.println("\nContactos en la agenda después de eliminar a Chris Hemsworth y Robert Downey Jr.:");
        agenda.mostrarContactos();
    }
}
