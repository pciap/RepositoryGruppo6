package it.unisa.diem.softeng.prenotazioneaule.modello;

/**
 *
 * @author paolo
 * @brief rappresenta la collezione di Tipi di attività per i quali possono
 * essere effettuate prenotazioni, realizza requisito ED3.3
 */
public enum TipoAttivita {

    LEZIONE("Lezione"), ESAME("Esame"), SEMINARIO("Seminario"), ALTRO("Altro");
    private final String etichetta;

    /**
     * @brief associa etichetta alla costante
     * @param etichetta indica il tipo di attività
     */
    TipoAttivita(String etichetta) {
        this.etichetta = etichetta;
    }

    /**
     * @brief restituisce il tipo di attività tramite il campo String etichetta
     * @return restituisce il campo etichetta
     */
    public String getEtichetta() {

        return this.etichetta;

    }

    /**
     * @brief restituisce etichetta tramite toString()
     * @return restituisce il campo etichetta tramite toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getEtichetta());
        return sb.toString();

    }

}
