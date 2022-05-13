package uv.fei.tutorias.domain;

// author @liu

public class





Estudiante extends Persona {
    private int id;
    private String matricula;
    private int idPersona;
    private int idTutorAcademico;
    private int idProgramaEducativo;

    public Estudiante(String nombre, String apellidoPaterno, String apellidoMaterno, String matricula) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.matricula = matricula;
    }

    public Estudiante(String matricula) {
        this.matricula = matricula;
    }


    public Estudiante() {
    }

    // Getters of uv.fei.tutorias.domain.Estudiante
    public int getId() {
        return id;
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
    public void setId(int idEstudiante) {
        this.id = idEstudiante;
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