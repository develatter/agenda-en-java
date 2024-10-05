package Agenda.model;
import Agenda.View.MenuCLI;
import Agenda.util.InputValidator;

import java.util.Scanner;

public class ContactoDAO {

    /**
     * Añade un contacto a la lista de contactos.
     * @param a La lista de contactos.
     * @param sc Un objeto Scanner.
     */
    public static void add(ListadoContactos a, Scanner sc) {
        String nombre = "";
        String telefono = "";
        String descripcion = "";
        String email = "";

        System.out.println("Introduzca el nombre del contacto: ");

        nombre = InputValidator.validate(InputValidator.NAME, sc);
        while (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío");
            nombre = InputValidator.validate(InputValidator.NAME, sc);
        }

        System.out.println("Introduzca el teléfono del contacto: ");
        telefono = InputValidator.validate(InputValidator.PHONE, sc);

        System.out.println("Introduzca el email del contacto: ");
        email = InputValidator.validate(InputValidator.EMAIL, sc);

        System.out.println("Notas adicionales: ");
        descripcion = sc.nextLine();

        if (a.addContacto(new Contacto(
                nombre,
                telefono,
                descripcion,
                email
        ))) {
            System.out.println("Contacto añadido satisfactoriamente");
        }else {
            System.out.println("El contacto no ha podido ser añadido");
        };
    }

    /**
     * Permite buscar un contacto en la agenda por nombre, el cual será pedido por este método.
     * Imprimirá todos los contactos con nombre coincidente.
     * @param a La lista en la que se buscará el contacto
     * @param sc Un objeto Scanner para pedir el nombre del contacto
     */
    public static void search(ListadoContactos a, Scanner sc) {
        System.out.println("Introduzca el nombre del contacto: ");
        String nombre = sc.nextLine();
        MenuCLI.printHeader();
        for (var c : a.getContactos(nombre)) {
            System.out.println(MenuCLI.SEPARATOR);
            System.out.println(c);
        }
    }

    /**
     * Muestra todos los contactos de la lista.
     * @param a La lista de contactos.
     */
    public static void showAll(ListadoContactos a) {
        Contacto[] contactos = a.getAllContactos();
        MenuCLI.printHeader();
        for (var c : contactos) {
            System.out.println(MenuCLI.SEPARATOR);
            System.out.println(c);
        }
    }

    /**
     * Elimina un contacto de la lista.
     * @param a La lista de contactos.
     * @param sc Un objeto Scanner.
     */
    public static void delete(ListadoContactos a, Scanner sc) {
        System.out.println("Seleccione un contacto para borrar (n/N para salir): ");

        int id = MenuCLI.selectContact(a, sc);
        if (id == -1) {
            System.out.println("Se ha cancelado la eliminación");
            return;
        }
        System.out.println("¿Estás seguro de que quieres eliminar el contacto? (S/N)");
        String confirm = sc.nextLine();
        if (!confirm.equalsIgnoreCase("S")) {
            return;
        }
        if (a.removeContacto((long) id)) {
            System.out.println("Contacto eliminado");
        }

    }

    /**
     * Actualiza un contacto de la lista.
     * @param a La lista de contactos.
     * @param sc Un objeto Scanner.
     */
    public static void update(ListadoContactos a , Scanner sc) {
        System.out.println("Seleccione un contacto para modificar (n/N para cancelar): ");
        int id = MenuCLI.selectContact(a, sc);
        if (id == -1) {
            System.out.println("Se ha cancelado la modificación");
            return;
        }
        Contacto contacto = a.getContacto((long)id);
        System.out.println("Introduzca el nuevo nombre (Dejar en blanco para ignorar): ");
        String nombre = InputValidator.validate(InputValidator.NAME, sc);
        contacto.setName(
                (nombre.isEmpty()) ? contacto.getName() : nombre
        );

        System.out.println("Introduzca el nuevo teléfono (Dejar en blanco para ignorar): ");
        String telefono = InputValidator.validate(InputValidator.PHONE, sc);
        contacto.setPhone(
                (telefono.isEmpty()) ? contacto.getPhone() : telefono
        );

        System.out.println("Introduzca el nuevo email (Dejar en blanco para ignorar): ");
        String email = InputValidator.validate(InputValidator.EMAIL, sc);
        contacto.setEmail(
                (email.isEmpty()) ? contacto.getEmail() : email
        );

        System.out.println("Introduzca la nueva descripción (Dejar en blanco para ignorar): ");
        String descripcion = sc.nextLine();
        contacto.setDescription(
                (descripcion.isEmpty()) ? contacto.getDescription() : descripcion
        );

        System.out.println("¿Estás seguro de que quieres modificar el contacto? (S/N)");
        String confirm = sc.nextLine();
        if (!confirm.equalsIgnoreCase("S")) {
            return;
        }

        a.updateContacto((long)id, contacto);
        System.out.println("Contacto actualizado");
    }

}
