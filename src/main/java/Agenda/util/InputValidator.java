package Agenda.util;

import java.util.Scanner;

/**
 * Clase de utilidad para validar las entradas del usuario.
 */
public class InputValidator {

    public static final int NAME = 0;
    public static final int PHONE = 1;
    public static final int EMAIL = 2;


    /**
     * Comprueba que el nombre introducido está compuesto por letras y que se compone de una o varias palabras.
     * @param n La entrada a evaluar.
     * @return true si es válido, false si no lo es.
     */
    private static boolean isValidName(String n) {
        return n.matches("[A-Za-z]+( [A-Za-z]+)*");
    }

    /**
     * Comprueba que el teléfono introducido es un teléfono válido en España.
     * @param n Un teléfono en formato de 9 dígitos o con el prefijo +34.
     * @return true si el formato es correcto, false si no lo es.
     */
    private static boolean isValidPhone(String n) {
        return n.matches("^(\\+34|0034)?[6789]\\d{8}$");
    }

    /**
     * Comprueba si el mail introducido es válido.
     * @param s La entrada de mail a evaluar.
     * @return true si es correcto, false si no lo es.
     */
    private static boolean isValidEmail(String s) {
        return s.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    /**
     * Valida la entrada del usuario en función de la operación que se le pase.
     * @param operation La operación a realizar. Puede ser NAME, PHONE o EMAIL.
     * @param sc Un objeto Scanner.
     * @return La entrada validada.
     */
    public static String validate(int operation, Scanner sc) {
        String input = sc.nextLine();
        if (input.isBlank()) {
            return input;
        }
        switch (operation) {
            case NAME -> {
                while (!InputValidator.isValidName(input)) {
                    System.out.println("Introduzca un nombre válido");
                    input = sc.nextLine();
                }
            }
            case PHONE -> {
                while (!InputValidator.isValidPhone(input)) {
                    System.out.println("Introduzca un teléfono válido");
                    input = sc.nextLine();
                }
            }
            case EMAIL -> {
                while (!InputValidator.isValidEmail(input)) {
                    System.out.println("Introduzca un mail válido");
                    input = sc.nextLine();
                }
            }
        }
        return input;
    }
}
