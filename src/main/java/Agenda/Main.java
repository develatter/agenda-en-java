package Agenda;

import java.io.*;
import java.util.Scanner;

import Agenda.View.MenuCLI;
import Agenda.model.Contacto;
import Agenda.model.ListadoContactos;
import Agenda.util.Reader;
import Agenda.util.Writer;

/**
 * Clase principal de la aplicación. Contiene el main.
 * Se encarga de leer y escribir en el fichero y de mostrar el menú.
 * <p>
 * Este programa sirve para agendar contactos e incluye las siguientes funcionalidades:
 * - Añadir contacto
 * - Buscar contacto
 * - Mostrar todos los contactos
 * - Eliminar contacto
 * - Modificar contacto
 * - Eliminar todos los contactos
 * - Salir
 *
 * @author Alejandro López Martínez.
 * @version 1.0
 * @since 1.0
 */
public class Main {
    public static File fichero = new File("file.obj");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListadoContactos agenda = null;

        if (!fichero.exists()) {
            System.out.println("Creando fichero...");
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el fichero");
                return;
            }
        }
        agenda = Reader.read(fichero);
        if (agenda == null) {
            System.out.println("El fichero aún no tiene contactos");
            agenda = new ListadoContactos(new Contacto[0]);
        }

        int option = -1;
        do {
            MenuCLI.showMenu();
            option = MenuCLI.selectOption(sc, agenda);
            Writer.write(agenda, fichero);
            System.out.print("Pulsa cualquier tecla para continuar...");
            sc.nextLine();
        } while (option > 0);
        sc.close();
    }
}