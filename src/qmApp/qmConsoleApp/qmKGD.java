package qmApp.qmConsoleApp;

public class qmKGD {
    private String qmcedula;
    private String qmnombre;
    private String qmUsuario;
    private String qmClave;



    public qmKGD(String qmcedula, String qmnombre, String qmUsuario, String qmClave) {
        this.qmcedula = qmcedula;
        this.qmnombre = qmnombre;
        this.qmUsuario = "matias";
        this.qmClave = "123";
    }
    
    public void qmshowCedulaNombre(){
        System.out.println("Cedula:" + getqmCedula()
                                   + "\n Nombre:" + getqmNombre());
    }

    
    public String getqmUsuario() {
        return qmUsuario;
    }
    public void setqmUsuario(String qmUsuario) {
        this.qmUsuario = qmUsuario;
    }
    public String getqmClave() {
        return qmClave;
    }
    public void setqmClave(String qmClave) {
        this.qmClave = qmClave;
    }
    public String getqmCedula() {
        return qmcedula;
    }
    public void setqmCedula(String qmcedula) {
        this.qmcedula = qmcedula;
    }
    public String getqmNombre() {
        return qmnombre;
    }
    public void setqmNombre(String qmnombre) {
        this.qmnombre = qmnombre;
    }
    
    public void show(){
        System.out.println("[+] Alumno:");
        System.out.println(getqmCedula() + " | " + getqmNombre());
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n qmcedula    : "+ getqmCedula()
        + "\n qmnombre    : "+ getqmNombre()
        + "\n qmUsuario   : "+ getqmUsuario()
        + "\n qmClave     : "+ getqmClave();
    }
}
