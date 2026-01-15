package qmApp.qmConsoleApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import qmBusinessLogic.qmEntities.qmCoordenadaUK;

public class qmSistemaRuso {
    //Refactorizado
    public boolean qmshowKGD(String qmUsuario, String qmClave) {
        if (qmUsuario.equals("matias") && qmClave.equals("123")) {
            System.out.println("[+] Acceso concedido al sistema KGD Ruso.");
            return true;
        } else {
            System.out.println("[-] Acceso denegado al sistema KGD Ruso.");
            return false;
        }
        
    }
    public List<qmCoordenadaUK> readCoord(String fileNamePath) throws IOException {
        List<qmCoordenadaUK> lstCoord = new java.util.ArrayList<>();
        List<String> allLines = Files.readAllLines(Paths.get(fileNamePath));
        for (String line : allLines) {
            System.out.println(line);
            String[] coord = line.split(",");
            qmCoordenadaUK coordenada = new qmCoordenadaUK( coord[0], coord[6], false);
            lstCoord.add(coordenada);
            //System.out.println( coordenada.toString() );
        }
        return lstCoord;
    }
}
