package qmBusinessLogic.qmEntities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import qmBusinessLogic.qmInterface.qm_IEntomologo;
import qmDataAccess.qm_DAOs.qmAlimentoTipoDAO;
import qmDataAccess.qm_DAOs.qmHormigaDAO;
import qmDataAccess.qm_DTOs.qmAlimentoTipoDTO;
import qmDataAccess.qm_DTOs.qmHormigaDTO;
import qmInfrastructure.AppException;
import qmInfrastructure.Tools.qmCMDColor;

public abstract class qmEntomologoBase implements qm_IEntomologo {

    protected static final String RUTA_ANT_NEST = "storage/DataFiles/AntNest.txt";
    protected static final String RUTA_ANT_FOOD = "storage/DataFiles/AntFood.txt";

    public qmEntomologoBase() {
    }

    protected void mostrarLoadingHormiga(String texto) {
        String[] spinner = {"\\", "|", "/", "-"};
        for (int i = 0; i < 4; i++) {
            System.out.print(qmCMDColor.BLUE + "[" + spinner[i] + "] " + texto + "..." + qmCMDColor.RESET);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\r");
        }
    }
    protected void mostrarLoadingHormiga() {
        String[] spinner = {"\\", "|", "/", "-"};
        for (int i = 0; i < 4; i++) {
            System.out.print(qmCMDColor.BLUE + "[" + spinner[i] + "] " + "..." + qmCMDColor.RESET);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\r");
        }
    }
    protected void mostrarLoadingAlimento() {
        String[] spinner = {"0o0", "o0o", "0o0", "o0o"};
        for (int i = 0; i < 4; i++) {
            System.out.print(qmCMDColor.BLUE + "[" + spinner[i] + "] " + "..." + qmCMDColor.RESET);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\r");
        }
    }
    protected void mostrarLoadingAlimento(String texto) {
        String[] spinner = {"0o0", "o0o", "0o0", "o0o"};
        for (int i = 0; i < 4; i++) {
            System.out.print(qmCMDColor.BLUE + "[" + spinner[i] + "] " + texto + "..." + qmCMDColor.RESET);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\r");
        }
    }

