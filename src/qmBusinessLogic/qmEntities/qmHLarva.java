package qmBusinessLogic.qmEntities;

import java.util.List;

import qmDataAccess.qm_DTOs.qmHormigaDTO;
import qmInfrastructure.AppException;

/**
 * HLarva - Etapa inicial de desarrollo
 * Alimento: Nectarívoros
 * Puede transformarse en HSoldado si come Carnívoro con genoma XX
 * REQ03: Si tiene genoma pero no se transforma, muere
 * REQ03: Al transformarse cambia de sexo acorde al genoma
 */
public class qmHLarva extends qmHormiga {
    
    private qmGenoAlimento genoma = null; // Genoma inyectado

    public qmHormigaDTO getLava(int id) throws AppException{
        data = factory.getBy(id);
        return data;
    }
    
    public List<qmHormigaDTO> getLarvas() throws AppException {
        return factory.getAll();
    }
    
    /**
     * HLarva come su alimento (Nectarívoros)
     * - Si come Nectarívoros sin genoma: vive (data.Estado = "A")
     * - Si come Carnívoro + genoma XX: se transforma en HSoldado
     * - Si come Carnívoro + genoma pero NO es XX: muere (data.Estado = "X")
     * - Si come otro alimento: muere (data.Estado = "X")
     */
    @Override
    public qmHormiga comer(String comida) throws AppException {
        // HLarva solo vive con Nectarívoros
        if (comida.equalsIgnoreCase("Nectarívoros")) {
            setViva(true); // data.setEstado("A")
            System.out.println("[HLarva] Come Nectarívoros... ✓ Vive");
            return this;
        }
        
        // Si intenta comer Carnívoro
        if (comida.equalsIgnoreCase("Carnívoro")) {
            // Si tiene genoma XX, se transforma
            if (genoma != null && genoma instanceof qmGenoXX) {
                return transformarseEnSoldado();
            }
            
            // Si tiene otro genoma pero intenta comer Carnívoro: muere
            if (genoma != null) {
                setViva(false); // data.setEstado("X")
                System.out.println("[HLarva] Come Carnívoro con genoma " + genoma.getTipoGenoma() + 
                " pero no es XX... ✗ Muere (No puede transformarse)");
                return this;
            }
        }
        
        // Sin alimento correcto y sin genoma, muere
        setViva(false); // data.setEstado("X")
        System.out.println("[HLarva] No come su alimento... ✗ Muere");
        return this;
    }

    /**
     * Transformación de HLarva a HSoldado
     * REQ03: Cambia de sexo acorde al genoma
     * Para genoma XX: se convierte en Hombre (idSexo=3)
     */
    private qmHormiga transformarseEnSoldado() {
        System.out.println("[HLarva→HSoldado] Transformación completada con genoma " + genoma.getTipoGenoma());
        qmHSoldado soldado = new qmHSoldado();
        soldado.data = this.data;
        soldado.inyectarGenoma(this.genoma);
        
        // Cambiar sexo acorde al genoma usando IdSexo de BD
        if (genoma instanceof qmGenoXX) {
            soldado.setIdSexo(3); // 3 = Hombre (XX)
            System.out.println("[HLarva→HSoldado] Sexo cambiado a: Hombre (XX)");
        } else if (genoma instanceof qmGenoX) {
            soldado.setIdSexo(1); // 1 = Macho para X
            System.out.println("[HLarva→HSoldado] Sexo cambiado a: Macho (X)");
        } else if (genoma instanceof qmGenoXY) {
            soldado.setIdSexo(1); // 1 = Macho para XY
            System.out.println("[HLarva→HSoldado] Sexo cambiado a: Macho (XY)");
        }
        
        return soldado;
    }

    /**
     * Inyectar genoma (usado por EntomologoGen)
     */
    public void inyectarGenoma(qmGenoAlimento genoma) {
        this.genoma = genoma;
        System.out.println("[HLarva] Genoma inyectado: " + genoma.getTipoGenoma());
    }

    public qmGenoAlimento getGenoma() {
        return genoma;
    }

    public void setGenoma(qmGenoAlimento genoma) {
        this.genoma = genoma;
    }

    @Override
    public String toString() {
        String genoStr = genoma != null ? genoma.getTipoGenoma() : "null";
        String viva = estaViva() ? "Viva" : "Muerta";
        return "HLarva { Nombre='" + data.getNombre() + "', Genoma='" + genoStr + "', Sexo='" + getDescripcionSexo() + "', Estado='" + viva + "' }";
    }
}
