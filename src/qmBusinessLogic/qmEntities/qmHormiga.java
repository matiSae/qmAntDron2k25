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

    /**
     * La hormiga come su alimento
     * Si come su alimento vive, si no muere
     * El alimento es eliminado de la BD después de consumirlo
     * Usa data.Estado para persistencia en BD
     */
    public abstract qmHormiga comer(String tipoAlimento) throws AppException;

    /**
     * Alimentar la hormiga con alimento del DTO
     * Retorna la hormiga alimentada o transformada
     */
    public qmHormiga alimentarse(qmAlimentoTipoDTO alimento) throws AppException {
        if (alimento == null) {
            setViva(false);
            return this;
        }
        return comer(alimento.getNombre());
    }

    /**
     * Eliminar alimento de la base de datos
     */
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

    /**
     * Obtener estado de vida de la hormiga
     * Usa data.Estado de la BD (A=Activa/Viva, X=Inactiva/Muerta)
     */
    public boolean estaViva() {
        return data.getEstado() != null && data.getEstado().equalsIgnoreCase("A");
    }

    /**
     * Establecer estado de vida de la hormiga
     * Viva = "A", Muerta = "X"
     */
    public void setViva(boolean viva) {
        if (viva) {
            data.setEstado("A");
        } else {
            data.setEstado("X");
        }
    }

    /**
     * Obtener sexo de la hormiga
     * Usa data.IdSexo (FK a tabla Sexo en BD)
     */
    public Integer getIdSexo() {
        return data.getIdSexo();
    }

    /**
     * Establecer sexo de la hormiga
     * IdSexo: FK a tabla de Sexos en BD
     */
    public void setIdSexo(Integer idSexo) {
        data.setIdSexo(idSexo);
    }

    /**
     * Obtener descripción del sexo desde el DTO
     */
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


