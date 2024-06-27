public class Tarea implements Comparable<Tarea> {
    private int tiempo_ejec,nivel_prioridad;
    private String id,nombre;
    private boolean is_critica;

    public Tarea(String id,String nombre,int tiempo_ejec,boolean is_critica, int nivel_prioridad ) {
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

    public String getId() {
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

    public boolean is_critica() {
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
                "}\n";
    }

    @Override
    public int compareTo(Tarea tarea) {
        return Integer.compare(tarea.getTiempo_ejec(), this.getTiempo_ejec());
    }

    //int id,String nombre,int tiempo_ejec,boolean is_critica, int nivel_prioridad
    public Tarea getCopy() {
        return new Tarea(id, nombre, tiempo_ejec, is_critica, nivel_prioridad);
    }
}
