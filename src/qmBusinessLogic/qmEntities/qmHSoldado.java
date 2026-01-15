package qmBusinessLogic.qmEntities;

import qmInfrastructure.AppException;


public class qmHSoldado extends qmHormiga {
    
    private qmGenoAlimento genoma = null; // Genoma inyectado
    private boolean superSaltar = false; // SuperHabilidad


    @Override
    public qmHormiga comer(String tipoAlimento) throws AppException {
        if (tipoAlimento.equalsIgnoreCase("Carnívoro")) {
            setViva(true); // Usa data.setEstado("A")
            System.out.println("[HSoldado] Come Carnívoro... ✓ Vive");
            
            // Activar SuperHabilidad SOLO si genoma es XX
            if (genoma != null && genoma instanceof qmGenoXX) {
                activarSuperSaltar();
            }
            return this;
        }
        
        setViva(false); // Usa data.setEstado("X")
        System.out.println("[HSoldado] No come Carnívoro... ✗ Muere");
        return this;
    }

    //refactorizado
    public void activarSuperSaltar() {
        this.superSaltar = true;
        System.out.println("[HSoldado] ⚡ SuperHabilidad ACTIVADA: superSaltar (Genoma XX)");
    }

    /**
     * Usar SuperHabilidad: superSaltar
     */
    public void usarSuperSaltar() {
        if (superSaltar) {
            System.out.println("[HSoldado] 🦗 ¡SUPER SALTO EJECUTADO! Altitud máxima alcanzada");
        } else {
            System.out.println("[HSoldado] No puede hacer super salto - necesita genoma XX");
        }
    }

    /**
     * refactorizado
     * */
    public void inyectarGenoma(qmGenoAlimento genoma) {
        this.genoma = genoma;
        System.out.println("[HSoldado] Genoma inyectado: " + genoma.getTipoGenoma());
    }

    public qmGenoAlimento getGenoma() {
        return genoma;
    }

    public boolean tieneSuperSaltar() {
        return superSaltar;
    }

    @Override
    public String toString() {
        String genoStr = genoma != null ? genoma.getTipoGenoma() : "null";
        String viva = estaViva() ? "Viva" : "Muerta";
        return "HSoldado { Nombre='" + data.getNombre() + "', Genoma='" + genoStr + "', Sexo='" + getDescripcionSexo() + "', Estado='" + viva + "', SuperSaltar=" + superSaltar + " }";
    }
}