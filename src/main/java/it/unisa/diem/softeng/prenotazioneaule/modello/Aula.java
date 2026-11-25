package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.util.Comparator;

/**
 * @brief Aula del dipartimento che si può prenotare (ED1.1).
 *
 * Ha un codice univoco, un nome, un edificio, un piano e una capienza.
 * I dati sono sempre validi: costruttore e setter rifiutano valori non ammessi.
 *
 * @author paolo
 */
public class Aula {

    private String codice;
    private String nome;
    private String edificio;
    private int piano;
    private int capienza;

    /**
     * @brief Ordina le aule per codice (FI1.4).
     */
    public static final Comparator<Aula> ORDINE_PER_CODICE = Comparator.comparing(Aula::getCodice);

    /**
     * @brief Crea un'aula con i dati indicati.
     * @param codice codice identificativo dell'aula, non vuoto
     * @param nome nome dell'aula, non vuoto
     * @param edificio edificio in cui si trova l'aula, non vuoto
     * @param piano piano dell'aula: 0 piano terra, negativo per gli interrati (ED1.4)
     * @param capienza numero di posti, maggiore di zero (ED1.3)
     * @throws IllegalArgumentException se codice, nome o edificio sono vuoti
     *         oppure se la capienza non è maggiore di zero
     */
    public Aula(String codice, String nome, String edificio, int piano, int capienza) {
        this.codice = Controlli.richiediTesto(codice, "codice");
        this.nome = Controlli.richiediTesto(nome, "nome");
        this.edificio = Controlli.richiediTesto(edificio, "edificio");
        this.piano = piano;
        this.capienza = Controlli.richiediPositivo(capienza, "capienza");
    }

    /**
     * @brief Restituisce il codice dell'aula.
     * @return il codice, univoco tra le aule dell'archivio
     */
    public String getCodice() {
        return codice;
    }

    /**
     * @brief Restituisce il nome dell'aula.
     * @return il nome dell'aula
     */
    public String getNome() {
        return nome;
    }

    /**
     * @brief Restituisce l'edificio in cui si trova l'aula.
     * @return il nome dell'edificio
     */
    public String getEdificio() {
        return edificio;
    }

    /**
     * @brief Restituisce il piano dell'aula.
     * @return il piano: 0 piano terra, negativo per gli interrati
     */
    public int getPiano() {
        return piano;
    }

    /**
     * @brief Restituisce la capienza dell'aula.
     * @return il numero di posti, sempre maggiore di zero
     */
    public int getCapienza() {
        return capienza;
    }

    /**
     * @brief Modifica il codice dell'aula (UC2).
     *
     * L'unicità del codice tra le aule non si controlla qui ma in GestoreAule,
     * perché richiede di conoscere tutto l'archivio (ED1.2).
     * @param codice il nuovo codice, non vuoto
     * @throws IllegalArgumentException se il codice è vuoto
     */
    public void setCodice(String codice) {
        this.codice = Controlli.richiediTesto(codice, "codice");
    }

    /**
     * @brief Modifica il nome dell'aula (UC2).
     * @param nome il nuovo nome, non vuoto
     * @throws IllegalArgumentException se il nome è vuoto
     */
    public void setNome(String nome) {
        this.nome = Controlli.richiediTesto(nome, "nome");
    }

    /**
     * @brief Modifica l'edificio dell'aula (UC2).
     * @param edificio il nuovo edificio, non vuoto
     * @throws IllegalArgumentException se l'edificio è vuoto
     */
    public void setEdificio(String edificio) {
        this.edificio = Controlli.richiediTesto(edificio, "edificio");
    }

    /**
     * @brief Modifica il piano dell'aula (UC2).
     * @param piano il nuovo piano: 0 piano terra, negativo per gli interrati (ED1.4)
     */
    public void setPiano(int piano) {
        this.piano = piano;
    }

    /**
     * @brief Modifica la capienza dell'aula (UC2).
     * @param capienza il nuovo numero di posti, maggiore di zero (ED1.3)
     * @throws IllegalArgumentException se la capienza non è maggiore di zero
     */
    public void setCapienza(int capienza) {
        this.capienza = Controlli.richiediPositivo(capienza, "capienza");
    }
}
