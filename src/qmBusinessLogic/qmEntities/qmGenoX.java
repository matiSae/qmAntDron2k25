package qmBusinessLogic.qmEntities;

public class qmGenoX extends qmGenoAlimento {
    
    public qmGenoX() {
        super("X", "Genoma sAsexual");
    }
    
    @Override
    public void activarSuperhabilidad(qmHormiga hormiga) {
        System.out.println("[Genoma X]  " + hormiga.data.getNombre());
    }
    
    @Override
    public String getTipoGenoma() {
        return "X";
    }
}
