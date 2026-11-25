package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Test della classe Aula: valori validi, valori rifiutati e ordinamento.
 *
 * @author paolo
 */
public class AulaTest {

    private Aula aula;

    @BeforeEach
    public void setUp() {
        aula = new Aula("P3", "Aula P3", "E", 1, 120);
    }

    /**
     * @brief Il costruttore conserva i valori passati.
     */
    @Test
    public void testCostruttoreConservaIValori() {
        assertEquals("P3", aula.getCodice());
        assertEquals("Aula P3", aula.getNome());
        assertEquals("E", aula.getEdificio());
        assertEquals(1, aula.getPiano());
        assertEquals(120, aula.getCapienza());
    }

    /**
     * @brief Un codice fatto solo di spazi viene rifiutato.
     */
    @Test
    public void testCodiceVuotoRifiutato() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("   ", "Aula P3", "E", 1, 120));
    }

    /**
     * @brief Un nome vuoto viene rifiutato.
     */
    @Test
    public void testNomeVuotoRifiutato() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("P3", "", "E", 1, 120));
    }

    /**
     * @brief Una capienza pari a zero viene rifiutata.
     */
    @Test
    public void testCapienzaZeroRifiutata() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("P3", "Aula P3", "E", 1, 0));
    }

    /**
     * @brief Una capienza negativa viene rifiutata.
     */
    @Test
    public void testCapienzaNegativaRifiutata() {
        assertThrows(IllegalArgumentException.class,
                () -> new Aula("P3", "Aula P3", "E", 1, -5));
    }

    /**
     * @brief La capienza minima ammessa (1) viene accettata.
     */
    @Test
    public void testCapienzaUnoAccettata() {
        Aula a = new Aula("P3", "Aula P3", "E", 1, 1);
        assertEquals(1, a.getCapienza());
    }

    /**
     * @brief Un piano negativo (interrato) viene accettato (ED1.4).
     */
    @Test
    public void testPianoInterratoAccettato() {
        Aula a = new Aula("P3", "Aula P3", "E", -1, 120);
        assertEquals(-1, a.getPiano());
    }

    /**
     * @brief Gli spazi all'inizio e alla fine del codice vengono tolti.
     */
    @Test
    public void testSpaziTolti() {
        Aula a = new Aula(" P3 ", "Aula P3", "E", 1, 120);
        assertEquals("P3", a.getCodice());
    }

    /**
     * @brief Un setter con valore non valido lancia l'eccezione e non modifica l'aula.
     */
    @Test
    public void testSetterNonValidoNonCambiaValore() {
        assertThrows(IllegalArgumentException.class,
                () -> aula.setCapienza(0));
        assertEquals(120, aula.getCapienza());
    }

    /**
     * @brief ORDINE_PER_CODICE ordina le aule per codice (FI1.4).
     */
    @Test
    public void testOrdinePerCodice() {
        List<Aula> lista = new ArrayList<>();
        lista.add(new Aula("P5", "Aula P5", "E", 1, 80));
        lista.add(new Aula("P1", "Aula P1", "E", 0, 200));
        lista.add(new Aula("P3", "Aula P3", "E", 1, 120));

        lista.sort(Aula.ORDINE_PER_CODICE);

        assertEquals("P1", lista.get(0).getCodice());
        assertEquals("P3", lista.get(1).getCodice());
        assertEquals("P5", lista.get(2).getCodice());
    }
}
