package qmBusinessLogic.qmEntities;

public class qmCoordenadaUK {

    private String qmGeoposicion;
    private String qmTipoArsenal;
    private Boolean qmAutomataAccion;

    public qmCoordenadaUK(String geoposicion, String tipoArsenal, Boolean accion) {
        qmGeoposicion = geoposicion;
        qmTipoArsenal = tipoArsenal;
        qmAutomataAccion = accion;
    }
    
    public String getGeoposicion() {
        return qmGeoposicion;
    }
    public void setGeoposicion(String geoposicion) {
        qmGeoposicion = geoposicion;
    }
    public String getTipoArsenal() {
        return qmTipoArsenal;
    }
    public void setTipoArsenal(String tipoArsenal) {
        qmTipoArsenal = tipoArsenal;
    }
    public Boolean getAccion() {
        return qmAutomataAccion;
    }
    public void setAccion(Boolean accion) {
        qmAutomataAccion = accion;
    }
    @Override
    public String toString() {
        return "qmCoordenadaUK :" + getGeoposicion() 
        + ", qmTipoArsenal :" + getTipoArsenal()
        + ", qmAutomataAccion :" + getAccion() + "]";
    }
}
