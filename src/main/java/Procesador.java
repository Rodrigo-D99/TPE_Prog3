public class Procesador {
    private int id,anio_funcionamiento;
    private String codigo;
    private boolean is_refrigerado;

    public Procesador(int id, int anio_funcionamiento, boolean is_refrigerado, String codigo) {
        this.id = id;
        this.anio_funcionamiento = anio_funcionamiento;
        this.is_refrigerado = is_refrigerado;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public int getAnio_funcionamiento() {
        return anio_funcionamiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean is_refrigerado() {
        return is_refrigerado;
    }

    @Override
    public String toString() {
        return "Procesador{" +
                "id=" + id +
                ", anio_funcionamiento=" + anio_funcionamiento +
                ", codigo='" + codigo + '\'' +
                ", is_refrigerado=" + is_refrigerado +
                '}';
    }
}
