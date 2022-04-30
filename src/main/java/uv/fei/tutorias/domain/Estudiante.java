package uv.fei.tutorias.domain;

// author @liu
public class Estudiante {
    private int idEstudiante;
    private String matricula;
    private Persona persona;
    private int idProgramaEducativo;
    private int idTutorAcademico;

    // Constructors of uv.fei.tutorias.domain.Estudiante
    public Estudiante() {
        this.idEstudiante = 0;
        this.matricula = "";
        this.persona = new Persona();
        this.idProgramaEducativo = 0;
        this.idTutorAcademico = 0;
    }

    public Estudiante(String matricula, Persona persona, int idTutorAcademico, int idProgramaEducativo) {
        this.idEstudiante = 0;
        this.matricula = matricula;
        this.persona = persona;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idTutorAcademico = idTutorAcademico;
    }
    
    public Estudiante(int idEstudiante, String matricula, Persona persona, int idTutorAcademico, int idProgramaEducativo) {
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.persona = persona;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idTutorAcademico = idTutorAcademico;
    }
    
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

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }
    
    public int getIdTutorAcademico() {
        return idTutorAcademico;
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
                    && this.persona.equals(tmpEstudiante.getPersona())
                    && this.idProgramaEducativo == tmpEstudiante.getIdProgramaEducativo()
                    && this.idTutorAcademico == tmpEstudiante.getIdTutorAcademico()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return persona.getNombreCompleto() + " (" + this.matricula + ")";
    }
}