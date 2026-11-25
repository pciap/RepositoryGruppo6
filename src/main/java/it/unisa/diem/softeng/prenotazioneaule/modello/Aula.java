
package it.unisa.diem.softeng.prenotazioneaule.modello;

/**
 *
 * @author paolo
 * @brief Aula del dipartimento che si può prenotare (ED1.1)
 * 
 * ha un codice univoco, un nome, un edificio,un piano e una capienza;
 */
public class Aula {
    
    private String codice;
    private String nome;
    private String edificio;
    private int piano;
    private int capienza;
    
    
    public Aula(String codice, String nome, String edificio, int piano, int capienza){
    
        this.codice = Controlli.richiediTesto(codice, "codice");
        this.nome = Controlli.richiediTesto(nome, "nome");
        this.edificio = Controlli.richiediTesto(edificio, "edificio");
        this.piano = piano;
        this.capienza = Controlli.richiediPositivo(capienza, "capienza");
    
    }
    
    
    
    
    
}
