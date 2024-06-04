import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerArchivo {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String procesadoresArchivo;
        String tareasArchivo;
        boolean archivosValidos = false;

        while (!archivosValidos) {
            System.out.println("Ingrese el nombre del archivo de procesadores (con extensión .csv):");
            procesadoresArchivo = "src/main/resources/" + scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de tareas (con extensión .csv):");
            tareasArchivo = "src/main/resources/" + scanner.nextLine();

            if (Files.exists(Paths.get(procesadoresArchivo)) && Files.exists(Paths.get(tareasArchivo))) {
                archivosValidos = true;
                Servicio s=new Servicio(procesadoresArchivo,tareasArchivo);
                String ss=" ";

            } else {
                System.out.println("Uno o ambos archivos no existen en la carpeta 'resources'. Intente de nuevo.");
            }
        }
        scanner.close();

    }


}


