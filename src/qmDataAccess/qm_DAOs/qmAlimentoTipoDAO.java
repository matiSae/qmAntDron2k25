package qmDataAccess.qm_DAOs;

import qmDataAccess.qm_DTOs.qmAlimentoTipoDTO;
import qmDataAccess.qm_Helpers.qmDataHelperSQLiteDAO;
import qmInfrastructure.AppException;

public class qmAlimentoTipoDAO extends qmDataHelperSQLiteDAO<qmAlimentoTipoDTO>{
    public qmAlimentoTipoDAO() throws AppException {
        super(qmAlimentoTipoDTO.class, "qmAlimentoTipo", "IdAlimentoTipo");
    }
}
