package uv.fei.tutorias.domain;

// author @liu
public class Estudiante extends Persona {
    private int id;
    private String matricula;
    private boolean enRiesgo;
    private int idProgramaEducativo;
    private int idTutorAcademico;

    // Constructors of uv.fei.tutorias.domain.Estudiante
    public Estudiante() {
        super("","","");
        this.id = 0;
        this.matricula = "";
        this.idProgramaEducativo = 0;
        this.idTutorAcademico = 0;
    }

    public Estudiante(String matricula, Persona estudiante, int idTutorAcademico, int idProgramaEducativo) {
        super(estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno());
        this.id = 0;
        this.matricula = matricula;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idTutorAcademico = idTutorAcademico;
    }

    public Estudiante(int id, String matricula, boolean enRiesgo, Persona estudiante, int idTutorAcademico, int idProgramaEducativo) {
        super(estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno());
        this.id = id;
        this.matricula = matricula;
        this.enRiesgo = enRiesgo;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idTutorAcademico = idTutorAcademico;
    }

    // Getters of uv.fei.tutorias.domain.Estudiante
    public int getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }
    
    public boolean getEnRiesgo() {
        return enRiesgo;
    }

    // Setters of uv.fei.tutorias.domain.Estudiante
    public void setId(int id) {
        this.id = id;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
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
            if(this.id == tmpEstudiante.getId()
                    && this.matricula.equals(tmpEstudiante.getMatricula())
                    && this.idPersona == tmpEstudiante.getIdPersona()
                    && this.nombre.equals(tmpEstudiante.getNombre())
                    && this.enRiesgo == tmpEstudiante.getEnRiesgo()
                    && this.apellidoPaterno.equals(tmpEstudiante.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpEstudiante.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpEstudiante.getIdProgramaEducativo()
                    && this.idTutorAcademico == tmpEstudiante.getIdTutorAcademico()) {
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