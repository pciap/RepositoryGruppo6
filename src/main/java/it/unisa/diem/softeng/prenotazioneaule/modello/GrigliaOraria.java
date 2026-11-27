/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @brief Definisce la griglia settimanale e oraria prenotabile (ED3.4, ED3.5).
 * @author anton
 */
public final class GrigliaOraria {

    public static final LocalTime ORA_APERTURA = LocalTime.of(8, 30);
    public static final LocalTime ORA_CHIUSURA = LocalTime.of(18, 30);

    private GrigliaOraria() {
    }

    /**
     * @brief Verifica se la data cade in un giorno lavorativo (dal lunedì al venerdì) (ED3.4).
     * @param data data da controllare
     * @return true se dal lunedì al venerdì
     * @throws IllegalArgumentException se data è null
     */
    public static boolean isGiornoPrenotabile(LocalDate data) {
        Controlli.richiediPresente(data, "data");
        DayOfWeek giorno = data.getDayOfWeek();
        return giorno != DayOfWeek.SATURDAY && giorno != DayOfWeek.SUNDAY;
    }

    /**
     * @brief Verifica se un orario appartiene alla griglia 8:30, 9:30, ..., 18:30 (ED3.5).
     * @param ora orario da controllare
     * @return true se minuto == 30 e ora compresa tra 8 e 18
     * @throws IllegalArgumentException se ora è null
     */
    public static boolean isSullaGriglia(LocalTime ora) {
        Controlli.richiediPresente(ora, "ora");
        return ora.getMinute() == 30
                && ora.getSecond() == 0
                && ora.getNano() == 0
                && !ora.isBefore(ORA_APERTURA)
                && !ora.isAfter(ORA_CHIUSURA);
    }

    /**
     * @brief Restituisce le 10 fasce orarie da 1 ora di un giorno lavorativo (IU2.3).
     * @param data giorno lavorativo
     * @return lista non modificabile delle 10 fasce (8:30-9:30 ... 17:30-18:30)
     * @throws IllegalArgumentException se data è null o nel fine settimana
     */
    public static List<FasciaOraria> fasceDelGiorno(LocalDate data) {
        if (!isGiornoPrenotabile(data)) {
            throw new IllegalArgumentException("Le prenotazioni sono possibili solo dal lunedì al venerdì");
        }
        List<FasciaOraria> fasce = new ArrayList<>();
        for (int ora = 8; ora < 18; ora++) {
            fasce.add(new FasciaOraria(data, LocalTime.of(ora, 30), LocalTime.of(ora + 1, 30)));
        }
        return Collections.unmodifiableList(fasce);
    }
}