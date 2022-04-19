package uv.fei.tutorias.domain;

// author @liu
public class Estudiante {
    private int idEstudiante;
    private String matricula;
    private Persona persona;
    private ProgramaEducativo programaEducativo;
    private TutorAcademico tutorAcademico;

    // Constructors of uv.fei.tutorias.domain.Estudiante
    public Estudiante() {
        this.idEstudiante = 0;
        this.matricula = "";
        this.persona = null;
        this.programaEducativo = null;
        this.tutorAcademico = null;
    }
    
    public Estudiante(int idEstudiante, String matricula, Persona persona, TutorAcademico tutorAcademico, ProgramaEducativo programaEducativo) {
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.persona = persona;
        this.programaEducativo = programaEducativo;
        this.tutorAcademico = tutorAcademico;
    }

    public Estudiante(String matricula, Persona persona, TutorAcademico tutorAcademico, ProgramaEducativo programaEducativo) {
        this.idEstudiante = 0;
        this.matricula = matricula;
        this.persona = persona;
        this.programaEducativo = programaEducativo;
        this.tutorAcademico = tutorAcademico;
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

    public ProgramaEducativo getProgramaEducativo() {
        return programaEducativo;
    }
    
    public TutorAcademico getTutorAcademico() {
        return tutorAcademico;
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

    public void setProgramaEducativo(ProgramaEducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }
    
    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.tutorAcademico = tutorAcademico;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Estudiante) {
            Estudiante tmpEstudiante = (Estudiante)obj;
            if(this.idEstudiante == tmpEstudiante.getIdEstudiante()
                    && this.matricula.equals(tmpEstudiante.getMatricula())
                    && this.persona.equals(tmpEstudiante.getPersona())
                    && this.programaEducativo.equals(tmpEstudiante.getProgramaEducativo())
                    && this.tutorAcademico.equals(tmpEstudiante.getTutorAcademico())) {
                return true;
            }
        }
        return false;
    }
}