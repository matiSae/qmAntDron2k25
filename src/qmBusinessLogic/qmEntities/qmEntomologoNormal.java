package qmBusinessLogic.qmEntities;

/**
 * Entomólogo Normal
 * REQ05: Prepara alimentos sin genoma
 * Solo puede alimentar hormigas sin inyectar genomas
 */
public class qmEntomologoNormal extends qmEntomologoBase {
    
    public qmEntomologoNormal() {
        super();
    }

    @Override
    public String toString() {
        return "EntomologoNormal - Experto en alimentación de hormigas";
    }
}
