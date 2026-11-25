package it.unisa.diem.softeng.prenotazioneaule.modello;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Suite di unit test per la classe {@link Docente}.
 * 
 * Verifica la corretta inizializzazione, la validazione dei dati in ingresso 
 * (e-mail istituzionale @unisa.it, nome e cognome), la gestione delle eccezioni 
 * nei metodi setter, la formattazione del nome completo e il comparatore alfabetico.
 */
public class DocenteTest {

    /** Istanza di docente valida utilizzata come base nei singoli test. */
    private Docente d;

    /**
     * @brief Inizializza una fixture valida prima di ogni test.
     */
    @BeforeEach
    public void setUp() {
        d = new Docente("mario.rossi@unisa.it", "Mario", "Rossi");
    }

    /**
     * @brief Verifica che il costruttore inizializzi correttamente i campi con dati validi (ED2.1).
     */
    @Test
    public void testCostruttoreValido() {
        assertEquals("mario.rossi@unisa.it", d.getEmail());
        assertEquals("Mario", d.getNome());
        assertEquals("Rossi", d.getCognome());
    }

    /**
     * @brief Verifica che l'e-mail venga normalizzata rimuovendo gli spazi esterni e convertendola in minuscolo.
     */
    @Test
    public void testEmailMaiuscoleESpazi() {
        Docente x = new Docente(" M.Rossi@UNISA.IT ", "Mario", "Rossi");
        assertEquals("m.rossi@unisa.it", x.getEmail());
    }

    /**
     * @brief Verifica che venga sollevata IllegalArgumentException per un'e-mail priva del simbolo '@'.
     */
    @Test
    public void testEmailSenzaChiocciola() {
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("mario.rossi", "Mario", "Rossi"));
    }

    /**
     * @brief Verifica che venga rifiutata un'e-mail con dominio non istituzionale (ED2.3).
     */
    @Test
    public void testEmailGmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("mario@gmail.com", "Mario", "Rossi"));
    }

    /**
     * @brief Verifica che venga rifiutato il sottodominio degli studenti (@studenti.unisa.it) (ED2.3).
     */
    @Test
    public void testEmailDominioStudenti() {
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("mario@studenti.unisa.it", "Mario", "Rossi"));
    }

    /**
     * @brief Verifica che venga rifiutata un'e-mail contenente spazi interni.
     */
    @Test
    public void testEmailConSpazioInMezzo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("mario rossi@unisa.it", "Mario", "Rossi"));
    }

    /**
     * @brief Verifica che venga rifiutato un nome vuoto o composto da soli spazi (ED2.1).
     */
    @Test
    public void testNomeVuotoOSoloSpazi() {
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("a@unisa.it", "", "Rossi"));
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("a@unisa.it", "   ", "Rossi"));
    }

    /**
     * @brief Verifica che venga rifiutato un cognome vuoto o composto da soli spazi (ED2.1).
     */
    @Test
    public void testCognomeVuotoOSoloSpazi() {
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("a@unisa.it", "Mario", ""));
        assertThrows(IllegalArgumentException.class,
                () -> new Docente("a@unisa.it", "Mario", "   "));
    }

    /**
     * @brief Verifica che il tentativo di impostare un'e-mail non valida sollevi eccezione 
     *        mantenendo inalterato il valore precedente (FI2.2).
     */
    @Test
    public void testSetEmailNonValidaMantieneLaVecchia() {
        assertThrows(IllegalArgumentException.class,
                () -> d.setEmail("mario@gmail.com"));
        assertEquals("mario.rossi@unisa.it", d.getEmail());
    }

    /**
     * @brief Verifica che getNomeCompleto restituisca la stringa nel formato "Cognome Nome".
     */
    @Test
    public void testGetNomeCompleto() {
        assertEquals("Rossi Mario", d.getNomeCompleto());
    }

    /**
     * @brief Verifica che il comparatore ORDINE_ALFABETICO ordini prima per cognome e poi per nome (FI2.4).
     */
    @Test
    public void testOrdineAlfabetico() {
        List<Docente> lista = new ArrayList<>();
        lista.add(new Docente("anna.verdi@unisa.it", "Anna", "Verdi"));
        lista.add(new Docente("paolo.rossi@unisa.it", "Paolo", "Rossi"));
        lista.add(d); // Rossi Mario

        lista.sort(Docente.ORDINE_ALFABETICO);

        assertEquals("Rossi Mario", lista.get(0).getNomeCompleto());
        assertEquals("Rossi Paolo", lista.get(1).getNomeCompleto());
        assertEquals("Verdi Anna", lista.get(2).getNomeCompleto());
    }
}