/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @brief Rappresenta un docente del dipartimento universitario (ED2).
 * @author anton
 */
public class Docente  implements Serializable{

    /**
     * @brief Ordina i docenti per cognome e, a parità di cognome, per nome (FI2.4), senza distinguere maiuscole e minuscole.
     */
    private static final long serialVersionUID = 1L;
    public static final Comparator<Docente> ORDINE_ALFABETICO
            = Comparator.comparing(Docente::getCognome, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(Docente::getNome, String.CASE_INSENSITIVE_ORDER);
    
    private String email;
    private String nome;
    private String cognome;

    /**
     * @brief Crea un docente.
     * @param email e-mail istituzionale @unisa.it
     * @param nome nome del docente, non vuoto
     * @param cognome cognome del docente, non vuoto
     * @throws IllegalArgumentException se un dato non è valido
     * @post il docente ha i dati indicati (l'e-mail è salvata ripulita e in minuscolo)
     */
    public Docente(String email, String nome, String cognome) {
        this.email = Controlli.richiediEmailUnisa(email);
        this.nome = Controlli.richiediTesto(nome, "nome");
        this.cognome = Controlli.richiediTesto(cognome, "cognome");
    }

    /**
     * @brief Restituisce l'e-mail del docente.
     * @return l'e-mail istituzionale
     */
    public String getEmail() {
        return email;
    }

    /**
     * @brief Modifica l'e-mail del docente (UC6).
     * @param email nuova e-mail istituzionale @unisa.it
     * @throws IllegalArgumentException se l'e-mail non è valida; in tal caso resta quella di prima
     */
    public void setEmail(String email) {
        this.email = Controlli.richiediEmailUnisa(email);
    }

    /**
     * @brief Restituisce il nome del docente.
     * @return il nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @brief Modifica il nome del docente.
     * @param nome nuovo nome, non vuoto
     * @throws IllegalArgumentException se il nome è vuoto
     */
    public void setNome(String nome) {
        this.nome = Controlli.richiediTesto(nome, "nome");
    }

    /**
     * @brief Restituisce il cognome del docente.
     * @return il cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @brief Modifica il cognome del docente.
     * @param cognome nuovo cognome, non vuoto
     * @throws IllegalArgumentException se il cognome è vuoto
     */
    public void setCognome(String cognome) {
        this.cognome = Controlli.richiediTesto(cognome, "cognome");
    }

    /**
     * @brief Restituisce il nome per elenchi e menu a tendina.
     * @return una stringa nel formato "Cognome Nome"
     */
    public String getNomeCompleto() {
        return cognome + " " + nome;
    }
}
