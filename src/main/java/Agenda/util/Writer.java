package Agenda.util;

import Agenda.model.ListadoContactos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Clase para escribir en un fichero los contactos de un ListadoContactos.
 */
public class Writer {

    /**
     * Escribe en un fichero los contactos de un ListadoContactos.
     * @param ls El ListadoContactos a escribir.
     * @param file El fichero en el que escribir.
     */
    public static void write(ListadoContactos ls, File file) {
        if (file.canWrite()) {
            try (var oos = new ObjectOutputStream(
                    new FileOutputStream(file))) {
                oos.writeObject(ls.getAllContactos());
            } catch (IOException e) {
                System.out.println("Error al escribir en el fichero: " + e.getMessage());
            }
        }
    }
}
