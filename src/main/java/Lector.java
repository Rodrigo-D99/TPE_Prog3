import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Lector {

    public static void leerProcesadores(String archivo, List<Procesador> lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Leer y descartar la primera línea (encabezado)
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String codigo = datos[1];
                boolean refrigerado = Boolean.parseBoolean(datos[2]);
                int anio = Integer.parseInt(datos[3]);
                Procesador procesador = new Procesador(id,anio,refrigerado,codigo);
                lista.add(procesador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void leerTareas(String archivo, Servicio s) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Leer y descartar la primera línea (encabezado)
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String id = datos[0];
                String nombre = datos[1];
                int tiempo = Integer.parseInt(datos[2]);
                boolean critica = Boolean.parseBoolean(datos[3]);
                int prioridad = Integer.parseInt(datos[4]);
                Tarea tarea = new Tarea(id, nombre, tiempo, critica, prioridad);
                s.addTarea(tarea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
