//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package qmDataAccess.qm_Interfaces;

import java.util.List;

import qmInfrastructure.AppException;

public interface qm_IDAO<T> {
    List<T> readAll()            throws AppException;
    T       readBy (Integer id)  throws AppException;
    boolean create (T entity)    throws AppException;
    boolean update (T entity)    throws AppException;
    boolean delete (Integer id)  throws AppException;
    Integer getMaxReg()          throws AppException;
}
