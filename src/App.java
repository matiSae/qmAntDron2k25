import java.util.Scanner;
import java.util.List;

import qmApp.qmConsoleApp.qmKGD;
import qmApp.qmConsoleApp.qmSistemaRuso;
import qmBusinessLogic.qmEntities.qmEntomologoBase;
import qmBusinessLogic.qmEntities.qmEntomologoGen;
import qmBusinessLogic.qmEntities.qmEntomologoNormal;
import qmBusinessLogic.qmEntities.qmGenoXX;
import qmBusinessLogic.qmEntities.qmHLarva;
import qmBusinessLogic.qmEntities.qmHormiga;
import qmBusinessLogic.qmEntities.qmHSoldado;
import qmDataAccess.qm_DTOs.qmAlimentoTipoDTO;
import qmDataAccess.qm_DTOs.qmHormigaDTO;
import qmInfrastructure.AppException;
import qmInfrastructure.Tools.qmCMDColor;

public class App {
    private static final int MAX_INTENTOS = 3;
    
    public static void main(String[] args) {
        try {
          
            Scanner scanner = new Scanner(System.in);
            qmKGD sistema = new qmKGD("02", "Matias", "matias", "123");
            qmSistemaRuso sistemaRuso = new qmSistemaRuso();
            
            boolean autenticado = false;
            int intentos = 0;
            
            while (intentos < MAX_INTENTOS && !autenticado) {
                intentos++;
                System.out.print("Usuario: ");
                String usuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String clave = scanner.nextLine();
                
                // Usar método qmshowKGD() de qmSistemaRuso
                if (sistemaRuso.qmshowKGD(usuario, clave)) {
                    autenticado = true;
                    System.out.println(qmCMDColor.GREEN + "✓ AUTENTICACIÓN EXITOSA" + qmCMDColor.RESET);
                    System.out.println(qmCMDColor.GREEN + "  Cédula: " + sistema.getqmCedula() + qmCMDColor.RESET);
                    System.out.println(qmCMDColor.GREEN + "  Nombre: " + sistema.getqmNombre() + qmCMDColor.RESET);
                } else {
                    System.out.println(qmCMDColor.RED + "✗ Credenciales inválidas (Intento " + intentos + "/" + MAX_INTENTOS + ")" + qmCMDColor.RESET);
                }
            }
            
            if (!autenticado) {
                System.out.println(qmCMDColor.RED + " Acceso denegado después de " + MAX_INTENTOS + " intentos." + qmCMDColor.RESET);
                scanner.close();
                return;
            }
           System.out.println("Datos de caso de estudio (Cédula 02): HSoldado con genoma XX");
            
            qmEntomologoBase entomologoNormal = new qmEntomologoNormal();
            List<qmHormiga> hormigas = null;
            List<qmAlimentoTipoDTO> alimentos = null;
            
            // ETL Hormigas
            System.out.println("\n Cargando Hormigas desde AntNest.txt:");
            try {
                hormigas = entomologoNormal.etlAntNest();
            } catch (AppException e) {
                throw new AppException("Error en ETL de Hormigas: " + e.getMessage(), e, App.class, "main - etlAntNest");
            }
            
            // ETL Alimentos
            System.out.println("\n Cargando Alimentos desde AntFood.txt:");
            try {
                alimentos = entomologoNormal.etlAntFood();
            } catch (AppException e) {
                throw new AppException("Error en ETL de Alimentos: " + e.getMessage(), e, App.class, "main - etlAntFood");
            }
            
            try {

                qmHLarva larva1 = new qmHLarva();
                qmHormigaDTO dto1 = new qmHormigaDTO();
                dto1.setNombre("Larva_Nectarívoro");
                dto1.setEstado("A");
                larva1.data = dto1;
                
                System.out.println("  [Antes] " + larva1.toString());
                larva1.comer("Nectarívoros");
                System.out.println("  [Después] " + larva1.toString());
                System.out.println(qmCMDColor.GREEN + "  ✓ Resultado: HLarva vive comiendo Nectarívoros" + qmCMDColor.RESET);
                
                
                qmHLarva larva2 = new qmHLarva();
                qmHormigaDTO dto2 = new qmHormigaDTO();
                dto2.setNombre("Larva_XX_Soldado");
                dto2.setEstado("A");
                larva2.data = dto2;
                
                System.out.println("  [Antes] " + larva2.toString());
                larva2.inyectarGenoma(new qmGenoXX());
                System.out.println("  [Con Genoma XX] " + larva2.toString());
                
                qmHormiga resultado = larva2.comer("Carnívoro");
                System.out.println("  [Después] " + resultado.toString());
                
                if (resultado instanceof qmHSoldado) {
                    qmHSoldado soldado = (qmHSoldado) resultado;
                    System.out.println(qmCMDColor.GREEN + "  ✓ Resultado: HLarva transformada a HSoldado (Genoma XX)" + qmCMDColor.RESET);
                    System.out.println(qmCMDColor.GREEN + "  ✓ Sexo cambiado a: " + soldado.getDescripcionSexo() + " (IdSexo=3)" + qmCMDColor.RESET);
                }
                
                qmHLarva larva3 = new qmHLarva();
                qmHormigaDTO dto3 = new qmHormigaDTO();
                dto3.setNombre("Larva_SinGenoma");
                dto3.setEstado("A");
                larva3.data = dto3;
                
                System.out.println("  [Antes] " + larva3.toString());
                larva3.comer("Carnívoro");
                System.out.println("  [Después] " + larva3.toString());
                System.out.println(qmCMDColor.RED + "  ✗ Resultado: HLarva muere (no tiene genoma para transformarse)" + qmCMDColor.RESET);
            } catch (AppException e) {
                throw new AppException("Error en REQ03 - Alimentación de hormigas: " + e.getMessage(), e, App.class, "main - REQ03");
            }
            
            try {
                System.out.println("\n PRUEBA 4.1: HSoldado con Genoma XX activa SuperSaltar");
                qmHLarva larva4 = new qmHLarva();
                qmHormigaDTO dto4 = new qmHormigaDTO();
                dto4.setNombre("Larva_SuperSaltar");
                dto4.setEstado("A");
                larva4.data = dto4;
                
                larva4.inyectarGenoma(new qmGenoXX());
                qmHormiga resultado2 = larva4.comer("Carnívoro");
                
                if (resultado2 instanceof qmHSoldado) {
                    qmHSoldado soldadoSuper = (qmHSoldado) resultado2;
                    System.out.println("  " + soldadoSuper.toString());
                    System.out.println("\n  [Ejecutando SuperHabilidad]");
                    soldadoSuper.usarSuperSaltar();
                }
            } catch (AppException e) {
                throw new AppException("Error en REQ04 - SuperHabilidad: " + e.getMessage(), e, App.class, "main - REQ04");
            }
            
            try {
                qmAlimentoTipoDTO alimento1 = new qmAlimentoTipoDTO();
                alimento1.setNombre("Carnívoro");
                
                System.out.println("\n PRUEBA 5.1: Entomólogo Normal prepara Carnívoro (sin genoma)");
                entomologoNormal.preparar(alimento1);
                
                System.out.println("\n PRUEBA 5.2: Entomólogo Genetista prepara Carnívoro (con genoma aleatorio)");
                qmEntomologoBase genetista = new qmEntomologoGen();
                genetista.preparar(alimento1);
                genetista.preparar(alimento1);
                genetista.preparar(alimento1);
            } catch (Exception e) {
                throw new AppException("Error en REQ05 - Preparación de alimentos: " + e.getMessage(), e, App.class, "main - REQ05");
            }
          
            scanner.close();
            
        } catch (AppException e) {
            try {
                throw new AppException("Error en REQ02-REQ05: " + e.getMessage(), e, App.class, "main");
            } catch (AppException ex) {
                System.out.println(qmCMDColor.RED + " Error de aplicación: " + ex.getMessage() + qmCMDColor.RESET);
            }
        } catch (Exception e) {
            try {
                throw new AppException("Error inesperado en aplicación: " + e.getMessage(), e, App.class, "main");
            } catch (AppException ex) {
                System.out.println(qmCMDColor.RED + "Error inesperado: " + ex.getMessage() + qmCMDColor.RESET);
            }
        }
    }
}
