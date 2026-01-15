package qmDataAccess.qm_DAOs;

import qmDataAccess.qm_DTOs.qmSexoDTO;
import qmDataAccess.qm_Helpers.qmDataHelperSQLiteDAO;
import qmInfrastructure.AppException;

public class qmSexoDAO extends qmDataHelperSQLiteDAO<qmSexoDTO>{
     public qmSexoDAO() throws AppException {
        super(qmSexoDTO.class, "qmSexo", "IdSexo");
    }
}
