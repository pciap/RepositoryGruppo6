package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @brief Intervallo orario prenotabile in una data specifica (ED3.4, ED3.5).
 * @author Gruppo 6
 */
public final class FasciaOraria implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate data;
    private final LocalTime inizio;
    private final LocalTime fine;

    /**
     * @brief Crea una fascia oraria valida sulla griglia settimanale (ED3.4, ED3.5).
     * @param data giorno della prenotazione, dal lunedì al venerdì
     * @param inizio ora di inizio sulla griglia (8:30..18:30)
     * @param fine ora di fine sulla griglia, successiva a inizio
     * @throws IllegalArgumentException se un campo è null, il giorno è nel weekend,
     *         gli orari sono fuori griglia o fine non è successiva a inizio
     */
    public FasciaOraria(LocalDate data, LocalTime inizio, LocalTime fine) {
        this.data = Controlli.richiediPresente(data, "data");
        this.inizio = Controlli.richiediPresente(inizio, "ora di inizio");
        this.fine = Controlli.richiediPresente(fine, "ora di fine");

        if (!GrigliaOraria.isGiornoPrenotabile(data)) {
            throw new IllegalArgumentException("Le prenotazioni sono possibili solo dal lunedì al venerdì");
        }
        if (!GrigliaOraria.isSullaGriglia(inizio)) {
            throw new IllegalArgumentException("L'ora di inizio deve essere sulla griglia 8:30, 9:30, ... 18:30");
        }
        if (!GrigliaOraria.isSullaGriglia(fine)) {
            throw new IllegalArgumentException("L'ora di fine deve essere sulla griglia 8:30, 9:30, ... 18:30");
        }
        if (!fine.isAfter(inizio)) {
            throw new IllegalArgumentException("L'ora di fine deve essere successiva all'ora di inizio");
        }
    }

    /**
     * @brief Restituisce la data della fascia.
     * @return la data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * @brief Restituisce l'ora di inizio.
     * @return ora di inizio
     */
    public LocalTime getInizio() {
        return inizio;
    }

    /**
     * @brief Restituisce l'ora di fine.
     * @return ora di fine
     */
    public LocalTime getFine() {
        return fine;
    }

    /**
     * @brief Verifica se questa fascia si sovrappone a un'altra (FI3.6, FI3.7).
     *        Due fasce consecutive (es. 10:30-12:30 e 12:30-14:30) non si sovrappongono.
     * @param altra altra fascia oraria da confrontare
     * @return true se cadono nello stesso giorno e gli intervalli si intersecano
     * @throws IllegalArgumentException se altra è null
     */
    public boolean siSovrapponeA(FasciaOraria altra) {
        Controlli.richiediPresente(altra, "fascia oraria");
        return this.data.equals(altra.data)
                && this.inizio.isBefore(altra.fine)
                && altra.inizio.isBefore(this.fine);
    }

    /**
     * @brief Crea una nuova fascia con gli stessi orari in una diversa data (FI3.5).
     * @param nuovaData nuova data lavorativa
     * @return nuova istanza di FasciaOraria
     * @throws IllegalArgumentException se nuovaData è null o cade nel fine settimana
     */
    public FasciaOraria conData(LocalDate nuovaData) {
        return new FasciaOraria(nuovaData, this.inizio, this.fine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, inizio, fine);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final FasciaOraria other = (FasciaOraria) obj;
        return Objects.equals(this.data, other.data)
                && Objects.equals(this.inizio, other.inizio)
                && Objects.equals(this.fine, other.fine);
    }

    @Override
    public String toString() {
        return data.format(FORMATO_DATA) + " " + inizio + "-" + fine;
    }
}