    @Override
    public List<qmHormiga> etlAntNest() throws AppException {
        List<qmHormiga> lstHormigas = new ArrayList<>();
        qmHormigaDAO hormigaDAO = new qmHormigaDAO();
        java.util.Set<String> procesados = new java.util.HashSet<>();
        java.util.Set<String> existentes = new java.util.HashSet<>();
        
        try {
            System.out.println(qmCMDColor.BLUE + "\n╔════════════════════════════════════════╗" + qmCMDColor.RESET);
            System.out.println(qmCMDColor.BLUE + "║  CARGANDO HORMIGAS (CASO ESTUDIO)     ║" + qmCMDColor.RESET);
            System.out.println(qmCMDColor.BLUE + "╚════════════════════════════════════════╝" + qmCMDColor.RESET);
            
            try {
                System.out.print(qmCMDColor.BLUE + "[\\] ");
                Thread.sleep(200);
                System.out.print("\r[|] ");
                Thread.sleep(200);
                System.out.print("\r[/] ");
                Thread.sleep(200);
                System.out.print("\r[-] ");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("\r[✓] Iniciando ETL de Hormigas..." + qmCMDColor.RESET);
            
            // Cargar nombres de hormigas ya existentes en BD
            for (qmHormigaDTO dto : hormigaDAO.readAll()) {
                if (dto.getNombre() != null) {
                    existentes.add(dto.getNombre().toLowerCase());
                }
            }
            
            List<String> allLines = Files.readAllLines(Paths.get(RUTA_ANT_NEST));
            System.out.println(qmCMDColor.BLUE + "[ETL-AntNest] Buscando HSoldado en archivo..." + qmCMDColor.RESET);
            
            for (String line : allLines) {
                if (line.trim().isEmpty()) continue;
                
                String[] tipos = line.split(",");
                for (String tipo : tipos) {
                    String tipoLimpio = tipo.trim();
                    if (!tipoLimpio.isEmpty() && !procesados.contains(tipoLimpio.toLowerCase())) {
                        procesados.add(tipoLimpio.toLowerCase());
                        
                        // CASO DE ESTUDIO: Solo cargar HSoldado
                        if (tipoLimpio.equalsIgnoreCase("HSoldado")) {
                            // Solo insertar si NO existe en BD
                            if (!existentes.contains(tipoLimpio.toLowerCase())) {
                                try {
                                    mostrarLoadingHormiga(tipoLimpio);
                                    
                                    qmHormigaDTO hormiga = new qmHormigaDTO();
                                    hormiga.setNombre(tipoLimpio);
                                    hormiga.setDescripcion("Hormiga tipo: " + tipoLimpio);
                                    hormiga.setEstado("A");
                                    hormiga.setIdHormigaTipo(2); // HSoldado = 2
                                    hormiga.setIdSexo(2);
                                    hormiga.setIdEstado(1);
                                    
                                    if (hormigaDAO.create(hormiga)) {
                                        mostrarLoadingHormiga();
                                        System.out.println(qmCMDColor.GREEN + tipoLimpio + " ✓ VÁLIDO" + qmCMDColor.RESET);
                                        
                                    } else {
                                        mostrarLoadingHormiga();
                                        System.out.println(qmCMDColor.RED + "[\\|] " + tipoLimpio + " ✗ FALLÓ" + qmCMDColor.RESET);
                                    }
                                } catch (Exception e) {
                                    System.out.println(qmCMDColor.RED + "[\\|] " + tipoLimpio + " ✗ ERROR: " + e.getMessage() + qmCMDColor.RESET);
                                }
                            }
                        } else {
                            // Rechazar hormigas que NO son HSoldado
                            mostrarLoadingHormiga();
                            System.out.println(qmCMDColor.RED + "[\\|] " + tipoLimpio + " [RECHAZADO]" + qmCMDColor.RESET);
                        }
                    }
                }
            }
            
            // Cargar hormigas de BD
            lstHormigas = convertDTOsToEntities(hormigaDAO.readAll());
            System.out.println(qmCMDColor.GREEN + "[ETL-AntNest] Total hormigas cargadas (Caso de Estudio): " + lstHormigas.size() + qmCMDColor.RESET);
            
        } catch (IOException e) {
            throw new AppException(null, e, getClass(), "etlAntNest");
        }
        
        return lstHormigas;
    }

    @Override
    public List<qmAlimentoTipoDTO> etlAntFood() throws AppException {
        List<qmAlimentoTipoDTO> lstAlimentos = new ArrayList<>();
        qmAlimentoTipoDAO alimentoDAO = new qmAlimentoTipoDAO();
        java.util.Set<String> procesados = new java.util.HashSet<>();
        java.util.Set<String> existentes = new java.util.HashSet<>();
        
        try {
            System.out.println(qmCMDColor.BLUE + "\n╔════════════════════════════════════════╗" + qmCMDColor.RESET);
            System.out.println(qmCMDColor.BLUE + "║  CARGANDO ALIMENTOS (CASO ESTUDIO)    ║" + qmCMDColor.RESET);
            System.out.println(qmCMDColor.BLUE + "╚════════════════════════════════════════╝" + qmCMDColor.RESET);
            
            try {
                System.out.print(qmCMDColor.BLUE + "[o] ");
                Thread.sleep(200);
                System.out.print("\r[0] ");
                Thread.sleep(200);
                System.out.print("\r[o] ");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("\r[✓] Iniciando ETL de Alimentos..." + qmCMDColor.RESET);
            
            // Cargar nombres de alimentos ya existentes en BD
            for (qmAlimentoTipoDTO dto : alimentoDAO.readAll()) {
                if (dto.getNombre() != null) {
                    existentes.add(dto.getNombre().toLowerCase());
                }
            }
            
            List<String> allLines = Files.readAllLines(Paths.get(RUTA_ANT_FOOD));
            System.out.println(qmCMDColor.BLUE + "[ETL-AntFood] Buscando Carnívoro en archivo..." + qmCMDColor.RESET);
            
            // CASO DE ESTUDIO: Solo cargar Carnívoro para HSoldado
            String[] alimentosValidos = { "Carnívoro" };
            
            for (String line : allLines) {
                if (line.trim().isEmpty()) continue;
                
                // Limpiar línea de guiones y espacios extra
                String lineLimpia = line.replaceAll("-", ",").replaceAll("\\s+", " ");
                String[] items = lineLimpia.split(",");
                for (String item : items) {
                    String itemLimpio = item.trim();
                    
                    // Validar que sea alimento válido y no esté duplicado
                    boolean esValido = false;
                    for (String alimento : alimentosValidos) {
                        if (itemLimpio.equalsIgnoreCase(alimento)) {
                            esValido = true;
                            break;
                        }
                    }
                    
                    if (esValido && !procesados.contains(itemLimpio.toLowerCase())) {
                        procesados.add(itemLimpio.toLowerCase());
                        
                        // Solo insertar si NO existe en BD
                        if (!existentes.contains(itemLimpio.toLowerCase())) {
                            try {
                                mostrarLoadingAlimento(itemLimpio);
                                
                                qmAlimentoTipoDTO alimento = new qmAlimentoTipoDTO();
                                alimento.setNombre(itemLimpio);
                                alimento.setDescripcion("Alimento tipo: " + itemLimpio);
                                alimento.setEstado("A");
                                
                                if (alimentoDAO.create(alimento)) {
                                    mostrarLoadingAlimento();
                                    System.out.println(qmCMDColor.GREEN + "[0o0] " + itemLimpio + " ✓ VÁLIDO" + qmCMDColor.RESET);
                                    lstAlimentos.add(alimento);
                                } else {
                                    mostrarLoadingAlimento();
                                    System.out.println(qmCMDColor.RED + "[0o0] " + itemLimpio + " ✗ FALLÓ" + qmCMDColor.RESET);
                                }
                            } catch (Exception e) {
                                System.out.println(qmCMDColor.RED + "[0o0] " + itemLimpio + " ✗ ERROR: " + e.getMessage() + qmCMDColor.RESET);
                            }
                        }
                    } else if (!esValido && !procesados.contains(itemLimpio.toLowerCase())) {
                        mostrarLoadingAlimento();
                        // Rechazar alimentos que NO son válidos para el caso de estudio
                        procesados.add(itemLimpio.toLowerCase());
                        System.out.println(qmCMDColor.RED + "[0o0] " + itemLimpio + " [RECHAZADO]" + qmCMDColor.RESET);
                    }
                }
            }
            
            // Cargar alimentos de BD
            lstAlimentos = alimentoDAO.readAll();
            System.out.println(qmCMDColor.GREEN + "[ETL-AntFood] Total alimentos cargados (Caso de Estudio): " + lstAlimentos.size() + qmCMDColor.RESET);
            
        } catch (IOException e) {
            throw new AppException(null, e, getClass(), "etlAntFood");
        }
        
        return lstAlimentos;
    }

    @Override
    public qmAlimentoTipoDTO preparar(qmAlimentoTipoDTO alimento) {
        System.out.println(qmCMDColor.BLUE + "[Preparado]-( " + alimento.getNombre() + " )-" + qmCMDColor.RESET);
        return alimento;
    }

    @Override
    public qmHormiga alimentarAnt(qmHormiga hormiga, qmAlimentoTipoDTO alimento) throws AppException {
        // Alimentar la hormiga
        System.out.println("[Alimentando] " + hormiga.data.getNombre() + " con " + alimento.getNombre());
        
        // Hormiga come el alimento
        qmHormiga hormigaResultado = hormiga.comer(alimento.getNombre());
        
        // Eliminar alimento de BD después del consumo
        if (alimento.getIdAlimentoTipo() != null) {
            try {
                qmAlimentoTipoDAO alimentoDAO = new qmAlimentoTipoDAO();
                alimentoDAO.delete(alimento.getIdAlimentoTipo());
                System.out.println(qmCMDColor.GREEN + "[BD] Alimento '" + alimento.getNombre() + "' eliminado después del consumo" + qmCMDColor.RESET);
            } catch (Exception e) {
                System.out.println(qmCMDColor.RED + "[BD] Error al eliminar alimento: " + e.getMessage() + qmCMDColor.RESET);
            }
        }
        
        return hormigaResultado;
    }

    protected List<qmHormiga> convertDTOsToEntities(List<qmHormigaDTO> dtos) {
        List<qmHormiga> hormigas = new ArrayList<>();
        for (qmHormigaDTO dto : dtos) {
            qmHLarva larva = new qmHLarva();
            larva.data = dto;
            hormigas.add(larva);
        }
        return hormigas;
    }

}
