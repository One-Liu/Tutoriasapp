package uv.fei.tutorias.domain;

// author @liu

public class Estudiante {
    private int idEstudiante;
    private String matricula;
    private int idPersona;
    private int idTutorAcademico;
    private int idProgramaEducativo;

    // Getters of uv.fei.tutorias.domain.Estudiante
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    // Setters of uv.fei.tutorias.domain.Estudiante
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setIdTutorAcademico(int idTutorAcademico) {
        this.idTutorAcademico = idTutorAcademico;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }
}