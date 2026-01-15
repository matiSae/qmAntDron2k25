package qmBusinessLogic.qmEntities;

import qmBusinessLogic.qmFactoryBL;
import qmDataAccess.qm_DAOs.qmAlimentoTipoDAO;
import qmDataAccess.qm_DAOs.qmHormigaDAO;
import qmDataAccess.qm_DTOs.qmAlimentoTipoDTO;
import qmDataAccess.qm_DTOs.qmHormigaDTO;
import qmInfrastructure.AppException;

public abstract class qmHormiga {
    protected qmFactoryBL<qmHormigaDTO> factory = new qmFactoryBL<>(qmHormigaDAO.class);
    public qmHormigaDTO data = new qmHormigaDTO();


    public abstract qmHormiga comer(String tipoAlimento) throws AppException;


    public qmHormiga alimentarse(qmAlimentoTipoDTO alimento) throws AppException {
        if (alimento == null) {
            setViva(false);
            return this;
        }
        return comer(alimento.getNombre());
    }
//refactorizado
    protected void eliminarAlimentoDeBD(qmAlimentoTipoDTO alimento) throws AppException {
        try {
            qmAlimentoTipoDAO alimentoDAO = new qmAlimentoTipoDAO();
            if (alimento.getIdAlimentoTipo() != null) {
                alimentoDAO.delete(alimento.getIdAlimentoTipo());
                System.out.println("[BD] Alimento '" + alimento.getNombre() + "' eliminado");
            }
        } catch (AppException e) {
            System.out.println("[BD] Error al eliminar alimento: " + e.getMessage());
        }
    }

    public boolean estaViva() {
        return data.getEstado() != null && data.getEstado().equalsIgnoreCase("A");
    }


    public void setViva(boolean viva) {
        if (viva) {
            data.setEstado("A");
        } else {
            data.setEstado("X");
        }
    }


    public Integer getIdSexo() {
        return data.getIdSexo();
    }

    
    public void setIdSexo(Integer idSexo) {
        data.setIdSexo(idSexo);
    }

    public String getDescripcionSexo() {
        Integer idSexo = data.getIdSexo();
        if (idSexo == null) {
            return "Indefinido";
        }
        // Mapeo: 1=Macho, 2=Hembra, 3=Hombre (XX)
        switch (idSexo) {
            case 1: return "Macho";
            case 2: return "Hembra";
            case 3: return "Hombre";
            default: return "Indefinido";
        }
    }
}


