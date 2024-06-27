import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String procesadoresArchivo = "src/main/resources/" + "procesadoresNoRefrigerados.csv";
        String tareasArchivo = "src/main/resources/" + "tareas.csv";

        if (Files.exists(Paths.get(procesadoresArchivo)) && Files.exists(Paths.get(tareasArchivo))) {
            Servicio s = new Servicio(procesadoresArchivo, tareasArchivo);


            Tarea pruebaServ1 = s.servicio1("T103");
            System.out.println("Resultado: " + pruebaServ1 + "\n");

            List<Tarea> pruebaServ2T = s.servicio2(true);
            pruebaServ2T.forEach(System.out::println);

            List<Tarea> pruebaServ3 = s.servicio3(5, 14);
            if (pruebaServ3 != null)
                pruebaServ3.forEach(System.out::println);

            s.asignarTareas(30);
        }
    }
}


