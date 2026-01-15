package qmBusinessLogic.qmEntities;

import java.util.List;

import qmDataAccess.qm_DTOs.qmHormigaDTO;
import qmInfrastructure.AppException;

public class qmHLarva extends qmHormiga {

    public qmHormigaDTO getLava(int id) throws AppException{
        data = factory.getBy(id);
        return data;
    }
    
    public List<qmHormigaDTO> getLarvas() throws AppException {
        return factory.getAll();
    }
    
    public qmHormiga comer(String comida) {
        if (comida.equals("carne")) {
            return new qmHSoldado();
        }
        return this;
    }

    @Override
    public String toString() {
        return "HLarva {}";
    }
}
