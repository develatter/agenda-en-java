package Agenda.View;

import Agenda.model.ContactoDAO;
import Agenda.model.ListadoContactos;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase para agrupar toda los menús de la línea de comandos. Todos sus métodos son estáticos.
 *
 * @author Alejandro López Martínez.
 */
public class MenuCLI {

    //Separador para los menús.
    public static final String SEPARATOR = "----------------------------------------------------------------";


    /**
     * Muestra el menú principal de la agenda por línea de comandos.
     * Las opciones son:
     * 1. Añadir contacto
     * 2. Buscar contacto
     * 3. Mostrar todos los contactos
     * 4. Eliminar contacto
     * 5. Modificar contacto
     * 6. Eliminar todos los contactos
     * 7. Salir
     */
    public static void showMenu() {
        clearConsole();
        System.out.println("Elige una opción:");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Mostrar todos los contactos");
        System.out.println("4. Eliminar contacto");
        System.out.println("5. Modificar contacto");
        System.out.println("6. Eliminar todos los contactos");
        System.out.println("7. Salir");
        System.out.print(">> ");
    }


    /**
     * Limpia la salida de consola
     */
    public static void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Comprueba si la opción introducida por teclado es un número entero.
     *
     * @param s Un número en formato String.
     * @return Un número entero válido.
     */
    public static int optionFilter(String s) {
        int option = -1;

        try {
            option = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return option;
        }
        return option;
    }


    /**
     * Pregunta por una opción al usuario hasta que se ingresa un número válido, y posteriormente ejecuta una acción
     * u otra en función del número introducido.
     *
     * @param sc Un objeto Scanner.
     * @param ag Una ListaContactos con la información de la agenda.
     * @return 0 en caso de haber seleccionado "Salir", 1 en caso contrario.
     */
    public static int selectOption(Scanner sc, ListadoContactos ag) {
        int option = 0;
        while (option < 1 || option > 7) {
            option = optionFilter(sc.nextLine());
            if (option < 1 || option > 7) {
                System.out.println("Introduce una opción válida");
            }
        }
        switch (option) {
            case 1 -> ContactoDAO.add(ag, sc);
            case 2 -> ContactoDAO.search(ag, sc);
            case 3 -> ContactoDAO.showAll(ag);
            case 4 -> ContactoDAO.delete(ag, sc);
            case 5 -> ContactoDAO.update(ag, sc);
            case 6 -> ag.removeAllContactos();
            case 7 -> {
                return 0;
            }
        }
        return 1;
    }


    /**
     * Muestra la lista de contactos con su ID y devuelve el número entero correspondiente al elegido por el usuario
     * si este es válido.
     *
     * @param a  La ListaContactos con la información de la agenda.
     * @param sc Un objeto Scanner.
     * @return Un número entero válido correspondiente a un usario existente.
     */
    public static int selectContact(ListadoContactos a, Scanner sc) {
        int option = -1;
        String input = "";
        System.out.print("ID\t|");
        printHeader();

        for (Long k : a.getMap().keySet()) {
            System.out.println(SEPARATOR);
            System.out.println(k.toString() + "\t|\t" + a.getMap().get(k));
        }

        while (option < 1 || input.equalsIgnoreCase("N")) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("N")) {
                return -1;
            }
            option = optionFilter(input);
            if (!a.getMap().containsKey((long) option)) {
                System.out.println("Introduce un ID válido");
                option = -1;
                sc.nextLine();
            }
        }
        return option;
    }


    /**
     * Imprime la cabecera de la tabla de contactos.
     */
    public static void printHeader() {
        System.out.println("\tNombre\t|\tTeléfono\t|\t\tEmail\t\t|\tDescripción ");
    }
}
