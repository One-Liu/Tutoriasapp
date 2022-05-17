package uv.fei.tutorias.domain;

// author @liu
public class Estudiante extends Persona {
    private int id;
    private String matricula;
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

    public Estudiante(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, int idTutorAcademico, int idProgramaEducativo) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.matricula = matricula;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idTutorAcademico = idTutorAcademico;
    }

    public Estudiante(int id, String matricula, String nombre, String apellidoPaterno, String apellidoMaterno, int idTutorAcademico, int idProgramaEducativo) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
        this.matricula = matricula;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Estudiante) {
            Estudiante tmpEstudiante = (Estudiante)obj;
            if(this.idEstudiante == tmpEstudiante.getIdEstudiante()
                    && this.matricula.equals(tmpEstudiante.getMatricula())
                    && getId() == tmpEstudiante.getId()
                    && this.idProgramaEducativo == tmpEstudiante.getIdProgramaEducativo()
                    && this.idTutorAcademico == tmpEstudiante.getIdTutorAcademico()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellidoPaterno() + " " + getApellidoMaterno() + " (" + this.matricula + ")";
    }
}