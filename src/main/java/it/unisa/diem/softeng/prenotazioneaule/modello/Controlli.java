package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.util.regex.Pattern;

/**
 *
 * @author paolo
 */
final class Controlli {

    static final String DOMINIO_EMAIL = "@unisa.it";

    private Controlli() {

    }

    /**
     * @brief controlla che un campo sia sempre inserito
    *
     */
    static String richiediTesto(String valore, String campo) {
        if (valore == null || valore.trim().isEmpty()) {

            throw new IllegalArgumentException("Il campo " + campo + " è obbligatorio");

        }
        return valore.trim();
    }

    /**
     * @brief controlla che il valore del campo sia sempre positivo
     */

    static int richiediPositivo(int valore, String campo) {

        if (valore <= 0) {
            throw new IllegalArgumentException("Il campo " + campo + " deve contenere un numero POSITIVO.");
        }
        return valore;
    }

    /**
     * @brief controlla che il valore sia un'e-mail istituzionale @unisa.it
     * @param valore l'e-mail da controllare
     * @return l'e-mail ripulita e in minuscolo
     * @throws IllegalArgumentException se è vuota o non è un indirizzo @unisa.it
     */
    static String richiediEmailUnisa(String valore) {
        String email = richiediTesto(valore, "e-mail").toLowerCase();
        // almeno un carattere senza spazi né @, poi esattamente @unisa.it alla fine
        if (!email.matches("[^\\s@]+" + Pattern.quote(DOMINIO_EMAIL))) {
            throw new IllegalArgumentException(
                    "L'e-mail deve essere un indirizzo " + DOMINIO_EMAIL);
        }
        return email;
    }
    public static <T> T richiediPresente(T valore, String campo) {
        if (valore == null) {
            throw new IllegalArgumentException("Il campo " + campo + " è obbligatorio");
        }
        return valore;
    }
}
