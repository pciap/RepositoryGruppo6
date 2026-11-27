/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Suite di unit test per la classe {@link FasciaOraria}.
 *
 * Verifica la corretta creazione delle fasce orarie sulla griglia settimanale,
 * il rifiuto dei giorni nel fine settimana o orari non validi, la gestione dei
 * parametri null, il controllo delle sovrapposizioni tra intervalli e i metodi
 * di utilità (conData, equals, hashCode, toString).
 *
 * @author anton
 */
public class FasciaOrariaTest {

    private static final LocalDate LUNEDI = LocalDate.of(2026, 11, 30);
    private static final LocalDate MARTEDI = LocalDate.of(2026, 12, 1);
    private static final LocalDate SABATO = LocalDate.of(2026, 12, 5);
    private static final LocalDate DOMENICA = LocalDate.of(2026, 12, 6);

    /** Istanza valida di fascia oraria utilizzata come base nei test. */
    private FasciaOraria fasciaBase;

    /**
     * @brief Inizializza una fascia oraria valida prima di ogni test.
     */
    @BeforeEach
    public void setUp() {
        fasciaBase = new FasciaOraria(LUNEDI, LocalTime.of(9, 30), LocalTime.of(11, 30));
    }

    /**
     * @brief Verifica che il costruttore crei correttamente fasce valide (ED3.4, ED3.5).
     */
    @Test
    public void testCostruttoreValido() {
        assertEquals(LUNEDI, fasciaBase.getData());
        assertEquals(LocalTime.of(9, 30), fasciaBase.getInizio());
        assertEquals(LocalTime.of(11, 30), fasciaBase.getFine());

        FasciaOraria interaGiornata = new FasciaOraria(LUNEDI, LocalTime.of(8, 30), LocalTime.of(18, 30));
        assertEquals(LocalTime.of(8, 30), interaGiornata.getInizio());
        assertEquals(LocalTime.of(18, 30), interaGiornata.getFine());
    }

    /**
     * @brief Verifica che vengano rifiutate fasce nel fine settimana (ED3.4).
     */
    @Test
    public void testGiorniNonPrenotabili() {
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(SABATO, LocalTime.of(9, 30), LocalTime.of(11, 30)));
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(DOMENICA, LocalTime.of(9, 30), LocalTime.of(11, 30)));
    }

    /**
     * @brief Verifica che vengano rifiutati orari fuori griglia o con fine non successiva all'inizio (ED3.5).
     */
    @Test
    public void testOrariFuoriGrigliaNonValidi() {
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(LUNEDI, LocalTime.of(9, 0), LocalTime.of(10, 30)));
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(LUNEDI, LocalTime.of(7, 30), LocalTime.of(9, 30)));
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(LUNEDI, LocalTime.of(17, 30), LocalTime.of(19, 30)));
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(LUNEDI, LocalTime.of(10, 30), LocalTime.of(10, 30)));
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(LUNEDI, LocalTime.of(11, 30), LocalTime.of(9, 30)));
    }

    /**
     * @brief Verifica che venga sollevata IllegalArgumentException se uno dei parametri è null (IU3.1).
     */
    @Test
    public void testParametriNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(null, LocalTime.of(9, 30), LocalTime.of(11, 30)));
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(LUNEDI, null, LocalTime.of(11, 30)));
        assertThrows(IllegalArgumentException.class,
                () -> new FasciaOraria(LUNEDI, LocalTime.of(9, 30), null));
    }

    /**
     * @brief Verifica il calcolo delle sovrapposizioni tra fasce orarie (FI3.6, FI3.7).
     */
    @Test
    public void testSovrapposizioni() {
        FasciaOraria f1 = new FasciaOraria(LUNEDI, LocalTime.of(10, 30), LocalTime.of(12, 30));
        FasciaOraria f2 = new FasciaOraria(LUNEDI, LocalTime.of(11, 30), LocalTime.of(13, 30));
        assertTrue(f1.siSovrapponeA(f2));
        assertTrue(f2.siSovrapponeA(f1));

        FasciaOraria consecutiva = new FasciaOraria(LUNEDI, LocalTime.of(12, 30), LocalTime.of(14, 30));
        assertFalse(f1.siSovrapponeA(consecutiva));

        FasciaOraria grande = new FasciaOraria(LUNEDI, LocalTime.of(8, 30), LocalTime.of(12, 30));
        assertTrue(grande.siSovrapponeA(fasciaBase));

        FasciaOraria altroGiorno = new FasciaOraria(MARTEDI, LocalTime.of(10, 30), LocalTime.of(12, 30));
        assertFalse(f1.siSovrapponeA(altroGiorno));
    }

    /**
     * @brief Verifica la creazione immutabile di una nuova fascia in una data diversa (FI3.5).
     */
    @Test
    public void testConData() {
        FasciaOraria suMartedi = fasciaBase.conData(MARTEDI);
        assertEquals(MARTEDI, suMartedi.getData());
        assertEquals(fasciaBase.getInizio(), suMartedi.getInizio());
        assertEquals(LUNEDI, fasciaBase.getData());

        assertThrows(IllegalArgumentException.class, () -> fasciaBase.conData(SABATO));
    }

    /**
     * @brief Verifica il funzionamento di equals, hashCode e la formattazione toString (IU2.2).
     */
    @Test
    public void testEqualsHashCodeToString() {
        FasciaOraria copia = new FasciaOraria(LUNEDI, LocalTime.of(9, 30), LocalTime.of(11, 30));
        assertEquals(fasciaBase, copia);
        assertEquals(fasciaBase.hashCode(), copia.hashCode());
        assertEquals("30/11/2026 09:30-11:30", fasciaBase.toString());
    }
}