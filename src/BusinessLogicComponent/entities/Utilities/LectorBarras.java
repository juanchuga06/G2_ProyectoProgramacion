package BusinessLogicComponent.entities.Utilities;
import java.util.Scanner;

public class LectorBarras {
    private Scanner scanner = new Scanner(System.in); // Se mantiene abierto

    public String LeerCodigo() {
        System.out.println("Escaneando código de barras...");
        String barcode = scanner.nextLine();
        System.out.println("Código escaneado: " + barcode);

        return barcode != null ? barcode : "";
    }
}

