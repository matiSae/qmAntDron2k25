package qmBusinessLogic.qmEntities;

public class qmGenoXY extends qmGenoAlimento {
    
    public qmGenoXY() {
        super("XY", "Genoma hembra");
    }
    
    @Override
    public void activarSuperhabilidad(qmHormiga hormiga) {
        System.out.println("[Genoma XY] Capacidades  " + hormiga.data.getNombre());
    }
    
    @Override
    public String getTipoGenoma() {
        return "XY";
    }
}
