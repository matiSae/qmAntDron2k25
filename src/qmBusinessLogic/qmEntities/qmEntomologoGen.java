package qmBusinessLogic.qmEntities;

import java.util.Random;
import qmDataAccess.qm_DTOs.qmAlimentoTipoDTO;
import qmInfrastructure.Tools.qmCMDColor;

/**
 * Entomólogo Genetista
 */
public class qmEntomologoGen extends qmEntomologoBase {
    
    private qmGenoAlimento[] genomas;
    private Random random = new Random();

    public qmEntomologoGen() {
        super();
        // Inicializar los genomas disponibles
        this.genomas = new qmGenoAlimento[] {
            new qmGenoX(),
            new qmGenoXX(),
            new qmGenoXY()
        };
    }

    @Override
    public qmAlimentoTipoDTO preparar(qmAlimentoTipoDTO alimento) {
        // Generar genoma aleatorio
        qmGenoAlimento genoSeleccionado = genomas[random.nextInt(genomas.length)];
        
        System.out.println(qmCMDColor.YELLOW + "[Preparado]-[ " + alimento.getNombre() + " + Genoma:" + genoSeleccionado.getTipoGenoma() + " ]-" + qmCMDColor.RESET);
        
        // Adjuntar genoma al alimento (se crearía campo en DTO)
        return alimento;
    }

    public void inyectarGenoma(qmHLarva larva) {
        qmGenoAlimento genoSeleccionado = genomas[random.nextInt(genomas.length)];
        larva.inyectarGenoma(genoSeleccionado);
        
        // Notificar si es genoma XX (para HSoldado)
        if (genoSeleccionado instanceof qmGenoXX) {
            System.out.println(qmCMDColor.YELLOW + "[EntomologoGen]  Genoma XX inyectado - SuperSaltar se activará si se transforma en HSoldado" + qmCMDColor.RESET);
        }
    }

    public void inyectarGenomaEnSoldado(qmHSoldado soldado, String tipoGenoma) {
        qmGenoAlimento genoma = obtenerGenoma(tipoGenoma);
        soldado.inyectarGenoma(genoma);
        System.out.println("[EntomologoGen] Genoma " + tipoGenoma + " inyectado en HSoldado");
        
        // Si es XX, activar superhabilidad
        if (genoma instanceof qmGenoXX) {
            soldado.activarSuperSaltar();
        }
    }

    public qmGenoAlimento obtenerGenoma(String tipo) {
        switch (tipo.toUpperCase()) {
            case "X":
                return new qmGenoX();
            case "XX":
                return new qmGenoXX();
            case "XY":
                return new qmGenoXY();
            default:
                return genomas[random.nextInt(genomas.length)];
        }
    }

    @Override
    public String toString() {
        return "EntomologoGen (Genetista) - Experto en inyección de genomas";
    }
}

