package it.unisa.diem.softeng.prenotazioneaule.modello;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author paolo
 */
public class TipoAttivitaTest {

    /**
     * Test of getEtichetta method, of class TipoAttivita.
     */
    @Test
    public void testGetEtichetta() {
        TipoAttivita instance = TipoAttivita.LEZIONE;
        String expResult = "Lezione";
        String result = instance.getEtichetta();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class TipoAttivita.
     */
    @Test
    public void testToString() {

        for (TipoAttivita ta : TipoAttivita.values()) {
            assertEquals(ta.getEtichetta(), ta.toString());

        }

    }

}
