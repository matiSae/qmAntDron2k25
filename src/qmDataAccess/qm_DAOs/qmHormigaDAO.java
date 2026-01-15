package qmDataAccess.qm_DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import qmDataAccess.qm_DTOs.qmHormigaDTO;
import qmDataAccess.qm_DTOs.qmVWHormigaDTO;
import qmDataAccess.qm_Helpers.qmDataHelperSQLiteDAO;
import qmInfrastructure.AppException;

public class qmHormigaDAO extends qmDataHelperSQLiteDAO<qmHormigaDTO>{
    public qmHormigaDAO() throws AppException {
        super(qmHormigaDTO.class, "qmHormiga", "IdHormiga");
    }

    // Leer todos los registros de la vista vwHormiga
    public List<qmVWHormigaDTO> readAllvwHormiga() throws AppException { 
        qmVWHormigaDTO dto;
        List<qmVWHormigaDTO> lst = new ArrayList<>();
        String query = " SELECT IdHormiga"
                      +"  ,Tipo         "   
                      +"  ,Sexo         "
                      +"  ,EstadoHormiga"   
                      +"  ,Nombre       "
                      +"  ,Descripcion  "
                      +"  ,Estado       "
                      +"  ,FechaCreacion"   
                      +"  ,FechaModifica" 
                      +"  FROM vwHormiga";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new qmVWHormigaDTO(rs.getString(1)          // IdHormiga
                                        ,rs.getString(2)        // Tipo            
                                        ,rs.getString(3)        // Sexo        
                                        ,rs.getString(4)        // EstadoHormiga 
                                        ,rs.getString(5)        // Nombre 
                                        ,rs.getString(6)        // Descripcion
                                        ,rs.getString(7)        // Estado
                                        ,rs.getString(8)        // FechaCreacion
                                        ,rs.getString(9)        // FechaModifica
                                      ); 
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw new AppException("Ups... porblemas con la vista", e, getClass(), "getVWHormiga()");
        }
        return lst;
    }
}