package uv.fei.tutorias.domain;

public class Profesor extends Persona{
    private int idProfesor;
   
    public Profesor() {
        super();
        this.idProfesor = 0;
    }
    
    public Profesor(Persona profesor) {
        super(profesor.getIdPersona(), profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getIdProgramaEducativo());
        this.idProfesor = 0;
    } 
   
    public Profesor(int idProfesor, Persona profesor) {
        super(profesor.getIdPersona(), profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getIdProgramaEducativo());
        this.idProfesor = idProfesor;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int id) {
        this.idProfesor = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Profesor) {
            Profesor tmpProfesor = (Profesor) obj;
            if(this.idProfesor == tmpProfesor.getIdProfesor()
                    && this.idPersona == tmpProfesor.getIdPersona()
                    && this.nombre.equals(tmpProfesor.getNombre())
                    && this.apellidoPaterno.equals(tmpProfesor.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpProfesor.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpProfesor.getIdProgramaEducativo()) {
                return true;
            } 
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getIdProfesor();
        result = 31 * result + getIdPersona();
        return result;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + idProfesor +
                ", nombre=" + getNombre() +
                ", apellidoPaterno=" + getApellidoPaterno() +
                ", apellidoMaterno=" + getApellidoMaterno() +
                '}';
    }
}