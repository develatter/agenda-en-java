package Agenda.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ListadoContactos {

    private HashMap<Long, Contacto> contactos;
    private static Long count = 0L;

    /**
     * Constructor de la clase ListadoContactos. Representa el modelo de la agenda.
     *
     * @param arrayContactos Un array de contactos. Este es obtenido leyendo un fichero, y se transforma
     *                       en un HashMap para indexar los contactos y acceder rápidamente a ellos
     */
    public ListadoContactos(Contacto[] arrayContactos) {
        contactos = new HashMap<Long, Contacto>();
        for (var contacto : arrayContactos) {
            this.contactos.put(++count, contacto);
        }
    }

    /**
     * Añade un Contacto al listado.
     *
     * @param contacto El contacto a añadir.
     * @return true si se ha añadido, false si ya existía.
     */
    public boolean addContacto(Contacto contacto) {
        if (contactos.containsValue(contacto)) {
            return false;
        }
        this.contactos.put(++count, contacto);
        return true;
    }

    /**
     * Devuelve un ArrayList con los contactos de la agenda cuyo nombre empiece por la cadena de texto introducida
     * por parámetro.
     *
     * @param nombre La cadena de texto que vamos a comparar.
     * @return Un ArrayList con los contactos.
     */
    public ArrayList<Contacto> getContactos(String nombre) {
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        for (Contacto contacto : contactos.values()) {
            if (contacto.getName().toLowerCase().startsWith(nombre.toLowerCase())) {
                listaContactos.add(contacto);
            }
        }
        if (listaContactos.isEmpty()) {
            System.out.println("No se ha encontrado ningún contacto");
        }
        return listaContactos;
    }


    /**
     * Devuelve un contacto a partir de su ID.
     *
     * @param id El ID del contacto a buscar.
     * @return El contacto si se ha encontrado, null si no.
     */
    public Contacto getContacto(Long id) {
        return contactos.get(id);
    }

    /**
     * Devuelve un array con todos los contactos de la agenda.
     *
     * @return Un array con los contactos.
     */
    public Contacto[] getAllContactos() {
        return contactos.values().toArray(new Contacto[0]);
    }

    /**
     * Devuelve el HashMap con los contactos y sus respectivos ID
     *
     * @return Un HashMap con los contactos.
     */
    public HashMap<Long, Contacto> getMap() {
        return contactos;
    }

    /**
     * Elimina el contacto
     *
     * @param id El ID del contacto a eliminar
     * @return true si se ha eliminado, false si no se ha encontrado.
     */
    public boolean removeContacto(Long id) {
        if (contactos.containsKey(id)) {
            contactos.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Actualiza un contacto
     *
     * @param id       El ID del contacto a actualizar
     * @param contacto El contacto con la información actualizada.
     */
    public void updateContacto(Long id, Contacto contacto) {
        Contacto c = getContacto(id);
        if (c != null) {
            c.updateContact(contacto);
        }
    }

    /**
     * Elimina todos los contactos de la agenda.
     */
    public void removeAllContactos() {
        contactos.clear();
        count = 0L;
        System.out.println("Todos los contactos han sido eliminados");
    }


}
