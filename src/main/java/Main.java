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
        String comando = "";
        int tiempoDEMax;
        boolean archivosValidos = false;
        boolean repetir = false;

        while (!archivosValidos || repetir) {
            repetir = false;
            System.out.println("Ingrese el nombre del archivo de procesadores (con extensión .csv):");
            //procesadoresArchivo = "src/main/resources/" + scanner.nextLine();

            procesadoresArchivo = "src/main/resources/" + "procesadores.csv";

            System.out.println("Ingrese el nombre del archivo de tareas (con extensión .csv):");
            //tareasArchivo = "src/main/resources/" + scanner.nextLine();
            tareasArchivo = "src/main/resources/" + "tareas.csv";

            if (Files.exists(Paths.get(procesadoresArchivo)) && Files.exists(Paths.get(tareasArchivo))) {
                archivosValidos = true;
                Servicio s=new Servicio(procesadoresArchivo,tareasArchivo);

                /*System.out.println("Ingrese el numero de ID de la tarea buscada:");
                comando = scanner.nextLine();
                if (comando.matches("\\d+")) {  // La expresión regular \\d+ significa "uno o más dígitos"
                    System.out.println("Número ingresado: " + comando);
                    Tarea pruebaServ1 = s.servicio1(comando);
                    System.out.println("Resultado: " + pruebaServ1);
                } else {
                    System.out.println("El valor ingresado no es un número. No se ejecutará la función.");
                }*/

                Tarea pruebaServ1 = s.servicio1("T4");

                /*System.out.println("Mostrar tareas criticas? Y/N");
                comando = scanner.nextLine();
                if(comando.equalsIgnoreCase("Y")){
                    List<Tarea> pruebaServ2T = s.servicio2(true);
                    pruebaServ2T.forEach(System.out::println);
                }*/

                List<Tarea> pruebaServ2T = s.servicio2(true);
                pruebaServ2T.forEach(System.out::println);

                /*System.out.println("Mostrar tareas NO criticas? Y/N");
                comando = scanner.nextLine();
                if(comando.equalsIgnoreCase("Y")){
                    List<Tarea> pruebaServ2T = s.servicio2(false);
                    pruebaServ2T.forEach(System.out::println);
                }
*/

                /*System.out.println("Ingrese el numero de prioridad inferior:");
                comando = scanner.nextLine();
                if(comando.matches("\\d+")){
                    int prioridadInferior = Integer.parseInt(comando);

                    System.out.println("Ingrese el numero de prioridad superior:");
                    comando = scanner.nextLine();
                    if(comando.matches("\\d+")){
                        List<Tarea> pruebaServ3 = s.servicio3(prioridadInferior, Integer.parseInt(comando));
                        if (pruebaServ3 != null)
                            pruebaServ3.forEach(System.out::println);
                        else
                            System.out.println("No se encontraron resultados para ese rango");
                    }else
                        System.out.println("Comando no fue un numero, no se buscara por prioridad");
                }else
                    System.out.println("Comando no fue un numero, no se buscara por prioridad");*/

                List<Tarea> pruebaServ3 = s.servicio3(5, 14);
                if (pruebaServ3 != null)
                    pruebaServ3.forEach(System.out::println);




                System.out.println("Desea asignar las tareas a los procesadores? Y/N");
                asignarTareas = scanner.nextLine();

                if(asignarTareas.equalsIgnoreCase("Y")){
                    System.out.println("Ingrese el tiempo de ejecucion max para los procesadores no refrigerados:");
                    tiempoDEMax = Integer.parseInt(scanner.nextLine());
                    s.asignarTareas(tiempoDEMax);
                }

                System.out.println("Repetir programa? Y/N");
                comando = scanner.nextLine();
                if(comando.equalsIgnoreCase("Y")){
                    repetir = true;
                }else
                    repetir = false;


            } else {
                System.out.println("Uno o ambos archivos no existen en la carpeta 'resources'. Intente de nuevo.");
            }
        }
        scanner.close();
        System.out.println("Fin del programa");
    }
}


