public class Tarea {
    private int id,tiempo_ejec,nivel_prioridad;
    private String nombre;
    private boolean is_critica;

    public Tarea(int tiempo_ejec, String nombre, int nivel_prioridad, boolean is_critica, int id) {
        this.tiempo_ejec = tiempo_ejec;
        this.nombre = nombre;
        this.nivel_prioridad = nivel_prioridad;
        this.is_critica = is_critica;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getTiempo_ejec() {
        return tiempo_ejec;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel_prioridad() {
        return nivel_prioridad;
    }

    public boolean isIs_critica() {
        return is_critica;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", tiempo_ejec=" + tiempo_ejec +
                ", nivel_prioridad=" + nivel_prioridad +
                ", nombre='" + nombre + '\'' +
                ", is_critica=" + is_critica +
                '}';
    }
}
