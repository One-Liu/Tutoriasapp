package uv.fei.tutorias.domain;

// author @liu
public class Estudiante {
    private int idEstudiante;
    private String matricula;
    private Persona persona;
    private TutorAcademico tutorAcademico;
    private ProgramaEducativo programaEducativo;

    // Getters of uv.fei.tutorias.domain.Estudiante
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public Persona getPersona() {
        return persona;
    }

    public TutorAcademico getTutorAcademico() {
        return tutorAcademico;
    }

    public ProgramaEducativo getProgramaEducativo() {
        return programaEducativo;
    }

    // Setters of uv.fei.tutorias.domain.Estudiante
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.tutorAcademico = tutorAcademico;
    }

    public void setProgramaEducativo(ProgramaEducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }
}