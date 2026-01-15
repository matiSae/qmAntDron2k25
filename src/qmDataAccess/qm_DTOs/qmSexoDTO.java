package qmDataAccess.qm_DTOs;

public class qmSexoDTO {
    private Integer IdSexo         ;
    private String  Nombre         ;
    private String  Estado         ;
    private String  FechaCreacion  ;
    private String  FechaModifica  ;

    public qmSexoDTO() {}
    public qmSexoDTO(Integer idSexo, String nombre, String estado, String fechaCreacion, String fechaModifica) {
        IdSexo = idSexo;
        Nombre = nombre;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }
    
    public Integer getIdSexo() {
        return IdSexo;
    }
    public void setIdSexo(Integer idSexo) {
        IdSexo = idSexo;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCreacion() {
        return FechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
    public String getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdSexo    : "+ getIdSexo    ()
        + "\n Nombre    : "+ getNombre    ()
        + "\n Estado    : "+ getEstado    ()
        + "\n FechaCreacion : "+ getFechaCreacion ()
        + "\n FechaModifica : "+ getFechaModifica ();
    }
}
