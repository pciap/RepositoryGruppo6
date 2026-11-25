package it.unisa.diem.softeng.prenotazioneaule.modello;



/**
 *
 * @author paolo
 */
final class Controlli {
    
    private Controlli(){
    
    
    }
    
    /**
    *@brief controlla che un campo sia sempre inserito
    * */
     static String richiediTesto(String valore, String campo){
        if (valore == null || valore.trim().isEmpty()){
        
            throw new IllegalArgumentException("Il campo " + campo + " è obbligatorio");
        
        }
        return valore.trim();
    }
    /**
     * @brief controlla che il valore del campo sia sempre positivo
     */
    
     static int richiediPositivo(int valore, String campo){
        
        if( valore <= 0){
            throw new IllegalArgumentException("Il campo " + campo + " deve contenere un numero POSITIVO.");
        }
        return valore;
    }
    
    
}
