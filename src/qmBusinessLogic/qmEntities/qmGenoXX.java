package qmBusinessLogic.qmEntities;


public class qmGenoXX extends qmGenoAlimento {
    
    public qmGenoXX() {
        super("XX", "Genoma macho - Activa SuperSaltar en HSoldado");
    }
    
    /**
     * Activa la SuperHabilidad "superSaltar" en HSoldado
     */
    @Override
    public void activarSuperhabilidad(qmHormiga hormiga) {
        if (hormiga instanceof qmHSoldado) {
            qmHSoldado soldado = (qmHSoldado) hormiga;
            soldado.activarSuperSaltar();
        } else {
            System.out.println("[Genoma XX] Superhabilidad específica para HSoldado");
        }
    }
    
    @Override
    public String getTipoGenoma() {
        return "XX";
    }
}
