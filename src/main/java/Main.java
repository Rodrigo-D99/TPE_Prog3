import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String procesadoresArchivo;
        String tareasArchivo;
        String asignarTareas;
        int tiempoDEMax;
        boolean archivosValidos = false;

        while (!archivosValidos) {
            System.out.println("Ingrese el nombre del archivo de procesadores (con extensión .csv):");
            //procesadoresArchivo = "src/main/resources/" + scanner.nextLine();

            procesadoresArchivo = "src/main/resources/" + "procesadore.csv";

            System.out.println("Ingrese el nombre del archivo de tareas (con extensión .csv):");
            //tareasArchivo = "src/main/resources/" + scanner.nextLine();
            tareasArchivo = "src/main/resources/" + "tarea.csv";

            if (Files.exists(Paths.get(procesadoresArchivo)) && Files.exists(Paths.get(tareasArchivo))) {
                archivosValidos = true;
                Servicio s=new Servicio(procesadoresArchivo,tareasArchivo);

                Tarea pruebaServ1 = s.servicio1("T1");

                List<Tarea> pruebaServ2T = s.servicio2(true);
                List<Tarea> pruebaServ2F = s.servicio2(false);
                List<Tarea> pruebaServ3 = s.servicio3(2,15);

                System.out.println("Desea asignar las tareas a los procesadores? Y/N");
                //asignarTareas = scanner.nextLine();
                asignarTareas = "y";
                System.out.println("tarea Por id "+pruebaServ1+"\n");
                System.out.println("tareas criticas "+pruebaServ2T+"\n");
                System.out.println("tareas criticas "+pruebaServ2F+"\n");
                System.out.println("tareas por prioridad "+pruebaServ3+"\n");
                if(asignarTareas.equalsIgnoreCase("Y")){
                    System.out.println("Ingrese el tiempo de ejecucion max para los procesadores no refrigerados:");
                    //tiempoDEMax = Integer.parseInt(scanner.nextLine());
                    s.asignarTareas(99);
                }

                String ss=" ";

            } else {
                System.out.println("Uno o ambos archivos no existen en la carpeta 'resources'. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}


