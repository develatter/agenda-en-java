package Agenda.model;

import java.io.Serializable;

public class Contacto implements Serializable {
    private String name;
    private String phone;
    private String description;
    private String email;

    /**
     * Constructor de la clase Contacto.
     * @param name Nombre del contacto
     * @param phone Teléfono del contacto
     * @param description Descripción del contacto
     * @param email Correo electrónico del contacto
     */
    public Contacto(String name, String phone, String description, String email) {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.email = email;
    }


    /**
     * Actualiza la información de un contacto. A efectos prácticos, lo clona en profundidad.
     * @param c Un objeto Contacto con la información actualizada.
     */
    public void updateContact(Contacto c) {
        this.name = c.name;
        this.phone = c.phone;
        this.description = c.description;
        this.email = c.email;
    }

    /**
     * Devuelve un hashcode generado con los datos del Contacto.
     * @return El hashcode con los datos de contacto.
     */
    @Override
    public int hashCode() {
        return name.hashCode()
                + phone.hashCode()
                + description.hashCode()
                + email.hashCode();
    }


    /**
     * Compara dos objetos Contacto. Serán iguales si son idénticos aunque tengan distinto id.
     * @param object El objeto Contacto a comparar.
     * @return true si son dos objetos idénticos, salvo en ID.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Contacto) {
            Contacto c = (Contacto) object;
            return this.description.equals(c.description) &&
                    this.name.equals(c.name) &&
                    this.phone.equals(c.phone) &&
                    this.email.equals(c.email);
        }
        return false;
    }


    //Setters y Getters

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    /**
     * Devuelve una cadena de texto con los datos del contacto.
     * @return Una cadena de texto con los datos del contacto.
     */
    public String toString() {
        return name + "\t|\t" +
                phone + "\t|\t" +
                email + "\t|\t" +
                description;
    }

}
