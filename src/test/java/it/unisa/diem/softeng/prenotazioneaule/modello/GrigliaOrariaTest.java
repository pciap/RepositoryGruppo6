/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Suite di unit test per la classe {@link GrigliaOraria}.
 *
 * Verifica il riconoscimento dei giorni lavorativi (lunedì-venerdì),
 * il controllo di appartenenza degli orari alla griglia 8:30-18:30
 * e la generazione delle 10 fasce orarie giornaliere.
 *
 * @author anton
 */
public class GrigliaOrariaTest {

    private static final LocalDate LUNEDI = LocalDate.of(2026, 11, 30);
    private static final LocalDate VENERDI = LocalDate.of(2026, 12, 4);
    private static final LocalDate SABATO = LocalDate.of(2026, 12, 5);
    private static final LocalDate DOMENICA = LocalDate.of(2026, 12, 6);

    /**
     * @brief Verifica che i giorni dal lunedì al venerdì siano prenotabili (ED3.4).
     */
    @Test
    public void testGiorniPrenotabili() {
        assertTrue(GrigliaOraria.isGiornoPrenotabile(LUNEDI));
        assertTrue(GrigliaOraria.isGiornoPrenotabile(LocalDate.of(2026, 12, 2)));
        assertTrue(GrigliaOraria.isGiornoPrenotabile(VENERDI));
    }

    /**
     * @brief Verifica che sabato e domenica non siano prenotabili (ED3.4).
     */
    @Test
    public void testGiorniNonPrenotabili() {
        assertFalse(GrigliaOraria.isGiornoPrenotabile(SABATO));
        assertFalse(GrigliaOraria.isGiornoPrenotabile(DOMENICA));
    }

    /**
     * @brief Verifica che data null sollevi IllegalArgumentException (IU3.1).
     */
    @Test
    public void testIsGiornoPrenotabileNull() {
        assertThrows(IllegalArgumentException.class,
                () -> GrigliaOraria.isGiornoPrenotabile(null));
    }

    /**
     * @brief Verifica che gli orari validi sulla griglia 8:30..18:30 siano accettati (ED3.5).
     */
    @Test
    public void testOrariSullaGriglia() {
        assertTrue(GrigliaOraria.isSullaGriglia(LocalTime.of(8, 30)));
        assertTrue(GrigliaOraria.isSullaGriglia(LocalTime.of(13, 30)));
        assertTrue(GrigliaOraria.isSullaGriglia(LocalTime.of(18, 30)));
    }

    /**
     * @brief Verifica che gli orari fuori fascia o con minuti/secondi diversi siano rifiutati (ED3.5).
     */
    @Test
    public void testOrariFuoriGriglia() {
        assertFalse(GrigliaOraria.isSullaGriglia(LocalTime.of(7, 30)));
        assertFalse(GrigliaOraria.isSullaGriglia(LocalTime.of(19, 30)));
        assertFalse(GrigliaOraria.isSullaGriglia(LocalTime.of(9, 0)));
        assertFalse(GrigliaOraria.isSullaGriglia(LocalTime.of(9, 15)));
        assertFalse(GrigliaOraria.isSullaGriglia(LocalTime.of(9, 30, 1)));
    }

    /**
     * @brief Verifica che ora null sollevi IllegalArgumentException (IU3.1).
     */
    @Test
    public void testIsSullaGrigliaNull() {
        assertThrows(IllegalArgumentException.class,
                () -> GrigliaOraria.isSullaGriglia(null));
    }

    /**
     * @brief Verifica che fasceDelGiorno restituisca le 10 fasce orarie da 1 ora nell'ordine corretto (IU2.3).
     */
    @Test
    public void testFasceDelGiornoValide() {
        List<FasciaOraria> fasce = GrigliaOraria.fasceDelGiorno(LUNEDI);

        assertEquals(10, fasce.size());
        assertEquals(new FasciaOraria(LUNEDI, LocalTime.of(8, 30), LocalTime.of(9, 30)), fasce.get(0));
        assertEquals(new FasciaOraria(LUNEDI, LocalTime.of(17, 30), LocalTime.of(18, 30)), fasce.get(9));
        assertThrows(UnsupportedOperationException.class, () -> fasce.clear());
    }

    /**
     * @brief Verifica che fasceDelGiorno rifiuti null o giorni nel fine settimana (ED3.4, IU3.1).
     */
    @Test
    public void testFasceDelGiornoNonValide() {
        assertThrows(IllegalArgumentException.class,
                () -> GrigliaOraria.fasceDelGiorno(null));
        assertThrows(IllegalArgumentException.class,
                () -> GrigliaOraria.fasceDelGiorno(SABATO));
        assertThrows(IllegalArgumentException.class,
                () -> GrigliaOraria.fasceDelGiorno(DOMENICA));
    }
}