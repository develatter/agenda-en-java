package Agenda.util;

import Agenda.model.Contacto;
import Agenda.model.ListadoContactos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Clase para leer un fichero y devolver un ListadoContactos con los contactos que contiene.
 */
public class Reader {
    /**
     * Lee un fichero y devuelve un ListadoContactos con los contactos que contiene.
     * @param file El fichero a leer.
     * @return Un ListadoContactos con los contactos del fichero.
     */
    public static ListadoContactos read(File file) {
        if (file.canRead()) {
            try (var ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                Object obj = ois.readObject();
                if (obj instanceof Contacto[]) {
                    return new ListadoContactos((Contacto[]) obj);
                } else {
                    System.out.println("El contenido del fichero no es válido.");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al leer el fichero: " + e.getMessage());
            }
        } else {
            return new ListadoContactos(new Contacto[1]);
        }
        return null;
    }
}
