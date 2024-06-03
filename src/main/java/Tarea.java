public class Tarea {
    private int id,tiempo_ejec,nivel_prioridad;
    private String nombre;
    private boolean is_critica;

    public Tarea(int tiempo_ejec, String nombre, int nivel_prioridad, boolean is_critica, int id) {
        setTiempo_ejec(tiempo_ejec);
        setNivel_prioridad(nivel_prioridad);
        this.nombre = nombre;
        this.is_critica = is_critica;
        this.id = id;
    }

    private void setNivel_prioridad(int nivel_prioridad) {//Controlar nivel de prioridad
        if (nivel_prioridad<0)
            nivel_prioridad = 0;

        if (nivel_prioridad>100)
            nivel_prioridad = 100;

        this.nivel_prioridad = nivel_prioridad;
    }

    public void setTiempo_ejec(int tiempo_ejec) { //Tiempo de ejecucion no puede ser negativo
        if (tiempo_ejec < 0)
            tiempo_ejec = 1;
        this.tiempo_ejec = tiempo_ejec;
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
