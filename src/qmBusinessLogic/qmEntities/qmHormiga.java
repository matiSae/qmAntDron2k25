package qmBusinessLogic.qmEntities;

import qmBusinessLogic.qmFactoryBL;
import qmDataAccess.qm_DAOs.qmHormigaDAO;
import qmDataAccess.qm_DTOs.qmHormigaDTO;

public abstract class qmHormiga {
    protected qmFactoryBL<qmHormigaDTO> factory = new qmFactoryBL<>(qmHormigaDAO.class);
    public qmHormigaDTO data = new qmHormigaDTO();

    // protected HormigaDAO hormigaDAO;
    // protected Hormiga() throws AppException  {
    //     this.hormigaDAO = new HormigaDAO();
    // }

    // public FactoryBL<HormigaDTO> factory = new FactoryBL<>(() -> {
    //     try {
    //         return new HormigaDAO();
    //     } catch (Exception e) {
    //         new RuntimeException();
    //     }
    // });
}
