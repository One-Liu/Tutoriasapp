package uv.fei.tutorias.domain;

// author @liu
public class Estudiante extends Persona {
    private int idEstudiante;
    private String matricula;
    private boolean enRiesgo;
    private int idTutorAcademico;
    
    public Estudiante() {
        super();
        this.idEstudiante = 0;
        this.matricula = "";
        this.enRiesgo = false;
        this.idTutorAcademico = 0;
    }

    public Estudiante(String matricula, int idTutorAcademico, Persona estudiante) {
        super(estudiante.getIdPersona(), estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno(), estudiante.getIdProgramaEducativo());
        this.idEstudiante = 0;
        this.matricula = matricula;
        this.enRiesgo = false;
        this.idTutorAcademico = idTutorAcademico;
    }

    public Estudiante(int idEstudiante, String matricula, boolean enRiesgo, int idTutorAcademico, Persona estudiante) {
        super(estudiante.getIdPersona(), estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno(), estudiante.getIdProgramaEducativo());
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.enRiesgo = enRiesgo;
        this.idTutorAcademico = idTutorAcademico;
    }

    // Getters of uv.fei.tutorias.domain.Estudiante
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }
    
    public boolean getEnRiesgo() {
        return enRiesgo;
    }

    // Setters of uv.fei.tutorias.domain.Estudiante
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setIdTutorAcademico(int idTutorAcademico) {
        this.idTutorAcademico = idTutorAcademico;
    }

    public void setEnRiesgo(boolean enRiesgo) {
        this.enRiesgo = enRiesgo;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Estudiante) {
            Estudiante tmpEstudiante = (Estudiante)obj;
            if(this.idEstudiante == tmpEstudiante.getIdEstudiante()
                    && this.matricula.equals(tmpEstudiante.getMatricula())
                    && this.enRiesgo == tmpEstudiante.getEnRiesgo()
                    && this.idTutorAcademico == tmpEstudiante.getIdTutorAcademico()
                    && this.idPersona == tmpEstudiante.getIdPersona()
                    && this.nombre.equals(tmpEstudiante.getNombre())
                    && this.apellidoPaterno.equals(tmpEstudiante.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpEstudiante.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpEstudiante.getIdProgramaEducativo()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula='" + matricula + '\'' +
                ", enRiesgo=" + enRiesgo +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }
}