package qmBusinessLogic.qmInterface;

import java.util.List;
import qmBusinessLogic.qmEntities.qmHormiga;
import qmDataAccess.qm_DTOs.qmAlimentoTipoDTO;
import qmInfrastructure.AppException;

public interface qm_IEntomologo {

    // ETL: Extract-Transform-Load de Hormigas desde AntNest.txt
    public List<qmHormiga> etlAntNest() throws AppException;
    
    // ETL: Extract-Transform-Load de Alimentos desde AntFood.txt
    public List<qmAlimentoTipoDTO> etlAntFood() throws AppException;
    
    // Preparar alimento (con o sin genoma según implementación)
    public qmAlimentoTipoDTO preparar(qmAlimentoTipoDTO alimento);
    
    // Alimentar hormiga con alimento
    public qmHormiga alimentarAnt(qmHormiga hormiga, qmAlimentoTipoDTO alimento) throws AppException;

}
