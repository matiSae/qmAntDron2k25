//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package qmBusinessLogic;

import java.util.List;

import qmDataAccess.qm_Interfaces.qm_IDAO;
import qmInfrastructure.AppException;

public class qmFactoryBL<T>  {
    private final qm_IDAO<T> oDAO;
    public qmFactoryBL(Class<? extends qm_IDAO<T>> classDAO) {
        try {
            this.oDAO = classDAO.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            AppException er = new AppException("Error al instanciar classDAO<T>", e, getClass(), "FactoryBL(...)");
            throw new RuntimeException(er);
        }
    }

    // Constructor que usa un Supplier para crear la instancia de T
    // public FactoryBL(Supplier<IDAO<T>> supplier) {
    //     this.oDAO = supplier.get(); 
    // }
 
    public List<T> getAll() throws AppException {
         return oDAO.readAll();
    }

    public T getBy(Integer id) throws AppException {
        return oDAO.readBy(id);
    }

    public boolean add(T oT) throws AppException {
        return oDAO.create(oT);
    }

    public boolean upd(T oT) throws AppException {
        return oDAO.update(oT);
    }

    public boolean del(Integer id) throws AppException {
        return oDAO.delete(id);
    }
}
