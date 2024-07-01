import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String procesadoresArchivo = "src/main/resources/" + "Procesadore.csv";
        String tareasArchivo = "src/main/resources/" + "Tarea.csv";
        String id = "T1";
        int tiempoDEMax = 70;
        boolean criticas = true;
        int prioridadInferior = 5;
        int prioridadSuperior = 14;

        if (Files.exists(Paths.get(procesadoresArchivo)) && Files.exists(Paths.get(tareasArchivo))) {
            Servicio s = new Servicio(procesadoresArchivo, tareasArchivo);


            Tarea pruebaServ1 = s.servicio1(id);
            System.out.println("Resultado: " + pruebaServ1 + "\n");

            List<Tarea> pruebaServ2T = s.servicio2(criticas);
            System.out.println("\nTareas: criticas " + criticas);
            pruebaServ2T.forEach(System.out::println);

            List<Tarea> pruebaServ3 = s.servicio3(prioridadInferior, prioridadSuperior);
            System.out.println("\nTareas: prioridad " + prioridadInferior + " - " + prioridadSuperior);
            if (pruebaServ3 != null)
                pruebaServ3.forEach(System.out::println);

            s.asignarTareas(tiempoDEMax);
        }
    }
}


