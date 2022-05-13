package uv.fei.tutorias.domain;


public class Profesor extends Persona{
   private int id;
   private int idPersona;
    //constructor con el id profesor y los datos de persona para el metodo de searchname
    public Profesor(String nombre, String apellidoPaterno, String apellidoMaterno, int id) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
    }

    public Profesor(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
    }

    public Profesor(int idPersona) {
        this.idPersona = idPersona;
    }

    public Profesor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor)) return false;

        Profesor profesor = (Profesor) o;

        if (getId() != profesor.getId()) return false;
        return getIdPersona() == profesor.getIdPersona();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getIdPersona();
        return result;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre=" + getNombre() +
                ", apellidoPaterno=" + getApellidoPaterno() +
                ", apellidoMaterno=" + getApellidoMaterno() +
                '}';
    }
}