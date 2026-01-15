package qmBusinessLogic.qmEntities;

/**
 * Clase abstracta qmGenoAlimento
 * Representa los genomas que pueden inyectarse en las hormigas
 * Define el contrato para todos los tipos de genoma: X, XX, XY
 */
public abstract class qmGenoAlimento {
    
    protected String codigo;
    protected String descripcion;
    
    public qmGenoAlimento(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    /**
     * Obtener el código del genoma
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * Obtener la descripción del genoma
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Método abstracto para activar la superhabilidad específica del genoma
     */
    public abstract void activarSuperhabilidad(qmHormiga hormiga);
    
    /**
     * Método abstracto para obtener el tipo de genoma
     */
    public abstract String getTipoGenoma();
    
    @Override
    public String toString() {
        return "Genoma[" + codigo + "] - " + descripcion;
    }
}
